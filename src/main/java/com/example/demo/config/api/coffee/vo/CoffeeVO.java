package com.example.demo.config.api.coffee.vo;

import java.util.UUID;

public class CoffeeVO {
    private String id;
    private String name;
    private int rowNum;
    private int limitNum;

    public CoffeeVO() {}
    public CoffeeVO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CoffeeVO(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRowNum() {
        return rowNum;
    }
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getLimitNum() {
        return limitNum;
    }
    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }
}
