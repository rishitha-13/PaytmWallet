package controller;
import dto.Wallet;
import exception.WalletException;
import service.WalletService;
import service.WalletServiceImpl;

import java.sql.*;
import java.util.Scanner;

public class Controller {
    public static void main(String []args) throws SQLException {

        WalletService walletService = new WalletServiceImpl();
        Scanner sc = new Scanner(System.in);

        System.out.println("1.Registration" + '\n' + "2.Login" + '\n' + "3.Add funds to Wallet" + '\n' + "4.Check Wallet Balance" + '\n' + "5.Fund Transfer" + '\n' + "6.Withdraw an amount" + '\n' + "7.Delete the wallet" +'\n' + "8.Exit");
        System.out.println("Choose One option");
        int select = sc.nextInt();
        while (select < 9) {
            try {

                switch (select) {
                    case 1:
                        System.out.println("Enter  newWalletId: ");
                        Integer newWalletId = sc.nextInt();
                        System.out.println("Enter newWalletName: ");
                        String newWalletName = sc.next();
                        System.out.println("Enter  newWalletBalance: ");
                        Double newWalletBalance = sc.nextDouble();
                        System.out.println("Enter newWalletPassword: ");
                        String newWalletPassword = sc.next();
                        Wallet newWallet = walletService.registerWallet(new Wallet(newWalletId, newWalletName, newWalletBalance, newWalletPassword));
                        System.out.println(newWallet);
                        break;
                    case 2:
                        System.out.println("Enter loginWalletId: ");
                        Integer loginWalletId = sc.nextInt();
                        System.out.println("Enter loginWalletPassword: ");
                        String loginWalletPassword = sc.next();
                        Boolean isLoginSuccess = walletService.login(loginWalletId, loginWalletPassword);
                        System.out.println("Login is Success? : " + isLoginSuccess);
                        break;
                    case 3:
                        System.out.println("Enter addFundWalletId: ");
                        Integer addFundWalletId = sc.nextInt();
                        System.out.println("Enter addFundAmount: ");
                        Double addFundAmount = sc.nextDouble();
                        Double updatedBalance = walletService.addFundsToWallet(addFundWalletId, addFundAmount);
                        System.out.println("Updated Balance is :" + updatedBalance);
                        break;
                    case 4:
                        System.out.println("Enter checkWalletId: ");
                        Integer checkWalletId = sc.nextInt();
                        Double checkBalance = walletService.showWalletBalance(checkWalletId);
                        System.out.println("The balance of wallet is :" + checkBalance);
                        break;
                    case 5:
                        System.out.println("Enter fromWalletId: ");
                        Integer fromWalletId = sc.nextInt();
                        System.out.println("Enter toWalletId: ");
                        Integer toWalletId = sc.nextInt();
                        System.out.println("Enter amountTransfer: ");
                        Double amountTransfer = sc.nextDouble();
                        Boolean isAmountTransferred = walletService.fundTransfer(fromWalletId, toWalletId, amountTransfer);
                        System.out.println("The Amount is transferred from one wallet to other: " + isAmountTransferred);
                        break;
                    case 6:
                        System.out.println("Enter withdrawWalletId: ");
                        Integer withdrawWalletId = sc.nextInt();
                        System.out.println("Enter withdrawAmount: ");
                        Double withdrawAmount = sc.nextDouble();
                        Double balanceAmount = walletService.withdrawFunds(withdrawWalletId, withdrawAmount);
                        System.out.println("The Amount after withdraw from wallet: " + balanceAmount);
                        break;
                    case 7:
                        System.out.println("Enter validWalletId: ");
                        Integer validWalletId = sc.nextInt();
                        System.out.println("Enter validWalletPassword: ");
                        String validWalletPassword = sc.next();
                        Wallet deleteWallet = walletService.unRegisterWallet(validWalletId, validWalletPassword);
                        System.out.println("Is wallet is deleted? :" + deleteWallet);
                        break;
                    case  8:
                        System.out.println("Over");
                        select=9;
                        break;
                }

            } catch (WalletException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
            if(select==9) break;
            System.out.println("1.Registration" + '\n' + "2.Login" + '\n' + "3.Add funds to Wallet" + '\n' + "4.Check Wallet Balance" + '\n' + "5.Fund Transfer" + '\n' + "6.Withdraw an amount" + '\n' + "7.Delete the wallet" +'\n' + "8.Exit");
            System.out.println("Choose One option");
            select = sc.nextInt();


        }
    }
}
