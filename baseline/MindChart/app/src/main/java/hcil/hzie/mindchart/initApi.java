package hcil.hzie.mindchart;

import hcil.hzie.mindchart.Data.LoginRequest;
import hcil.hzie.mindchart.Data.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface initMyApi {
    @POST("/user/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);
}
