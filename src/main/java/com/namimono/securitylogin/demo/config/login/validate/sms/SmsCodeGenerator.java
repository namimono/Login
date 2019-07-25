package com.namimono.securitylogin.demo.config.login.validate.sms;

import com.namimono.securitylogin.demo.config.login.validate.ValidateCode;
import com.namimono.securitylogin.demo.config.login.validate.ValidateCodeGenerator;
import com.namimono.securitylogin.demo.config.properties.LoginProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {

    private Random random=new Random();
    @Autowired
    private LoginProperties loginProperties;

    @Override
    public ValidateCode generateCode() {
        int codeSize=loginProperties.getCodeSize();
        int livetime=loginProperties.getLivetime();
        System.out.println(livetime);
        String code = String.valueOf(random.nextInt((int)Math.pow(10,codeSize)));
//        String code = RandomStringUtils.randomNumeric(codeSize);
//
//        new SmsValidateCode(codeSize,livetime);
        return new SmsValidateCode(code,livetime);
    }
}
