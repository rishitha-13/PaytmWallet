import dto.Wallet;
import exception.WalletException;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.WalletService;
import service.WalletServiceImpl;

import java.sql.SQLException;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class WalletServiceTest{
    WalletService walletService = new WalletServiceImpl();

    public WalletServiceTest() throws SQLException {
    }

    @BeforeAll
    public static void setupTestData() {
        System.out.println("Create all test data");
    }

    @Test
    public void registerWalletTest() throws SQLException{
        try {
            Wallet wallet =walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertNotNull(wallet);
            assertEquals(100, wallet.getId());

        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void loginValidWalletIdExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 1000.0, "123"));
            assertThrows(WalletException.class,()-> walletService.login(2500,"123"));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void loginValidPasswordIdExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 1000.0, "123"));
            assertThrows(WalletException.class,()-> walletService.login(100,"1234"));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void loginValidTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertEquals(true, walletService.login(100, "123"));
        }catch(WalletException e){
            e.printStackTrace();
        }
    }
    @Test
    public void addFundsToWalletValidWalletIdExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 1000.0, "123"));
            assertThrows(WalletException.class,()-> walletService.addFundsToWallet(5000,2000.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void addFundsToWalletValidWalletExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            walletService.unRegisterWallet(100,"123");
            assertThrows(WalletException.class,()-> walletService.addFundsToWallet(100,2000.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void addFundsToWalletValidAmountExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertThrows(WalletException.class,()-> walletService.addFundsToWallet(100,-2000.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void addFundsToWalletValidBalanceAmountExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", -100.0, "123"));
            assertThrows(WalletException.class,()-> walletService.addFundsToWallet(100,-2000.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void addFundsToWalletTest() throws SQLException{
        try{
            walletService.registerWallet(new Wallet(100,"test name1",1000.0,"123"));
            Double amount=walletService.addFundsToWallet(100,200.0);
            assertEquals(1200, amount);
        }catch(WalletException e){
            e.printStackTrace();
        }
    }
    @Test
    public void showWalletBalanceValidWalletIdExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 1000.0, "123"));
            assertThrows(WalletException.class,()-> walletService.showWalletBalance(1000));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void showWalletBalanceValidWalletExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 1000.0, "123"));
            walletService.unRegisterWallet(100,"123");
            assertThrows(WalletException.class,()-> walletService.showWalletBalance(100));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void showWalletBalanceTest()throws SQLException {

        try {
            walletService.registerWallet(new Wallet(10, "testname1", 1000.0, "123"));
            Double amount=walletService.showWalletBalance(10);
            assertEquals(1000.0, amount);
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void fromWalletValidWalletExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            walletService.unRegisterWallet(100,"123");
            assertThrows(WalletException.class,()-> walletService.fundTransfer(300,200,4000.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void toWalletValidWalletExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(200, "test name1", 100.0, "123"));
            walletService.unRegisterWallet(200,"123");
            assertThrows(WalletException.class,()-> walletService.fundTransfer(200,300,300.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void fundTransferValidAmountExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertThrows(WalletException.class,()-> walletService.fundTransfer(100,200,-300.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void fundTransferExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertThrows(WalletException.class,()-> walletService.fundTransfer(100,200,3000.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void fundTransferTest() throws SQLException {
        try{
            walletService.registerWallet(new Wallet(1300, "test name1", 1000.0, "123"));
            walletService.registerWallet(new Wallet(1301, "test name2", 2000.0, "12345"));
            assertEquals(true,walletService.fundTransfer(1300,1301,500.0));
        }catch(WalletException e){
            e.printStackTrace();
        }
    }
    @Test
    public void unRegisterWalletValidWalletExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            walletService.unRegisterWallet(100,"123");
            assertThrows(WalletException.class,()-> walletService.unRegisterWallet(100,"123"));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void unRegisterWalletExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertThrows(WalletException.class,()-> walletService.unRegisterWallet(100,"1234"));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void unRegisterWalletTest() throws SQLException{
        try{
            walletService.registerWallet(new Wallet(100,"test name1",1000.0,"123"));
            assertEquals(true,walletService.unRegisterWallet(100,"123"));
        }catch(WalletException e){
            e.printStackTrace();
        }
    }
    @Test
    public void withdrawFundsValidWalletIdExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertThrows(WalletException.class,()-> walletService.withdrawFunds(200,200.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void withdrawFundsValidWalletExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(1200, "test name1", 100.0, "123"));
            walletService.unRegisterWallet(1200,"123");
            assertThrows(WalletException.class,()-> walletService.withdrawFunds(1200,200.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void withdrawFundsValidAmountExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertThrows(WalletException.class,()-> walletService.withdrawFunds(100,-200.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void withdrawFundsExceptionTest() throws SQLException{
        try {
            walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertThrows(WalletException.class,()-> walletService.withdrawFunds(100,2000.0));
        } catch (WalletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
   public void withdrawFundsTest() {
        try {
            walletService.registerWallet(new Wallet(1500, "testname1", 100.0, "123"));
            assertEquals(50.0, walletService.withdrawFunds(1500, 50.0));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterAll
    public static void destroyTestData() {
        System.out.println("Clear all test data");
    }
}

