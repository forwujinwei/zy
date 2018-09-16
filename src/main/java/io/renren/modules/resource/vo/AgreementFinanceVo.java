package io.renren.modules.resource.vo;

import java.util.Date;

/**
 * @Auther: jinweia.wu
 * @Date: 2018/9/16 16:32
 * @Description:
 */
public class AgreementFinanceVo {
    private String id;

    private String agreementId;

    private String adviser;

    private Integer money;

    private String statusCode;

    private String statusDes;

    private String remarksDetail;

    private String firstParty;

    private String remitCode;

    private String remitDes;

    private Date remitDate;

    private String remitAccount;

    private String gatheringAccount;

    private Integer governmentCost;

    private Integer proxyCost;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getAdviser() {
        return adviser;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getRemarksDetail() {
        return remarksDetail;
    }

    public void setRemarksDetail(String remarksDetail) {
        this.remarksDetail = remarksDetail;
    }

    public String getRemitCode() {
        return remitCode;
    }

    public void setRemitCode(String remitCode) {
        this.remitCode = remitCode;
    }

    public String getRemitDes() {
        return remitDes;
    }

    public void setRemitDes(String remitDes) {
        this.remitDes = remitDes;
    }

    public Date getRemitDate() {
        return remitDate;
    }

    public void setRemitDate(Date remitDate) {
        this.remitDate = remitDate;
    }

    public String getRemitAccount() {
        return remitAccount;
    }

    public void setRemitAccount(String remitAccount) {
        this.remitAccount = remitAccount;
    }

    public String getGatheringAccount() {
        return gatheringAccount;
    }

    public void setGatheringAccount(String gatheringAccount) {
        this.gatheringAccount = gatheringAccount;
    }

    public Integer getGovernmentCost() {
        return governmentCost;
    }

    public void setGovernmentCost(Integer governmentCost) {
        this.governmentCost = governmentCost;
    }

    public Integer getProxyCost() {
        return proxyCost;
    }

    public void setProxyCost(Integer proxyCost) {
        this.proxyCost = proxyCost;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
