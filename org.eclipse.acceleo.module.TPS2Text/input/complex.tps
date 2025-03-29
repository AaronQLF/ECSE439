<?xml version="1.0" encoding="ASCII"?>
<tps:TPS xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tps="http://www.example.org/tps" xmi:id="_T-dnYN_9Ee-Wd-55Kl546Q">
  <taskRules xmi:id="_T-dnYd_9Ee-Wd-55Kl546Q" result="JUNIOR">
    <expression xsi:type="tps:CompoundExpression" xmi:id="_T-dnYt_9Ee-Wd-55Kl546Q" operator="OR">
      <RHS xsi:type="tps:CompoundExpression" xmi:id="_T-eOcN_9Ee-Wd-55Kl546Q">
        <RHS xsi:type="tps:ShiftExpression" xmi:id="_T-eOcd_9Ee-Wd-55Kl546Q"/>
        <LHS xsi:type="tps:DifficultyExpression" xmi:id="_T-eOct_9Ee-Wd-55Kl546Q" difficulty="HARD"/>
      </RHS>
      <LHS xsi:type="tps:CompoundExpression" xmi:id="_T-eOc9_9Ee-Wd-55Kl546Q" operator="OR">
        <RHS xsi:type="tps:CompoundExpression" xmi:id="_T-eOdN_9Ee-Wd-55Kl546Q">
          <RHS xsi:type="tps:CompoundExpression" xmi:id="_T-eOdd_9Ee-Wd-55Kl546Q">
            <RHS xsi:type="tps:DurationExpression" xmi:id="_T-eOdt_9Ee-Wd-55Kl546Q" minutes="180"/>
            <LHS xsi:type="tps:DifficultyExpression" xmi:id="_T-eOd9_9Ee-Wd-55Kl546Q" difficulty="MODERATE"/>
          </RHS>
          <LHS xsi:type="tps:CompoundExpression" xmi:id="_T-eOeN_9Ee-Wd-55Kl546Q">
            <RHS xsi:type="tps:DurationExpression" xmi:id="_T-eOed_9Ee-Wd-55Kl546Q" operator="GREATERTHAN" minutes="90"/>
            <LHS xsi:type="tps:DifficultyExpression" xmi:id="_T-eOet_9Ee-Wd-55Kl546Q" difficulty="MODERATE"/>
          </LHS>
        </RHS>
        <LHS xsi:type="tps:CompoundExpression" xmi:id="_T-eOe9_9Ee-Wd-55Kl546Q">
          <RHS xsi:type="tps:DurationExpression" xmi:id="_T-eOfN_9Ee-Wd-55Kl546Q" operator="GREATERTHANOREQUAL" minutes="100"/>
          <LHS xsi:type="tps:DifficultyExpression" xmi:id="_T-eOfd_9Ee-Wd-55Kl546Q"/>
        </LHS>
      </LHS>
    </expression>
  </taskRules>
</tps:TPS>
