package com.namimono.securitylogin.demo.config.login.validate.sms;

import com.namimono.securitylogin.demo.config.login.validate.AbstractValidateCodeProcessor;
import com.namimono.securitylogin.demo.config.login.validate.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor {
    @Autowired
    private SmsCodeGenerator SmsCodeGenerator;
    @Override
    protected ValidateCode generateCode() {
        ValidateCode validateCode = SmsCodeGenerator.generateCode();
        System.out.println(validateCode);
        return validateCode;
    }

    @Override
    protected void send(ValidateCode validateCode, ServletWebRequest webRequest) {
//        webRequest.getRequest();
        System.out.println("smsProcessor send");

    }

    @Override
    protected void save(ValidateCode validateCode, ServletWebRequest webRequest) {
        System.out.println("smsProcessor save");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
//        httpServletRequest.getSession().setAttribute("SmsCode",validateCode);

    }
}
