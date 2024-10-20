package testPackage;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class TestCases {


    @Test (description = "Login with valid data")
    public void testCase1() {

        JSONObject json = new JSONObject();
        json.put("email","merchant@foodics.com");
        json.put("password", "123456");
        given()
                .contentType("application/json")
                .when()
                .post("https://pay2.foodics.dev/cp_internal/login")
                .then()
                .log().body();

    }

    @Test (description = "Login with invalid email format")
    public void testCase2 (){
        JSONObject json = new JSONObject();
        json.put("email","merchant@foo");
        json.put("password", "123456");
        given()
                .contentType("application/json")
                .post("https://pay2.foodics.dev/cp_internal/login")
                .then()
                .assertThat().statusCode(400);

    }

    @Test (description = "Login with wrong password")
    public void testCase3 (){
        JSONObject json = new JSONObject();
        json.put("email","merchant@foodics.com");
        json.put("password", "12300");
        given().header("Accept", "*/*")
                .contentType("application/json")
                .post("https://pay2.foodics.dev/cp_internal/login").then()
                .statusCode(400).log().all();


    }

}
