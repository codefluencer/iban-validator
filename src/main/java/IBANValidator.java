
import api.IBANController;
import model.Validator;
import org.apache.commons.cli.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;



public class IBANValidator {

   public static void main(String[] args) {


      IBANController controller = new IBANController();

      CommandLineParser parser = new DefaultParser();
      Options options = new Options();

      Option ibanOpt = new Option("i", "iban", false,"Pass up to 10 IBAN numbers separated by comma.");
      ibanOpt.setValueSeparator(',');
      ibanOpt.setArgs(10);

      options.addOption(ibanOpt);
      options.addOption("f", "file", true,"Path to *.txt file, where each row has one IBAN number.");

      HelpFormatter formatter = new HelpFormatter();

      try {
         CommandLine line = parser.parse(options, args);
         handleOptions(line);
      }
      catch(ParseException exp) {
         formatter.printHelp("java -jar SEB-1.0.jar", options);
      }

   }

   private static void handleOptions(CommandLine line) {

      if (line.hasOption("i")) {
         String[] ibans = line.getOptionValues("i");
         validateIBANArgs(ibans);
      }

      if (line.hasOption("f")) {
         String filePath = line.getOptionValue("f");
         validateAndWriteToOutputFile(filePath);
      }
   }

   private static void validateAndWriteToOutputFile(String filePath) {

      Path path = Paths.get(filePath);
      Stream<String> lines = readLinesFromFile(path);

      ArrayList<String> outputResult = new ArrayList<>();
      Validator validator = Validator.getInstance();

      lines.forEach( item -> {
         item = item.replaceAll("\\s+", "");
         boolean res = validator.validate(item);
         outputResult.add(item + ";" + res);
      });

      String fileName = path.getFileName().toString();
      fileName = fileName.substring(0, fileName.length() - 3) + "out";

      try {
         Files.write(Paths.get(fileName), outputResult);
      } catch (IOException e) {
         throw new RuntimeException("Error writing to file " + Paths.get(fileName));
      }
   }

   private static Stream<String> readLinesFromFile(Path filePath) {

      Stream<String> lines;

      try {
         lines = Files.lines(filePath);
      } catch (IOException e) {
         throw new RuntimeException("Error reading file with path: " + filePath.toString());
      }

      return lines;
   }

   private static void validateIBANArgs(String[] ibans) {
      Validator validator = Validator.getInstance();

      System.out.println("------------------------------------------------------------------------");
      for (String iban : ibans) {
         boolean res = validator.validate(iban);
         System.out.println(iban + " is " + (res ? "valid!" : "not valid!"));
      }
      System.out.println("------------------------------------------------------------------------");
   }

}


