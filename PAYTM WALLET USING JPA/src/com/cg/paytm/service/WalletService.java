package com.cg.paytm.service;

import java.math.BigDecimal;
import java.util.List;

import com.cg.paytm.dto.Customer;
import com.cg.paytm.dto.Transactions;
import com.cg.paytm.exception.InsufficientBalanceException;
import com.cg.paytm.exception.InvalidInputException;


public interface WalletService {
	public Customer createAccount(String name ,String mobileno, BigDecimal amount) throws InvalidInputException;
	public Customer showBalance (String mobileno) throws InvalidInputException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
	public Customer depositAmount (String mobileNo,BigDecimal amount ) throws InvalidInputException;
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
	public List<Transactions> getAllTransactions(String mobileNumber);
}
