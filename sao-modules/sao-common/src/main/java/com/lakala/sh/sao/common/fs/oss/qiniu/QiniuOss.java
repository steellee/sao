package com.lakala.sh.sao.common.fs.oss.qiniu;

public class QiniuOss {
    private String urlprefix;
    // 要上传的空间
    private String bucketName;
    private String accessKey;
    private String secretKey;
    private boolean isPrivate;

    public String getUrlprefix() {
        return urlprefix;
    }

    public void setUrlprefix(String urlprefix) {
        this.urlprefix = urlprefix;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
