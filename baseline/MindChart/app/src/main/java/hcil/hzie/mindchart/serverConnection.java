package hcil.hzie.mindchart;

import android.util.Log;

import java.util.List;

import hcil.hzie.mindchart.Data.HitRequest;
import hcil.hzie.mindchart.Data.LoginRequest;
import hcil.hzie.mindchart.Data.PostResponse;
import hcil.hzie.mindchart.Data.createLogRequest;
import hcil.hzie.mindchart.Data.readLogResponse;
import hcil.hzie.mindchart.Data.updateLogRequest;
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
        initApi.getPostResponse(loginRequest).enqueue(new Callback<PostResponse>(){

            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Log.d(TAG, "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    PostResponse result = response.body();

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
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // hit
    static void hit(){
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        TAG = "serverConnection-hit()";
        // request obj 생성
        HitRequest req = new HitRequest(pid);

        initApi.getPostResponse(req).enqueue(new Callback<PostResponse>(){
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Log.d(TAG, "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    PostResponse result = response.body();

                    // 받은 토큰 저장
                    String message = result.getMessage();

                    Log.d(TAG, "message: "+message);
                }
                else{
                    Log.e(TAG, "message: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // NOT USING BELOW CODE
    // create log
    static void createLog(String category, int val){
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        TAG = "serverConnection-createLog()";
        // request obj 생성
        createLogRequest req = new createLogRequest(pid, category, val);

        initApi.getPostResponse(req).enqueue(new Callback<PostResponse>(){
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Log.d(TAG, "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    PostResponse result = response.body();

                    // 받은 토큰 저장
                    String message = result.getMessage();

                    Log.d(TAG, "message"+message);
                }
                else{
                    Log.e(TAG, "message"+response.message());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // read log
    static void readLog(String log_date, String category){
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        TAG = "serverConnection-readLog()";

        initApi.getReadLogResponse(pid, log_date, category).enqueue(new Callback<readLogResponse>() {
            @Override
            public void onResponse(Call<readLogResponse> call, Response<readLogResponse> response) {
                Log.d(TAG, "Data fetch success");

                if(response.isSuccessful() && response.body() != null){
                    readLogResponse res = response.body();
                    Log.d(TAG, "response.raw: " + response.raw());

                    List<readLogResponse.logs> logs = res.getLogs();
                    for(int i = 0; i < logs.size(); i++){
                        readLogResponse.logs log = logs.get(i);
                        Log.d(TAG, "logs["+i+"]: date-" + log.getLog_date() + ", category: " + log.getCategory() + ", val: " + log.getVal());
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

        initApi.getPostResponse(req).enqueue(new Callback<PostResponse>(){
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Log.d(TAG, "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    PostResponse result = response.body();

                    // 받은 토큰 저장
                    String message = result.getMessage();

                    Log.d(TAG, "message"+message);
                }
                else{
                    Log.e(TAG, "message"+response.message());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    // delete log
    static void deleteLog(String log_date, String category){
        retrofitClient = RetrofitClient.getInstance();
        initApi = RetrofitClient.getRetrofitInterface();
        TAG = "serverConnection-deleteLog()";

        initApi.getPostResponse(pid, log_date, category).enqueue(new Callback<PostResponse>(){
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Log.d(TAG, "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    PostResponse result = response.body();

                    // 받은 토큰 저장
                    String message = result.getMessage();

                    Log.d(TAG, "message"+message);
                }
                else{
                    Log.e(TAG, "message"+response.message());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
