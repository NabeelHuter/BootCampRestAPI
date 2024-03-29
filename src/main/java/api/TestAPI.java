package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestAPI {



    public String baseURI = RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
    String employees = "employees";
    String employeeWithId = "employee/";
    String create = "create";
    String update = "update/";
    String delete = "delete/";


    @Test
    public void getAllEmployees() {
        Response response = given().when().get(employees).then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath.get("employee_name"));


    }


    @Test
    public void getOneEmployee() {
        Response response = given().when().get(employeeWithId + 8198).then().assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());

        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(jsonPath.get("employee_name"));

}

    @Test
    public void testPostData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "angulartesting692");
        jsonObject.put("salary", "696999");
        jsonObject.put("age", "699");

        Response response = given().header("Content-Type", "application/json")
                .body(jsonObject.toString()).when().post(create).then().statusCode(200).extract().response();
        System.out.println(response.asString());
    }

    @Test
    public void testPutCall() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Random");
        jsonObject.put("salary", "99000");
        jsonObject.put("age", "29");

        Response response = given().contentType(ContentType.JSON).body(jsonObject.toString()).put(update + 2063);
        System.out.println(response.getStatusCode());

    }
    @Test
    public void testDelete() {
        Response response = given().contentType(ContentType.JSON).delete(delete + 2063);
        System.out.println(response.statusCode());
    }
}


