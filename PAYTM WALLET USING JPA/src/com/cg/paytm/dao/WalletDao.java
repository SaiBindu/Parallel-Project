package com.cg.paytm.dao;

import java.util.List;

import com.cg.paytm.dto.Customer;
import com.cg.paytm.dto.Transactions;
import com.cg.paytm.exception.InvalidInputException;




public interface WalletDao {

	public boolean save(Customer customer) throws InvalidInputException;

	public Customer findOne(String mobileNo) throws InvalidInputException;

//	public void remove(String mobileNo);
	
	public void startTransaction();
	
	public void commitTransaction();

	public void update(Customer customer);

	public void addTransaction(Transactions transactions);

	public List<Transactions> getAllTransactions(String mobileNumber);
}
