package commuteTime;/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TestKakao {
    private String URL = "https://dapi.kakao.com/v2/local/search/address.json?query=";
    private String userID = "1566f07ecfe3ef8970214e2b61767dcc";

    public String x;
    public String y;
    public String building;
    public TestKakao(String addr){
        URL obj;
        HttpURLConnection con = null;
        try {
            String address = URLEncoder.encode(addr, StandardCharsets.UTF_8);
            obj = new URL(URL + address);
            con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "KakaoAK "+userID);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            Charset charset = StandardCharsets.UTF_8;
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            //System.out.println(response.toString());
            //jsonFileRead(response.toString());
            parsing(response.toString());
//            HashMap map = jsonToObject(response.toString(), HashMap.class);
//            List docList = (ArrayList)map.get("documents");
//            System.out.println(docList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*private void jsonFileRead(String response) {
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(response);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObj = (JSONObject) obj;
        JSONArray jsonArr = (JSONArray) jsonObj.get("documents");
        jsonObj = (JSONObject) jsonArr.get(0);
        //jsonObj = (JSONObject) jsonObj.get("regular");
        x = jsonObj.get("x").toString();
        y = jsonObj.get("y").toString();
    }*/
    private void parsing(String response){
        String[] arraysStr = response.split("[{}|:,\"]");
        x = arraysStr[73];
        y = arraysStr[79];
        building = arraysStr[108];
		System.out.println(x);
		System.out.println(y);
    }
    
}