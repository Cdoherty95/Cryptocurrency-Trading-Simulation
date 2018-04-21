package model;

import javafx.beans.property.DoubleProperty;

import java.util.Date;

public class CryptoHistory {
    Date date;
    DoubleProperty cryptoPrice;
    DoubleProperty usdPrice;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCryptoPrice() {
        return cryptoPrice.get();
    }

    public DoubleProperty cryptoPriceProperty() {
        return cryptoPrice;
    }

    public void setCryptoPrice(double cryptoPrice) {
        this.cryptoPrice.set(cryptoPrice);
    }

    public double getUsdPrice() {
        return usdPrice.get();
    }

    public DoubleProperty usdPriceProperty() {
        return usdPrice;
    }

    public void setUsdPrice(double usdPrice) {
        this.usdPrice.set(usdPrice);
    }

    public CryptoHistory(Date d, DoubleProperty cp, DoubleProperty usd){
        this.date = d;
        this.cryptoPrice = cp;
        this.usdPrice = usd;
    }


}
