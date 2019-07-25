package com.namimono.securitylogin.demo.config.login.validate.mail;

import com.namimono.securitylogin.demo.config.login.validate.AbstractValidateCodeProcessor;
import com.namimono.securitylogin.demo.config.login.validate.ValidateCode;
import com.namimono.securitylogin.demo.config.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Component
@Slf4j
public class MailCodeProcessor extends AbstractValidateCodeProcessor {
    private Random random=new Random();

    /**
     *      * public void create(ServletWebRequest webRequest){
     *      *         ValidateCode validateCode = generateCode();
     *      *         save(validateCode,webRequest);
     *      *         send(validateCode,webRequest);
     *     }
     * @return
     */
    @Override
    protected ValidateCode generateCode() {
        int livetime=60;
        String code = String.valueOf(random.nextInt((int)Math.pow(10,6)));
        return new MailValidateCode(60,code);
    }

    @Override
    protected void send(ValidateCode validateCode, ServletWebRequest webRequest) {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String address = request.getParameter("address");

        MailUtil MailSender = new MailUtil.Builder()
                .props("smtp.qq.com","25","true")
                .auth("1050657124@qq.com","ktmpwbsirrqjbdah")
                .session()
                .message("1050657124@qq.com",
                        address,
                        "沙雕！",
                        "验证码： <h1>"+((MailValidateCode)validateCode).getCode()+"</h1>",
                        MimeMessage.RecipientType.TO)
                .build();
        MailSender.send();
    }

    @Override
    protected void save(ValidateCode validateCode, ServletWebRequest webRequest) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String address = request.getParameter("address");
        log.info("向邮箱: "+address+"发送验证码");
        request.getSession().setAttribute("validateCode",((MailValidateCode)validateCode).getCode());
    }
}
