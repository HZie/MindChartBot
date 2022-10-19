package hcil.hzie.mindchart.Data;

import com.google.gson.annotations.SerializedName;

public class readLogRequest {
    @SerializedName("pid")
    private String pid;

    // if log_date is -1, it means get everything
    @SerializedName("log_date")
    private String log_date;

    @SerializedName("category")
    private String category;

    public readLogRequest(String pid, String log_date, String category){
        this.pid = pid;
        this.log_date = log_date;
        this.category = category;
    }

}
