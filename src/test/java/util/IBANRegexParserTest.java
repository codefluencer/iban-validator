package util;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IBANRegexParserTest {

    /**
     * IBAN Parser Test
     * Passed arg is never NULL,
     * Passed arg is only taken from {@link model.Validator#countryCodeToStruct)}
     */

    @Test
    public void parseIBANStructure() {
        IBANRegexParser parser = IBANRegexParser.getInstance();
        String actualPattern = parser.parseIBANStructure("LT2!n5!n11!n").pattern();
        assertEquals( "^LT(\\d{2})(\\d{5})(\\d{11})$", actualPattern);
    }
}