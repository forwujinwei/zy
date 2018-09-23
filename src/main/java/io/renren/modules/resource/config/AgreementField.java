package io.renren.modules.resource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: jinweia.wu
 * @Date: 2018/9/23 11:21
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "pdfField")
public class AgreementField {
     private String  company_address;
     private String  sign_address_1;
     private String  bank_name;
     private String  bank_account_name;
     private String  bank_account_number;

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getSign_address_1() {
        return sign_address_1;
    }

    public void setSign_address_1(String sign_address_1) {
        this.sign_address_1 = sign_address_1;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_account_name() {
        return bank_account_name;
    }

    public void setBank_account_name(String bank_account_name) {
        this.bank_account_name = bank_account_name;
    }

    public String getBank_account_number() {
        return bank_account_number;
    }

    public void setBank_account_number(String bank_account_number) {
        this.bank_account_number = bank_account_number;
    }
}
