package hcil.hzie.mindchart.Data;

import com.google.gson.annotations.SerializedName;

public class deleteLogResponse {
    @SerializedName("resultCode")
    public String resultCode;

    @SerializedName("message")
    public String message;

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getMessage(){
        return message;
    }
}
