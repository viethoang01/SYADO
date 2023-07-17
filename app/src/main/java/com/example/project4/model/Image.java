package com.example.project4.model;

import java.io.Serializable;

public class Image implements Serializable {
    private String dataImage,imgName;

    public Image() {
    }

    public Image(String dataImage, String imgName) {
        this.dataImage = dataImage;
        this.imgName = imgName;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
