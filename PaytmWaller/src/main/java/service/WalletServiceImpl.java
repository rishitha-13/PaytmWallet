package service;


import dao.WalletDao;
import dao.WalletDaoImpl;
import dto.Wallet;
import exception.WalletException;
import exception.WalletRepositoryException;

import java.sql.SQLException;

public class WalletServiceImpl implements WalletService {
    private  WalletDao walletRepository = new WalletDaoImpl();

    public WalletServiceImpl() throws SQLException {
    }

    @Override
    public Wallet registerWallet(Wallet newWallet) throws WalletException,SQLException {
        try {
            if (newWallet == null);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return this.walletRepository.addWallet(newWallet);
    }

    @Override
    public Boolean login(Integer walletId, String password) throws WalletException {
        if (walletId == null)
            throw new WalletException("WalletId doesn't exist");
        Wallet loginWallet= this.walletRepository.getWalletById(walletId);
        if(password != loginWallet.getPassword())
            throw new WalletException("Forget Password Please Try Again.");

        return true;
    }

    @Override
    public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException {
            if (walletId == null)
                throw new WalletException("WalletId doesn't exist");
            Wallet fundsToWallet = this.walletRepository.getWalletById(walletId);
            if(fundsToWallet==null)
                throw new WalletException("WalletId doesn't exist");
            Double balanceAmount = fundsToWallet.getBalance();
            if (amount <= 0.0)
                throw new WalletException("Enter Minimum Rs.1 to add amount to Wallet");
            if (balanceAmount<=0.0)
                throw new WalletException("The balance in account is 0.0 ");
            balanceAmount += amount;
            fundsToWallet.setBalance(balanceAmount);
            this.walletRepository.updateWallet(fundsToWallet);
            return this.walletRepository.getWalletById(walletId).getBalance();

    }

    @Override
    public Double showWalletBalance(Integer walletId) throws WalletException {
        if(walletId == null)
            throw new WalletException("WalletID does not exist");
        Wallet foundWallet = this.walletRepository.getWalletById(walletId);

        if (foundWallet == null)
            throw new WalletException("Wallet does not exists for id:" + walletId);

        return foundWallet.getBalance();
    }

    @Override
    public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException {
        Wallet fromWallet = this.walletRepository.getWalletById(fromId);
        Wallet toWallet = this.walletRepository.getWalletById(toId);
        if (fromWallet == null)
            throw new WalletException("From Wallet id does not exists");
        if (toWallet == null)
            throw new WalletException("To Wallet id does not exists");

        Double fromBalance = fromWallet.getBalance();
        if (fromBalance < amount)
            throw new WalletException("From wallet have insufficent balance:" + fromBalance);
        if (amount<=0)
            throw new WalletException("The amount should be greater Rs.1 to Transfer");
        fromBalance -= amount;
        fromWallet.setBalance(fromBalance);
        Double toBalance = toWallet.getBalance();
        toBalance += amount;
        toWallet.setBalance(toBalance);
        this.walletRepository.updateWallet(fromWallet);
        this.walletRepository.updateWallet(toWallet);


        return true;
    }

    @Override
    public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException {
        Wallet foundWallet = this.walletRepository.getWalletById(walletId);
        if (foundWallet == null)
            throw new WalletException("Wallet not found to unregister");

        if (!foundWallet.getPassword().equals(password))
            throw new WalletException("Password does n't match to unregister your account.");

        Wallet deletedWallet;
        Wallet delete;
        try {
            deletedWallet = this.walletRepository.deleteWalletById(walletId);
            delete = this.walletRepository.updateWallet(deletedWallet);
        } catch (WalletRepositoryException e) {

            throw new WalletException(e.getMessage());
        }
        return delete;
    }

    @Override
    public Double withdrawFunds(Integer walletID, Double amount) throws WalletException {
        if (walletID == null)
            throw new WalletException("WalletId doesn't exist");
        if (amount <= 0.0)
            throw new WalletException("Enter Minimum Rs.1 to add amount to Wallet");
        Wallet withdrawWallet= this.walletRepository.getWalletById(walletID);
        if(withdrawWallet == null)
            throw new WalletException("Wallet does not exists for id:" + walletID);
        Double balanceWithdraw;
        balanceWithdraw = withdrawWallet.getBalance();
        if(balanceWithdraw < amount)
            throw new WalletException("The amount is insufficient to withdraw");
        balanceWithdraw =balanceWithdraw-amount;
        withdrawWallet.setBalance(balanceWithdraw);
        this.walletRepository.updateWallet(withdrawWallet);
        Double currentBalance;
        Wallet currentWallet= this.walletRepository.getWalletById(walletID);
       currentBalance=currentWallet.getBalance();
        return currentBalance;

    }


}
