package com.namimono.securitylogin.demo.config.login.validate.image;


import com.namimono.securitylogin.demo.config.login.validate.ValidateCode;

import java.awt.image.BufferedImage;

public class ImageValidateCode extends ValidateCode {

    private BufferedImage image;

    public ImageValidateCode(BufferedImage image,int liveTime) {
        super(liveTime);
        this.image = image;
    }

}
