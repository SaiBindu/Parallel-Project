package com.cg.paytm.test;


import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.assertNotEquals
			


import com.cg.paytm.dto.Customer;
import com.cg.paytm.dto.Wallet;
import com.cg.paytm.exception.InsufficientBalanceException;
import com.cg.paytm.exception.InvalidInputException;
import com.cg.paytm.service.WalletService;
import com.cg.paytm.service.WalletServiceImpl;



public class TestClass {

	static WalletService walletService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		walletService = new WalletServiceImpl();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testCreateAccount() throws InvalidInputException {
		Customer customer = new Customer();
		Wallet wallet = new Wallet();
		String name = "bindu";
		String number = "7095134616";
		BigDecimal amount = new BigDecimal(5000);
		
		customer.setName(name);
		customer.setMobileNo(number);
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		
		customer = walletService.createAccount(name, number, amount);
		
		assertNotEquals(null, customer);
	}
	
	

	@Test
	public void testshowBalance() throws InvalidInputException {
		String number = "7095134616";
		
		Customer customer = walletService.showBalance(number);
		
		assertNotEquals(null,customer);
	}
	*/
	@Test(expected = InsufficientBalanceException.class)
	public void testWithdrawAmount() throws InvalidInputException, InsufficientBalanceException {
		String name = "bindu";
		String mobileNumber = "7095134616";
		BigDecimal balance = new BigDecimal(2000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}
	
	@Test
	public void testDepositAmount() throws InvalidInputException {
		String name = "meghana";
		String mobileNumber = "7095134616";
		BigDecimal balance = new BigDecimal(3000);
		
		Customer customer = walletService.createAccount(name, mobileNumber, balance);
		System.out.println(customer.getWallet().getBalance());
		Customer customer1 = walletService.depositAmount(mobileNumber, new BigDecimal(3000));
		System.out.println(customer1.getWallet().getBalance());
		assertEquals(6000 , customer1.getWallet().getBalance());
	}
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() throws InvalidInputException, InsufficientBalanceException {
		String name = "meghana";
		String mobileNumber = "70951346163";
		BigDecimal balance = new BigDecimal(3000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}


}
