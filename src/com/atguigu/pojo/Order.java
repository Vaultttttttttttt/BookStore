package com.atguigu.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
* @description: TODO
* @author wxj27
* @date 2023-03-26 9:08
* @version 1.0
*/

public class Order {
    private String orderId;
    private Timestamp creatTime;
    private BigDecimal price;
    //0未发货，1已发货，2已签收
    private Integer status=0;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Timestamp creatTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.creatTime = creatTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", creatTime=" + creatTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
