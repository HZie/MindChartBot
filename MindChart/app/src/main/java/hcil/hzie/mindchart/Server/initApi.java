package hcil.hzie.mindchart.Server;

import hcil.hzie.mindchart.Server.HitRequest;
import hcil.hzie.mindchart.Server.PostResponse;
import hcil.hzie.mindchart.Server.LoginRequest;
import hcil.hzie.mindchart.Server.createLogRequest;
import hcil.hzie.mindchart.Server.readLogResponse;
import hcil.hzie.mindchart.Server.updateLogRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface initApi {
    @POST("/user/login")
    Call<PostResponse> getPostResponse(@Body LoginRequest loginRequest);

    @POST("/user/hit")
    Call<PostResponse> getPostResponse(@Body HitRequest hitRequest);

    // NOT USING BELOW CODE
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
