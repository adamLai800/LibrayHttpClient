import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddUser {
    public static void main(String[] args) {
        try {
            //建立JSONObject
            JSONObject getRequest = new JSONObject();
            getRequest.put("userName", "Test");

            //設定URL
            URL getUrl = new URL("http://localhost:8080/api/2.0/user/addUser");
            HttpURLConnection httpConnection  = (HttpURLConnection) getUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept", "application/json");

            //連接JSON
            DataOutputStream getDataOutputStream = new DataOutputStream(httpConnection.getOutputStream());
            getDataOutputStream.write(getRequest.toString().getBytes());
            Integer getResponseCode = httpConnection.getResponseCode();

            BufferedReader bufferedReader;

            //創建一個閱讀器緩衝區
            if (getResponseCode > 199 && getResponseCode < 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }

            //接收回應response
            StringBuilder getResponse = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                getResponse.append(line).append("\n");
            }
            bufferedReader.close();

            //列印response
            System.out.println(getResponse.toString());

        } catch (Exception e) {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
