package dao;
import dto.Wallet;
import exception.WalletException;
import exception.WalletRepositoryException;

import java.sql.SQLException;

public interface WalletDao {
    //CRUD
    Wallet addWallet(Wallet newWallet)throws WalletException, SQLException;
    Wallet getWalletById(Integer walletId)throws WalletException;
    Wallet updateWallet(Wallet updateWallet)throws WalletException;
    Wallet deleteWalletById(Integer walletID)throws WalletRepositoryException;
}
