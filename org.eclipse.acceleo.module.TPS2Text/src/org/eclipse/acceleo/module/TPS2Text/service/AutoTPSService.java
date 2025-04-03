package org.eclipse.acceleo.module.TPS2Text.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This service class implements the AutoTPS analysis tool functionality.
 * It analyzes a TPS model and determines task assignments based on rules.
 */
public class AutoTPSService {
    
    // Constants for shift time ranges
    private static final int DAY_SHIFT_START = 6; // 6 AM
    private static final int DAY_SHIFT_END = 14; // 2 PM
    private static final int EVENING_SHIFT_START = 14; // 2 PM
    private static final int EVENING_SHIFT_END = 22; // 10 PM
    // Night shift is from 10 PM to 6 AM
    
    /**
     * Generate task assignments based on the TPS model.
     * 
     * @param tpsModel The TPS model element from which to generate assignments
     * @return HTML representation of task assignments
     */
    public String generateTaskAssignments(EObject tpsModel) {
        StringBuilder result = new StringBuilder();
        
        try {
            // Check for existing assignments first
            Map<String, List<TaskInfo>> existingAssignments = extractExistingAssignments(tpsModel);
            
            // If we have existing assignments, use them
            if (!existingAssignments.isEmpty()) {
                result.append(generateAssignmentHTML(existingAssignments, "Existing Task Assignments"));
            } else {
                // Otherwise, run the assignment algorithm
                // Extract model components
                List<WorkerInfo> workers = extractWorkers(tpsModel);
                List<TaskInfo> tasks = extractTasks(tpsModel);
                List<RuleInfo> rules = extractRules(tpsModel);
                
                if (workers.isEmpty()) {
                    result.append("<div class='assignment-results'>");
                    result.append("<h3>Task Assignment Results</h3>");
                    result.append("<p style='color: #cf6679;'>No available workers found for assignment.</p>");
                    result.append("</div>");
                } else if (tasks.isEmpty()) {
                    result.append("<div class='assignment-results'>");
                    result.append("<h3>Task Assignment Results</h3>");
                    result.append("<p style='color: #cf6679;'>No unassigned tasks found for assignment.</p>");
                    result.append("</div>");
                } else {
                    Map<String, List<TaskInfo>> calculatedAssignments = calculateTaskAssignments(workers, tasks, rules);
                    result.append(generateAssignmentHTML(calculatedAssignments, "Calculated Task Assignments"));
                }
            }
        } catch (Exception e) {
            // Error handling with informative message
            result.append("<div class='assignment-results'>");
            result.append("<h3>Task Assignment Results</h3>");
            result.append("<p style='color: #cf6679;'>Error processing model: " + e.getMessage() + "</p>");
            result.append("<p style='color: #e0e0e0;'>Please ensure your model contains properly defined workers, tasks, and rules.</p>");
            result.append("</div>");
        }
        
        return result.toString();
    }
    
    /**
     * Extract worker information from the TPS model.
     */
    private List<WorkerInfo> extractWorkers(EObject tpsModel) {
        List<WorkerInfo> workers = new ArrayList<>();
        
        EStructuralFeature workersFeature = tpsModel.eClass().getEStructuralFeature("workers");
        if (workersFeature != null) {
            @SuppressWarnings("unchecked")
            EList<EObject> workerObjects = (EList<EObject>) tpsModel.eGet(workersFeature);
            
            for (EObject workerObj : workerObjects) {
                String name = (String) workerObj.eGet(workerObj.eClass().getEStructuralFeature("name"));
                String employeeNumber = (String) workerObj.eGet(workerObj.eClass().getEStructuralFeature("employeeNumber"));
                Object seniorityLevel = workerObj.eGet(workerObj.eClass().getEStructuralFeature("seniorityLevel"));
                boolean active = (Boolean) workerObj.eGet(workerObj.eClass().getEStructuralFeature("active"));
                
                // Skip workers that already have assignments (they'll be handled separately)
                EStructuralFeature tasksFeature = workerObj.eClass().getEStructuralFeature("tasks");
                if (tasksFeature != null) {
                    @SuppressWarnings("unchecked")
                    EList<EObject> tasks = (EList<EObject>) workerObj.eGet(tasksFeature);
                    if (tasks != null && !tasks.isEmpty()) {
                        continue;
                    }
                }
                
                if (active) {
                    workers.add(new WorkerInfo(name, employeeNumber, seniorityLevel.toString(), active));
                }
            }
        }
        
        return workers;
    }
    
