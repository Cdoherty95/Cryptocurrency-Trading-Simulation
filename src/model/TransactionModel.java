package model;

import javafx.beans.property.*;

import java.util.Date;

public class TransactionModel {

    IntegerProperty transID;
    IntegerProperty userID;
    StringProperty type;
    StringProperty firstCurrencyCode;
    StringProperty secondCurrencyCode;
    DoubleProperty firstCurrencyAmount;
    DoubleProperty secondCurrencyAmount;
    Date date;

    public String getFirstCurrencyCode() {
        return firstCurrencyCode.get();
    }

    public StringProperty firstCurrencyCodeProperty() {
        return firstCurrencyCode;
    }

    public void setFirstCurrencyCode(String firstCurrencyCode) {
        this.firstCurrencyCode.set(firstCurrencyCode);
    }

    public String getSecondCurrencyCode() {
        return secondCurrencyCode.get();
    }

    public StringProperty secondCurrencyCodeProperty() {
        return secondCurrencyCode;
    }

    public void setSecondCurrencyCode(String secondCurrencyCode) {
        this.secondCurrencyCode.set(secondCurrencyCode);
    }

    public double getFirstCurrencyAmount() {
        return firstCurrencyAmount.get();
    }

    public DoubleProperty firstCurrencyAmountProperty() {
        return firstCurrencyAmount;
    }

    public void setFirstCurrencyAmount(double firstCurrencyAmount) {
        this.firstCurrencyAmount.set(firstCurrencyAmount);
    }

    public double getSecondCurrencyAmount() {
        return secondCurrencyAmount.get();
    }

    public DoubleProperty secondCurrencyAmountProperty() {
        return secondCurrencyAmount;
    }

    public void setSecondCurrencyAmount(double secondCurrencyAmount) {
        this.secondCurrencyAmount.set(secondCurrencyAmount);
    }

    public int getTransID() {
        return transID.get();
    }

    public IntegerProperty transIDProperty() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID.set(transID);
    }

    public int getUserID() {
        return userID.get();
    }

    public IntegerProperty userIDProperty() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionModel(int tid, int uId, String typ, String fcc, Double fca, String scc, Double sca, Date ts) {
        this.transID = new SimpleIntegerProperty(tid);
        this.userID = new SimpleIntegerProperty(uId);
        this.type = new SimpleStringProperty(typ);
        this.firstCurrencyCode = new SimpleStringProperty(fcc);
        this.firstCurrencyAmount = new SimpleDoubleProperty(fca);
        this.secondCurrencyCode = new SimpleStringProperty(scc);
        this.secondCurrencyAmount = new SimpleDoubleProperty(sca);

        this.date = ts;
    }

}
