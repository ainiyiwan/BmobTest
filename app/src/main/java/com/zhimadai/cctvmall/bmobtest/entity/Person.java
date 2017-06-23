package com.zhimadai.cctvmall.bmobtest.entity;

import cn.bmob.v3.BmobObject;

/**
 * Author ： zhangyang
 * Date   ： 2017/6/23
 * Email  :  18610942105@163.com
 * Description  :
 */

public class Person extends BmobObject {

    private String name;
    private String sex;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
