package com.ebayshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener  {

//    public void onClick(View view) {
//        EditText keywordsTextField = (EditText) findViewById(R.id.keywordsTextField);
//        String s = keywordsTextField.getText().toString();
//        Log.i("info", " aaaaa");
////        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText(s);
//    }
    JSONObject searchFilter = new JSONObject();
    private static final Map<String, String> optionMap = new HashMap<>();
    static {
        optionMap.put("Best Match", "BestMatch");
        optionMap.put("Price: Highest First", "CurrentPriceHighest");
        optionMap.put("Price + Shipping: Highest First", "PricePlusShippingHighest");
        optionMap.put("Price + Shipping: Lowest First", "PricePlusShippingLowest");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_by_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        try {
            Log.i("Tapped", String.valueOf(parent.getItemAtPosition(pos)));
            searchFilter.put("sort_order", optionMap.get((String)parent.getItemAtPosition(pos)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}