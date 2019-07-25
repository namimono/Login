package com.namimono.securitylogin.demo.config.login.validate;


import java.time.LocalDateTime;

public class ValidateCode {

//    private LocalDateTime deadLine;
//    private int liveTime;
    protected LocalDateTime deadLine;

    public ValidateCode(int liveTime) {
//        this.liveTime=liveTime;
        this.deadLine=LocalDateTime.now().plusSeconds(liveTime);
    }

    public boolean isDead(){

        return LocalDateTime.now().isAfter(deadLine);
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "ValidateCode{" +
                "deadLine=" + deadLine +
                '}';
    }
}
