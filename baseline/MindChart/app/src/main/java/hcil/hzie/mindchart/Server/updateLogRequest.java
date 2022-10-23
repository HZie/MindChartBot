package hcil.hzie.mindchart.Server;

import com.google.gson.annotations.SerializedName;

public class updateLogRequest {
    @SerializedName("pid")
    private String pid;

    @SerializedName("log_date")
    private String log_date;

    @SerializedName("category")
    private String category;

    @SerializedName("val")
    private int val;

    public updateLogRequest(String pid, String log_date, String category, int val){
        this.pid = pid;
        this.log_date = log_date;
        this.category = category;
        this.val = val;
    }

}
