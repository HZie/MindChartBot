package hcil.hzie.mindchart.Server;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class readLogResponse {
    @SerializedName("resultCode")
    public int resultCode;
    @SerializedName("list")
    ArrayList<logs> logs = new ArrayList<>();

    public class logs{
        @SerializedName("log_date") String log_date;
        @SerializedName("category") String category;
        @SerializedName("val")      int val;

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

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public int getResultCode() {
        return resultCode;
    }

    public ArrayList<readLogResponse.logs> getLogs() {
        return logs;
    }
}
