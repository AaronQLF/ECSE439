<?xml version="1.0" encoding="ASCII"?>
<tps:TPS xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tps="http://www.example.org/tps" xmi:id="_LxoVcA_4EfCTDcHNSNMtJQ">
  <workers xmi:id="_LxoVcQ_4EfCTDcHNSNMtJQ" seniorityLevel="SENIOR" name="John Doe" employeeNumber="1" active="true"/>
  <workers xmi:id="_LxoVcg_4EfCTDcHNSNMtJQ" seniorityLevel="JUNIOR" name="Jane Smith" employeeNumber="2" active="true"/>
  <workers xmi:id="_LxoVcw_4EfCTDcHNSNMtJQ" name="Alice Johnson" employeeNumber="3" active="true"/>
  <taskRules xmi:id="_LxoVdA_4EfCTDcHNSNMtJQ" result="SENIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_LxoVdQ_4EfCTDcHNSNMtJQ">
      <RHS xsi:type="tps:DifficultyExpression" xmi:id="_LxoVdg_4EfCTDcHNSNMtJQ" difficulty="HARD"/>
      <LHS xsi:type="tps:DurationExpression" xmi:id="_Lxo8gA_4EfCTDcHNSNMtJQ" operator="GREATERTHANOREQUAL" minutes="150"/>
    </expression>
  </taskRules>
  <taskRules xmi:id="_Lxo8gQ_4EfCTDcHNSNMtJQ" result="JUNIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_Lxo8gg_4EfCTDcHNSNMtJQ">
      <RHS xsi:type="tps:CompoundExpression" xmi:id="_Lxo8gw_4EfCTDcHNSNMtJQ">
        <RHS xsi:type="tps:CompoundExpression" xmi:id="_Lxo8hA_4EfCTDcHNSNMtJQ" operator="OR">
          <RHS xsi:type="tps:DifficultyExpression" xmi:id="_Lxo8hQ_4EfCTDcHNSNMtJQ" difficulty="HARD"/>
          <LHS xsi:type="tps:DifficultyExpression" xmi:id="_Lxo8hg_4EfCTDcHNSNMtJQ" difficulty="MODERATE"/>
        </RHS>
        <LHS xsi:type="tps:CompoundExpression" xmi:id="_Lxo8hw_4EfCTDcHNSNMtJQ" operator="OR">
          <RHS xsi:type="tps:DurationExpression" xmi:id="_LxpjkA_4EfCTDcHNSNMtJQ" minutes="60"/>
          <LHS xsi:type="tps:DurationExpression" xmi:id="_LxpjkQ_4EfCTDcHNSNMtJQ" operator="GREATERTHAN" minutes="120"/>
        </LHS>
      </RHS>
      <LHS xsi:type="tps:ShiftExpression" xmi:id="_Lxpjkg_4EfCTDcHNSNMtJQ"/>
    </expression>
  </taskRules>
  <taskRules xmi:id="_Lxpjkw_4EfCTDcHNSNMtJQ">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_LxpjlA_4EfCTDcHNSNMtJQ" operator="OR">
      <RHS xsi:type="tps:CompoundExpression" xmi:id="_LxpjlQ_4EfCTDcHNSNMtJQ">
        <RHS xsi:type="tps:CompoundExpression" xmi:id="_Lxpjlg_4EfCTDcHNSNMtJQ">
          <RHS xsi:type="tps:DifficultyExpression" xmi:id="_Lxpjlw_4EfCTDcHNSNMtJQ" difficulty="MODERATE"/>
          <LHS xsi:type="tps:DurationExpression" xmi:id="_LxpjmA_4EfCTDcHNSNMtJQ" minutes="60"/>
        </RHS>
        <LHS xsi:type="tps:ShiftExpression" xmi:id="_LxpjmQ_4EfCTDcHNSNMtJQ" shift="EVENINGSHIFT"/>
      </RHS>
      <LHS xsi:type="tps:CompoundExpression" xmi:id="_Lxpjmg_4EfCTDcHNSNMtJQ">
        <RHS xsi:type="tps:CompoundExpression" xmi:id="_Lxpjmw_4EfCTDcHNSNMtJQ">
          <RHS xsi:type="tps:DifficultyExpression" xmi:id="_LxpjnA_4EfCTDcHNSNMtJQ"/>
          <LHS xsi:type="tps:DurationExpression" xmi:id="_LxpjnQ_4EfCTDcHNSNMtJQ" minutes="120"/>
        </RHS>
        <LHS xsi:type="tps:ShiftExpression" xmi:id="_Lxpjng_4EfCTDcHNSNMtJQ"/>
      </LHS>
    </expression>
  </taskRules>
  <tasks xmi:id="_Lxpjnw_4EfCTDcHNSNMtJQ" ID="t1" duration="120" startTime="2025-04-02T07:00:00.000-0400"/>
  <tasks xmi:id="_LxpjoA_4EfCTDcHNSNMtJQ" ID="t2" difficultyLevel="MODERATE" duration="90" startTime="2025-04-02T10:00:00.000-0400"/>
  <tasks xmi:id="_LxpjoQ_4EfCTDcHNSNMtJQ" ID="t3" difficultyLevel="HARD" duration="60" startTime="2025-04-02T12:00:00.000-0400"/>
</tps:TPS>
