package hcil.hzie.mindchart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import hcil.hzie.mindchart.Data.LoginRequest;
import hcil.hzie.mindchart.Data.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pid = "test";
        String pwd = "1234";

        LoginResponse(pid, pwd);
    }

    public void LoginResponse(String pid, String pwd){
        // pid와 pwd 저장
        LoginRequest loginRequest = new LoginRequest(pid, pwd);

        // retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        // loginRequest에 저장된 데이터와 getLoginResponse 함수 실행 후 응답 받기
        initMyApi.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>(){

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("retrofit", "Data fetch success");

                // 통신 성공
                if(response.isSuccessful() && response.body() != null){
                    // response.body()를 result에 저장
                    LoginResponse result = response.body();

                    // 받은 토큰 저장
                    String message = result.getMessage();

                    String success = "200";
                    String error = "204";

                    Log.d("message", message);
                }
                else{
                    Log.d("오류","오류");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("통신오류", t.getMessage());
            }
        });

    }
}