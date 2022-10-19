package hcil.hzie.mindchart;

import android.util.Log;

import java.util.ArrayList;

import hcil.hzie.mindchart.Data.LoginRequest;
import hcil.hzie.mindchart.Data.LoginResponse;
import hcil.hzie.mindchart.Data.createLogRequest;
import hcil.hzie.mindchart.Data.createLogResponse;
import hcil.hzie.mindchart.Data.deleteLogRequest;
import hcil.hzie.mindchart.Data.deleteLogResponse;
import hcil.hzie.mindchart.Data.logData;
import hcil.hzie.mindchart.Data.readLogRequest;
import hcil.hzie.mindchart.Data.readLogResponse;
import hcil.hzie.mindchart.Data.updateLogRequest;
import hcil.hzie.mindchart.Data.updateLogResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class serverConnection {
    static String TAG = "serverConnection";
    private static String pid = "test";
    private static String pwd = "1234";

    // server connection
    private static RetrofitClient retrofitClient;
    private static initApi initApi;

    // server connection
    public static void login(){
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        TAG = "serverConnection-login()";
        // pid와 pwd 저장
        LoginRequest loginRequest = new LoginRequest(pid, pwd);

        // loginRequest에 저장된 데이터와 getLoginResponse 함수 실행 후 응답 받기
        initApi.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>(){

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG, "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    LoginResponse result = response.body();

                    // 받은 토큰 저장
                    String message = result.getMessage();

                    String success = "200";
                    String error = "204";

                    Log.d(TAG, "message"+message);
                }
                else{
                    Log.e(TAG, "message"+response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // create log
    static void createLog(String category, int val){
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        TAG = "serverConnection-createLog()";
        // request obj 생성
        createLogRequest req = new createLogRequest(pid, category, val);

        initApi.getCreateLogResponse(req).enqueue(new Callback<createLogResponse>(){
            @Override
            public void onResponse(Call<createLogResponse> call, Response<createLogResponse> response) {
                Log.d(TAG, "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    createLogResponse result = response.body();

                    // 받은 토큰 저장
                    String message = result.getMessage();

                    Log.d(TAG, "message"+message);
                }
                else{
                    Log.e(TAG, "message"+response.message());
                }
            }

            @Override
            public void onFailure(Call<createLogResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // read log
    static void readLog(String log_date, String category){
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        TAG = "serverConnection-readLog()";
        readLogRequest req = new readLogRequest(pid, log_date, category);

        initApi.getReadLogResponse(req).enqueue(new Callback<readLogResponse>(){
            @Override
            public void onResponse(Call<readLogResponse> call, Response<readLogResponse> response) {
                Log.d(TAG, "Data fetch success");

                if(response.isSuccessful() && response.body() != null){
                    readLogResponse result = response.body();

                    ArrayList<logData> list = result.getList();
                    for(int i = 0; i < list.size(); i++){
                        Log.d(TAG, "list["+i+"]: date-" + list.get(i).getLog_date()+", category-"+list.get(i).getCategory());
                    }
                }
                else{
                    Log.e(TAG, "error");
                }
            }

            @Override
            public void onFailure(Call<readLogResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // update log
    static void updateLog(String log_date, String category, int val){
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        TAG = "serverConnection-updateLog()";
        // request obj 생성
        updateLogRequest req = new updateLogRequest(pid, log_date, category, val);

        initApi.getUpdateLogResponse(req).enqueue(new Callback<updateLogResponse>(){
            @Override
            public void onResponse(Call<updateLogResponse> call, Response<updateLogResponse> response) {
                Log.d(TAG, "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    updateLogResponse result = response.body();

                    // 받은 토큰 저장
                    String message = result.getMessage();

                    Log.d(TAG, "message"+message);
                }
                else{
                    Log.e(TAG, "message"+response.message());
                }
            }

            @Override
            public void onFailure(Call<updateLogResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // delete log
    static void deleteLog(String log_date, String category){
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        TAG = "serverConnection-deleteLog()";
        // request obj 생성
        deleteLogRequest req = new deleteLogRequest(pid, log_date, category);

        initApi.getDeleteLogResponse(req).enqueue(new Callback<deleteLogResponse>(){
            @Override
            public void onResponse(Call<deleteLogResponse> call, Response<deleteLogResponse> response) {
                Log.d(TAG, "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    deleteLogResponse result = response.body();

                    // 받은 토큰 저장
                    String message = result.getMessage();

                    Log.d(TAG, "message"+message);
                }
                else{
                    Log.e(TAG, "message"+response.message());
                }
            }

            @Override
            public void onFailure(Call<deleteLogResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
