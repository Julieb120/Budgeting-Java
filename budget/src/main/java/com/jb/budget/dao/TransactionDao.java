/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dao;

import com.jb.budget.dto.Transaction;
import java.util.List;

/**
 *
 * @author julie
 */
public interface TransactionDao {
    List<Transaction> getTransactionsByMonth();
    List<Transaction> getTransactionsByCategory();
    Transaction addTransaction();
    void editTransaction();
    void deleteTransaction();
}
