package hcil.hzie.mindchart.Data;

import com.google.gson.annotations.SerializedName;

public class deleteLogRequest {
    @SerializedName("pid")
    private String pid;

    @SerializedName("log_date")
    private String log_date;

    @SerializedName("category")
    private String category;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public deleteLogRequest(String pid, String log_date, String category){
        this.pid = pid;
        this.log_date = log_date;
        this.category = category;
    }
}
