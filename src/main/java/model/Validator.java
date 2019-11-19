package model;

import util.IBANRegexParser;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public final class Validator {

    private Validator() {
    }

    public static Validator getInstance() {
        return ValidatorHolder.instance;
    }

    private static class ValidatorHolder {

        private static final Validator instance = new Validator();
    }

    /**
     * Country codes and IBAN Structure data are taken from:
     * https://www.swift.com/standards/data-standards/iban
     */
    private Map<String, String> countryCodeToStruct = Map.ofEntries(
            entry("AD", "AD2!n4!n4!n12!c"),
            entry("AE", "AE2!n3!n16!n"),
            entry("AL", "AL2!n8!n16!c"),
            entry("AT", "AT2!n5!n11!n"),
            entry("AZ", "AZ2!n4!a20!c"),
            entry("BA", "BA2!n3!n3!n8!n2!n"),
            entry("BE", "BE2!n3!n7!n2!n"),
            entry("BG", "BG2!n4!a4!n2!n8!c"),
            entry("BH", "BH2!n4!a14!c"),
            entry("BR", "BR2!n8!n5!n10!n1!a1!c"),
            entry("BY", "BY2!n4!c4!n16!c"),
            entry("CH", "CH2!n5!n12!c"),
            entry("CR", "CR2!n4!n14!n"),
            entry("CY", "CY2!n3!n5!n16!c"),
            entry("CZ", "CZ2!n4!n6!n10!n"),
            entry("DE", "DE2!n8!n10!n"),
            entry("DK", "DK2!n4!n9!n1!n"),
            entry("DO", "DO2!n4!c20!n"),
            entry("EE", "EE2!n2!n2!n11!n1!n"),
            entry("EG", "EG2!n3!n3!n17!n"),
            entry("ES", "ES2!n4!n4!n1!n1!n10!n"),
            entry("FI", "FI2!n3!n11!n"),
            entry("FO", "FO2!n4!n9!n1!n"),
            entry("FR", "FR2!n5!n5!n11!c2!n"),
            entry("GB", "GB2!n4!a6!n8!n"),
            entry("GE", "GE2!n2!a16!n"),
            entry("GI", "GI2!n4!a15!c"),
            entry("GL", "GL2!n4!n9!n1!n"),
            entry("GR", "GR2!n3!n4!n16!c"),
            entry("GT", "GT2!n4!c20!c"),
            entry("HR", "HR2!n7!n10!n"),
            entry("HU", "HU2!n3!n4!n1!n15!n1!n"),
            entry("IE", "IE2!n4!a6!n8!n"),
            entry("IL", "IL2!n3!n3!n13!n"),
            entry("IQ", "IQ2!n4!a3!n12!n"),
            entry("IS", "IS2!n4!n2!n6!n10!n"),
            entry("IT", "IT2!n1!a5!n5!n12!c"),
            entry("JO", "JO2!n4!a4!n18!c"),
            entry("KW", "KW2!n4!a22!c"),
            entry("KZ", "KZ2!n3!n13!c"),
            entry("LB", "LB2!n4!n20!c"),
            entry("LC", "LC2!n4!a24!c"),
            entry("LI", "LI2!n5!n12!c"),
            entry("LT", "LT2!n5!n11!n"),
            entry("LU", "LU2!n3!n13!c"),
            entry("LV", "LV2!n4!a13!c"),
            entry("MC", "MC2!n5!n5!n11!c2!n"),
            entry("MD", "MD2!n2!c18!c"),
            entry("ME", "ME2!n3!n13!n2!n"),
            entry("MK", "MK2!n3!n10!c2!n"),
            entry("MR", "MR2!n5!n5!n11!n2!n"),
            entry("MT", "MT2!n4!a5!n18!c"),
            entry("MU", "MU2!n4!a2!n2!n12!n3!n3!a"),
            entry("NL", "NL2!n4!a10!n"),
            entry("NO", "NO2!n4!n6!n1!n"),
            entry("PK", "PK2!n4!a16!c"),
            entry("PL", "PL2!n8!n16!n"),
            entry("PS", "PS2!n4!a21!c"),
            entry("PT", "PT2!n4!n4!n11!n2!n"),
            entry("QA", "QA2!n4!a21!c"),
            entry("RO", "RO2!n4!a16!c"),
            entry("RS", "RS2!n3!n13!n2!n"),
            entry("SA", "SA2!n2!n18!c"),
            entry("SC", "SC2!n4!a2!n2!n16!n3!a"),
            entry("SE", "SE2!n3!n16!n1!n"),
            entry("SI", "SI2!n5!n8!n2!n"),
            entry("SK", "SK2!n4!n6!n10!n"),
            entry("SM", "SM2!n1!a5!n5!n12!c"),
            entry("ST", "ST2!n4!n4!n11!n2!n"),
            entry("SV", "SV2!n4!a20!n"),
            entry("TL", "TL2!n3!n14!n2!n"),
            entry("TN", "TN2!n2!n3!n13!n2!n"),
            entry("TR", "TR2!n5!n1!n16!c"),
            entry("UA", "UA2!n6!n19!c"),
            entry("VA", "VA2!n3!n15!n"),
            entry("VG", "VG2!n4!a16!n"),
            entry("XK", "XK2!n4!n10!n2!n"));


    private HashMap<String, Pattern> countryCodeToRegex = new HashMap<>();

    public boolean validate(String IBAN) {

        if (IBAN == null || IBAN.length() < 4 || IBAN.length() > 34) return false;

        String countryCode = IBAN.substring(0, 2);

        Pattern regex = countryCodeToRegex.get(countryCode);

        if (regex == null) {

            String IBANStruct = countryCodeToStruct.get(countryCode);

            if (IBANStruct == null) {
                return false;
            }

            regex = IBANRegexParser.getInstance()
                    .parseIBANStructure(IBANStruct);

            countryCodeToRegex.put(countryCode, regex);
        }

        boolean checkDigits = mod97Check(IBAN);
        if (!checkDigits) return false;

        boolean checkStructure = regex.matcher(IBAN).matches();
        if (!checkStructure) return false;

        return true;

    }

    private boolean mod97Check(String IBAN) {

        String rearrangedString = IBAN.substring(4) + IBAN.substring(0, 4);

        String stringed = rearrangedString.chars()
                .mapToObj(i -> {
                    int numValue = Character.getNumericValue((char) i);
                    return Integer.toString(numValue);
                })
                .collect(Collectors.joining());

        BigInteger number = new BigInteger(stringed);
        int res = number.remainder(new BigInteger("97")).intValue();

        return res == 1;
    }
}
