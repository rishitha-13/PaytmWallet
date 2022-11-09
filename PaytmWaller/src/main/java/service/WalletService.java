package service;
import dto.Wallet;
import exception.WalletException;

import java.sql.SQLException;


public interface WalletService {
    Wallet registerWallet(Wallet newWallet) throws WalletException, SQLException;

    Boolean login(Integer walletId,String password)throws WalletException;

    Double addFundsToWallet(Integer walletId, Double amount)throws WalletException;

    Double showWalletBalance(Integer walletId)throws WalletException;

    Boolean fundTransfer(Integer fromId, Integer toId, Double amount)throws WalletException;

    Wallet unRegisterWallet(Integer walletId,String password)throws WalletException;

    Double withdrawFunds(Integer walletID, Double amount) throws WalletException;

}
