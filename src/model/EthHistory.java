package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Date;

public class EthHistory {
    Date date;
    DoubleProperty btcPrice;
    DoubleProperty usdPrice;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getBtcPrice() {
        return btcPrice.get();
    }

    public DoubleProperty btcPriceProperty() {
        return btcPrice;
    }

    public void setBtcPrice(double btcPrice) {
        this.btcPrice.set(btcPrice);
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

    public EthHistory(Date d, Double btc, Double usd){
        this.date = d;
        this.btcPrice = new SimpleDoubleProperty(btc);
        this.usdPrice = new SimpleDoubleProperty(usd);
    }
}
