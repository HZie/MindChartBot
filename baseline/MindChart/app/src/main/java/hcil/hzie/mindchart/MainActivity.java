package hcil.hzie.mindchart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
    View baselineView, chatView;
    RecyclerView recyclerView;
    ArrayList<ChatData> data;

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
                        todayHit = new File(MainActivity.this.getFilesDir(), date.toString());
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
                recyclerView = findViewById(R.id.recyclerView);
                TextInputEditText input = findViewById(R.id.input);
                MaterialButton send = findViewById(R.id.btn_send);
                data = new ArrayList<>();

                ChatAdapter chatAdapter = new ChatAdapter();

                // data 뷰에 저장
                chatAdapter.submitData(getData());

                recyclerView.setAdapter(chatAdapter);

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("sendBtn","하이");
                        if(input.getText().toString().length() == 0)
                            return;
                        Date timestamp = new Date(System.currentTimeMillis());
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                        String time = sdf.format(timestamp).toString();
                        data.add(new ChatData(time, input.getText().toString(), "user"));
                        chatAdapter.submitData(data);
                    }
                });
                break;

            // chatbot w/voice
            default:
                setContentView(R.layout.activity_end);
                break;
        }

        helpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, help).commit();
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
        data.add(new ChatData("05:22", "안넝하세요 마인드봇입니다.", "마인드봇"));
        data.add(new ChatData("05:23", "오늘의 기분은 어떠세요?", "마인드봇"));
        data.add(new ChatData("06:23", "좋아!", "user"));
        data.add(new ChatData("07:01", "얼만큼 좋으세요?", "마인드봇"));
        data.add(new ChatData("11:20", "일상생활에 지장 없을 정도!", "user"));
        return data;
    }
}