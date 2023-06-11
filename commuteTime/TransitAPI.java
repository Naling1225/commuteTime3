package commuteTime;
/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class TransitAPI {

    int fare;
    double time;
    String commuteTime;
    String departureName;
    String destinationName;


    public TransitAPI(String departure, String destination) throws IOException, InterruptedException {
        String searchDate = "202305221806";

        String startAddr = new String(departure.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        String endAddr = new String(destination.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        TestKakao start = new TestKakao(startAddr);
        String startX = start.x;
        String startY = start.y;
        departureName = start.building;
        TestKakao end = new TestKakao(endAddr);
        String endX = end.x;
        String endY = end.y;
        destinationName = end.building;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://apis.openapi.sk.com/transit/routes/sub"))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("appKey", "e8wHh2tya84M88aReEpXCa5XTQf3xgo01aZG39k5")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"startX\":\""+startX+"\",\"startY\":\""+startY+"\",\"endX\":\""+endX+"\",\"endY\":\""+endY+"\",\"format\":\"json\",\"count\":1,\"searchDttm\":\""+searchDate+"\"}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        parsing(response.body());
        int min = 0, hour = 0;
        time = time*2;
        if (time>60) {
            min = (int) (time/60);
            //time = (int) time%60;
            if (min > 60 ) {
                hour = (int) (min/60);
                min = min%60;
            }
        }
        commuteTime = "왕복 약 "+hour+"시간 "+min+"분 소요";
        System.out.println("왕복 약 "+hour+"시간 "+min+"분 소요");
        System.out.println("fare:"+fare);
        System.out.println(departure);
        //jsonFileRead(response.body());
    }

    private void parsing(String response){
        String[] arraysStr = response.split("[{|:,]");
		
        time = Double.parseDouble(arraysStr[34]);
        fare = Integer.parseInt(arraysStr[24]);
    }
    /*private static void jsonFileRead(String response) {
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(response);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObj = (JSONObject) obj;
        jsonObj = (JSONObject) jsonObj.get("metaData");
        jsonObj = (JSONObject) jsonObj.get("plan");
        JSONArray jsonArr = (JSONArray) jsonObj.get("itineraries");
        jsonObj = (JSONObject) jsonArr.get(0);
        jsonObj = (JSONObject) jsonObj.get("fare");
        jsonObj = (JSONObject) jsonObj.get("regular");
        System.out.println(jsonObj.get("totalFare"));
    }*/


}