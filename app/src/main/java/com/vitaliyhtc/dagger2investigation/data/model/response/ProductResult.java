package com.vitaliyhtc.dagger2investigation.data.model.response;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProductResult {

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private Product result;


    public ProductResult(int status, String message, Product result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product getResult() {
        return result;
    }

    public void setResult(Product result) {
        this.result = result;
    }
}
