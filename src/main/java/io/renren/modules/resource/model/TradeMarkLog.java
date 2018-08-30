package io.renren.modules.resource.model;

import java.util.Date;

public class TradeMarkLog {
    private String id;

    private String tradeMarkId;

    private String statusCode;

    private String statusDes;

    private String applyNumber;

    private String img;

    private String operationCode;

    private String operationDes;

    private String remark;

    private String createBy;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradeMarkId() {
        return tradeMarkId;
    }

    public void setTradeMarkId(String tradeMarkId) {
        this.tradeMarkId = tradeMarkId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public String getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationDes() {
        return operationDes;
    }

    public void setOperationDes(String operationDes) {
        this.operationDes = operationDes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}