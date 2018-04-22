package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Date;

public class BtcHistory {
    Date date;
    DoubleProperty ethPrice;
    DoubleProperty usdPrice;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getEthPrice() {
        return ethPrice.get();
    }

    public DoubleProperty ethPriceProperty() {
        return ethPrice;
    }

    public void setEthPrice(double ethPrice) {
        this.ethPrice.set(ethPrice);
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





    public BtcHistory(Date d, Double eth, Double usd){
        this.date = d;
        this.ethPrice = new SimpleDoubleProperty(eth);
        this.usdPrice = new SimpleDoubleProperty(usd);
    }


}
