package com.example.monikapiechatzek.degreeconverter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {


    Button convertButton;
    EditText result;
    String degreeto;
    String degreefrom;
    float resultToConvert;
    TextView realResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //top spinner
        final Spinner dropdown = (Spinner) findViewById(R.id.spinner_degree);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.degree_array, android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        //bottom spinner
        final Spinner dropdown2 = (Spinner) findViewById(R.id.spinner_to);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.degree_array, android.R.layout.simple_spinner_dropdown_item);
        dropdown2.setAdapter(adapter2);



        //grabbing id's from activity main
        convertButton = (Button) findViewById(R.id.convert);
        result = (EditText) findViewById(R.id.degree_input);
        realResult = (TextView) findViewById(R.id.convertedresult);

        //when convert button is pressed
        convertButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.v("EditText", result.getText().toString());
                        String temp = result.getText().toString();

                        //handles an empty value
                        if (temp.matches("")) {
                            Context context = getApplicationContext();
                            Toast.makeText(context, "You did not enter a degree!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //saves all 
                        resultToConvert = Float.parseFloat(temp);
                        degreefrom = dropdown.getSelectedItem().toString();
                        degreeto = dropdown2.getSelectedItem().toString();
                        Log.v("EditText", degreeto);
                        Log.v("EditText", degreefrom);
                        if (degreefrom.equals("Fahrenheit") && degreeto.equals("Celsius") || degreefrom.equals("Celsius") && degreeto.equals("Fahrenheit")) {
                            float result = Convert(degreefrom, degreeto, resultToConvert);
                            //this is what needs to happen i just dont know how
                            realResult.setText(Float.toString(result));
                            Log.v("EditText", Float.toString(result));
                        } else {
                            Context context = getApplicationContext();
                            CharSequence text = "No Conversion Needed!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast.makeText(context, text, duration).show();
                        }

                    }

                });


    }

    //converts to proper degree
    public float Convert(String from, String to, float resultToConvertRaw) {
        if (from.equals("Fahrenheit") && to.equals("Celsius")) {
            float Celsius = (resultToConvertRaw - 32) * 5 / 9;
            return Celsius;
        } else {
            float Fahrenheit = (resultToConvertRaw * 9 / 5) + 32;
            return Fahrenheit;
        }
    }
//**
//inputText01.addTextChangedListener( new TextWatcher(){
//    public void beforeTextChanged(CharSequence charSequence, int i, int)
//    {
//
//    }
//
//    public void onTextChanged(CharSequence mytext, int i, int i1,)
//})
//    }



}
