package com.example.smbat.develandooexample;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

class Utils {

    private static final String BASE_URL = "https://randomuser.me/api/?results=1000";
    private static final String RESULTS = "results";
    private static final String NAME = "name";
    private static final String FIRST = "first";
    private static final String LAST = "last";
    private static final String PICTURE = "picture";
    private static final String MEDIUM = "medium";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String DOB = "dob";
    private static final String DATE = "date";
    private static final String AGE = "age";

    static List<User> getUsersList() {
        final List<User> usersList = new ArrayList<>();
        try {
            final JSONArray data = new JSONObject(getJSON(BASE_URL)).getJSONArray(RESULTS);
            for (int i = 0; i < data.length(); ++i) {
                final JSONObject row = data.getJSONObject(i);
                usersList.add(getUser(row));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    private static String getJSON(String url) {
        HttpsURLConnection con = null;
        try {
            final URL u = new URL(url);
            con = (HttpsURLConnection) u.openConnection();
            con.connect();
            final BufferedReader br =
                    new BufferedReader(new InputStreamReader(con.getInputStream()));
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            return sb.toString();
        } catch (IOException ex) {
            Log.d("IOException: ", ex.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    Log.d("Exception: ", ex.getMessage());
                }
            }
        }
        return null;
    }

    @NonNull
    private static User getUser(JSONObject row) throws JSONException {
        final User user = new User();
        final JSONObject nameObject = row.getJSONObject(NAME);
        final String fullName = String.format("%s %s",
                capitalize(nameObject.getString(FIRST)),
                        capitalize(nameObject.getString(LAST)));
        user.setName(fullName);
        final JSONObject imageObject = row.getJSONObject(PICTURE);
        final String imageUrl = imageObject.getString(MEDIUM);
        user.setImageUrl(imageUrl);
        final String email = row.getString(EMAIL);
        user.setEmail(email);
        final String phoneNumber = row.getString(PHONE);
        user.setPhoneNumber(phoneNumber);
        final JSONObject dob = row.getJSONObject(DOB);
        final String date = dob.getString(DATE);
        user.setDateOfBirth(date);
        final int age = dob.getInt(AGE);
        user.setAge(String.valueOf(age));
        return user;
    }

    private static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
