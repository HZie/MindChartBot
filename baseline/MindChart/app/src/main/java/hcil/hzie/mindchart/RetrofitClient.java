package hcil.hzie.mindchart;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static initApi initApi;
    // 서버 BASE주소
    private static final String BASE = "http://ec2-3-35-3-61.ap-northeast-2.compute.amazonaws.com:3000/";

    private RetrofitClient(){
        // 로그를 위한 Interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client) // 로그 기능 추가
                .build();

        initApi = retrofit.create(initApi.class);
    }

    public static RetrofitClient getInstance(){
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static initApi getRetrofitInterface(){
        return initApi;
    }
}
