package com.ebayshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public void onClick(View view) {
        EditText keywordsTextField = (EditText) findViewById(R.id.keywordsTextField);
        String s = keywordsTextField.getText().toString();
        Log.i("info", " aaaaa");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}