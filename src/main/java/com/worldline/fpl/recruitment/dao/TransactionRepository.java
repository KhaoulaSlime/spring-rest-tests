package com.worldline.fpl.recruitment.dao;

import com.worldline.fpl.recruitment.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
public interface TransactionRepository {

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return
	 */
	Page<Transaction> getTransactionsByAccount(String accountId, Pageable p);

	/**
	 * Check if an transaction exists
	 *
	 * @param transactionId
	 *            the transaction id
	 * @return true if the transaction exists
	 */
	boolean exists(String transactionId);

	Optional<Transaction> findById(String transactionId);

	/**
	 * Remove a transaction
	 *
	 * @param transactionId
	 *            the transaction id
	 *
	 */
	boolean remove(String transactionId);

	/**
	 * Add a new transaction
	 *
	 * @param transact
	 *            the new transaction to add
	 *
	 */
	void add(Transaction transact);

	/**
	 * Update a transaction
	 *
	 * @param transactionId
	 *            the transaction id
	 * @param transaction
	 * 			the value to update
	 *
	 */
	boolean updateTransaction(String transactionId,Transaction transaction);

}
