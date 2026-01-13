package restAPI;

import static io.restassured.RestAssured.*;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class RestSampleTests {
    @Test
    public void SampleGetTest(){
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId",2)
                .baseUri("https://reqres.in/")
                .when()
                .get("api/users/{userId}")
                .prettyPrint();
    }

    @Test
    public void SampleGetTestUsingRequest(){
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId",2)
                .baseUri("https://reqres.in/")
                .when()
                .request(Method.GET,"api/users/{userId}")
                .prettyPrint();
    }

    @Test
    public void getAllBookingAndValidate(){
        Response response = given()
                .contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Content-Type", "application/json; charset=utf-8")
                .extract().response();
        System.out.println(response.getBody().asString());
        System.out.println(response.getTime());
    }

    @Test
    public void cookiesSet(){
        given()
                .cookies("Cookie1","API")
                .baseUri("https://www.postman-echo.com/cookies/set")
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void SamplePostTest(){
        String requestBody ="{\n" +
                "    \"name\": \"venkat\",\n" +
                "    \"job\": \"test lead\"\n" +
                "}";
        Response response = given()
                .contentType(ContentType.JSON)
                .baseUri("https://reqres.in/")
                .body(requestBody)
                .when()
                    .post("api/users");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(201)
                .body("name", Matchers.equalTo("venkat"));
        System.out.println(response.jsonPath().get("id").toString());
    }

    @Test
    public void blacklistHeader(){
        given().config(config().logConfig(LogConfig.logConfig().blacklistHeader("Content-Type")))
                .contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .log().all()
                .when()
                .get();
    }
    @Test
    public void maskSensitiveData(){
        String token = System.getenv("GIT_TOKEN");
        RequestSpecification reqSpec = given()
                .header("Authorization","Bearer " + token)
                .baseUri("https://api.github.com/user/repos")
                        .filter((req, res , ctx)->{
                            //Password is masked here
                            if(req.getBody()!=null){
                                String body = req.getBody().toString();
                                body = body.replaceAll("\"password\":\"[^\"]*\"", "\"password\":\"*****\"");
                                req.body(body);
                            }
                            //Header is masked here
                            if (req.getHeaders().hasHeaderWithName("Authorization")) {
                                req.removeHeader("Authorization");
                                req.header("Authorization", "*****");
                            }
                            return ctx.next(req, res);
                        }).log().all();

        reqSpec.contentType(ContentType.JSON)
                .when()
                .get()
                .prettyPrint();
    }
    @Test
    public void RequestSpecBuilderTest(){
    String requestBody ="{\n" +
            "    \"name\": \"venkat\",\n" +
            "    \"job\": \"test lead\"\n" +
            "}";
    //RequestSpecification
    RequestSpecification reqSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/")
            .addHeader("Content-Type", "application/json")
            .setContentType(ContentType.JSON)
            .build();

    //ResponseSpecification
    ResponseSpecification resSpec = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .expectStatusCode(201)
            .build();
    //invoke request and response specifications
    given(reqSpec)
            .body(requestBody)
            .when()
            .post("api/users")
            .then()
            .spec(resSpec);
        Json.parse("");
    }
}
