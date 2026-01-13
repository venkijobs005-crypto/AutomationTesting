package restAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import static io.restassured.RestAssured.given;

public class ParseJson {

    @Test
    public void parseUsingJsonPath(){
       Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("page",2)
                .baseUri("https://reqres.in/")
                .when()
                .get("api/users");
        System.out.println(response.jsonPath().get("data[0].last_name").toString());
    }

    @Test
    public void parseUsingJsonPathSingleResource(){
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParams("userId",2)
                .baseUri("https://reqres.in/")
                .when()
                .get("api/unknown/{userId}");
        System.out.println(response.jsonPath().get("data.name").toString());
    }

    @Test
    public void parseUsingJsonObject(){
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("page",2)
                .baseUri("https://reqres.in/")
                .when()
                .get("api/users");
        JSONObject jo = new JSONObject(response.asString());
        List<String> firstNames = new ArrayList<>();
        for(int i =0; i<jo.getJSONArray("data").length();i++){
            firstNames.add(jo.getJSONArray("data").getJSONObject(i).get("first_name").toString());
        }
        firstNames.forEach(System.out::println);
    }

    @Test
    public void verifyValueFromBody(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("page",2)
                .baseUri("https://reqres.in/")
                .when()
                .get("api/users")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data[0].last_name", Matchers.equalTo("Lawson"));
    }

    @Test
    public void compare2Jsons() throws JsonProcessingException {
        String json1 = "{\n" +
                "  \"data\": {\n" +
                "    \"id\": 2,\n" +
                "    \"email\": \"janet.weaver@reqres.in\",\n" +
                "    \"first_name\": \"Janet\",\n" +
                "    \"last_name\": \"Weaver\",\n" +
                "    \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" +
                "  }\n" +
                " }";
        String json2 = "{\n" +
                "  \"data\": {\n" +
                "    \"id\": 2,\n" +
                "    \"email\": \"janet.weaver@reqres.in\",\n" +
                "    \"first_name\": \"Janet\",\n" +
                "    \"last_name\": \"Weaver\",\n" +
                "    \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" +
                "  }\n" +
                " }";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonVal1 = mapper.readTree(json1);
        JsonNode jsonVal2 = mapper.readTree(json2);
        System.out.println(jsonVal1.equals(jsonVal2));
    }

    public void compare2JsonsUsingJsonSchemaValidator() throws JsonProcessingException {
        given()
                .baseUri("")
                .when()
                .get()
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(""));
    }
    @Test
    public void parseUsingMapTypeRef(){
        Map<String,Object> map = given()
                .contentType(ContentType.JSON)
                .queryParam("page",2)
                .baseUri("https://reqres.in/")
                .when()
                .get("api/users")
                        .as(new TypeRef<Map<String, Object>>() {
                        });
        String lastNm = (String) map.get("data");
        System.out.println(lastNm);
        map.keySet().forEach(k->System.out.println(k));
    }

    @Test
    public void javaObjToJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = new Employee("sachin", "mh", "hr","lead");
        try {
            objectMapper.writeValue(new File("target/emp.json"),employee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(objectMapper.writeValueAsString(employee));

        //To read the value from json using readTree method
        try {
            JsonNode jnode = objectMapper.readTree(objectMapper.writeValueAsString(employee));
            System.out.println("City retrieved from jnode as : " + jnode.get("city").asText());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void jsonToJavaObj(){
        Employee emp = null;
        ObjectMapper objectMapper = new ObjectMapper();
        String empJson = "{\"name\":\"venkat\",\"city\":\"tamilnadu\",\"department\":\"it\",\"designation\":\"lead\"}";
        try {
            emp = objectMapper.readValue(empJson,Employee.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(emp.getName());
        System.out.println(emp.getCity());
        System.out.println(emp.getDepartment());
        System.out.println(emp.getDesignation());

        //To read the value from json using readTree method
        try {
            JsonNode jnode = objectMapper.readTree(empJson);
            System.out.println("City retrieved from jnode as : " + jnode.get("city").asText());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void javaArrObjToJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee [] employee = new Employee[3];
        employee[0] = new Employee("sachin", "mh", "hr","lead");
        employee[1] = new Employee("dhoni", "jr", "manage","lead");
        employee[2] = new Employee("virat", "dl", "finance","senior");

        try {
            objectMapper.writeValue(new File("target/emp.json"),employee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(objectMapper.writeValueAsString(employee));
    }

    @Test
    public void jsonArrToJavaObj(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,true);
        String empJson = "[{ \"name\": \"sachin\", \"city\": \"mh\", \"department\": \"hr\", \"designation\": \"lead\" }, { \"name\": \"dhoni\", \"city\": \"jr\", \"department\": \"manage\", \"designation\": \"lead\" }, { \"name\": \"virat\", \"city\": \"dl\", \"department\": \"finance\", \"designation\": \"senior\" }]";
        try {
            Employee [] newEmp = objectMapper.readValue(empJson,Employee[].class);
            System.out.println(objectMapper.writeValueAsString(newEmp));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void typeRefExample() throws JsonProcessingException {
        String simpleJson = "{\"name\":\"venkat\",\"city\":\"tamilnadu\",\"department\":\"it\",\"designation\":\"lead\"}";
        String jsonArr = "[{ \"name\": \"sachin\", \"city\": \"mh\", \"department\": \"hr\", \"designation\": \"lead\" }, { \"name\": \"dhoni\", \"city\": \"jr\", \"department\": \"manage\", \"designation\": \"lead\" }, { \"name\": \"virat\", \"city\": \"dl\", \"department\": \"finance\", \"designation\": \"senior\" }]";
        ObjectMapper objectMapper = new ObjectMapper();
        Employee [] empArr = objectMapper.readValue(jsonArr, new TypeReference<Employee[]>() {})       ;
        List<Employee> listArr = objectMapper.readValue(jsonArr, new TypeReference<List<Employee>>() {})       ;
        Map<String,String> map = objectMapper.readValue(simpleJson, new TypeReference<Map<String,String>>() {})       ;
        Map<String,String> map1 = objectMapper.readValue(simpleJson, new TypeReference<Map<String,String>>() {})       ;
//        System.out.println(objectMapper.writeValueAsString(empArr));
//        System.out.println(objectMapper.writeValueAsString(empArr[1].getDepartment()));
//        System.out.println(objectMapper.writeValueAsString(listArr));
//        System.out.println(objectMapper.writeValueAsString(listArr.get(0).getName()));
//        System.out.println(objectMapper.writeValueAsString(map));
//        System.out.println(objectMapper.writeValueAsString(map.get("name")));
//        listArr.forEach(System.out::println);
        for(Employee all : listArr){
            System.out.println(all.getName());
        }

    }
}
