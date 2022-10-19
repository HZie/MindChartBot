package hcil.hzie.mindchart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    MaterialButton saveBtn;
    /*
    MaterialButton createBtn;
    MaterialButton readBtn;
    MaterialButton updateBtn;
    MaterialButton deleteBtn;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverConnection.login();
        saveBtn = findViewById(R.id.btn_save);

        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                serverConnection.hit();
            }
        });
    };


        /*
        createBtn = findViewById(R.id.btn_create);
        readBtn = findViewById(R.id.btn_read);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);
        createBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                serverConnection.createLog("mood", 2);
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                serverConnection.readLog("all", "mood");
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                serverConnection.updateLog("2008-03-04","mood", 10);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                serverConnection.deleteLog("2008-03-05","mood");
            }
        });
    */



}