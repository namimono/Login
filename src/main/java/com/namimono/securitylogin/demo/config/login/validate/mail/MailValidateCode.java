package com.namimono.securitylogin.demo.config.login.validate.mail;


import com.namimono.securitylogin.demo.config.login.validate.ValidateCode;

public class MailValidateCode extends ValidateCode {
    private String code;

    public MailValidateCode(int liveTime,String code) {
        super(liveTime);
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