    /**
     * Extract task information from the TPS model.
     */
    private List<TaskInfo> extractTasks(EObject tpsModel) {
        List<TaskInfo> tasks = new ArrayList<>();
        
        EStructuralFeature tasksFeature = tpsModel.eClass().getEStructuralFeature("tasks");
        if (tasksFeature != null) {
            @SuppressWarnings("unchecked")
            EList<EObject> taskObjects = (EList<EObject>) tpsModel.eGet(tasksFeature);
            
            for (EObject taskObj : taskObjects) {
                String id = (String) taskObj.eGet(taskObj.eClass().getEStructuralFeature("ID"));
                Object difficulty = taskObj.eGet(taskObj.eClass().getEStructuralFeature("difficultyLevel"));
                int duration = (Integer) taskObj.eGet(taskObj.eClass().getEStructuralFeature("duration"));
                Date startTime = (Date) taskObj.eGet(taskObj.eClass().getEStructuralFeature("startTime"));
                
                // Skip tasks that are already assigned
                EStructuralFeature workerFeature = taskObj.eClass().getEStructuralFeature("worker");
                if (workerFeature != null) {
                    EObject worker = (EObject) taskObj.eGet(workerFeature);
                    if (worker != null) {
                        continue;
                    }
                }
                
                tasks.add(new TaskInfo(id, difficulty.toString(), duration, startTime));
            }
        }
        
        return tasks;
    }
    
    /**
     * Extract rule information from the TPS model.
     */
    private List<RuleInfo> extractRules(EObject tpsModel) {
        List<RuleInfo> rules = new ArrayList<>();
        
        EStructuralFeature rulesFeature = tpsModel.eClass().getEStructuralFeature("taskRules");
        if (rulesFeature != null) {
            @SuppressWarnings("unchecked")
            EList<EObject> ruleObjects = (EList<EObject>) tpsModel.eGet(rulesFeature);
            
            for (EObject ruleObj : ruleObjects) {
                Object result = ruleObj.eGet(ruleObj.eClass().getEStructuralFeature("result"));
                EObject expression = (EObject) ruleObj.eGet(ruleObj.eClass().getEStructuralFeature("expression"));
                
                rules.add(new RuleInfo(result.toString(), expression));
            }
        }
        
        return rules;
    }
    
    /**
     * Extract existing worker-task assignments from the TPS model.
     */
    private Map<String, List<TaskInfo>> extractExistingAssignments(EObject tpsModel) {
        Map<String, List<TaskInfo>> assignments = new HashMap<>();
        
        EStructuralFeature workersFeature = tpsModel.eClass().getEStructuralFeature("workers");
        if (workersFeature != null) {
            @SuppressWarnings("unchecked")
            EList<EObject> workers = (EList<EObject>) tpsModel.eGet(workersFeature);
            
            for (EObject worker : workers) {
                String name = (String) worker.eGet(worker.eClass().getEStructuralFeature("name"));
                Object seniorityLevel = worker.eGet(worker.eClass().getEStructuralFeature("seniorityLevel"));
                
                String workerKey = name + " (" + seniorityLevel + ")";
                
                EStructuralFeature tasksFeature = worker.eClass().getEStructuralFeature("tasks");
                if (tasksFeature != null) {
                    @SuppressWarnings("unchecked")
                    EList<EObject> tasks = (EList<EObject>) worker.eGet(tasksFeature);
                    
                    if (tasks != null && !tasks.isEmpty()) {
                        List<TaskInfo> workerTasks = new ArrayList<>();
                        
                        for (EObject task : tasks) {
                            String id = (String) task.eGet(task.eClass().getEStructuralFeature("ID"));
                            Object difficulty = task.eGet(task.eClass().getEStructuralFeature("difficultyLevel"));
                            int duration = (Integer) task.eGet(task.eClass().getEStructuralFeature("duration"));
                            Date startTime = (Date) task.eGet(task.eClass().getEStructuralFeature("startTime"));
                            
                            workerTasks.add(new TaskInfo(id, difficulty.toString(), duration, startTime));
                        }
                        
                        assignments.put(workerKey, workerTasks);
                    }
                }
            }
        }
        
        return assignments;
    }
    
