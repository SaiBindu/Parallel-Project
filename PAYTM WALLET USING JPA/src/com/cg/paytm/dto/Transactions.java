package com.cg.paytm.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@Entity
public class Transactions {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int TransactionID;
	String mobileNo;
	String transactionType;
	BigDecimal amount;
	BigDecimal balance;
	String status;
	Date validdate;
	
	public Transactions() {
		super();
	}
	
	
	public Transactions(String mobileNo, String transactionType, BigDecimal amount, BigDecimal balance, String status,
			Date validdate) {
		super();
		this.mobileNo = mobileNo;
		this.transactionType = transactionType;
		this.amount = amount;
		this.balance = balance;
		this.status = status;
		this.validdate = validdate;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return validdate;
	}

	public void setDate(Date validdate) {
		this.validdate = validdate;
	}	

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
