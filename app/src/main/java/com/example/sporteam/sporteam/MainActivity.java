package com.example.sporteam.sporteam;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView ;
    Button helpButton;
    String host;
    String contentAsString;
    GroupDetailsPOJO [] groupDetailsPOJO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helpButton = (Button) findViewById(R.id.helpButt);
        helpButton.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listV);
        host = "http://sporteam-2016.rapidapi.io/find_teams";
        new PostTask().execute("");
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<GroupDetailsPOJO> pojos = new ArrayList<GroupDetailsPOJO>();
        ArrayList<String> images= new ArrayList<String>();

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        groupDetailsPOJO = createJsonObj();

        for(int i=0; i<groupDetailsPOJO.length; ++i)
        {
           /* String groupName = groupDetailsPOJO[i].getName();
            String groupType = groupDetailsPOJO[i].getType();
            values.add(groupName);
            images.add(groupType);*/
            pojos.add(i, groupDetailsPOJO[i]);
        }

       /* holder.image.setImageResource(
                res.getIdentifier(
                        "com.androidexample.customlistview:drawable/"+groupType.getImage()
                        ,null,null));*/

        GroupListAdapter adapter = new GroupListAdapter(this, pojos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;
                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();


                //Open activity of group


                defineChosenGroup(itemPosition);

                Intent intent = new Intent( MainActivity.this, GroupDetailsActivity.class);
                //intent.putExtra("GroupDetails", groupDetailsPOJO);
                startActivity(intent);
            }
        });
    }
    private GroupDetailsPOJO[] createJsonObj() {
        Gson gson = new Gson();
        GroupDetailsPOJO[] groupDetailsPOJO = gson.fromJson(contentAsString, GroupDetailsPOJO[].class);
        return groupDetailsPOJO;
    }

    private void defineChosenGroup(int position){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        // Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);

        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.help_dialog_layout, null));

        // Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        // Add OK button
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });

        // 4. Get the AlertDialog from create()
        builder.create().show();
    }


    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        StringBuilder out = new StringBuilder();
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        //Arrays.fill(buffer, '\0');
        int dataread = 0;
      //  out.append("{\"key\":");
        for(;;){
            dataread = reader.read(buffer, 0, buffer.length);
            if(dataread < 0)
                break;
            out.append(buffer, 0 , dataread);
        }
       // out.append('\n');
       // out.append("}");
        Log.d("@@! Read:", "datareadlen = " + dataread);
        return String.valueOf(out);
    }

    private class PostTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            HttpURLConnection client = null;
            URL url = null;
            int response = -1;
            InputStream is = null;
            try {
                url = new URL(host);

                client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                client.setRequestProperty("key", "value");
                client.setDoOutput(true);

                client.connect();
                response = client.getResponseCode();
                Log.d("MY APP", "The response is: " + response);
                is = client.getInputStream();

                // Convert the InputStream into a string
                contentAsString = readIt(is,2000);
                Log.d("@@!MY APP", "The body is: " + contentAsString);

            } catch (Exception e) {
                Log.e("MY_APP", "unable to connect", e);
            } finally {
                if (client != null)
                    client.disconnect();
            }
            createJsonObj();
            return String.valueOf(response);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

        }
    }
}
