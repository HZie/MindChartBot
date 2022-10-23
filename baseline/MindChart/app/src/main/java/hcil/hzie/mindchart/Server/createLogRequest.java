package hcil.hzie.mindchart.Server;

import com.google.gson.annotations.SerializedName;

public class createLogRequest {
    @SerializedName("pid")
    private String pid;

    @SerializedName("category")
    private String category;

    @SerializedName("val")
    private int val;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public createLogRequest(String pid, String category, int val){
        this.pid = pid;
        this.category = category;
        this.val = val;
    }
}
