package dao;
import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dto.Wallet;
import exception.WalletException;
import exception.WalletRepositoryException;

public class WalletDaoImpl implements WalletDao {
    // Create collection to store the Wallet information.
    Map<Integer, Wallet> wallets = new HashMap<>();

    @Override
    public Wallet addWallet(Wallet newWallet) throws WalletException,SQLException {

        try {
            Connection com;
            com=DriverManager.getConnection("jdbc:mysql://mysql5040.site4now.net:3306/db_a8f58e_datadb", "a8f58e_datadb", "ford1234");

            PreparedStatement ast=com.prepareStatement("INSERT INTO casestudy VALUES (?,?,?,?)");
            ast.setInt(1,newWallet.getId());
            ast.setString(2,newWallet.getName());
            ast.setDouble(3,newWallet.getBalance());
            ast.setString(4,newWallet.getPassword());
            ast.execute();
            com.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    return newWallet;
    }

    @Override
    public Wallet getWalletById(Integer walletId) throws WalletException {
        Connection com;
        try {
             com=DriverManager.getConnection("jdbc:mysql://MYSQL5040.site4now.net:3306/db_a8f58e_datadb", "a8f58e_datadb", "ford1234");

            PreparedStatement ast=com.prepareStatement("Select * FROM casestudy WHERE id=?");
            ast.setInt(1, walletId);
            ResultSet resultSet = ast.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            Double balance = resultSet.getDouble("balance");
            String password = resultSet.getString("password");
            Wallet wallet = new Wallet(walletId, name, balance, password);
            return wallet;
        }catch(SQLException e){
            throw new WalletException("Error occurred while Getting wallet by ID");
        }

    }

    @Override
    public Wallet updateWallet(Wallet updateWallet) throws WalletException {
        Wallet newUpdatedWallet;
        Connection com;
        try{
            com=DriverManager.getConnection("jdbc:mysql://MYSQL5040.site4now.net:3306/db_a8f58e_datadb", "a8f58e_datadb", "ford1234");

            PreparedStatement ast = com.prepareStatement(" UPDATE casestudy SET balance=? WHERE id=?");
            PreparedStatement ast2 = com.prepareStatement("SELECT * FROM casestudy WHERE id=?");
            ast.setDouble(1, updateWallet.getBalance());
            ast.setInt(2, updateWallet.getId());
            ast.executeUpdate();
            // updated wallet
            ast2.setInt(1, updateWallet.getId());

            ResultSet resultSet = ast2.executeQuery();
            resultSet.next();

            newUpdatedWallet = new Wallet(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
                    resultSet.getString(4));

        } catch (Exception e) {
            throw new WalletException("Error occurred while Updating Wallet");
        }
        return newUpdatedWallet;
        }

    @Override
    public Wallet deleteWalletById(Integer walletID) throws WalletRepositoryException{
        Wallet wallet=new Wallet();
        Connection com;
    try{
        com=DriverManager.getConnection("jdbc:mysql://MYSQL5040.site4now.net:3306/db_a8f58e_datadb", "a8f58e_datadb", "ford1234");

        PreparedStatement preparedStatement = com.prepareStatement("DELETE FROM casestudy WHERE id=?");
        preparedStatement.setInt(1, walletID);
        preparedStatement.executeUpdate();

    } catch (Exception e) {
        throw new WalletRepositoryException("Error occurred while Deletion of wallet");
        }
        return wallet;
    }
}

