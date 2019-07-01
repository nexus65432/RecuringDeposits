package co.twine.bank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deposits {

    @SerializedName("depositTitle")
    @Expose
    public String depositTitle;

    @SerializedName("status")
    @Expose
    public String status;

    public String getDepositTitle() {
        return depositTitle;
    }

    public String getStatus() {
        return status;
    }

}
