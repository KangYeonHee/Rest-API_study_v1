package com.example.demo.api.job.vo;

public class JobVO {
    private int id;
    private String name;
    private int income;

    public JobVO() {}
    public JobVO(int id, String name, int income) {
        this.id = id;
        this.name = name;
        this.income = income;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getIncome() { return this.income; }
    public void setIncome(int income) { this.income = income; }
}
