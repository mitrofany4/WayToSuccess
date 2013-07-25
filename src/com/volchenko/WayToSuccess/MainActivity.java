package com.volchenko.WayToSuccess;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import java.lang.reflect.Field;


/**
 * Created by Максим on 23.07.13.
 */
public class MainActivity extends SherlockActivity {

    ActionBar actionbar;
    public static final int DEVICE_VERSION   = Build.VERSION.SDK_INT;
    public static final int DEVICE_HONEYCOMB = Build.VERSION_CODES.HONEYCOMB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionbar = getSupportActionBar();
        actionbar.setDisplayUseLogoEnabled(true);
        if (DEVICE_VERSION >= DEVICE_HONEYCOMB)   {
            try {
                ViewConfiguration config = ViewConfiguration.get(this);
                Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
                if(menuKeyField != null) {
                    menuKeyField.setAccessible(true);
                    menuKeyField.setBoolean(config, false);
                }
            } catch (Exception ex) {
                // Ignore
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_calendar:
                Intent intent = new Intent(getApplicationContext(), ExpertActivity.class);
                startActivity(intent);
        }
        return false;

//        return super.onOptionsItemSelected(item);
    }

    public void calendar_Click(View v) {

        Intent intent = new Intent(getApplicationContext(), ExpertActivity.class);
        startActivity(intent);

    }
}
