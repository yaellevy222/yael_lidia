package com.example.sporteam.sporteam;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class registerActivity extends AppCompatActivity {

    /**
     *  JSON Response node names.
     **/

    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_FIRSTNAME = "fname";
    private static String KEY_LASTNAME = "lname";
    private static String KEY_USERNAME = "uname";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
    private static String KEY_ERROR = "error";

    /**
     * Defining layout items.
     **/

    EditText inputFirstName;
    EditText inputLastName;
    EditText inputEmail;
    Button btnRegister;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
         * Defining all layout items
         **/
        inputFirstName = (EditText) findViewById(R.id.fname);
        inputLastName = (EditText) findViewById(R.id.lname);
        inputEmail = (EditText) findViewById(R.id.email);
        btnRegister = (Button) findViewById(R.id.bregister);

/**
 * Button which Switches back to the login screen on clicked
 **/



        /**
         * Register Button click event.
         * A Toast is set to alert when the fields are empty.
         * Another toast is set to alert Username must be 5 characters.
         **/

        inputFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputFirstName.setText("");
            }
        });

        inputLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputLastName.setText("");
            }
        });

        inputEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputEmail.setText("");
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (  ( !inputEmail.getText().toString().equals("")) && ( !inputFirstName.getText().toString().equals("")) && ( !inputLastName.getText().toString().equals("")) )
                {
                    if ( inputEmail.getText().toString().length() > 4 ){
                        Log.d("Register", "email is: " + inputEmail.getText().toString());
                        new PostTask().execute("");
                        Intent intent = new Intent( registerActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),
                                "Username should be minimum 5 characters", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Async Task to check whether internet connection is working
     **/

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private class PostTask extends AsyncTask<String, Void, String> {

        String fname = inputFirstName.getText().toString();
        String lname = inputLastName.getText().toString();
        String email = inputEmail.getText().toString();

        @Override
        protected String doInBackground(String... urls) {


            HttpURLConnection client = null;
            URL url = null;
            int response = -1;
            InputStream is = null;
            try {
                url = new URL("http://sporteam-2016.rapidapi.io/save_player");

                client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                client.setRequestProperty("fname", fname);
                client.setRequestProperty("lname", lname);
                client.setRequestProperty("email", email);
                client.setDoOutput(true);

                client.connect();
                response = client.getResponseCode();
                Log.d("MY APP", "The response is: " + response);
                is = client.getInputStream();
                // Convert the InputStream into a string
                String contentAsString = readIt(is, 1000);
                Log.d("MY APP", "The body is: " + contentAsString);

            } catch (Exception e) {
                Log.e("MY_APP", "unable to connect", e);
            } finally {
                if (client != null)
                    client.disconnect();
            }
            return String.valueOf(response);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

        }
    }
}



