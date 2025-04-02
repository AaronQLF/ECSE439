package tps.modelgeneration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;

import tps.ComparisonOperator;
import tps.CompoundExpression;
import tps.DifficultyExpression;
import tps.DifficultyLevel;
import tps.DurationExpression;
import tps.Expression;
import tps.Operator;
import tps.SeniorityLevel;
import tps.Shift;
import tps.ShiftExpression;
import tps.TPS;
import tps.Task;
import tps.TaskRule;
import tps.TpsFactory;
import tps.TpsPackage;
import tps.Worker;
import tps.util.ResourceHelper;
import tps.util.TPSResourceFactoryImpl;

public class TPSModelGenerator {

    public static void main(String[] args) {
        TpsPackage.eINSTANCE.eClass();
        
        generateTPSModel("examples/assignment2.tps");
        generateTPSModel("examples/simple.tps");
        generateTPSModel("examples/compound.tps");
        generateTPSModel("examples/nested.tps");
        generateTPSModel("examples/complex.tps");
        generateTPSModel("examples/assignment2unknowns.tps");
        generateTPSModel("examples/unknown1.tps");
        generateTPSModel("examples/unknown2.tps");
        generateTPSModel("examples/unknown3.tps");
    }

	private static void generateTPSModel(String filename) {
		TPS tps = TpsFactory.eINSTANCE.createTPS();
		
		if (filename.contains("t2.") || filename.contains("simple")) {
			DurationExpression duS = createDurationExpression(ComparisonOperator.GREATERTHANOREQUAL, 200);
			TaskRule simple = createRule(duS, SeniorityLevel.SENIOR, tps);
		}
		
		if (filename.contains("t2.") || filename.contains("compound")) {
			DurationExpression duC = createDurationExpression(ComparisonOperator.GREATERTHAN, 100);
			DifficultyExpression diC = createDifficultyExpression(DifficultyLevel.MODERATE);
			CompoundExpression cC = createCompoundExpression(duC, diC, Operator.OR);
			TaskRule compound = createRule(cC, SeniorityLevel.JUNIOR, tps);
		}
		
		if (filename.contains("t2.") || filename.contains("nested")) {
			DifficultyExpression diN = createDifficultyExpression(DifficultyLevel.EASY);
			DurationExpression duN = createDurationExpression(ComparisonOperator.LESSTHAN, 100);
			ShiftExpression sN = createShiftExpression(Shift.DAYSHIFT);
			CompoundExpression c1N = createCompoundExpression(duN, sN, Operator.OR);
			CompoundExpression c2N = createCompoundExpression(diN, c1N, Operator.AND);
			TaskRule nested = createRule(c2N, SeniorityLevel.TRAINEE, tps);
		}
		
		if (filename.contains("t2.") || filename.contains("complex")) {
			DifficultyExpression di1O = createDifficultyExpression(DifficultyLevel.EASY);
			DifficultyExpression di2O = createDifficultyExpression(DifficultyLevel.MODERATE);
			DifficultyExpression di3O = createDifficultyExpression(DifficultyLevel.MODERATE);
			DifficultyExpression di4O = createDifficultyExpression(DifficultyLevel.HARD);
			DurationExpression du1O = createDurationExpression(ComparisonOperator.GREATERTHANOREQUAL, 100);
			DurationExpression du2O = createDurationExpression(ComparisonOperator.GREATERTHAN, 90);
			DurationExpression du3O = createDurationExpression(ComparisonOperator.LESSTHAN, 180);
			ShiftExpression sO = createShiftExpression(Shift.DAYSHIFT);
			CompoundExpression c1O = createCompoundExpression(di1O, du1O, Operator.AND);
			CompoundExpression c2O = createCompoundExpression(di2O, du2O, Operator.AND);
			CompoundExpression c3O = createCompoundExpression(di3O, du3O, Operator.AND);
			CompoundExpression c4O = createCompoundExpression(di4O, sO, Operator.AND);
			CompoundExpression c5O = createCompoundExpression(c2O, c3O, Operator.AND);
			CompoundExpression c6O = createCompoundExpression(c1O, c5O, Operator.OR);
			CompoundExpression c7O = createCompoundExpression(c6O, c4O, Operator.OR);
			TaskRule complex = createRule(c7O, SeniorityLevel.JUNIOR, tps);
		}

		if (filename.contains("t2u") || filename.contains("n1")) {
			Worker w1 = createWorker("John Doe", SeniorityLevel.SENIOR, "1", true);
			Worker w2 = createWorker("Jane Smith", SeniorityLevel.JUNIOR, "2", true);
			Worker w3 = createWorker("Alice Johnson", SeniorityLevel.TRAINEE, "3", true);
			tps.getWorkers().add(w1);
			tps.getWorkers().add(w2);
			tps.getWorkers().add(w3);
		    Task t1 = createTask("t1", 120, DifficultyLevel.EASY, Date.from(LocalTime.of(7, 0).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
			Task t2 = createTask("t2", 90, DifficultyLevel.MODERATE, Date.from(LocalTime.of(10, 0).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
			Task t3 = createTask("t3", 60, DifficultyLevel.HARD, Date.from(LocalTime.of(12, 0).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
			tps.getTasks().add(t1);
			tps.getTasks().add(t2);
			tps.getTasks().add(t3);
			DurationExpression duU1 = createDurationExpression(ComparisonOperator.GREATERTHANOREQUAL, 150);
			DifficultyExpression diU1 = createDifficultyExpression(DifficultyLevel.HARD);
			CompoundExpression cU1 = createCompoundExpression(duU1, diU1, Operator.AND);
			TaskRule unknown1 = createRule(cU1, SeniorityLevel.SENIOR, tps);
		}
		
		if (filename.contains("t2u") || filename.contains("n2")) {
			ShiftExpression sU2 = createShiftExpression(Shift.DAYSHIFT);
			DurationExpression du1U2 = createDurationExpression(ComparisonOperator.GREATERTHAN, 120);
			DurationExpression du2U2 = createDurationExpression(ComparisonOperator.LESSTHAN, 60);
			DifficultyExpression di1U2 = createDifficultyExpression(DifficultyLevel.MODERATE);
			DifficultyExpression di2U2 = createDifficultyExpression(DifficultyLevel.HARD);
			CompoundExpression c1U2 = createCompoundExpression(du1U2, du2U2, Operator.OR);
			CompoundExpression c2U2 = createCompoundExpression(di1U2, di2U2, Operator.OR);
			CompoundExpression c3U2 = createCompoundExpression(c1U2, c2U2, Operator.AND);
			CompoundExpression c4U2 = createCompoundExpression(sU2, c3U2, Operator.AND);
			TaskRule unknown2 = createRule(c4U2, SeniorityLevel.JUNIOR, tps);
		}

		if (filename.contains("t2u") || filename.contains("n3")) {
			ShiftExpression s1U3 = createShiftExpression(Shift.DAYSHIFT);
			ShiftExpression s2U3 = createShiftExpression(Shift.EVENINGSHIFT);
			DurationExpression du1U3 = createDurationExpression(ComparisonOperator.LESSTHAN, 120);
			DurationExpression du2U3 = createDurationExpression(ComparisonOperator.LESSTHAN, 60);
			DifficultyExpression di1U3 = createDifficultyExpression(DifficultyLevel.EASY);
			DifficultyExpression di2U3 = createDifficultyExpression(DifficultyLevel.MODERATE);
			CompoundExpression c1U3 = createCompoundExpression(du1U3, di1U3, Operator.AND);
			CompoundExpression c2U3 = createCompoundExpression(s1U3, c1U3, Operator.AND);
			CompoundExpression c3U3 = createCompoundExpression(du2U3, di2U3, Operator.AND);
			CompoundExpression c4U3 = createCompoundExpression(s2U3, c3U3, Operator.AND);
			CompoundExpression c5U3 = createCompoundExpression(c2U3, c4U3, Operator.OR);
			TaskRule unknown3 = createRule(c5U3, SeniorityLevel.TRAINEE, tps);
		}

		saveModel(filename, tps);
	}
	
	private static Worker createWorker(String name, SeniorityLevel level, String employeeNumber, Boolean isActive) {
		Worker w = TpsFactory.eINSTANCE.createWorker();
		w.setName(name);
		w.setSeniorityLevel(level);
		w.setEmployeeNumber(employeeNumber);
		w.setActive(isActive);
		return w;
	}
	
	private static Task createTask(String id, int duration, DifficultyLevel level, Date startTime) {
		Task t = TpsFactory.eINSTANCE.createTask();
		t.setID(id);
		t.setDuration(duration);
		t.setDifficultyLevel(level);
		t.setStartTime(startTime);
		return t;
	}
	
	private static DurationExpression createDurationExpression(ComparisonOperator operator, int minutes) {
        DurationExpression d = TpsFactory.eINSTANCE.createDurationExpression();
        d.setOperator(operator);
        d.setMinutes(minutes);
        return d;
	}

	private static DifficultyExpression createDifficultyExpression(DifficultyLevel level) {
        DifficultyExpression d = TpsFactory.eINSTANCE.createDifficultyExpression();
        d.setDifficulty(level);
        return d;
	}
	
	private static ShiftExpression createShiftExpression(Shift shift) {
        ShiftExpression s = TpsFactory.eINSTANCE.createShiftExpression();
        s.setShift(shift);
        return s;
	}
	
	private static CompoundExpression createCompoundExpression(Expression LHS, Expression RHS, Operator operator) {
        CompoundExpression c = TpsFactory.eINSTANCE.createCompoundExpression();
        c.setOperator(operator);
        c.setLHS(LHS);
        c.setRHS(RHS);
        return c;
	}

	private static TaskRule createRule(Expression expression, SeniorityLevel result, TPS tps) {
        TaskRule tr = TpsFactory.eINSTANCE.createTaskRule();
        tr.setResult(result);
        tr.setExpression(expression);
        tps.getTaskRules().add(tr);
        return tr;
	}
	
	private static void saveModel(String filename, TPS ims) {
		// Save the model.
        ResourceHelper.INSTANCE.addResourceFactory("tps", new TPSResourceFactoryImpl());
        ResourceHelper.INSTANCE.saveResource(ims, filename);
        Resource resource = ResourceHelper.INSTANCE.loadResource(filename);
        
        // Get the root object.
        TPS savedTPS = (TPS) resource.getContents().get(0);
        
        // Print the rules.
        printRules(savedTPS.getTaskRules());
	}
	
	private static void printRules(EList<TaskRule> rules) {
		int counter = 1;
		for (TaskRule tr : rules) {
			System.out.print("Rule " + counter + ": if ");
			String expression = convertToString(tr.getExpression());
			System.out.print(expression);
			System.out.println(" then " + tr.getResult().toString().toLowerCase());
			counter++;
		}
		System.out.println();
	}

	private static String convertToString(Expression exp) {
		String result = "";
		if (exp instanceof DurationExpression duE) {
			if (duE.getOperator() == ComparisonOperator.LESSTHAN) {
				result = result + "< ";
			} else if (duE.getOperator() == ComparisonOperator.LESSTHANOREQUAL) {
				result = result + "<= ";				
			} else if (duE.getOperator() == ComparisonOperator.EQUAL) {
				result = result + "= ";				
			} else if (duE.getOperator() == ComparisonOperator.GREATERTHANOREQUAL) {
				result = result + ">= ";				
			} else if (duE.getOperator() == ComparisonOperator.GREATERTHAN) {
				result = result + "> ";				
			}
			result = result + duE.getMinutes() + " min";
		} else if (exp instanceof DifficultyExpression diE) {
			result = result + diE.getDifficulty().toString().toLowerCase();
		} else if (exp instanceof ShiftExpression sE) {
			result = result + sE.getShift().toString().toLowerCase();
		} else if (exp instanceof CompoundExpression cE) {
			result = "(" + convertToString(cE.getLHS()) + " " + cE.getOperator().toString() + 
					" " + convertToString(cE.getRHS()) + ")";
		} 
		return result;
	}
	    
}
