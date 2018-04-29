package model;

import javafx.beans.property.*;

import java.util.Date;

public class UseraccountsView {
    StringProperty Username;
    StringProperty Role;
    IntegerProperty UserID;
    LongProperty LastLoggedIn;
    Date lastindate;

    public Date getLastindate() {
        return lastindate;
    }

    public void setLastindate(Date lastindate) {
        this.lastindate = lastindate;
    }

    public String getUsername() {
        return Username.get();
    }

    public StringProperty usernameProperty() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username.set(username);
    }

    public String getRole() {
        return Role.get();
    }

    public StringProperty roleProperty() {
        return Role;
    }

    public void setRole(String role) {
        this.Role.set(role);
    }

    public int getUserID() {
        return UserID.get();
    }

    public IntegerProperty userIDProperty() {
        return UserID;
    }

    public void setUserID(int userID) {
        this.UserID.set(userID);
    }

    public long getLastLoggedIn() {
        return LastLoggedIn.get();
    }

    public LongProperty lastLoggedInProperty() {
        return LastLoggedIn;
    }

    public void setLastLoggedIn(long lastLoggedIn) {
        this.LastLoggedIn.set(lastLoggedIn);
    }

    public UseraccountsView(Integer uid, String uname, String roll, Date lastin) {
        this.UserID = new SimpleIntegerProperty(uid);
        this.Username = new SimpleStringProperty(uname);
        this.Role = new SimpleStringProperty(roll);
        //this.LastLoggedIn = new SimpleLongProperty(lli);
        this.lastindate = lastin;

    }


}