    /**
     * Algorithm to calculate task assignments based on workers, tasks, and rules.
     */
    private Map<String, List<TaskInfo>> calculateTaskAssignments(
            List<WorkerInfo> workers, List<TaskInfo> tasks, List<RuleInfo> rules) {
        
        Map<String, List<TaskInfo>> assignments = new HashMap<>();
        
        // Group workers by seniority
        Map<String, List<WorkerInfo>> workersBySeniority = new HashMap<>();
        for (WorkerInfo worker : workers) {
            if (!workersBySeniority.containsKey(worker.seniorityLevel)) {
                workersBySeniority.put(worker.seniorityLevel, new ArrayList<>());
            }
            workersBySeniority.get(worker.seniorityLevel).add(worker);
            
            // Initialize assignment lists for each worker
            String workerKey = worker.name + " (" + worker.seniorityLevel + ")";
            assignments.put(workerKey, new ArrayList<>());
        }
        
        // First pass: Assign tasks based on explicit rules
        List<TaskInfo> unassignedTasks = new ArrayList<>(tasks);
        List<TaskInfo> tasksToRemove = new ArrayList<>();
        
        for (TaskInfo task : unassignedTasks) {
            String requiredSeniority = determineRequiredSeniority(task, rules);
            
            if (requiredSeniority != null && workersBySeniority.containsKey(requiredSeniority)) {
                List<WorkerInfo> eligibleWorkers = workersBySeniority.get(requiredSeniority);
                if (!eligibleWorkers.isEmpty()) {
                    // Simple load balancing - find worker with fewest tasks
                    WorkerInfo selectedWorker = findLeastLoadedWorker(eligibleWorkers, assignments);
                    
                    // Assign task
                    String workerKey = selectedWorker.name + " (" + selectedWorker.seniorityLevel + ")";
                    assignments.get(workerKey).add(task);
                    tasksToRemove.add(task);
                }
            }
        }
        
        // Remove assigned tasks
        unassignedTasks.removeAll(tasksToRemove);
        
        // Second pass: Assign remaining tasks to any available workers (fallback)
        if (!unassignedTasks.isEmpty() && !workers.isEmpty()) {
            for (TaskInfo task : unassignedTasks) {
                // Find any worker
                WorkerInfo selectedWorker = findLeastLoadedWorker(workers, assignments);
                
                // Assign task
                String workerKey = selectedWorker.name + " (" + selectedWorker.seniorityLevel + ")";
                assignments.get(workerKey).add(task);
            }
        }
        
        return assignments;
    }
    
    /**
     * Find the worker with the fewest assigned tasks.
     */
    private WorkerInfo findLeastLoadedWorker(List<WorkerInfo> workers, Map<String, List<TaskInfo>> assignments) {
        WorkerInfo leastLoadedWorker = workers.get(0);
        int minTasks = Integer.MAX_VALUE;
        
        for (WorkerInfo worker : workers) {
            String workerKey = worker.name + " (" + worker.seniorityLevel + ")";
            int taskCount = assignments.get(workerKey).size();
            
            if (taskCount < minTasks) {
                minTasks = taskCount;
                leastLoadedWorker = worker;
            }
        }
        
        return leastLoadedWorker;
    }
    
    /**
     * Determine the seniority level required for a task based on rules.
     */
    private String determineRequiredSeniority(TaskInfo task, List<RuleInfo> rules) {
        for (RuleInfo rule : rules) {
            if (evaluateRule(task, rule)) {
                return rule.result;
            }
        }
        
        // Default to TRAINEE if no rule matches
        return "TRAINEE";
    }
    
    /**
     * Evaluate a rule against a task.
     */
    private boolean evaluateRule(TaskInfo task, RuleInfo rule) {
        return evaluateExpression(task, rule.expression);
    }
    
    /**
     * Recursively evaluate an expression in a rule.
     */
    private boolean evaluateExpression(TaskInfo task, EObject expression) {
        String expressionType = expression.eClass().getName();
        
        switch (expressionType) {
            case "DifficultyExpression":
                return evaluateDifficultyExpression(task, expression);
                
            case "DurationExpression":
                return evaluateDurationExpression(task, expression);
                
            case "ShiftExpression":
                return evaluateShiftExpression(task, expression);
                
            case "CompoundExpression":
                return evaluateCompoundExpression(task, expression);
                
            default:
                return false;
        }
    }
    
    /**
     * Evaluate a difficulty expression.
     */
    private boolean evaluateDifficultyExpression(TaskInfo task, EObject expression) {
        Object difficulty = expression.eGet(expression.eClass().getEStructuralFeature("difficulty"));
        return task.difficulty.equals(difficulty.toString());
    }
    
    /**
     * Evaluate a duration expression.
     */
    private boolean evaluateDurationExpression(TaskInfo task, EObject expression) {
        Object operator = expression.eGet(expression.eClass().getEStructuralFeature("operator"));
        int minutes = (Integer) expression.eGet(expression.eClass().getEStructuralFeature("minutes"));
        
        switch (operator.toString()) {
            case "LESSTHAN":
                return task.duration < minutes;
            case "LESSTHANOREQUAL":
                return task.duration <= minutes;
            case "EQUAL":
                return task.duration == minutes;
            case "GREATERTHANOREQUAL":
                return task.duration >= minutes;
            case "GREATERTHAN":
                return task.duration > minutes;
            default:
                return false;
        }
    }
    
