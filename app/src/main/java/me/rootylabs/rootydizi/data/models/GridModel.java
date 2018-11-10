package me.rootylabs.rootydizi.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GridModel {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("codeName")
    @Expose
    private String codeName;
    @SerializedName("data")
    @Expose
    private List<GridSerie> data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public List<GridSerie> getData() {
        return data;
    }

    public void setData(List<GridSerie> data) {
        this.data = data;
    }

}