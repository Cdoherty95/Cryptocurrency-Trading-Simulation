package controller;

import model.DaoDBFunctions;
import model.DaoUsers;
import model.DaoWallet;

public interface DAOInterface {
    DaoUsers daoUsers = new DaoUsers();
    DaoWallet daoWallet = new DaoWallet();
    DaoDBFunctions daoDBFunctions = new DaoDBFunctions();

}
