package com.example.converter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout lengthConversionTableLayout;
    private ConstraintLayout temperatureConversionTableLayout;
    private ConstraintLayout weightConversionTableLayout;

    private TableLayout lengthConversionTable;
    private TableLayout temperatureConversionTable;
    private TableLayout weightConversionTable;

    private TextView cmOutputTextView;
    private TextView ftOutputTextView;
    private TextView inOutputTextView;

    private TextView fOutputTextView;
    private TextView kOutputTextView;

    private TextView gOutputTextView;
    private TextView ozOutputTextView;
    private TextView lbOutputTextView;

    private EditText unitInputEditText;

    private Button lengthButton;
    private Button temperatureButton;
    private Button weightButton;

    private Spinner unitSelectionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get includes
        lengthConversionTableLayout =
                (ConstraintLayout) findViewById(R.id.lengthInclude);
        temperatureConversionTableLayout =
                (ConstraintLayout) findViewById(R.id.temperatureInclude);
        weightConversionTableLayout =
                (ConstraintLayout) findViewById(R.id.weightInclude);

        // Get includes' table layouts
        lengthConversionTable =
                (TableLayout) lengthConversionTableLayout.findViewById(R.id.tableLayout);
        temperatureConversionTable =
                (TableLayout) temperatureConversionTableLayout.findViewById(R.id.tableLayout);
        weightConversionTable =
                (TableLayout) weightConversionTableLayout.findViewById(R.id.tableLayout);

        // Get length output text views
        cmOutputTextView =
                (TextView) lengthConversionTable.findViewById(R.id.outputTextView1);
        ftOutputTextView =
                (TextView) lengthConversionTable.findViewById(R.id.outputTextView2);
        inOutputTextView =
                (TextView) lengthConversionTable.findViewById(R.id.outputTextView3);

        // Get temperature output text views
        fOutputTextView =
                (TextView) temperatureConversionTable.findViewById(R.id.outputTextView1);
        kOutputTextView =
                (TextView) temperatureConversionTable.findViewById(R.id.outputTextView2);

        // Get weight output text views
        gOutputTextView =
                (TextView) weightConversionTable.findViewById(R.id.outputTextView1);
        ozOutputTextView =
                (TextView) weightConversionTable.findViewById(R.id.outputTextView2);
        lbOutputTextView =
                (TextView) weightConversionTable.findViewById(R.id.outputTextView3);

        // Get input edit text
        unitInputEditText =
                (EditText) findViewById(R.id.unitInputEditText);

        // Get buttons
        lengthButton = (Button) findViewById(R.id.lengthButton);
        temperatureButton = (Button) findViewById(R.id.temperatureButton);
        weightButton = (Button) findViewById(R.id.weightButton);

        // Get Spinner
        unitSelectionSpinner = (Spinner) findViewById(R.id.unitSelectionSpinner);

        // Load options into spinner
        ArrayAdapter<CharSequence> unitSelectionAdapter =
                ArrayAdapter.createFromResource(this, R.array.unit_options_array,
                        android.R.layout.simple_spinner_item);
        unitSelectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSelectionSpinner.setAdapter(unitSelectionAdapter);

    }

    private double metreToCentimetre(double input){
        return input * 100;
    }

    private double metreToFoot(double input){
        return input * 3.28084;
    }

    private double metreToInch(double input){
        return input * 39.3701;
    }

    private double celsiusToFahrenheit(double input){
        return (input * 1.8) + 32;
    }

    private double celsiusToKelvin(double input){
        return input + 273.15;
    }

    private double kilogramToGram(double input){
        return input * 1000;
    }

    private double kilogramToOunce(double input){
        return input * 35.274;
    }

    private double kilogramToPound(double input){
        return input * 2.20462;
    }

    private void setFormattedOutput(TextView view, double value){
        String text = String.format("%.2f", value);
        view.setText(text);
    }

    public void onLengthButtonClick(View view){
        int selection = unitSelectionSpinner.getSelectedItemPosition();

        if (selection == 0){
            double curValue =
                    Double.parseDouble(unitInputEditText.getText().toString());

            lengthConversionTableLayout.setVisibility(View.VISIBLE);
            temperatureConversionTableLayout.setVisibility(View.INVISIBLE);
            weightConversionTableLayout.setVisibility(View.INVISIBLE);

            setFormattedOutput(cmOutputTextView,metreToCentimetre(curValue));
            setFormattedOutput(ftOutputTextView,metreToFoot(curValue));
            setFormattedOutput(inOutputTextView,metreToInch(curValue));
        } else {
            displayConversionError();
        }
    }

    public void onTemperatureButtonClick(View view){
        int selection = unitSelectionSpinner.getSelectedItemPosition();

        if (selection == 1) {
            double curValue =
                    Double.parseDouble(unitInputEditText.getText().toString());

            lengthConversionTableLayout.setVisibility(View.INVISIBLE);
            temperatureConversionTableLayout.setVisibility(View.VISIBLE);
            weightConversionTableLayout.setVisibility(View.INVISIBLE);

            setFormattedOutput(fOutputTextView,celsiusToFahrenheit(curValue));
            setFormattedOutput(kOutputTextView,celsiusToKelvin(curValue));
        } else {
            displayConversionError();
        }

    }

    public void onWeightButtonClick(View view) {

        int selection = unitSelectionSpinner.getSelectedItemPosition();

        if (selection == 2) {
            double curValue =
                    Double.parseDouble(unitInputEditText.getText().toString());

            lengthConversionTableLayout.setVisibility(View.INVISIBLE);
            temperatureConversionTableLayout.setVisibility(View.INVISIBLE);
            weightConversionTableLayout.setVisibility(View.VISIBLE);

            setFormattedOutput(gOutputTextView, kilogramToGram(curValue));
            setFormattedOutput(ozOutputTextView, kilogramToOunce(curValue));
            setFormattedOutput(lbOutputTextView, kilogramToPound(curValue));
        } else {
            displayConversionError();
        }
    }

    private void displayConversionError(){
        new AlertDialog.Builder(this)
                .setTitle("Conversion Error")
                .setMessage("Please select the correct conversion icon")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK",null)
                .show();
    }
}