package com.namimono.securitylogin.demo.config.login.validate.mail;

import com.namimono.securitylogin.demo.config.login.validate.AbstractValidateCodeProcessor;
import com.namimono.securitylogin.demo.config.login.validate.ValidateCode;
import com.namimono.securitylogin.demo.config.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Component
@Slf4j
public class MailCodeProcessor extends AbstractValidateCodeProcessor {
    @Autowired
    private JavaMailSender javaMailSender;
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

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom("1050657124@qq.com");
            helper.setTo(address);
            helper.setSubject("验证码");
            helper.setText("验证码： <h1>"+((MailValidateCode)validateCode).getCode()+"</h1>", true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//        simpleMailMessage.setFrom("1050657124@qq.com");
//        simpleMailMessage.setTo(address);
//        simpleMailMessage.setSubject("验证码");
//        simpleMailMessage.setText("验证码： <h1>"+((MailValidateCode)validateCode).getCode()+"</h1>");
//
//        javaMailSender.send(simpleMailMessage);

//        MailUtil MailSender = new MailUtil.Builder()
//                .props("smtp.qq.com","25","true")
//                .auth("1050657124@qq.com","ktmpwbsirrqjbdah")
//                .session()
//                .message("1050657124@qq.com",
//                        address,
//                        "沙雕！",
//                        "验证码： <h1>"+((MailValidateCode)validateCode).getCode()+"</h1>",
//                        MimeMessage.RecipientType.TO)
//                .build();
//        MailSender.send();
    }

    @Override
    protected void save(ValidateCode validateCode, ServletWebRequest webRequest) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String address = request.getParameter("address");
        log.info("向邮箱: "+address+"发送验证码");
//        将验证码保存在session中
        request.getSession().setAttribute("validateCode",((MailValidateCode)validateCode).getCode());
    }
}
