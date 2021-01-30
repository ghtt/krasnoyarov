package com.akrasnoyarov.developerslife.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("result")
    @Expose
    private List<GifImage> result = null;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;

    public List<GifImage> getResult() {
        return result;
    }

    public void setResult(List<GifImage> result) {
        this.result = result;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}