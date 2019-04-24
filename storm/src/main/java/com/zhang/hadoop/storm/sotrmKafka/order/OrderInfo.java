package com.zhang.hadoop.storm.sotrmKafka.order;

import jodd.json.JsonSerializer;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by zhang yufei on 2019/1/21.
 */
public class OrderInfo implements Serializable {

    private String orderId;
    private Date createOrderTime;
    private String paymentId;
    private Date paymentTime;
    private String productId;
    private String productName;
    private long productPrice;
    private String shopId;
    private String shopName;
    private long payPrice;
    private int num;

    public String random(){
        this.orderId= UUID.randomUUID().toString();
        this.productId="100100100";
        this.paymentId=UUID.randomUUID().toString();
        this.productPrice=new Random().nextInt(1000);
        this.payPrice=new Random().nextInt();
        String date="2015-11-11 12:22:12";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            this.createOrderTime=simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new JsonSerializer().deep(true).serialize(this);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(Date createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public long getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(long payPrice) {
        this.payPrice = payPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }



}
