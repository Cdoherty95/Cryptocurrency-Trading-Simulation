package application;

import model.DaoDBFunctions;
import model.DaoUpdateCurrencyHist;
import model.DaoUsers;
import model.DaoWallet;

public interface DAOInterface {
    DaoUsers daoUsers = new DaoUsers();
    DaoWallet daoWallet = new DaoWallet();
    DaoUpdateCurrencyHist daoUpdateCurrencyHist = new DaoUpdateCurrencyHist();
    DaoDBFunctions daoDBFunctions = new DaoDBFunctions();

}
