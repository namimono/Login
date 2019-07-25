package com.namimono.securitylogin.demo.config.login.validate.sms;


import com.namimono.securitylogin.demo.config.login.validate.ValidateCode;

public class SmsValidateCode extends ValidateCode {

    private String code;
//    private LocalDateTime deadLine;

    public SmsValidateCode(String code,int liveTime) {
        super(liveTime);
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SmsValidateCode{" +
                "code='" + code + '\'' +
                ", deadLine=" + deadLine +
                '}';
    }

//    public SmsValidateCode(String code,int livetime) {
//        super(code);
//        //设定Smscode存在期限
//        this.deadLine=LocalDateTime.now().plusSeconds(livetime);
//    }
//
//    public boolean isDead(){
//
//        return LocalDateTime.now().isAfter(deadLine);
//    }

}
