package com.example.minlifeapp.entity;

import java.io.Serializable;

//将item中的数据封装到类MerchantsEntity
public class CommentEntity implements Serializable {
    private String username , comment ,updatetime ;
    int productid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", productid=" + productid +
                '}';
    }
}
