package api;

import api.dao.IBAN;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import model.Validator;
import java.util.Arrays;

import static spark.Spark.*;

public class IBANController {


    private static Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();

    static {
        port(8080);

        post("/api/validate", "application/json", (request, response) -> {

            IBAN[] ibans = gson.fromJson(request.body(), IBAN[].class);

            Arrays.stream(ibans)
                    .forEach(item -> {
                        boolean isValid = Validator.getInstance()
                                .validate(item.getIban());
                        if (item.getIban() != null)
                            item.setValid(isValid);
                    });

            String json = gson.toJson(ibans, IBAN[].class);
            response.type("application/json");

            return json;
        });

        exception(MalformedJsonException.class, (exception, request, response) -> {
            response.status(400);
            response.body("Malformed JSON, please check your input list.");

        });

        exception(JsonSyntaxException.class, (exception, request, response) -> {
            response.status(400);
            response.body("JSON Syntax is not valid, please check your input list.");
        });

    }

}



