package com.example.sporteam.sporteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redbend on 7/13/16.
 */
public class GroupDetailsActivity extends AppCompatActivity {

    ListView listView;
    GroupDetailsPOJO groupDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        Intent intent = getIntent();
        groupDetails = (GroupDetailsPOJO) intent.getSerializableExtra("GroupDetails");

        listView = (ListView) findViewById(R.id.listGroupDetailsV);
        String[] values = new String[] {"Name: ", "Sport type: ", "Where: ", "Day: ", "Starts at: " , "Repeatable: "};
//        String[] values = new String[] {name , time, isRepeatable};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

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
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG);

            }


        });
    }

}
