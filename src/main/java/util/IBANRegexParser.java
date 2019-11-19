package util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates a RegEx Pattern
 * from given IBAN Structure
 */
public final class IBANRegexParser {

    private IBANRegexParser() {
    }

    public static IBANRegexParser getInstance() {
        return IBANRegexParserHolder.instance;
    }

    private static final class IBANRegexParserHolder {
        private static final IBANRegexParser instance = new IBANRegexParser();
    }

    private Pattern IBANStructPattern = Pattern.compile("\\d{1,2}!(a|n|c)");
    private Pattern intPattern = Pattern.compile("^\\d{1,2}");


    public Pattern parseIBANStructure(String IBANStruct) {

        StringBuilder regexStringBuilder = new StringBuilder("^" + IBANStruct.substring(0, 2));
        Matcher IBANStructMatcher = IBANStructPattern.matcher(IBANStruct);

        while (IBANStructMatcher.find()) {
            String group = IBANStructMatcher.group();
            regexStringBuilder.append("(");

            char charType = group.charAt(group.length() - 1);
            switch (charType) {
                case 'a':
                    regexStringBuilder.append("[A-Z]");
                    break;
                case 'n':
                    regexStringBuilder.append("\\d");
                    break;
                case 'c':
                    regexStringBuilder.append("[a-zA-Z0-9]");
                    break;
            }

            Matcher intMatcher = intPattern.matcher(group);

            if (intMatcher.find()) {
                String lengthOfGroup = intMatcher.group();
                regexStringBuilder.append("{" + lengthOfGroup + "}");
                regexStringBuilder.append(")");
            }
        }

        regexStringBuilder.append("$");

        return Pattern.compile(regexStringBuilder.toString());
    }

}
