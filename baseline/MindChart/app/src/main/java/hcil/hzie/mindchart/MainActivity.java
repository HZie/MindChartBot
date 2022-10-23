package hcil.hzie.mindchart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hcil.hzie.mindchart.Server.serverConnection;

public class MainActivity extends AppCompatActivity {
    int MODE = 1;   // 0: baseline, 1: chatbot
    String TAG = "MainActivity";
    MaterialButton saveBtn;
    MaterialButton helpBtn;
    AutoCompleteTextView dropdownMenu;
    HelpFragment help;
    File todayHit;
    View baselineView;
    RecyclerView chatView;

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

        // login
        serverConnection.login();

        // 공통
        help = new HelpFragment();
        setContentView(R.layout.activity_main);
        helpBtn = findViewById(R.id.btn_help);
        baselineView = findViewById(R.id.baseline);
        chatView = findViewById(R.id.chat);

        baselineView.setVisibility(View.GONE);
        chatView.setVisibility(View.GONE);

        switch(MODE){
            // baseline
            case 0:
                baselineView.setVisibility(View.VISIBLE);
                saveBtn = findViewById(R.id.btn_save);
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
                saveBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        //serverConnection.hit();
                        setContentView(R.layout.activity_end);
                        //todayHit = new File(MainActivity.this.getFilesDir(), date.toString());
                        //Log.d("Directory",MainActivity.this.getFilesDir().toString());
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
                break;

            // chatbot
            case 1:
                chatView.setVisibility(View.VISIBLE);
                ChatAdapter chatAdapter = new ChatAdapter();

                // data 뷰에 저장
                chatAdapter.submitData(getData());

                chatView.setAdapter(chatAdapter);
                break;

            // chatbot w/voice
            default:
                setContentView(R.layout.activity_end);
                break;
        }

        helpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, help).commit();
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

    @Override
    protected void onResume() {
        super.onResume();
        serverConnection.hit();
    }

    private ArrayList<ChatData> getData(){
        ArrayList<ChatData> data = new ArrayList<>();
        data.add(new ChatData("05:22", "hello", "user"));
        data.add(new ChatData("05:23", "can I have your phone number?", "user"));
        data.add(new ChatData("06:23", "000000", "user1"));
        data.add(new ChatData("07:01", "111111", "user2"));
        data.add(new ChatData("11:20", "I'll let you know later.", "user3"));
        return data;
    }
}