package org.eclipse.acceleo.module.TPS2Text.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * This service class provides methods to interact with the AutoTPS analysis tool.
 * It simulates the functionality of the AutoTPS tool by generating task assignments
 * based on TPS model data.
 * 
 * In a real implementation, this would interface with the actual AutoTPS tool
 * from assignment #1, adjusted to take XMI input instead of textual input.
 */
public class AutoTPSService {
    
    /**
     * Generate task assignments based on the TPS model.
     * 
     * @param tpsModel The TPS model element from which to generate assignments
     * @return HTML representation of task assignments
     */
    public String generateTaskAssignments(EObject tpsModel) {
        // This is a placeholder for the actual AutoTPS integration
        // In a real implementation, this would call the AutoTPS tool
        
        StringBuilder result = new StringBuilder();
        
        // Simple mock of task assignment logic
        // In a real implementation, this would parse the results from AutoTPS
        result.append("<div class='assignment-results'>");
        result.append("<h3>Task Assignment Results</h3>");
        result.append("<ul>");
        
        // Sample assignment data (would come from AutoTPS in real implementation)
        Map<String, List<String>> mockAssignments = getMockAssignments();
        
        for (Map.Entry<String, List<String>> entry : mockAssignments.entrySet()) {
            result.append("<li><strong>").append(entry.getKey()).append("</strong>: ");
            result.append("<ul>");
            
            for (String task : entry.getValue()) {
                result.append("<li>").append(task).append("</li>");
            }
            
            result.append("</ul></li>");
        }
        
        result.append("</ul>");
        result.append("</div>");
        
        return result.toString();
    }
    
    /**
     * Helper method to create mock assignments for demo purposes.
     * In a real implementation, this data would come from the AutoTPS tool.
     */
    private Map<String, List<String>> getMockAssignments() {
        Map<String, List<String>> assignments = new HashMap<>();
        
        List<String> worker1Tasks = new ArrayList<>();
        worker1Tasks.add("Task 1 (High priority)");
        worker1Tasks.add("Task 3 (Medium priority)");
        assignments.put("Senior Worker", worker1Tasks);
        
        List<String> worker2Tasks = new ArrayList<>();
        worker2Tasks.add("Task 2 (Medium priority)");
        worker2Tasks.add("Task 5 (Low priority)");
        assignments.put("Junior Worker", worker2Tasks);
        
        List<String> worker3Tasks = new ArrayList<>();
        worker3Tasks.add("Task 4 (Low priority)");
        assignments.put("Trainee", worker3Tasks);
        
        return assignments;
    }
} 