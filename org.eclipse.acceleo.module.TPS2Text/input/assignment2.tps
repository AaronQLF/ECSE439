<?xml version="1.0" encoding="ASCII"?>
<tps:TPS xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tps="http://www.example.org/tps" xmi:id="_T8RPAN_9Ee-Wd-55Kl546Q">
  <taskRules xmi:id="_T8WHgN_9Ee-Wd-55Kl546Q" result="SENIOR">
    <expression xsi:type="tps:DurationExpression" xmi:id="_T8WHgd_9Ee-Wd-55Kl546Q" operator="GREATERTHANOREQUAL" minutes="200"/>
  </taskRules>
  <taskRules xmi:id="_T8WHgt_9Ee-Wd-55Kl546Q" result="JUNIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_T8WHg9_9Ee-Wd-55Kl546Q" operator="OR">
      <RHS xsi:type="tps:DifficultyExpression" xmi:id="_T8WHhN_9Ee-Wd-55Kl546Q" difficulty="MODERATE"/>
      <LHS xsi:type="tps:DurationExpression" xmi:id="_T8WHhd_9Ee-Wd-55Kl546Q" operator="GREATERTHAN" minutes="100"/>
    </expression>
  </taskRules>
  <taskRules xmi:id="_T8WHht_9Ee-Wd-55Kl546Q">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_T8WHh9_9Ee-Wd-55Kl546Q">
      <RHS xsi:type="tps:CompoundExpression" xmi:id="_T8WHiN_9Ee-Wd-55Kl546Q" operator="OR">
        <RHS xsi:type="tps:ShiftExpression" xmi:id="_T8WHid_9Ee-Wd-55Kl546Q"/>
        <LHS xsi:type="tps:DurationExpression" xmi:id="_T8WHit_9Ee-Wd-55Kl546Q" minutes="100"/>
      </RHS>
      <LHS xsi:type="tps:DifficultyExpression" xmi:id="_T8WHi9_9Ee-Wd-55Kl546Q"/>
    </expression>
  </taskRules>
  <taskRules xmi:id="_T8WHjN_9Ee-Wd-55Kl546Q" result="JUNIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_T8WukN_9Ee-Wd-55Kl546Q" operator="OR">
      <RHS xsi:type="tps:CompoundExpression" xmi:id="_T8Wukd_9Ee-Wd-55Kl546Q">
        <RHS xsi:type="tps:ShiftExpression" xmi:id="_T8Wukt_9Ee-Wd-55Kl546Q"/>
        <LHS xsi:type="tps:DifficultyExpression" xmi:id="_T8Wuk9_9Ee-Wd-55Kl546Q" difficulty="HARD"/>
      </RHS>
      <LHS xsi:type="tps:CompoundExpression" xmi:id="_T8WulN_9Ee-Wd-55Kl546Q" operator="OR">
        <RHS xsi:type="tps:CompoundExpression" xmi:id="_T8Wuld_9Ee-Wd-55Kl546Q">
          <RHS xsi:type="tps:CompoundExpression" xmi:id="_T8Wult_9Ee-Wd-55Kl546Q">
            <RHS xsi:type="tps:DurationExpression" xmi:id="_T8Wul9_9Ee-Wd-55Kl546Q" minutes="180"/>
            <LHS xsi:type="tps:DifficultyExpression" xmi:id="_T8WumN_9Ee-Wd-55Kl546Q" difficulty="MODERATE"/>
          </RHS>
          <LHS xsi:type="tps:CompoundExpression" xmi:id="_T8Wumd_9Ee-Wd-55Kl546Q">
            <RHS xsi:type="tps:DurationExpression" xmi:id="_T8Wumt_9Ee-Wd-55Kl546Q" operator="GREATERTHAN" minutes="90"/>
            <LHS xsi:type="tps:DifficultyExpression" xmi:id="_T8Wum9_9Ee-Wd-55Kl546Q" difficulty="MODERATE"/>
          </LHS>
        </RHS>
        <LHS xsi:type="tps:CompoundExpression" xmi:id="_T8WunN_9Ee-Wd-55Kl546Q">
          <RHS xsi:type="tps:DurationExpression" xmi:id="_T8Wund_9Ee-Wd-55Kl546Q" operator="GREATERTHANOREQUAL" minutes="100"/>
          <LHS xsi:type="tps:DifficultyExpression" xmi:id="_T8Wunt_9Ee-Wd-55Kl546Q"/>
        </LHS>
      </LHS>
    </expression>
  </taskRules>
</tps:TPS>
