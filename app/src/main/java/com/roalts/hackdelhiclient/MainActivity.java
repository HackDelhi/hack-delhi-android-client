package com.roalts.hackdelhiclient;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseUser parseUser = ParseUser.getCurrentUser();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        App.MAJOR = sharedPref.getString("major", "0");
        App.MINOR = sharedPref.getString("minor", "0");
        App.NAME = sharedPref.getString("name", "Hello");

//        if ((parseUser == null)) {
//            Intent i = new Intent(this, LoginActivity.class);
//            startActivity(i);
//            finish();
//        } else {
        Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT)
                .show();
        Button recharge = (Button) findViewById(R.id.recharge);
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListBeaconsActivity.class);
                intent.putExtra(ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, NotifyDemoActivity.class.getName());
                startActivity(intent);
            }
        });
//        }

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
        if (id == R.id.uuid) {
            final Dialog customDialog = new Dialog(this);
            customDialog.setContentView(R.layout.add_uuid);
            customDialog.setTitle("What are your details?");
            customDialog.setCancelable(true);

            final EditText minor = (EditText) customDialog.findViewById(R.id.minor);
            final EditText name = (EditText) customDialog.findViewById(R.id.name);
            final EditText major = (EditText) customDialog.findViewById(R.id.major);
            Button buttonDone = (Button) customDialog.findViewById(R.id.done_button);

            buttonDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nameOfUser = name.getText().toString();
                    if (!nameOfUser.equalsIgnoreCase("")) {
                        customDialog.dismiss();
                        App.NAME = name.getText().toString();
                        App.MAJOR = major.getText().toString();
                        App.MINOR = minor.getText().toString();
                        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("major", App.MAJOR);
                        editor.putString("minor", App.MINOR);
                        editor.putString("name", App.NAME);
                        editor.commit();
                    } else {
                        name.setError("Please Enter the UUID Code");
                    }
                }
            });
            customDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
