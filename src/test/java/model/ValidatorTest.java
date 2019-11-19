package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void validate() {
        Validator validator = Validator.getInstance();

        boolean actualNull = validator.validate(null);
        Assert.assertEquals(false, actualNull);

        boolean actualTooShort = validator.validate("1");
        Assert.assertEquals(false, actualTooShort);

        boolean actualOnlyCountry = validator.validate("LT1");
        Assert.assertEquals(false, actualOnlyCountry);

        boolean actualCheckDigits = validator.validate("LT227044077788877777");
        Assert.assertEquals(false, actualCheckDigits);

        boolean actualWrongCountryCode = validator.validate("LQ227044077788877777");
        Assert.assertEquals(false, actualWrongCountryCode);

        boolean actualTooLongIBAN = validator.validate("LT227044077788877777123456789101234567891456");
        Assert.assertEquals(false, actualTooLongIBAN);

        ArrayList<String> validIBANsOfEveryCountry = new ArrayList<>();

        validIBANsOfEveryCountry.add("LT647044001231465456");
        validIBANsOfEveryCountry.add("LT517044077788877777");
        validIBANsOfEveryCountry.add("SM86U0322509800000000270100");
        validIBANsOfEveryCountry.add("AL35202111090000000001234567");
        validIBANsOfEveryCountry.add("AD1400080001001234567890");
        validIBANsOfEveryCountry.add("AT483200000012345864");
        validIBANsOfEveryCountry.add("AZ96AZEJ00000000001234567890");
        validIBANsOfEveryCountry.add("BH02CITI00001077181611");
        validIBANsOfEveryCountry.add("BY86AKBB10100000002966000000");
        validIBANsOfEveryCountry.add("BE71096123456769");
        validIBANsOfEveryCountry.add("BA393385804800211234");
        validIBANsOfEveryCountry.add("BR1500000000000010932840814P2");
        validIBANsOfEveryCountry.add("BG18RZBB91550123456789");
        validIBANsOfEveryCountry.add("CR23015108410026012345");
        validIBANsOfEveryCountry.add("HR1723600001101234565");
        validIBANsOfEveryCountry.add("CY21002001950000357001234567");
        validIBANsOfEveryCountry.add("CZ5508000000001234567899");
        validIBANsOfEveryCountry.add("DK9520000123456789");
        validIBANsOfEveryCountry.add("DO22ACAU00000000000123456789");
        validIBANsOfEveryCountry.add("SV43ACAT00000000000000123123");
        validIBANsOfEveryCountry.add("EE471000001020145685");
        validIBANsOfEveryCountry.add("FO9264600123456789");
        validIBANsOfEveryCountry.add("FI1410093000123458");
        validIBANsOfEveryCountry.add("FR7630006000011234567890189");
        validIBANsOfEveryCountry.add("GE60NB0000000123456789");
        validIBANsOfEveryCountry.add("DE75512108001245126199");
        validIBANsOfEveryCountry.add("GI04BARC000001234567890");
        validIBANsOfEveryCountry.add("GR9608100010000001234567890");
        validIBANsOfEveryCountry.add("GL8964710123456789");
        validIBANsOfEveryCountry.add("GT20AGRO00000000001234567890");
        validIBANsOfEveryCountry.add("VA59001123000012345678");
        validIBANsOfEveryCountry.add("HU93116000060000000012345676");
        validIBANsOfEveryCountry.add("IS750001121234563108962099");
        validIBANsOfEveryCountry.add("IQ20CBIQ861800101010500");
        validIBANsOfEveryCountry.add("IE64IRCE92050112345678");
        validIBANsOfEveryCountry.add("IL170108000000012612345");
        validIBANsOfEveryCountry.add("IT60X0542811101000000123456");
        validIBANsOfEveryCountry.add("JO71CBJO0000000000001234567890");
        validIBANsOfEveryCountry.add("KZ563190000012344567");
        validIBANsOfEveryCountry.add("XK051212012345678906");
        validIBANsOfEveryCountry.add("KW81CBKU0000000000001234560101");
        validIBANsOfEveryCountry.add("LV97HABA0012345678910");
        validIBANsOfEveryCountry.add("LB92000700000000123123456123");
        validIBANsOfEveryCountry.add("LI7408806123456789012");
        validIBANsOfEveryCountry.add("LT601010012345678901");
        validIBANsOfEveryCountry.add("LU120010001234567891");
        validIBANsOfEveryCountry.add("MT31MALT01100000000000000000123");
        validIBANsOfEveryCountry.add("MR1300020001010000123456753");
        validIBANsOfEveryCountry.add("MU43BOMM0101123456789101000MUR");
        validIBANsOfEveryCountry.add("MD21EX000000000001234567");
        validIBANsOfEveryCountry.add("MC5810096180790123456789085");
        validIBANsOfEveryCountry.add("ME25505000012345678951");
        validIBANsOfEveryCountry.add("NL02ABNA0123456789");
        validIBANsOfEveryCountry.add("MK07200002785123453");
        validIBANsOfEveryCountry.add("NO8330001234567");
        validIBANsOfEveryCountry.add("PK36SCBL0000001123456702");
        validIBANsOfEveryCountry.add("PS92PALS000000000400123456702");
        validIBANsOfEveryCountry.add("PL10105000997603123456789123");
        validIBANsOfEveryCountry.add("PT50002700000001234567833");
        validIBANsOfEveryCountry.add("QA54QNBA000000000000693123456");
        validIBANsOfEveryCountry.add("RO09BCYP0000001234567890");
        validIBANsOfEveryCountry.add("LC14BOSL123456789012345678901234");
        validIBANsOfEveryCountry.add("SM76P0854009812123456789123");
        validIBANsOfEveryCountry.add("ST23000200000289355710148");
        validIBANsOfEveryCountry.add("SA4420000001234567891234");
        validIBANsOfEveryCountry.add("RS35105008123123123173");
        validIBANsOfEveryCountry.add("SC52BAHL01031234567890123456USD");
        validIBANsOfEveryCountry.add("SK8975000000000012345671");
        validIBANsOfEveryCountry.add("SI56192001234567892");
        validIBANsOfEveryCountry.add("ES7921000813610123456789");
        validIBANsOfEveryCountry.add("SE7280000810340009783242");
        validIBANsOfEveryCountry.add("CH5604835012345678009");
        validIBANsOfEveryCountry.add("TL380010012345678910106");
        validIBANsOfEveryCountry.add("TN5904018104004942712345");
        validIBANsOfEveryCountry.add("TR320010009999901234567890");
        validIBANsOfEveryCountry.add("UA903052992990004149123456789");
        validIBANsOfEveryCountry.add("AE460090000000123456789");
        validIBANsOfEveryCountry.add("GB33BUKB20201555555555");
        validIBANsOfEveryCountry.add("VG21PACG0000000123456789");

        for (String IBAN : validIBANsOfEveryCountry) {
            boolean actual = validator.validate(IBAN);
            assertEquals(true, actual);
        }

    }

}