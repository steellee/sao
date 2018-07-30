package com.lakala.sh.sao.common.vo;

/**
 * 测试用
 */
public class TestInfo {
    String Name = "";
    String Ip = "";

    public TestInfo() {
        this.Name = "";
        this.Ip = "";
    }

    public TestInfo(String Name, String Ip) {
        this.Name = Name;
        this.Ip = Ip;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIp() {
        return Ip;
    }
}
