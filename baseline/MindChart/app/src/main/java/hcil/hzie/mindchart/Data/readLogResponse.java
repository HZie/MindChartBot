package hcil.hzie.mindchart.Data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class readLogResponse {
    @SerializedName("resultCode")
    public String resultCode;

    @SerializedName("list")
    public ArrayList<logData> list =  new ArrayList<>();

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ArrayList<logData> getList() {
        return list;
    }

    public void setList(ArrayList<logData> list) {
        this.list = list;
    }
}
