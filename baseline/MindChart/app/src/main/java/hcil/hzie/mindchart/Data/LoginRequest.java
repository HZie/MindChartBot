package hcil.hzie.mindchart.Data;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("pid")
    private String pid;

    @SerializedName("pwd")
    private String pwd;

    public String getPid() {
        return pid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public LoginRequest(String pid, String pwd){
        this.pid = pid;
        this.pwd = pwd;
    }
}
