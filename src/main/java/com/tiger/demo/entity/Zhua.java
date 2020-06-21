package com.tiger.demo.entity;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

public class Zhua {
    private int id;
    private String xiugaiqian;
    private String xiugaihou;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXiugaiqian() {
        return xiugaiqian;
    }

    public void setXiugaiqian(String xiugaiqian) {
        this.xiugaiqian = xiugaiqian;
    }

    public String getXiugaihou() {
        return xiugaihou;
    }

    public void setXiugaihou(String xiugaihou) {
        this.xiugaihou = xiugaihou;
    }

    @Override
    public String toString() {
        return "Zhua{" +
                "id=" + id +
                ", xiugaiqian='" + xiugaiqian + '\'' +
                ", xiugaihou='" + xiugaihou + '\'' +
                '}';
    }
}
