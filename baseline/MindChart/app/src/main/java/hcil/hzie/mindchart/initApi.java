package hcil.hzie.mindchart;

import hcil.hzie.mindchart.Data.LoginRequest;
import hcil.hzie.mindchart.Data.LoginResponse;
import hcil.hzie.mindchart.Data.createLogRequest;
import hcil.hzie.mindchart.Data.createLogResponse;
import hcil.hzie.mindchart.Data.deleteLogRequest;
import hcil.hzie.mindchart.Data.deleteLogResponse;
import hcil.hzie.mindchart.Data.readLogRequest;
import hcil.hzie.mindchart.Data.readLogResponse;
import hcil.hzie.mindchart.Data.updateLogRequest;
import hcil.hzie.mindchart.Data.updateLogResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface initApi {
    @POST("/user/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @POST("/user/createLog")
    Call<createLogResponse> getCreateLogResponse(@Body createLogRequest create);

    @GET("/user/readLog")
    Call<readLogResponse> getReadLogResponse(@Body readLogRequest read);

    @POST("/user/updateLog")
    Call<updateLogResponse> getUpdateLogResponse(@Body updateLogRequest update);

    @DELETE("/user/deleteLog")
    Call<deleteLogResponse> getDeleteLogResponse(@Body deleteLogRequest delete);
}
