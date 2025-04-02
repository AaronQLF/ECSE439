<?xml version="1.0" encoding="ASCII"?>
<tps:TPS xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tps="http://www.example.org/tps" xmi:id="_u6xbsA_zEfC1Z7OFqZl4RQ">
  <workers xmi:id="_u6xbsQ_zEfC1Z7OFqZl4RQ" seniorityLevel="SENIOR" name="John Doe" employeeNumber="1" active="true"/>
  <workers xmi:id="_u6xbsg_zEfC1Z7OFqZl4RQ" seniorityLevel="JUNIOR" name="Jane Smith" employeeNumber="2" active="true"/>
  <workers xmi:id="_u6xbsw_zEfC1Z7OFqZl4RQ" name="Alice Johnson" employeeNumber="3" active="true"/>
  <taskRules xmi:id="_u6xbtA_zEfC1Z7OFqZl4RQ" result="SENIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_u6xbtQ_zEfC1Z7OFqZl4RQ">
      <RHS xsi:type="tps:DifficultyExpression" xmi:id="_u6xbtg_zEfC1Z7OFqZl4RQ" difficulty="HARD"/>
      <LHS xsi:type="tps:DurationExpression" xmi:id="_u6xbtw_zEfC1Z7OFqZl4RQ" operator="GREATERTHANOREQUAL" minutes="150"/>
    </expression>
  </taskRules>
  <taskRules xmi:id="_u6xbuA_zEfC1Z7OFqZl4RQ" result="JUNIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_u6xbuQ_zEfC1Z7OFqZl4RQ">
      <RHS xsi:type="tps:CompoundExpression" xmi:id="_u6xbug_zEfC1Z7OFqZl4RQ">
        <RHS xsi:type="tps:CompoundExpression" xmi:id="_u6xbuw_zEfC1Z7OFqZl4RQ" operator="OR">
          <RHS xsi:type="tps:DifficultyExpression" xmi:id="_u6xbvA_zEfC1Z7OFqZl4RQ" difficulty="HARD"/>
          <LHS xsi:type="tps:DifficultyExpression" xmi:id="_u6xbvQ_zEfC1Z7OFqZl4RQ" difficulty="MODERATE"/>
        </RHS>
        <LHS xsi:type="tps:CompoundExpression" xmi:id="_u6xbvg_zEfC1Z7OFqZl4RQ" operator="OR">
          <RHS xsi:type="tps:DurationExpression" xmi:id="_u6xbvw_zEfC1Z7OFqZl4RQ" minutes="60"/>
          <LHS xsi:type="tps:DurationExpression" xmi:id="_u6xbwA_zEfC1Z7OFqZl4RQ" operator="GREATERTHAN" minutes="120"/>
        </LHS>
      </RHS>
      <LHS xsi:type="tps:ShiftExpression" xmi:id="_u6xbwQ_zEfC1Z7OFqZl4RQ"/>
    </expression>
  </taskRules>
  <taskRules xmi:id="_u6xbwg_zEfC1Z7OFqZl4RQ">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_u6xbww_zEfC1Z7OFqZl4RQ" operator="OR">
      <RHS xsi:type="tps:CompoundExpression" xmi:id="_u6xbxA_zEfC1Z7OFqZl4RQ">
        <RHS xsi:type="tps:CompoundExpression" xmi:id="_u6xbxQ_zEfC1Z7OFqZl4RQ">
          <RHS xsi:type="tps:DifficultyExpression" xmi:id="_u6xbxg_zEfC1Z7OFqZl4RQ" difficulty="MODERATE"/>
          <LHS xsi:type="tps:DurationExpression" xmi:id="_u6xbxw_zEfC1Z7OFqZl4RQ" minutes="60"/>
        </RHS>
        <LHS xsi:type="tps:ShiftExpression" xmi:id="_u6xbyA_zEfC1Z7OFqZl4RQ" shift="EVENINGSHIFT"/>
      </RHS>
      <LHS xsi:type="tps:CompoundExpression" xmi:id="_u6xbyQ_zEfC1Z7OFqZl4RQ">
        <RHS xsi:type="tps:CompoundExpression" xmi:id="_u6xbyg_zEfC1Z7OFqZl4RQ">
          <RHS xsi:type="tps:DifficultyExpression" xmi:id="_u6xbyw_zEfC1Z7OFqZl4RQ"/>
          <LHS xsi:type="tps:DurationExpression" xmi:id="_u6xbzA_zEfC1Z7OFqZl4RQ" minutes="120"/>
        </RHS>
        <LHS xsi:type="tps:ShiftExpression" xmi:id="_u6xbzQ_zEfC1Z7OFqZl4RQ"/>
      </LHS>
    </expression>
  </taskRules>
  <tasks xmi:id="_u6xbzg_zEfC1Z7OFqZl4RQ" ID="t1" duration="120"/>
  <tasks xmi:id="_u6xbzw_zEfC1Z7OFqZl4RQ" ID="t2" difficultyLevel="MODERATE" duration="90"/>
  <tasks xmi:id="_u6xb0A_zEfC1Z7OFqZl4RQ" ID="t3" difficultyLevel="HARD" duration="60"/>
</tps:TPS>
