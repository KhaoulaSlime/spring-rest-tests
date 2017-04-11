package com.worldline.fpl.recruitment.controller;

import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.json.ErrorResponse;
import com.worldline.fpl.recruitment.json.TransactionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Transaction controller
 * 
 * @author A525125
 *
 */
@RequestMapping(value = "/accounts/{accountId}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TransactionController {

	/**
	 * Get transaction list by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return the transaction list
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "Get transaction list related to an account", response = TransactionResponse.class, responseContainer = "List")
	@ApiResponses({
			@ApiResponse(code = 404, message = "Account not found", response = ErrorResponse.class),
			@ApiResponse(code = 204, message = "No transactions", response = ErrorResponse.class) })
	ResponseEntity<Page<TransactionResponse>> getTransactionsByAccount(
			@ApiParam("Account ID") @PathVariable("accountId") String accountId,
			@ApiParam("Pageable information") @PageableDefault Pageable p);

	/**
	 * Delete transaction
	 *
	 * @param accountId
	 * 			  the account id
	 * @param transactionId
	 * 			  the transaction id
	 *
	 */
     @RequestMapping(value="/{transactionId}", method = RequestMethod.DELETE)
	 @ApiOperation(value = "Delete transaction")
	 @ApiResponses({
			 @ApiResponse(code = 404, message = "Account not found", response = ErrorResponse.class),
			 @ApiResponse(code = 204, message = "Account not found", response = ErrorResponse.class) })
	 ResponseEntity<Page<TransactionResponse>> deleteTransaction(
			 @ApiParam("Account ID") @PathVariable("accountId") String accountId,
			 @ApiParam("Transaction ID") @PathVariable("transactionId") String transactionId,
			 @ApiParam("Pageable information") @PageableDefault Pageable p);


	/**
	 * Add transaction
	 *
	 * @param accountId
	 * 			  the account id
	 * @param transaction
	 *            the new transaction to add
	 */
	@RequestMapping(value="", method = RequestMethod.POST)
	@ApiOperation(value = "Add transaction")
	ResponseEntity<Page<TransactionResponse>> createTransaction(
			@PathVariable("accountId") String accountId,
			@RequestBody Transaction transaction);


}
