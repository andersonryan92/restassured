import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test01_Get {

    @Test
    void test_01(){

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    void test_02() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .body("data.id[0]", equalTo(7));
    }

}
