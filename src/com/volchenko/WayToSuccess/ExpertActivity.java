package com.volchenko.WayToSuccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

/**
 * Created by Максим on 23.07.13.
 */
public class ExpertActivity extends SherlockActivity {

    RadioGroup radiogroup;
    Button furtherbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        radiogroup = (RadioGroup) findViewById(R.id.RadioGroup1);
        furtherbutton = (Button)    findViewById(R.id.furtherbutton);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId==-1) {
                    furtherbutton.setVisibility(View.GONE);
                }   else
                    furtherbutton.setVisibility(View.VISIBLE);
            }
        });
    }

    public void further_Click(View v) {
        int checkedRadioButtonId = radiogroup.getCheckedRadioButtonId();
        RadioButton myradiobutton = (RadioButton) findViewById(checkedRadioButtonId);

        String expertname = myradiobutton.getText().toString();
        Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
        intent.putExtra("expert",expertname);
        startActivity(intent);

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
