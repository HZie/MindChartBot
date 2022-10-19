package hcil.hzie.mindchart;

import com.google.gson.annotations.SerializedName;

import hcil.hzie.mindchart.Data.PostResponse;
import hcil.hzie.mindchart.Data.LoginRequest;
import hcil.hzie.mindchart.Data.createLogRequest;
import hcil.hzie.mindchart.Data.readLogResponse;
import hcil.hzie.mindchart.Data.updateLogRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface initApi {
    @POST("/user/login")
    Call<PostResponse> getPostResponse(@Body LoginRequest loginRequest);

    @POST("/user/createLog")
    Call<PostResponse> getPostResponse(@Body createLogRequest create);

    @GET("/user/readLog")
    Call<readLogResponse> getReadLogResponse(
            @Query("pid")  String pid,
            @Query("log_date")  String log_date,
            @Query("category") String category
            );

    @POST("/user/updateLog")
    Call<PostResponse> getPostResponse(@Body updateLogRequest update);
    @DELETE("/user/deleteLog")
    Call<PostResponse> getPostResponse(@Query("pid") String pid, @Query("log_date") String log_date, @Query("category") String category);
}
