package com.example.jeremiah8100.test;

import com.example.jeremiah8100.test.Items.Account;
import com.example.jeremiah8100.test.Items.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import org.json.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jeremiah8100 on 19-3-2018.
 */

public class Connection {
    public static class ApiResponse{
        String content, url;
        public ApiResponse(){}
    }


    public static ApiResponse Post(final String url, final String data, final String auth) {
        final ApiResponse response = new ApiResponse();
        Async.RunTask(new Async.Item(0) {
            @Override
            public void Method() {
                try {

                    String httpsURL = url;
                    URL myUrl = new URL(httpsURL);
                    HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
                    if(auth != null)
                        conn.setRequestProperty("Authorization", auth);
                    conn.setDoOutput( true );
                    conn.setInstanceFollowRedirects( false );
                    conn.setRequestMethod( "POST" );
                    conn.setUseCaches( false );
                    try{
                        OutputStreamWriter wr = new OutputStreamWriter( conn.getOutputStream());
                        wr.write( data );
                        wr.flush();
                        wr.close();
                    } catch (Exception e) {
                        System.out.println("b1" + e.getMessage());
                    }

                    InputStream is = conn.getInputStream();

                    InputStreamReader isr = new InputStreamReader(is);

                    BufferedReader br = new BufferedReader(isr);
                    String inputLine;
                    while ((inputLine = br.readLine()) != null) {
                        response.content = inputLine;
                    }
                    response.url = conn.getHeaderField("Location");

                    br.close();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        return response;
    }

    public static ApiResponse Get(final String url, final String data, final String auth) {
        final ApiResponse response = new ApiResponse();
        Async.RunTask(new Async.Item(0) {
            @Override
            public void Method() {
                try {

                    String httpsURL = url+"?"+data;
                    URL myUrl = new URL(httpsURL);
                    HttpsURLConnection conn = (HttpsURLConnection) myUrl.openConnection();
                    if(auth != null)
                        conn.setRequestProperty("Authorization", auth);


                    InputStream is = conn.getInputStream();

                    InputStreamReader isr = new InputStreamReader(is);

                    BufferedReader br = new BufferedReader(isr);
                    String inputLine;
                    while ((inputLine = br.readLine()) != null) {
                        response.content = inputLine;
                    }
                    response.url = conn.getHeaderField("Location");

                    br.close();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        return response;
    }


    public static Account Login(String email, String password){
        ApiResponse response = Post("https://api.tikcit.com/oauth/authorize","client_id=vH20yrkqW0aKOM8Z1nQAHlg2Ik4doy&state=&scope=unrestricted&email="+email+"&password="+password+"", null);

        if(response.content!=null){
            System.out.println("bad auth: ");

        } else {

            String code = response.url.replace("https://api.tikcit.com?code=", "");
            ApiResponse response2 = Post("https://api.tikcit.com/oauth/token", "grant_type=authorization_code&client_id=vH20yrkqW0aKOM8Z1nQAHlg2Ik4doy&code=" + code, null);
            try {
                JSONObject obj = new JSONObject(response2.content);
                String token = obj.getString("access_token");
                System.out.println("token: " + token);
                return new Account(token, email);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return null;

    }

    public static List<Event> GetEvents(Account account){
        List<Event> events = new ArrayList<Event>();
        ApiResponse response = Get("https://api.tikcit.com/events","sort=dateStart%7Casc&page=1&per_page=20&filter=dateStart+gte+1526047537.291&search=", account.getToken());
        System.out.println("events:" + response.content);
        return events;
    }




}
