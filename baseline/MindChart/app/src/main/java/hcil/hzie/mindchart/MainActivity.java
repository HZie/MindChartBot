package hcil.hzie.mindchart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    MaterialButton saveBtn;
    MaterialButton helpBtn;
    AutoCompleteTextView dropdownMenu;
    HelpFragment help;
    File todayHit;
    /*
    MaterialButton createBtn;
    MaterialButton readBtn;
    MaterialButton updateBtn;
    MaterialButton deleteBtn;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date date = new Date(System.currentTimeMillis());
        List<String> list = Arrays.asList(this.fileList());

        setContentView(R.layout.activity_main);
        serverConnection.login();
        saveBtn = findViewById(R.id.btn_save);
        helpBtn = findViewById(R.id.btn_help);
        help = new HelpFragment();

        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                serverConnection.hit();
                setContentView(R.layout.activity_end);
                todayHit = new File(MainActivity.this.getFilesDir(), date.toString());
                Log.d("Directory",MainActivity.this.getFilesDir().toString());
                try{
                    FileOutputStream fos = openFileOutput(date.toString(), MODE_PRIVATE);
                    fos.write("".getBytes(StandardCharsets.UTF_8));
                    fos.close();
                }
                catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });


        helpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, help).commit();
            }
        });

        String[] items={"-4","-3","-2","-1","0","1","2","3","4"};
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.list_values, items);

        dropdownMenu = findViewById(R.id.menu02_fear);
        dropdownMenu.setAdapter(itemAdapter);

        dropdownMenu = findViewById(R.id.menu03_irritation);
        dropdownMenu.setAdapter(itemAdapter);

        dropdownMenu = findViewById(R.id.menu04_interest);
        dropdownMenu.setAdapter(itemAdapter);

        dropdownMenu = findViewById(R.id.menu05_activity);
        dropdownMenu.setAdapter(itemAdapter);

        dropdownMenu = findViewById(R.id.menu06_speed);
        dropdownMenu.setAdapter(itemAdapter);

        dropdownMenu = findViewById(R.id.menu07_content);
        dropdownMenu.setAdapter(itemAdapter);
        for(String s: list){
            Log.d(TAG,s);
        }
        if(list.contains(date.toString())){
            setContentView(R.layout.activity_end);
        }
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