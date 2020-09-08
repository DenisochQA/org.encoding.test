import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.path.xml.XmlPath.*;

public class TestsAPI {

    RequestSpecification requestSpec = given()
            .baseUri("https://status.encoding.com");

    
    @Test
    public void testJsonRequest() {

        requestSpec.when()
                .get("status.php?format=json")
                .then()
                .assertThat()
                .body("status", equalTo("Ok"), "incident_count.lastYear", equalTo("1"),
                        "uptime", greaterThan(86400));

    }

    @Test
    public void testXmlRequest() {
        requestSpec.when()
                .get("status.php?format=xml")
                .then()
                .assertThat()
                .body("response.status", equalTo("Ok"), "response.incident_count.lastYear", equalTo("1"));

        String str = requestSpec
                .when()
                .get("status.php?format=xml")
                .thenReturn()
                .asString();

        XmlPath xml = new XmlPath(str);
        int currentValue = xml.getInt("response.uptime");
        Assert.assertTrue(currentValue > 86400);
    }
}





