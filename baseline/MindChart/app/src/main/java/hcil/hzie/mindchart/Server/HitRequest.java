package hcil.hzie.mindchart.Server;

import com.google.gson.annotations.SerializedName;

public class HitRequest {
    @SerializedName("pid")
    private String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }


    public HitRequest(String pid){
        this.pid = pid;
    }
}
