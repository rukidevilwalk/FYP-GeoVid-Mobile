package net.sourceforge.opencamera;


import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;


public class UserAccount {

    int serverResponseCode;
    HttpURLConnection conn;
    DataOutputStream wr;
    StringBuilder result;
    URL urlObj;

    public String makeHttpRequest(String url, String method,
                                      String email, String password, String username ) {

        if (method.equals("LOGIN")) {
            // request method is POST
            try {
                urlObj = new URL(url);
                JSONObject payload = new JSONObject();
                try {
                    payload.put("email", email);
                    payload.put("password", password);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                conn = (HttpURLConnection) urlObj.openConnection();

                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Content-Type", "application/json");

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(payload.toString());
                serverResponseCode = conn.getResponseCode();
                wr.flush();
                wr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("REGISTER")) {
            // request method is POST
            try {
                urlObj = new URL(url);
                JSONObject payload = new JSONObject();
                try {
                    payload.put("email", email);
                    payload.put("password", password);
                    payload.put("password2", password);
                    payload.put("name", username);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                conn = (HttpURLConnection) urlObj.openConnection();

                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Content-Type", "application/json");

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(payload.toString());
                serverResponseCode = conn.getResponseCode();
                wr.flush();
                wr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (serverResponseCode == 200) {
            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn
                        .getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
            } catch (IOException ioex) {
            }
            return sb.toString();
        }else {
            return "failed";
        }

    }


}