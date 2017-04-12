package com.worldline.fpl.recruitment.dao.impl;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of {@link TransactionRepository}
 * 
 * @author A525125
 *
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository,
		InitializingBean {

	private List<Transaction> transactions;

	@Override
	public void afterPropertiesSet() throws Exception {
		transactions = new ArrayList<>();
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(42.12));
			transaction.setId("1");
			transaction.setNumber("12151885120");
			transactions.add(transaction);
		}
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(456.00));
			transaction.setId("2");
			transaction.setNumber("12151885121");
			transactions.add(transaction);
		}
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(-12.12));
			transaction.setId("3");
			transaction.setNumber("12151885122");
			transactions.add(transaction);
		}
	}

	@Override
	public Page<Transaction> getTransactionsByAccount(String accountId,
			Pageable p) {
		return new PageImpl<Transaction>(transactions.stream()
				.filter(t -> t.getAccountId().equals(accountId))
				.collect(Collectors.toList()));
	}

	@Override
	public boolean exists(String transactionId) {
	 return transactions.stream().anyMatch(t -> t.getId().equals(transactionId));
	}

	@Override
	public Optional<Transaction> findById(String transactionId) {
		return transactions.stream().filter(t -> t.getId().equals(transactionId))
				.findFirst();
	}
	@Override
	public boolean remove(String transactionId) {
		Optional<Transaction> transact = findById(transactionId);
		if(transact != null) {
			transactions.remove(transactions.indexOf(transact.get()));
			return true;
		}
		return false;
	}

	@Override
	public void add(Transaction transact) {

		transactions.add(transact);
	}

	@Override
	public boolean updateTransaction(String transactionId,Transaction transaction){

		Optional<Transaction> transact = findById(transactionId);
		    if(transact != null) {

			 int index = transactions.indexOf(transact.get());
			 transactions.set(index,transaction);

			  return true;
		 }
		return false;
	}
}
