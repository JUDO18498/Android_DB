package com.example.admin.coontactdb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spiner1;
    Button button1;
    EditText edittext1,edittext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button);
        spiner1=(Spinner)findViewById(R.id.spinner);
        edittext1=(EditText)findViewById(R.id.editText);
        edittext2=(EditText)findViewById(R.id.editText2);
        loadSpinnerData();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label = edittext1.getText().toString();
                String label2=edittext2.getText().toString();

                if (label.trim().length() > 0 && label2.trim().length() > 0) {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.insertLabel(label,label2);
                    edittext1.setText("");
                    edittext2.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edittext1.getWindowToken(), 0);
                    loadSpinnerData();
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter label name or Number",Toast.LENGTH_SHORT).show();

                }


            }
        });

        }
    private void loadSpinnerData(){
        DatabaseHandler db=new DatabaseHandler(getApplicationContext());
        List<String> labels=db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner1.setAdapter(dataAdapter);
}
    public void onItemSelected(AdapterView<?>parent,View view,int position,long id) {
        String label = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "you Selected" + label, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?>arg0) {
    }
    }
