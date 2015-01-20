package com.slashandhyphen.tasterly;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class AddBeerActivity extends ActionBarActivity {

    FragmentManager fm = getFragmentManager();
    Fragment fragment1 = fm.findFragmentById(R.id.fragment_content_1);
    Fragment fragment2 = fm.findFragmentById(R.id.fragment_content_2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);

        Log.d("breakpoint", "thefuck");

        FragmentTransaction ft = fm.beginTransaction();
        if (fragment1 == null) {
            ft.add(R.id.fragment_content_1, new AddBeerAFragment());
        }
        if (fragment2 == null) {
           ft.add(R.id.fragment_content_2, new AddBeerBFragment());
        }
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_beer, menu);
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
}