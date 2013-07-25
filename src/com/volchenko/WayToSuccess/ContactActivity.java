package com.volchenko.WayToSuccess;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Максим on 23.07.13.
 */
public class ContactActivity extends SherlockActivity {

    private EditText dateET, nameET, phoneET;
    private Calendar calendar;
    private Note note;
    private int mYear,mMonth,mDay;
    DatePickerDialog.OnDateSetListener date;
    static final int DATE_DIALOG_ID = 0;
    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        dateET.setText(sdf.format(calendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            note = new Note(extras.getString("expert"));
        }

        dateET = (EditText) findViewById(R.id.DateET);
        nameET = (EditText) findViewById(R.id.nameET);
        phoneET = (EditText) findViewById(R.id.phoneET);

        Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateET.setText( sdf.format(c.getTime()));



/*        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };*/

        dateET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                        }
        });


    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);

        }

        return null;

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            dateET.setText(new StringBuilder().append(mDay).append("/").append(mMonth+1).append("/").append(mYear));

        }

    };

    public void sendInfo_Click(View v) {

        if (nameET.getText().length()==0||phoneET.getText().length()==0||dateET.getText().length()==0) {
            Toast.makeText(getApplicationContext(), "Заполните, пожалуйста, все поля!", Toast.LENGTH_LONG).show();
        } else

        note.setDate(dateET.getText().toString());
        note.setName(nameET.getText().toString());
        note.setPhone(phoneET.getText().toString());

        sendEmail(note);
        Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
        intent.putExtra("message",note.toString());
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

    public void sendEmail(Note note){

        String body;
        String device = android.os.Build.DEVICE;
        String OS = System.getProperty("os.version");

        body = "Имя:"+" "+note.getName()+"\n"+
               "Желаемая дата: "+" "+note.getDate()+"\n"+
               "Эксперт: "+note.getExpert()+"\n"+
               "Телефон: "+" "+note.getPhone()+"\n"+
               "Устройство: "+device+"\n"+
               "ОС: "+OS;


        try {
            GMailSender sender = new GMailSender("mitrofany4@gmail.com", "Dr1veM@x28");
            sender.sendMail("Новая запись на прием",
                    body,
                    "mitrofany4@gmail.com",
                    "mitrofany4@yandex.ua");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

/*        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");

        //recipient
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[] {"mitrofany4@gmail.com"});

        //subject
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                R.string.email_subject);

        //body
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }*/




    }
}
