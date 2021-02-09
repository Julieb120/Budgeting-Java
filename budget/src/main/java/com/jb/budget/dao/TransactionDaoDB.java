/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dao;

import com.jb.budget.dto.Transaction;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author julie
 */
@Repository
public class TransactionDaoDB implements TransactionDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<Transaction> getTransactionsByMonth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaction> getTransactionsByCategory(String category) {
        final String TRANSACTIONS_CATEGORY = "SELECT * FROM transactions WHERE category =?";
        try{
            return jdbc.query(TRANSACTIONS_CATEGORY, new TransactionMapper(), category);
            
        } catch (DataAccessException ex){return null;}
    }

    @Override
    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        final String ADD_TRANSACTION = "INSERT INTO transactions (location, amount,dateOccured,category) VALUES (?,?,?,?)";
        
        jdbc.update(ADD_TRANSACTION, transaction.getLocation(), Double.parseDouble(transaction.getAmount().toString()), 
                Date.valueOf(transaction.getDateOccured()), transaction.getCategory() );
        
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        transaction.setId(id);
        
        return transaction;
    
    }

    @Override
    public void editTransaction(Transaction transaction) {
        final String EDIT = "UPDATE transactions SET  location=?, amount =?, dateOccured=?, category=? WHERE id =?" ; // EXCEPTION IF CATEGORY DOESNT EXIST
    
        jdbc.update(EDIT, transaction.getLocation(), transaction.getAmount(), transaction.getDateOccured(), 
                transaction.getCategory(), transaction.getId());
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        final String DELETE = "DELETE FROM transactions where id=?";
        
        jdbc.update(DELETE, transaction.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        final String GET_ALL = "SELECt * FROM transactions";
        
        try{
            return jdbc.query(GET_ALL, new TransactionMapper());
        } catch (DataAccessException ex){return null;}
    }
    
    public class TransactionMapper implements RowMapper<Transaction>{
        
        public Transaction mapRow(ResultSet rs, int id) throws SQLException{
            Transaction transaction =  new Transaction();
            transaction.setAmount(BigDecimal.valueOf(rs.getDouble("amount")));
            transaction.setCategory(rs.getString("category")); //THIS MIGHT BREAK
            transaction.setDateOccured( rs.getDate("dateOccured").toLocalDate() );
            transaction.setLocation(rs.getString("location"));
            
            return transaction;
        }
        
    
    }
    
}
