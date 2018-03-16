package com.test.model;

/**
 * Created by lusaijie on 2018/3/15.
 */
public class OutStockDetail {
    private Integer oId;

    private Integer outstockId;

    private Integer pId;

    private Integer oNum;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getOutstockId() {
        return outstockId;
    }

    public void setOutstockId(Integer outstockId) {
        this.outstockId = outstockId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getoNum() {
        return oNum;
    }

    public void setoNum(Integer oNum) {
        this.oNum = oNum;
    }
}