package model;

import javafx.beans.property.*;

import java.util.Date;

public class TransactionModel {

    IntegerProperty transID;
    IntegerProperty userID;
    StringProperty type;
    StringProperty crycode;
    DoubleProperty cAmt;
    DoubleProperty usAmt;
    Date date;

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

    public String getCrycode() {
        return crycode.get();
    }

    public StringProperty crycodeProperty() {
        return crycode;
    }

    public void setCrycode(String crycode) {
        this.crycode.set(crycode);
    }

    public double getcAmt() {
        return cAmt.get();
    }

    public DoubleProperty cAmtProperty() {
        return cAmt;
    }

    public void setcAmt(double cAmt) {
        this.cAmt.set(cAmt);
    }

    public double getUsAmt() {
        return usAmt.get();
    }

    public DoubleProperty usAmtProperty() {
        return usAmt;
    }

    public void setUsAmt(double usAmt) {
        this.usAmt.set(usAmt);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionModel(int tid, int uId, String typ, String cc, Double cryptAmt, Double usdAmt, Date ts){
        this.transID = new SimpleIntegerProperty(tid);
        this.userID = new SimpleIntegerProperty(uId);
        this.type = new SimpleStringProperty(typ);
        this.crycode = new SimpleStringProperty(cc);
        this.cAmt = new SimpleDoubleProperty(cryptAmt);
        this.usAmt = new SimpleDoubleProperty(usdAmt);
        this.date = ts;
    }

}
