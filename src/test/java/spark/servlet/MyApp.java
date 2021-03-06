package spark.servlet;

import static spark.Spark.*;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;

public class MyApp implements SparkApplication {

    @Override
    public void init() {
        System.out.println("HELLO!!!");
        before("/protected/*", (request, response) -> {
            halt(401, "Go Away!");
        });

        get("/hi", (request, response) -> {
            return "Hello World!";
        });

        get("/:param", (request, response) -> {
            return "echo: " + request.params(":param");
        });

        get("/", (request, response) -> {
            return "Hello Root!";
        });

        post("/poster", (request, response) -> {
            String body = request.body();
            response.status(201); // created
            return "Body was: " + body;
        });

        after("/hi", (request, response) -> {
            response.header("after", "foobar");
        });

        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

}
