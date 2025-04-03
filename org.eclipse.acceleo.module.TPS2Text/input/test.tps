<?xml version="1.0" encoding="ASCII"?>
<tps:TPS xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tps="http://www.example.org/tps" xmi:id="_ComprehensiveTest">
  <workers xmi:id="_Worker1" seniorityLevel="SENIOR" name="Jennifer Lopez" employeeNumber="SR001" active="true"/>
  <workers xmi:id="_Worker2" seniorityLevel="SENIOR" name="Thomas Harris" employeeNumber="SR002" active="true"/>
  <workers xmi:id="_Worker3" seniorityLevel="JUNIOR" name="Lisa Chen" employeeNumber="JR001" active="true"/>
  <workers xmi:id="_Worker4" seniorityLevel="JUNIOR" name="Kevin Wong" employeeNumber="JR002" active="true"/>
  <workers xmi:id="_Worker5" seniorityLevel="TRAINEE" name="Maria Rodriguez" employeeNumber="TR001" active="true"/>
  <workers xmi:id="_Worker6" seniorityLevel="TRAINEE" name="Jason Kim" employeeNumber="TR002" active="true"/>
  
  <tasks xmi:id="_Task1" ID="COMPLEX-1" difficultyLevel="HARD" duration="240" startTime="2023-12-01T08:00:00.000"/>
  <tasks xmi:id="_Task2" ID="COMPLEX-2" difficultyLevel="HARD" duration="180" startTime="2023-12-01T14:00:00.000"/>
  <tasks xmi:id="_Task3" ID="COMPLEX-3" difficultyLevel="MODERATE" duration="150" startTime="2023-12-01T17:00:00.000"/>
  <tasks xmi:id="_Task4" ID="COMPLEX-4" difficultyLevel="MODERATE" duration="120" startTime="2023-12-01T20:00:00.000"/>
  <tasks xmi:id="_Task5" ID="COMPLEX-5" difficultyLevel="MODERATE" duration="90" startTime="2023-12-01T23:00:00.000"/>
  <tasks xmi:id="_Task6" ID="COMPLEX-6" difficultyLevel="EASY" duration="60" startTime="2023-12-02T09:00:00.000"/>
  <tasks xmi:id="_Task7" ID="COMPLEX-7" difficultyLevel="EASY" duration="45" startTime="2023-12-02T11:00:00.000"/>
  <tasks xmi:id="_Task8" ID="COMPLEX-8" difficultyLevel="EASY" duration="30" startTime="2023-12-02T14:00:00.000"/>
  
  <!-- Rule 1: Hard tasks with duration >= 200 min to Senior -->
  <taskRules xmi:id="_Rule1" result="SENIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_Expr1" operator="AND">
      <RHS xsi:type="tps:DurationExpression" xmi:id="_Expr1_RHS" operator="GREATERTHANOREQUAL" minutes="200"/>
      <LHS xsi:type="tps:DifficultyExpression" xmi:id="_Expr1_LHS" difficulty="HARD"/>
    </expression>
  </taskRules>
  
  <!-- Rule 2: Hard tasks during night shift to Senior -->
  <taskRules xmi:id="_Rule2" result="SENIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_Expr2" operator="AND">
      <RHS xsi:type="tps:ShiftExpression" xmi:id="_Expr2_RHS" shift="NIGHTSHIFT"/>
      <LHS xsi:type="tps:DifficultyExpression" xmi:id="_Expr2_LHS" difficulty="HARD"/>
    </expression>
  </taskRules>
  
  <!-- Rule 3: Moderate tasks during evening shift to Junior -->
  <taskRules xmi:id="_Rule3" result="JUNIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_Expr3" operator="AND">
      <RHS xsi:type="tps:ShiftExpression" xmi:id="_Expr3_RHS" shift="EVENINGSHIFT"/>
      <LHS xsi:type="tps:DifficultyExpression" xmi:id="_Expr3_LHS" difficulty="MODERATE"/>
    </expression>
  </taskRules>
  
  <!-- Rule 4: Hard tasks with duration < 200 min to Junior -->
  <taskRules xmi:id="_Rule4" result="JUNIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_Expr4" operator="AND">
      <RHS xsi:type="tps:DurationExpression" xmi:id="_Expr4_RHS" operator="LESSTHAN" minutes="200"/>
      <LHS xsi:type="tps:DifficultyExpression" xmi:id="_Expr4_LHS" difficulty="HARD"/>
    </expression>
  </taskRules>
  
  <!-- Rule 5: Moderate tasks with duration < 100 min to Junior -->
  <taskRules xmi:id="_Rule5" result="JUNIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_Expr5" operator="AND">
      <RHS xsi:type="tps:DurationExpression" xmi:id="_Expr5_RHS" operator="LESSTHAN" minutes="100"/>
      <LHS xsi:type="tps:DifficultyExpression" xmi:id="_Expr5_LHS" difficulty="MODERATE"/>
    </expression>
  </taskRules>
  
  <!-- Rule 6: Easy tasks during day shift to Trainee -->
  <taskRules xmi:id="_Rule6" result="TRAINEE">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_Expr6" operator="AND">
      <RHS xsi:type="tps:ShiftExpression" xmi:id="_Expr6_RHS" shift="DAYSHIFT"/>
      <LHS xsi:type="tps:DifficultyExpression" xmi:id="_Expr6_LHS" difficulty="EASY"/>
    </expression>
  </taskRules>
  
  <!-- Rule 7: Any tasks < 50 min to Trainee -->
  <taskRules xmi:id="_Rule7" result="TRAINEE">
    <expression xsi:type="tps:DurationExpression" xmi:id="_Expr7" operator="LESSTHAN" minutes="50"/>
  </taskRules>
  
  <!-- Task Assignments -->
  <workers xmi:id="_AssignedWorker1" seniorityLevel="SENIOR" name="William Davis" employeeNumber="SR101" active="true">
    <tasks xmi:id="_AssignedTask1" ID="ASSIGNED-1" difficultyLevel="HARD" duration="240" startTime="2023-12-01T08:00:00.000"/>
    <tasks xmi:id="_AssignedTask2" ID="ASSIGNED-2" difficultyLevel="HARD" duration="210" startTime="2023-12-01T23:00:00.000"/>
  </workers>
  
  <workers xmi:id="_AssignedWorker2" seniorityLevel="JUNIOR" name="Rachel Green" employeeNumber="JR101" active="true">
    <tasks xmi:id="_AssignedTask3" ID="ASSIGNED-3" difficultyLevel="HARD" duration="150" startTime="2023-12-01T12:00:00.000"/>
    <tasks xmi:id="_AssignedTask4" ID="ASSIGNED-4" difficultyLevel="MODERATE" duration="120" startTime="2023-12-01T17:00:00.000"/>
    <tasks xmi:id="_AssignedTask5" ID="ASSIGNED-5" difficultyLevel="MODERATE" duration="80" startTime="2023-12-01T23:30:00.000"/>
  </workers>
  
  <workers xmi:id="_AssignedWorker3" seniorityLevel="TRAINEE" name="Carlos Mendez" employeeNumber="TR101" active="true">
    <tasks xmi:id="_AssignedTask6" ID="ASSIGNED-6" difficultyLevel="EASY" duration="60" startTime="2023-12-02T09:00:00.000"/>
    <tasks xmi:id="_AssignedTask7" ID="ASSIGNED-7" difficultyLevel="EASY" duration="40" startTime="2023-12-02T11:00:00.000"/>
  </workers>
</tps:TPS> 