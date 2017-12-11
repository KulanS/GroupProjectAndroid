package com.example.tharindu.myapplication;


public class ShopClass {
    private String title;
    private String desc;

    public ShopClass(String title, String desc) {
        this.setTitle(title);
        this.setDesc(desc);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
