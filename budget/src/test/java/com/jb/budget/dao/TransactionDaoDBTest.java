/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dao;

import com.jb.budget.dto.Budget;
import com.jb.budget.dto.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author julie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionDaoDBTest {
    
    public TransactionDaoDBTest() {
    }
    
    @Autowired
    TransactionDao transactionDao;
    
    @Autowired 
    BudgetsDao budgetsDao;
    
    @Before
    public void setUp() {
        List <Transaction> transactions = transactionDao.getAllTransactions();
        for(Transaction transaction: transactions)
        {
            transactionDao.deleteTransaction(transaction);
        }
        
        List<Budget> budgets =  budgetsDao.getAllBudgets();
        for(Budget budget: budgets){
            budgetsDao.deleteBudget(budget);
        }
    }
    
 

//    @Test
//    public void testGetTransactionsByMonth() {
//        System.out.println("getTransactionsByMonth");
//        TransactionDaoDB instance = new TransactionDaoDB();
//        List<Transaction> expResult = null;
//        List<Transaction> result = instance.getTransactionsByMonth();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testAddAndGetTransactionsByCategory() {
        Budget budget = new Budget();
        budget.setCategory("food");
        budget.setBudget(BigDecimal.valueOf(100.50));
        budgetsDao.addBudget(budget);
        
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(25.75));
        transaction.setCategory("food");
        transaction.setDateOccured(LocalDate.now());
        transaction.setLocation("Wendy's");
        transactionDao.addTransaction(transaction);
        
        List<Transaction> resultList = transactionDao.getTransactionsByCategory("food");
        
        assertEquals(resultList.get(0).getCategory(),transaction.getCategory() );
        assertEquals(resultList.get(0).getAmount(),transaction.getAmount() );
        assertEquals(resultList.get(0).getDateOccured(),transaction.getDateOccured() );
        
    }


    @Test
    public void testEditTransaction() {
        Budget budget = new Budget();
        budget.setCategory("food");
        budget.setBudget(BigDecimal.valueOf(100.50));
        budgetsDao.addBudget(budget);
        
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(25.75));
        transaction.setCategory("food");
        transaction.setDateOccured(LocalDate.now());
        transaction.setLocation("Wendy's");
        transactionDao.addTransaction(transaction);
        
        transaction.setLocation("McDonald's");
        transactionDao.editTransaction(transaction);
        
        List<Transaction> transactions =  transactionDao.getAllTransactions();
        
        assertEquals(transactions.get(0).getLocation(),"McDonald's");
    }

    @Test
    public void testDeleteTransaction() {
        Budget budget = new Budget();
        budget.setCategory("food");
        budget.setBudget(BigDecimal.valueOf(100.50));
        budgetsDao.addBudget(budget);
        
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(25.75));
        transaction.setCategory("food");
        transaction.setDateOccured(LocalDate.now());
        transaction.setLocation("Wendy's");
        transactionDao.addTransaction(transaction);
        
        transactionDao.deleteTransaction(transaction);
        List<Transaction> transactions = transactionDao.getAllTransactions();
        
        assertEquals(transactions.size(),0);
    }
    
}
