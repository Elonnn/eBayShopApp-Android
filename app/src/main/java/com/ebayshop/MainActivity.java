package com.ebayshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Float.parseFloat;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    JSONObject searchFilter = new JSONObject();

    private static final Map<String, String> optionMap = new HashMap<>();

    static {
        optionMap.put("Best Match", "BestMatch");
        optionMap.put("Price: Highest First", "CurrentPriceHighest");
        optionMap.put("Price + Shipping: Highest First", "PricePlusShippingHighest");
        optionMap.put("Price + Shipping: Lowest First", "PricePlusShippingLowest");
    }

    /**
     * My custom exception class.
     */
    private static final class InvalidPriceException extends Exception {
        public InvalidPriceException(String message) {
            super(message);
        }
    }

    private static final int[] checkBoxId = new int[]{
            R.id.checkBoxNew,
            R.id.checkBoxUsed,
            R.id.checkBoxUnspecified,
    };

    public void onClear(View view) {
        EditText firstField = (EditText) this.findViewById(R.id.keywordsTextField);
        EditText secondField = (EditText) this.findViewById(R.id.minPriceTextField);
        EditText thirdField = (EditText) this.findViewById(R.id.maxPriceTextField);
        if (firstField != null) firstField.setText("");
        if (secondField != null) secondField.setText("");
        if (thirdField != null) thirdField.setText("");

        for (int value : checkBoxId) {
            ((CheckBox) findViewById(value)).setChecked(false);
        }

        ((Spinner) this.findViewById(R.id.spinner)).setSelection(0);

        ((TextView) this.findViewById(R.id.keywordsWarningTextView)).setVisibility(View.GONE);
        ((TextView) this.findViewById(R.id.priceRangeWarningTextView)).setVisibility(View.GONE);
    }

    public void onSearch(View view) throws JSONException {
        // handle invalid keywords
        EditText keywordsTextField = (EditText) this.findViewById(R.id.keywordsTextField);
        String keywords = keywordsTextField.getText().toString();
        if (keywords.isEmpty()) {
            ((TextView) this.findViewById(R.id.keywordsWarningTextView)).setVisibility(View.VISIBLE);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please fix all fields with errors", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        ((TextView) this.findViewById(R.id.keywordsWarningTextView)).setVisibility(View.GONE);
        searchFilter.put("keywords", keywords);

        // handle invalid price range
        EditText minPriceTextField = (EditText) this.findViewById(R.id.minPriceTextField);
        String minPriceString = minPriceTextField.getText().toString();
        EditText maxPriceTextField = (EditText) this.findViewById(R.id.maxPriceTextField);
        String maxPriceString = maxPriceTextField.getText().toString();
        try {
            if (!minPriceString.isEmpty()) {
                float minPrice = parseFloat(minPriceString);
                if (minPrice < 0) {
                    throw new InvalidPriceException("min_price < 0");
                }
                searchFilter.put("min_price", minPriceString);
            }
            if (!maxPriceString.isEmpty()) {
                float maxPrice = parseFloat(maxPriceString);
                if (maxPrice < 0) {
                    throw new InvalidPriceException("max_price < 0");
                }
                searchFilter.put("max_price", maxPriceString);
            }
            if (!minPriceString.isEmpty() && !maxPriceString.isEmpty()) {
                float minPrice = parseFloat(minPriceString);
                float maxPrice = parseFloat(maxPriceString);
                if (maxPrice < minPrice) {
                    throw new InvalidPriceException("max_price < min_price");
                }
            }
        } catch (Exception e) {
            ((TextView) this.findViewById(R.id.priceRangeWarningTextView)).setVisibility(View.VISIBLE);
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please fix all fields with errors", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        ((TextView) this.findViewById(R.id.priceRangeWarningTextView)).setVisibility(View.GONE);

        ArrayList<String> condition = new ArrayList<String>();
        for (int id : checkBoxId) {
            CheckBox checkBox = (CheckBox) findViewById(id);
            if (checkBox.isChecked()){
                Log.i("checked", checkBox.getText().toString());
                condition.add(checkBox.getText().toString());
            }
        }
        searchFilter.put("condition", new JSONArray(condition));

        Intent intent = new Intent(this, ItemCatalogActivity.class);
        intent.putExtra("SEARCH_PARAMS", searchFilter.toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("eBay Catalog Search");

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
            Log.i("selected", (String) parent.getItemAtPosition(pos));
            searchFilter.put("sort_order", optionMap.get((String) parent.getItemAtPosition(pos)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}