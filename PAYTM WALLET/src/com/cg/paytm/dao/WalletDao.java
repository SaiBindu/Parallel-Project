package com.cg.paytm.dao;

import com.cg.paytm.dto.Customer;



public interface WalletDao {
	public boolean save(Customer customer);
	public Customer findOne(String mobileNo);
	public void remove(String mobileNo);
	
}

