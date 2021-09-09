package com.example.minlifeapp.entity;

import java.io.Serializable;

//将item中的数据封装到类MerchantsEntity
public class BillEntity implements Serializable {
    private String MerchantsName, MerchantsAddress,
            MerchantsSendingFee, MerchantsMessage;
    private String MerchantsPicture;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerchantsPicture() {
        return MerchantsPicture;
    }

    public void setMerchantsPicture(String merchantsPicture) {
        MerchantsPicture = merchantsPicture;
    }

    public  String getMerchantsName() {
        return MerchantsName;
    }

    public void setMerchantsName(String merchantsName) {
        MerchantsName = merchantsName;
    }

    public String getMerchantsAddress() {
        return MerchantsAddress;
    }

    public void setMerchantsAddress(String merchantsAddress) {
        MerchantsAddress = merchantsAddress;
    }

    public String getMerchantsSendingFee() {
        return MerchantsSendingFee;
    }

    public void setMerchantsSendingFee(String merchantsSendingFee) {
        MerchantsSendingFee = merchantsSendingFee;
    }

    public String getMerchantsMessage() {
        return MerchantsMessage;
    }

    public void setMerchantsMessage(String merchantsMessage) {
        MerchantsMessage = merchantsMessage;
    }
}