    /**
     * Evaluate a shift expression.
     */
    private boolean evaluateShiftExpression(TaskInfo task, EObject expression) {
        Object shift = expression.eGet(expression.eClass().getEStructuralFeature("shift"));
        String shiftValue = shift.toString();
        
        if (task.startTime == null) {
            return false;
        }
        
        // Get the hour from the task start time (0-23)
        @SuppressWarnings("deprecation")
        int hour = task.startTime.getHours();
        
        switch (shiftValue) {
            case "DAYSHIFT":
                return hour >= DAY_SHIFT_START && hour < DAY_SHIFT_END;
            case "EVENINGSHIFT":
                return hour >= EVENING_SHIFT_START && hour < EVENING_SHIFT_END;
            case "NIGHTSHIFT":
                return hour >= EVENING_SHIFT_END || hour < DAY_SHIFT_START;
            default:
                return false;
        }
    }
    
    /**
     * Evaluate a compound expression.
     */
    private boolean evaluateCompoundExpression(TaskInfo task, EObject expression) {
        EObject lhs = (EObject) expression.eGet(expression.eClass().getEStructuralFeature("LHS"));
        EObject rhs = (EObject) expression.eGet(expression.eClass().getEStructuralFeature("RHS"));
        Object operator = expression.eGet(expression.eClass().getEStructuralFeature("operator"));
        
        boolean lhsResult = evaluateExpression(task, lhs);
        boolean rhsResult = evaluateExpression(task, rhs);
        
        switch (operator.toString()) {
            case "AND":
                return lhsResult && rhsResult;
            case "OR":
                return lhsResult || rhsResult;
            default:
                return false;
        }
    }
    
    /**
     * Generate HTML for assignment results.
     */
    private String generateAssignmentHTML(Map<String, List<TaskInfo>> assignments, String title) {
        StringBuilder html = new StringBuilder();
        
        html.append("<div class='assignment-results'>");
        html.append("<h3>").append(title).append("</h3>");
        
        if (assignments.isEmpty()) {
            html.append("<p style='color: #cf6679;'>No task assignments found.</p>");
            return html.toString();
        }
        
        html.append("<ul style='color: #e0e0e0;'>");
        
        for (Map.Entry<String, List<TaskInfo>> entry : assignments.entrySet()) {
            html.append("<li><strong style='color: #bb86fc;'>").append(entry.getKey()).append("</strong>: ");
            
            if (entry.getValue().isEmpty()) {
                html.append("<span style='color: #cf6679;'>No tasks assigned</span>");
            } else {
                html.append("<ul>");
                for (TaskInfo task : entry.getValue()) {
                    html.append("<li style='color: #03dac6;'>").append(task.id)
                          .append(" - Difficulty: ").append(task.difficulty)
                          .append(", Duration: ").append(task.duration).append(" min");
                    
                    if (task.startTime != null) {
                        html.append(", Shift: ").append(determineShift(task.startTime));
                    }
                    
                    html.append("</li>");
                }
                html.append("</ul>");
            }
            
            html.append("</li>");
        }
        
        html.append("</ul>");
        html.append("</div>");
        
        return html.toString();
    }
    
    /**
     * Determine the shift based on a start time.
     */
    private String determineShift(Date startTime) {
        if (startTime == null) {
            return "Unknown";
        }
        
        @SuppressWarnings("deprecation")
        int hour = startTime.getHours();
        
        if (hour >= DAY_SHIFT_START && hour < DAY_SHIFT_END) {
            return "Day Shift";
        } else if (hour >= EVENING_SHIFT_START && hour < EVENING_SHIFT_END) {
            return "Evening Shift";
        } else {
            return "Night Shift";
        }
    }
    
    /**
     * Worker information class.
     */
    private class WorkerInfo {
        private String name;
        private String employeeNumber;
        private String seniorityLevel;
        private boolean active;
        
        public WorkerInfo(String name, String employeeNumber, String seniorityLevel, boolean active) {
            this.name = name;
            this.employeeNumber = employeeNumber;
            this.seniorityLevel = seniorityLevel;
            this.active = active;
        }
    }
    
    /**
     * Task information class.
     */
    private class TaskInfo {
        private String id;
        private String difficulty;
        private int duration;
        private Date startTime;
        
        public TaskInfo(String id, String difficulty, int duration, Date startTime) {
            this.id = id;
            this.difficulty = difficulty;
            this.duration = duration;
            this.startTime = startTime;
        }
    }
    
    /**
     * Rule information class.
     */
    private class RuleInfo {
        private String result;
        private EObject expression;
        
        public RuleInfo(String result, EObject expression) {
            this.result = result;
            this.expression = expression;
        }
    }
} 