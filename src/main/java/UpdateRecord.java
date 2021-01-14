import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateRecord {
    public static void main(String[] args) {
        try {
            //new JSONObject
            JSONObject getRequest = new JSONObject();
            //放進key,值(Object)
            getRequest.put("userId", "U2");
            getRequest.put("bookId", "B5");

            //設定URL
            URL getUrl = new URL("http://localhost:8080/api/2.0/record/updateRecord");
            //用此java.net.HttpURLConnection類別 new getHttpConnection物件
            HttpURLConnection getHttpConnection  = (HttpURLConnection) getUrl.openConnection();
            /*設定是否向getHttpConnection輸出
                setDoOutput(true); true(是)/false(否)
              設定是否從getHttpConnection讀入
                setDoInput(true);
             */
            getHttpConnection.setDoOutput(true);
            /*設定請求方式
                POST,GET,PUT,DELETE
             */
            getHttpConnection.setRequestMethod("PUT");
            //使用此方法修改一般請求屬性
            getHttpConnection.setRequestProperty("Content-Type", "application/json");
            getHttpConnection.setRequestProperty("Accept", "application/json");
            //new getDataOutputStream 此DataOutputStream類別為應用程式以與機器無關方式從底層輸入流中讀取基本Java資料型別
            DataOutputStream getDataOutputStream = new DataOutputStream(getHttpConnection.getOutputStream());
            //用write方法,將getRequest連接起來
            getDataOutputStream.write(getRequest.toString().getBytes());
            //getResponseCode得到狀態碼的返回值 ex:200是ok
            Integer getResponseCode = getHttpConnection.getResponseCode();

            /*
            Bufferedreader:可以用來讀取鍵盤輸入和檔案內容
            BufferedWriter:可以將內容寫入檔案
             */
            //new getBufferedReader
            BufferedReader getBufferedReader;

            /*
            判斷狀態碼並將getHttpConnection內容給getBufferedReader
            */
            if (getResponseCode > 199 && getResponseCode < 300) {
                getBufferedReader = new BufferedReader(new InputStreamReader(getHttpConnection.getInputStream()));
            } else {
                getBufferedReader = new BufferedReader(new InputStreamReader(getHttpConnection.getErrorStream()));
            }

            //getBufferedReader接收到的內容
            StringBuilder getResponse = new StringBuilder();
            String line;
            while ((line = getBufferedReader.readLine()) != null) {
                //字串連接並換行
                getResponse.append(line).append("\n");
            }
            getBufferedReader.close();

            //列印response
            System.out.println(getResponse.toString());

        } catch (Exception e) {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
