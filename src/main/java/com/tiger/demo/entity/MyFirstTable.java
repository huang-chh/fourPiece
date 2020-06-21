package com.tiger.demo.entity;

/**
 * @Date 2020/6/21
 * @Author tiger
 */

public class MyFirstTable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyFristTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
