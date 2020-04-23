import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Tests_POST {

    private static RequestSpecification requestSpec;


    @BeforeClass
    public static void createRequestSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }

    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    }

//    @Test
////    public void testPostOne(){
////        Map<String, Object> map = new HashMap<String, Object>();
////        map.put("name", "Ryan");
////        map.put("Job", "Engineer");
////
////        System.out.println(map);
////
////        JSONObject request = new JSONObject();
////
////        request.put("name","Ryan");
////        request.put("job", "engineer");
////
////        given()
////                .body(request.toJSONString())
////                .when()
////                .post("")
////                .then()
////                .statusCode(200);
////
////    }

    @Test
    public void requestUsZipCode90210(){
        given().
                spec(requestSpec).
                when().
                get("us/90210").
                then().
                assertThat().
                statusCode(200);
    }


    @Test(dataProvider = "zipCodesAndPlaces")
    public void requestZipCodesFromCollection(String countryCode, String zipCode, String expectedPlaceName){

        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().
                get("http://zippopotam.us/{countryCode}/{zipCode}").
                then().
                log().all().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }

    
}
