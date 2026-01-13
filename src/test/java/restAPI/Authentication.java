package restAPI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;

import static io.restassured.RestAssured.*;
public class Authentication {
    @DataProvider(name="userData")
    public Object[][] createUserData(){
        return new Object[][]{
                {"postman","password"},
                {"postman","password"}
        };
    }

    @Test(dataProvider = "userData")
    public void basicAuthentication(String username, String password){
        given()
                .auth().basic(username,password)
                .contentType(ContentType.JSON)
                .baseUri("https://www.postman-echo.com/basic-auth")
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void basicAuthenticationUsingSpec(){
        given()
                .spec(getBasicAuthentication())
                .contentType(ContentType.JSON)
                .baseUri("https://www.postman-echo.com/basic-auth")
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);
    }

    public static RequestSpecification getBasicAuthentication() {
        System.out.println("Basic auth retrieves as Request Specification from method");
        return given().auth().basic("postman","password");
    }
@Test
    public void apiKey(){
        String apiKey = "0f940045bc3074907401b4b5ab2eebdd";
        given()
                .log()
                .all()
                .queryParam("q","Exton")
                .queryParam("appid",apiKey)
                .baseUri("https://api.openweathermap.org/data/2.5/weather")
                .when()
                .get()
                .prettyPrint();
    System.out.println(baseURI + ":" + port + basePath);
    }
@Test
    public void bearerToken(){
    String token = System.getenv("GIT_TOKEN");
        given()
                .header("Authorization","Bearer " + token)
                .baseUri("https://api.github.com/user/repos")
                .when()
                .get()
                .prettyPrint();
    }
@Test
    public void oAuth2(){
    String token = System.getenv("GIT_TOKEN");
        given()
                .auth().oauth2(token)
                .baseUri("https://api.github.com/user/repos")
                .when()
                .get()
                .prettyPrint();

    }
}
