package hcil.hzie.mindchart.Data;

import com.google.gson.annotations.SerializedName;

public class logData {
        @SerializedName("log_date")
        String log_date;
        @SerializedName("category")
        String category;
        @SerializedName("val")
        int val;

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
