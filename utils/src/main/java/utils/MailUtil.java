package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
//    private static MailUtil INSTANCE=null;
    private Properties props = new Properties();
    private Authenticator auth;
    private Session session;
    private Message message;
    private MailUtil() {

    }

    private MailUtil(Builder builder){
        this.props = builder.props;
        this.auth = builder.auth;
        this.session = builder.session;
        this.message = builder.message;
    }

//    public static MailUtil getInstance() {
//        return INSTANCE;
//    }


    public static class Builder{
        private Properties props ;
        private Authenticator auth;
        private Session session;
        private MimeMessage message;

        public Builder() {
        }

        public Builder props(String host, String port, String auth){
            this.props=new Properties();
            props.setProperty("mail.smtp.host", host);
            props.setProperty("mail.smtp.port", port);
            props.setProperty("mail.smtp.auth", auth);
            return this;
        }

        public Builder auth(final String username,final String password){
            this.auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };
            return this;
        }

        public Builder session(){
            this.session= Session.getInstance(this.props,this.auth);
            return this;
        }

        public Builder message(String from, String to, String subject, String content, Message.RecipientType type ){
            this.message = new MimeMessage(session);
// 设置发送者
            try {
                message.setFrom(new InternetAddress(from));
// 设置发送方式与接收者
                message.setRecipient(type, new InternetAddress(to));
// 设置主题
                message.setSubject(subject);
// 设置内容
                message.setContent(content, "text/html;charset=utf-8");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            return this;
        }
        public MailUtil build(){
//            if (INSTANCE==null){
//                synchronized (MailUtil.class){
//                    if (INSTANCE==null){
//                        INSTANCE=new MailUtil(this);
//                        return INSTANCE;
//                    }
//                }
//            }
            return new MailUtil(this);
        }

    }


    public void send(){
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public Authenticator getAuth() {
        return auth;
    }

    public void setAuth(Authenticator auth) {
        this.auth = auth;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
