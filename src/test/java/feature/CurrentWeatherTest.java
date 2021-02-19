package feature;

import annotations.Epic;
import annotations.Jira;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@Epic("Weather Service")
@Jira(id = "Jira-321", title = "Current weather data")
public class CurrentWeatherTest {
    @Test
    void getCurrentWeatherForMoscow(){
        /*
        Positive case for Moscow
        GET: http://api.openweathermap.org/data/2.5/weather?q=moscow&appid=915a3560d7449be2a823ec84cc877d86
        RESPONSE:
            {
                "weather": "clear sky",
                "timezone": 10800,
                "id": 524901,
                "name": "Moscow",
                "cod": 200
            }
         */
        String city = "Moscow";
        String url = "http://api.openweathermap.org/data/2.5/weather";
        Response response = given().log().all()
                .param("q",city)
                .param("appid","915a3560d7449be2a823ec84cc877d86")
                .when()
                .get(url)
                .then()
                .log().all()
                .extract().response();
        String cityName = response.getBody().jsonPath().getString("name");
        assertThat("Incorrect city name", cityName.equals(city));
    }
    @Test
    void getCurrentWeatherForChicago(){
        /*
        Positive case for Chicago
        GET: http://api.openweathermap.org/data/2.5/weather?q=chicago&appid=915a3560d7449be2a823ec84cc877d86
        RESPONSE:
            {
                "weather": "clear sky",
                "timezone": 10800,
                "id": 524901,
                "name": "Moscow",
                "cod": 200
            }
         */
        String city = "Chicago";
        String url = "http://api.openweathermap.org/data/2.5/weather";
        Response response = given().log().all()
                .param("q",city)
                .param("appid","915a3560d7449be2a823ec84cc877d86")
                .when()
                .get(url)
                .then()
                .log().all()
                .extract().response();
        String cityName = response.getBody().jsonPath().getString("name");
        assertThat("Incorrect city name", cityName.equals(city));
    }

    @Test
    @Disabled
    void getCurrentWeatherWithMissingToken(){
        /*
        Negative case with missing token
        GET: http://api.openweathermap.org/data/2.5/weather?q=moscow
        RESPONSE:
            {
                "cod": 401,
                "message": "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."
            }
         */
    }

    @Test
    @Disabled
    void getCurrentWeatherWithInvalidToken(){
        /*
        Negative case with invalid token
        GET: http://api.openweathermap.org/data/2.5/weather?q=moscow&appid=915a3560d7449be2a823ec84cc877d861231
        RESPONSE:
            {
                "weather": [
                    {
                        "id": 800,
                        "main": "Clear",
                        "description": "clear sky",
                        "icon": "01d"
                    }
                ],
                "timezone": 10800,
                "id": 524901,
                "name": "Moscow",
                "cod": 200
            }
         */
    }

    @Test
    @Disabled
    void getCurrentWeatherWithInvalidCityName(){
        /*
        Negative case with invalid city name
        GET: http://api.openweathermap.org/data/2.5/weather?q=kqwbekqwbekjqwbekj&appid=915a3560d7449be2a823ec84cc877d86
        RESPONSE:
            {
                "cod": "404",
                "message": "city not found"
            }
         */
    }
}
