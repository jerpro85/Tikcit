package com.example.jeremiah8100.test;

import com.example.jeremiah8100.test.Items.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jeremiah8100 on 19-3-2018.
 */

public class Connection {
    public static class ApiResponse{
        String content, url;
        public ApiResponse(){}
    }


    public static ApiResponse Post(String data) {
        try {

            String httpsURL = "https://api.tikcit.com/oauth/authorize";
            URL myUrl = new URL(httpsURL);
            HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
            conn.connect();
            String urlParameters  = "client_id=vH20yrkqW0aKOM8Z1nQAHlg2Ik4doy&state=&scope=unrestricted&email=noreplycoldfire@gmail.com&password=helloworld";
            int    postDataLength = urlParameters.length();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setUseCaches( false );
            try{
                OutputStreamWriter wr = new OutputStreamWriter( conn.getOutputStream());
                wr.write( urlParameters );
                wr.flush();
                wr.close();
            } catch (Exception e) {
                System.out.println("b1" + e.getMessage());
            }




            System.out.println("test55");
            InputStream is = conn.getInputStream();

            InputStreamReader isr = new InputStreamReader(is);

            BufferedReader br = new BufferedReader(isr);
            String inputLine;
            ApiResponse response = new ApiResponse();
            while ((inputLine = br.readLine()) != null) {
                response.content = inputLine;
            }
            response.url = conn.getHeaderField("Location");

            br.close();
            return response;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Account Login(String email, String password){
        ApiResponse response = Post("client_id=vH20yrkqW0aKOM8Z1nQAHlg2Ik4doy&state=&scope=unrestricted&email="+email+"&password="+password+"");
        String code = response.url.replace("https://api.tikcit.com?code=", "");
        System.out.println("lolcode: "+code);
        String auth = "";
        String token = "";
        return new Account(token, email);
    }


}
