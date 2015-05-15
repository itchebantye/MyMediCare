package com.example.siuni.mymedicare;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class weighinscreen extends ActionBarActivity {

    private Button result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weighinscreen);

        result = (Button) findViewById(R.id.button1);

        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText txtTempC = (EditText) findViewById(R.id.editText1);
                String strC = txtTempC.getText().toString();
                double TempC = Double.parseDouble(strC);
                if (TempC == Double.NaN) {
                    Toast.makeText(getApplicationContext(), "Please enter details",
                            Toast.LENGTH_LONG).show();
                    result.setEnabled(true);
                }

                EditText txtTempF = (EditText) findViewById(R.id.editText2);
                String strF = txtTempF.getText().toString();
                double TempF = Double.parseDouble(strF);

                EditText txtBPS = (EditText) findViewById(R.id.editText3);
                String strBPS = txtBPS.getText().toString();
                double BPS = Double.parseDouble(strBPS);

                EditText txtBPD = (EditText) findViewById(R.id.editText4);
                String strBPD = txtBPD.getText().toString();
                double BPD = Double.parseDouble(strBPD);

                EditText txtHeartR = (EditText) findViewById(R.id.editText5);
                String strH = txtHeartR.getText().toString();
                double HeartR = Double.parseDouble(strH);

                if (TempC <= 36 && TempF <= 97 && BPS <= 100 && BPD <= 40 && HeartR <= 59) {
                    Toast.makeText(getApplicationContext(), "Please enter contact your GP",
                            Toast.LENGTH_LONG).show();
                    result.setEnabled(true);
                } else if (TempC >= 36 && TempF >= 97 && BPS >= 100 && BPD >= 40 && HeartR >= 59) {

                    Toast.makeText(getApplicationContext(), "Your in good health",
                            Toast.LENGTH_LONG).show();
                    result.setEnabled(true);
                } else if (TempC <= 37 && TempF <= 98 && BPS <= 140 && BPD <= 80 && HeartR <= 100) {

                    Toast.makeText(getApplicationContext(), "Your in good health",
                            Toast.LENGTH_LONG).show();
                    result.setEnabled(true);
                } else if (TempC >= 37 && TempF >= 98 && BPS >= 140 && BPD >= 80 && HeartR >= 100) {

                    Toast.makeText(getApplicationContext(), "Please consider calling an ambulance",
                            Toast.LENGTH_LONG).show();
                    result.setEnabled(true);

                }
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
