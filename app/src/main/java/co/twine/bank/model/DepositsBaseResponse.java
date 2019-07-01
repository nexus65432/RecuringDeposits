package co.twine.bank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepositsBaseResponse {

    @SerializedName("total_amount")
    @Expose
    public String total_amount;

    @SerializedName("results")
    @Expose
    public Results results;

    public String getTotalAmount() {
        return total_amount;
    }

    public Results getResults() {
        return results;
    }

}
