package restAPI;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import static io.restassured.RestAssured.given;

public class MyITestContextTest {
    @Test
    public void ChainingUsingITestContext(ITestContext context){
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
        context.setAttribute("name",response.jsonPath().get("name"));
        context.setAttribute("job",response.jsonPath().get("job"));
        context.setAttribute("id",response.jsonPath().getInt("id"));
        System.out.println((int) context.getAttribute("id"));
        System.out.println((String) context.getAttribute("name"));
        System.out.println((String) context.getAttribute("job"));
        //Chaining from previous response
        int id = (int) context.getAttribute("id");
        Response res = given()
                .contentType(ContentType.JSON)
                .pathParams("userId",id)
                .baseUri("https://reqres.in/")
                .when()
                .request(Method.GET,"api/users/{userId}");
        System.out.println(res.getStatusCode());
    }

}
