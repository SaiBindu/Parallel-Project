package com.cg.paytm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cg.paytm.dao.WalletDao;
import com.cg.paytm.dao.WalletDaoImpl;
import com.cg.paytm.dto.Customer;
import com.cg.paytm.dto.Transactions;
import com.cg.paytm.dto.Wallet;
import com.cg.paytm.exception.InsufficientBalanceException;
import com.cg.paytm.exception.InvalidInputException;


public class WalletServiceImpl implements WalletService{

	private WalletDao dao;

	public WalletServiceImpl() {
		dao = new WalletDaoImpl();
	}

	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws InvalidInputException {
		if(isValid(mobileNo) && isValidName(name) && amount.compareTo(new BigDecimal(0)) > 0) {
			Wallet wallet = new Wallet();
			Customer customer = new Customer();
		
			wallet.setBalance(amount);
			customer.setName(name);
			customer.setMobileNo(mobileNo);
			customer.setWallet(wallet);
			
			dao.startTransaction();
			dao.save(customer);
			dao.commitTransaction();

			return customer;
		}
		else throw new InvalidInputException();

	}

	public Customer showBalance(String mobileNo) throws InvalidInputException {
		
		dao.startTransaction();
		Customer customer=dao.findOne(mobileNo);
		dao.commitTransaction();
		
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no ");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException {
		
		if(isValid(sourceMobileNo) == false || isValid(targetMobileNo) == false) 
			throw new InvalidInputException();
		
		Customer customer = withdrawAmount(sourceMobileNo, amount);
		depositAmount(targetMobileNo, amount);
		
		return customer;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException 
	{
		if(amount.compareTo(new BigDecimal(0)) <= 0) 
			throw new InvalidInputException("Enter valid amount");
		
		if(isValid(mobileNo)) {
			Customer customer = dao.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			
			wallet.setBalance(wallet.getBalance().add(amount));
	
			Transactions transaction = new Transactions();
			transaction.setMobileNo(mobileNo);
			transaction.setTransactionType("deposit  ");
			transaction.setAmount(amount);
			transaction.setDate(new Date());
			
			dao.startTransaction();
			dao.addTransaction(transaction);
			dao.update(customer);
			dao.commitTransaction();
			
			
			
			return customer;
		}
		else throw new InvalidInputException("Enter valid mobile number");
	}

	public boolean isValid(String mobileNo) {
		if(mobileNo.matches("[1-9][0-9]{9}")) 
		{
			return true;
		}		
		else 
			return false;
	}
	
	private boolean isValidName(String name) {
		if( name == null || name.trim().isEmpty() )
			return false;
		return true;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException 
	{	
		if(amount.compareTo(new BigDecimal(0)) <= 0) 
			throw new InvalidInputException("Enter valid amount");
		
		if(isValid(mobileNo)) 
		{
			Customer customer = dao.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			
			if(amount.compareTo(wallet.getBalance()) > 0) 
				throw new InsufficientBalanceException("Amount is not sufficient in your account");
			
			wallet.setBalance(wallet.getBalance().subtract(amount));
			customer.setWallet(wallet);
			
			Transactions transaction = new Transactions();
			transaction.setMobileNo(mobileNo);
			transaction.setTransactionType("withdraw");
			transaction.setAmount(amount);
			transaction.setBalance(customer.getWallet().getBalance());
			transaction.setStatus("Success");
			transaction.setDate(new Date());
			
			dao.startTransaction();
			dao.addTransaction(transaction);
			dao.update(customer);
			dao.addTransaction(transaction);
			dao.commitTransaction();
			
			return customer;
		}
		else throw new InvalidInputException("Enter valid mobile number");
	}

	@Override
	public List<Transactions> getAllTransactions(String mobileNumber) {
		return dao.getAllTransactions(mobileNumber);
	}
}
