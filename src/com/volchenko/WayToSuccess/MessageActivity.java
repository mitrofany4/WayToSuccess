package com.volchenko.WayToSuccess;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

/**
 * Created by Максим on 24.07.13.
 */
public class MessageActivity extends SherlockActivity {

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            message = extras.getString("message");
        }
        TextView mess = (TextView) findViewById(R.id.messageTV);
        mess.setText(message.toString());
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
        return super.onOptionsItemSelected(item);
    }

}
