package com.example.siuni.mymedicare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class register extends ActionBarActivity {

    TextView idView;
    EditText Firstname;
    EditText Surname;
    EditText Telephone;
    EditText Postcode;
    private Button Finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_register);

        idView = (TextView) findViewById(R.id.textView2);
        Firstname = (EditText) findViewById(R.id.editText1);
        Surname = (EditText) findViewById(R.id.editText2);
        Telephone = (EditText) findViewById(R.id.editText3);
        Postcode = (EditText) findViewById(R.id.editText4);
        Finish = (Button) findViewById(R.id.button1);


        MyDBHandler db = new MyDBHandler(getBaseContext());


        try {
            db.open();
            long id = db.insertContact(Firstname.getText().toString(), Surname.getText().toString(),
                    Telephone.getText().toString(), Postcode.getText().toString());
            db.close();
            Button btn = (Button) findViewById(R.id.button1);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Details Saved",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(register.this, weighinscreen.class));
                    Finish.setEnabled(true);

                }
                });
            } catch (SQLException e) {
            e.printStackTrace();
        }

    }








        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;

        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();

            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
