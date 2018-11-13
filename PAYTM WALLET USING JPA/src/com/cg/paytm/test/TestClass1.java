package com.cg.paytm.test;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;


import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.paytm.dto.Customer;
import com.cg.paytm.exception.InsufficientBalanceException;
import com.cg.paytm.exception.InvalidInputException;
import com.cg.paytm.service.WalletService;
import com.cg.paytm.service.WalletServiceImpl;



public class TestClass1 {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		service = new WalletServiceImpl();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() throws InvalidInputException 
	{
		service.createAccount(null, "9492451841", new BigDecimal(2000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() throws InvalidInputException 
	{
		service.createAccount("", "8639119207", new BigDecimal(2000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() throws InvalidInputException 
	{
		service.createAccount("deepika", "709", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() throws InvalidInputException 
	{
		service.createAccount("deepika", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() throws InvalidInputException 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}
	
	//@Test(expected=InvalidInputException.class)
//	public void testCreateAccount6() throws InvalidInputException 
//	{
//		service.createAccount("dpika", "9440827011", new BigDecimal(9000));
//		service.createAccount("sree", "9440827011", new BigDecimal(10000));
//	}
	
	/*@Test
	public void testCreateAccount7() throws InvalidInputException 
	{
		Customer customer1 = service.createAccount("deepika", "7095134689", new BigDecimal(5000));
		Customer customer2 = null;
		
		assertNotEquals(customer2, customer1);
	}
	*/
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount8() throws InvalidInputException 
	{
		service.createAccount("deepika", "9440827011", new BigDecimal(-100));
	}
	
   @Test(expected=InvalidInputException.class)
	public void testCreateAccount9() throws InvalidInputException 
	{
		Customer actual=service.createAccount("deepika", null, new BigDecimal(5000.75));
    }
	
	/*@Test
	public void testCreateAccount10() throws InvalidInputException 
	{
		Customer actual=service.createAccount("Aarthi", "8754922472", new BigDecimal(5000.75));
		Customer expected=null;
		
		assertNotEquals(expected, actual);
	}*/


	
//	@Test(expected=InvalidInputException.class)
//	public void testShowBalance11() throws InvalidInputException 
//	{
//		service.showBalance(null);		
//	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance12() throws InvalidInputException 
	{
		service.showBalance("");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance13() throws InvalidInputException 
	{
		service.showBalance("12345");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance14() throws InvalidInputException 
	{
		service.showBalance("8765432109");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance15() throws InvalidInputException 
	{
		service.showBalance("87654321091");		
	}
	
	
	@Test
	public void testShowBalance16() throws InvalidInputException 
	{
		service.createAccount("deepika", "9492451841", new BigDecimal(7000));
		Customer customer=service.showBalance("9492451841");
		BigDecimal expectedResult=new BigDecimal(7000);
		BigDecimal obtainedResult=customer.getWallet().getBalance();
		
		assertEquals(expectedResult, obtainedResult);
		
	}
	
	@Test
	public void testWithdrawAmount() throws InvalidInputException, InsufficientBalanceException {
		String name = "deepika";
		String mobileNumber = "7095134721";
		BigDecimal balance = new BigDecimal(7000);
		
		service.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		Customer customer = service.withdrawAmount(mobileNumber, amount);
		assertEquals(new BigDecimal(4000), customer.getWallet().getBalance());
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void testWithdrawAmount1() throws InvalidInputException, InsufficientBalanceException {
		String name = "deepika";
		String mobileNumber = "7095134611";
		BigDecimal balance = new BigDecimal(2000);
		
		service.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		service.withdrawAmount(mobileNumber, amount);
	}
	
	@Test
	public void testDepositAmount() throws InvalidInputException {
		String name = "deepika";
		String mobileNumber = "7095134612";
		BigDecimal balance = new BigDecimal(3000);
		
		Customer customer = service.createAccount(name, mobileNumber, balance);
		
		Customer customer1 = service.depositAmount(mobileNumber, new BigDecimal(3000));
		
		assertEquals(new BigDecimal(6000), customer1.getWallet().getBalance());
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount1() throws InvalidInputException {
		String name = "deepika";
		String mobileNumber = "7095134619";
		BigDecimal balance = new BigDecimal(2000);
		
		Customer customer = service.createAccount(name, mobileNumber, balance);
		
		Customer customer1 = service.depositAmount(mobileNumber, new BigDecimal(0));
		
	}
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() throws InvalidInputException, InsufficientBalanceException {
		String name = "deepika";
		String mobileNumber = "94408270113";
		BigDecimal balance = new BigDecimal(3000);
		
		service.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		service.withdrawAmount(mobileNumber, amount);
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void testFundTransfer() throws InvalidInputException, InsufficientBalanceException 
	{
		service.createAccount("dpika", "9876543210", new BigDecimal(1000));
		service.createAccount("Rahul", "8121101096", new BigDecimal(1000));
		service.fundTransfer("9876543210", "8121101096", new BigDecimal(2000));
	}

}

	