package com.ssafy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix="file")
public class FileUploadConfig {

    private String uploadDir;


    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}
