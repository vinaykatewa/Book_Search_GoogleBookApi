package com.example.book_library;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Api {

    public static ArrayList<ListDT> FindingFromNet(String urlFromMain){
        URL urlForThisFun = createUrl(urlFromMain);
        String JsonResponse = null;
        try {
            JsonResponse = makeHttpRequest(urlForThisFun);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<ListDT> books = extractJson(JsonResponse);
        return books;
    }
    private static URL createUrl(String u){
        URL url =null;
        try {
            url = new URL(u);
        } catch (MalformedURLException e) {
            Log.e("notWorking","createUrl");
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null){
            return jsonResponse;
        }
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try{
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.connect();
            Log.e("connected to Http" , "connected");
            inputStream = connection.getInputStream();
            jsonResponse =methodForInputStream(inputStream);
        }
        catch (IOException e){
            Log.e("Http not connected","not Connected");
        }
        finally {
            if (connection != null){
                connection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;

    }
    private static String methodForInputStream(InputStream inputStream) throws IOException {
        StringBuilder json=new StringBuilder();
        if(inputStream == null){
            return json.toString();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = reader.readLine();
        while (line != null){
            json.append(line);
            line=reader.readLine();
        }
        return json.toString();
    }
    private static ArrayList<ListDT> extractJson(String jsonResponse) {
        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        ArrayList<ListDT> bookList = new ArrayList<>();
        try {
            JSONObject baseJson = new JSONObject(jsonResponse);
            Log.e("Okay" , "till here");
            JSONArray items = baseJson.getJSONArray("items");
            Log.e("Okay" , "till here");
            for (int i = 0; i < items.length(); i++) {
                JSONObject currentOne = items.getJSONObject(i);
                JSONObject volumeInfo = currentOne.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                JSONArray authors = volumeInfo.getJSONArray("authors");
                String author = authors.getString(0);
                String year = volumeInfo.getString("publishedDate");
                bookList.add(new ListDT(title,author,year));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
