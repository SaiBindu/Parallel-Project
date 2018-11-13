package com.cg.paytm.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "customer")
public class Customer {

	private static final long serialVersionUID = 1L;
	private String name;
	
	@Id
	private String mobileNo;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="wallet_id")
	private Wallet wallet;

	@ManyToOne
	@JoinColumn(name="transaction_id")
	private Transactions transactions;
	
	public Customer() {
		super();
	}

	public Customer(String name, Wallet wallet) {
		super();
		this.name = name;
		this.wallet = wallet;
	}

	public Customer(String name2, String mobileNo2, Wallet wallet2) {
		this.name=name2;
		mobileNo=mobileNo2;
		wallet=wallet2;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Customer name=" + name + ", mobileNo=" + mobileNo
				 + wallet;
	}
	
	
}


