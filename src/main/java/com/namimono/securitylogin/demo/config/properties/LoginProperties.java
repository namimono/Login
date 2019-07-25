package com.namimono.securitylogin.demo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.login")
public class LoginProperties {

    private int codeSize;
    private int imageWidth;
    private int imageHigh;

    private int livetime;

    public int getLivetime() {
        return livetime;
    }

    public void setLivetime(int livetime) {
        this.livetime = livetime;
    }

    public int getCodeSize() {
        return codeSize;
    }

    public void setCodeSize(int codeSize) {
        this.codeSize = codeSize;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHigh() {
        return imageHigh;
    }

    public void setImageHigh(int imageHigh) {
        this.imageHigh = imageHigh;
    }
}
