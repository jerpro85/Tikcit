package com.example.jeremiah8100.test;

import com.example.jeremiah8100.test.Items.Account;
import com.example.jeremiah8100.test.Items.Activity;
import com.example.jeremiah8100.test.Items.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


    public static Account.Result Login(String email, String password){
        ApiResponse response = Post("https://api.tikcit.com/oauth/authorize","client_id=vH20yrkqW0aKOM8Z1nQAHlg2Ik4doy&state=&scope=unrestricted&email="+email+"&password="+password+"", null);
        Account.Result result = new Account.Result();
        if(response.content!=null){
            result.message = "Foute inloggegevens!";

        } else {

            String code = response.url.replace("https://api.tikcit.com?code=", "");
            ApiResponse response2 = Post("https://api.tikcit.com/oauth/token", "grant_type=authorization_code&client_id=vH20yrkqW0aKOM8Z1nQAHlg2Ik4doy&code=" + code, null);
            try {
                JSONObject obj = new JSONObject(response2.content);
                String token = obj.getString("access_token");
                System.out.println("token: " + token);
                result.account = new Account(token, email);
                result.authenticated = true;
                result.message = "Gebruiker ingelogd";
            } catch (JSONException e) {
                result.message = "Error bij het inloggen";
                e.printStackTrace();
            }


        }
        return result;

    }

    public static List<Event> GetEvents(Account account){
        List<Event> events = new ArrayList<Event>();
        ApiResponse response = Get("https://api.tikcit.com/events","sort=dateStart%7Casc&page=1&per_page=20&filter=dateStart+gte+1526047537.291&search=", account.getToken());
        System.out.println("events:" + response.content);
        try {
            JSONObject obj = new JSONObject(response.content);
            JSONArray arr = obj.getJSONArray("data");

            for(int a = 0;a < arr.length();a++) {
                JSONObject tobj = arr.getJSONObject(a);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-mm");
                Date Startdate = format.parse(tobj.getJSONObject("dateStart").getString("$date"));
                Date Enddate = format.parse(tobj.getJSONObject("dateEnd").getString("$date"));
                String eventid = tobj.getJSONObject("_id").getString("$oid");
                List<Activity> activities = GetActivities(eventid, account);
                Event event = new Event(eventid, tobj.getString("name"), tobj.getString("description"), activities, Startdate, Enddate);
                events.add(event);

            }
        } catch (JSONException e) {
            System.out.println( "error 3: " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return events;
    }

    private static List<Activity> GetActivities(String eventid, Account account){
        List<Activity> activities = new ArrayList<Activity>();
        ApiResponse response = Get("https://api.tikcit.com/events/", eventid + "/statistics", account.getToken());
        try {
            JSONArray arr = new JSONArray(response.content);

            for(int a = 0;a < arr.length();a++) {
                System.out.println("Activity: " + arr.getJSONObject(a).toString());
                JSONObject tobj = arr.getJSONObject(a);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-mm");


                Date Startdate = format.parse(tobj.getJSONObject("dateStart").getString("$date"));
                Date Enddate = format.parse(tobj.getJSONObject("dateEnd").getString("$date"));
                Activity activity = new Activity(tobj.getJSONObject("_id").getString("$oid"), tobj.getString("name"), tobj.getString("description"), Startdate, Enddate);
                activities.add(activity);

            }
        } catch (JSONException e) {
            System.out.println( "error 3: " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return activities;
    }




}
