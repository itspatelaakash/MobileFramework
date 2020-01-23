package utilities;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.equalTo;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ApiRunner {
    static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";


    public String GetUTCdatetimeAsString() {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar cal = Calendar.getInstance();
        String ap= sdf.format(cal.getTime());
        final String utcTime = sdf.format(new Date());


        return utcTime;
    }


    @Test
    public void firstApi() {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date());

        RestAssured.baseURI = "http://216.10.245.166";
        given().log().all().
                param("key", "qaclick123").
                when().post("maps/api/place/add/json").
                then().assertThat().statusCode(200);
    }

    /**---------------------------Rest Assured practice---------------------------------------------------------*/

    //End to end api operation creating place->extracting responce->deleting responce
    @Test
    public void createPlaceAPI() {
        String s = "Google Shoes!";
        String b = "{" +

                //use jSON escape to convert JSON to String automatically
                "\"location\": {" +

                "\"lat\": -33.8669710," +

                "\"lng\": 151.1958750" +

                "}," +

                "\"accuracy\": 50," +

                "\"name\": \"" + s + "\"," + //Passing Variable in body

                "\"phone_number\": \"(02) 9374 4000\"," +

                "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," +

                "\"types\": [\"shoe_store\"]," +

                "\"website\": \"http://www.google.com.au/\"," +

                "\"language\": \"en-AU\"" +

                "}";

        // --------------------here actual api is :- http://216.10.245.166/maps/api/place/add/json?key=qaclick123

        RestAssured.baseURI = "http://216.10.245.166";//Base URL
        Response res = given().log().all().//logging request parameters

                queryParam("key", "qaclick123"). //Parameter

                body(b).

                when().

                post("/maps/api/place/add/json"). //Resource

                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().

                body("status", equalTo("OK")).log().all()

                .extract().response(); // Extracting  response in 'res' variable

        //Printing response by converting raw response to string
        String responseString = res.asString();
        System.out.println(responseString);

        //Extracting value from response string by converting it to JSON format to travel to required object in JSON
        JsonPath js = new JsonPath(responseString);//converts string to JSON
        String placeId = js.get("place_id"); // Grabbing place_id
        System.out.println(placeId);

        //Deleting place_id
        //Delete api is :- http://216.10.245.166/maps/api/place/delete/json?key=qaclick123
        given().
                queryParam("key", "qaclick123").
                body("{" +
                        "\"place_id\":\""+placeId+"\""+
                      "}").
                when().
                post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));



    }

    @Test
    public void deletePlaceApi(){



    }
    /**---------------------------Rest Assured practice---------------------------------------------------------*/

    @Test

    public void createTournamnet() throws Exception {

        String tournamentName = RandomUtility.generateRandomString(5, RandomUtility.Mode.ALPHANUMERIC);

        final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar cal = Calendar.getInstance();
        String listingLiveDate= sdf.format(cal.getTime());
        cal.add(Calendar.MINUTE, 1);
        String registrationStartTime = sdf.format(cal.getTime());
        cal.add(Calendar.MINUTE, 1);
        String registrationEndTime = sdf.format(cal.getTime());
        cal.add(Calendar.MINUTE, 1);
        String tournamentStartTime = sdf.format(cal.getTime());
        cal.add(Calendar.MINUTE, 1);
        String tournamentEndTime = sdf.format(cal.getTime());

        String d = "{\n\"GameID\":1," +
                "\"FormatID\":1," +
                "\"RegionID\":2," +
                "\"TournamentInfoID\":1," +
                "\"EntryFee_TypeID\":2," +
                "\"ParticipantsTotal\":100," +
                "\"TournamentName\":\"Auto API "+tournamentName+"\"," +
                "\"TournamentDesc\":\"This is a auto generated tournament\"," +
                "\"ScoringText\":\"This is auto generated scoring text\"," +
                "\"TournamentBannerImageLink\":\"https://mobile-static-img.s3.amazonaws.com/tournament/banner/Screenshot%20from%202019-07-11%2012-00-51.png\"," +
                "\"TournamentImageLink\":\"https://mobile-static-img.s3.amazonaws.com/tournament/banner/Screenshot%20from%202019-07-11%2012-00-51.png\"," +
                "\"RegistrationStartTime\":\""+registrationStartTime+".000\"," +
                "\"RegistrationEndTime\":\""+registrationEndTime+".000\"," +
                "\"TournamentStartTime\":\""+tournamentStartTime+".000\"," +
                "\"TournamentEndTime\":\""+tournamentEndTime+".000\"," +
                "\"ListingLiveDate\":\""+listingLiveDate+".000\"," +
                "\"Prizes\":  [{\"rank\": \"1\",\"PrizeType\": \"mPoints\",\"units\": \"100.00\",\"PrizePoolID\": \"1\",\"PrizeTypeID\": \"2\"}]," +
                "\"EntryFee_Units\":0," +
                "\"ParticipantsMinimum\":1," +
                "\"StagesCount\":1," +
                "\"GameMapID\":2," +
                "\"IsTournamentFeatured\":1\n}";

        RestAssured.baseURI = "https://adminbackend.qa.ap-south-1.dev.mobimasala.x3e.app/admin/api/v1";
        Response res = given().log().all().//logging
                header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.YWNjYmQ1NDVkNGE2MGE0YWIzNmE0ZTU2NmRhYzliMDc.eJwcWuzuhg5azda0IeHDI4E-XXmpCemH0NCcaheCZ5o").
                header("Content-Type", "application/json").
                body(d).
                when().
                post("/tournaments/create").
                then().extract().response();

        //Printing response & Extracting value from response
        String responseString = res.asString();
        System.out.println(responseString);

        //converting string to json format to pull game id
        JsonPath js = new JsonPath(responseString);
    }






}
