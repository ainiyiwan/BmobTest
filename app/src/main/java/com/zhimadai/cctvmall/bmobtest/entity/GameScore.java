package com.zhimadai.cctvmall.bmobtest.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Author ： zhangyang
 * Date   ： 2017/6/23
 * Email  :  18610942105@163.com
 * Description  :
 */

//必须要继承自BmobObject类
public class GameScore extends BmobObject {

    private String playerName;
    private Integer score;
    private Boolean isPay;
    private BmobFile pic;
    // 仅在客户端使用，不希望被gson序列化提交到后端云，记得用transient修饰
    private transient Integer count;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean isPay) {
        this. isPay = isPay;
    }

    public BmobFile getPic() {
        return pic;
    }

    public void setPic(BmobFile pic) {
        this.pic = pic;
    }
}
