/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dao;

import com.jb.budget.dto.Budget;
import java.math.BigDecimal;
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
public class BudgetsDaoDB implements BudgetsDao{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Budget> getAllBudgets() {
        try{
            final String GET_ALL_BUDGETS = "SELECT * FROM budgets";
            
            return jdbc.query(GET_ALL_BUDGETS, new BudgetsMapper());
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public Budget getSpecificBudget(String category) {
       try{
           final String GET_SPECIFIC_BUDGET = "SELECT * FROM budgets WHERE category =?";
           
           return jdbc.queryForObject(GET_SPECIFIC_BUDGET, new BudgetsMapper(),category);
           
       }catch (DataAccessException ex){
           return null;
       }
    }

    @Override
    public Budget addBudget(Budget budget) {
            final String ADD_BUDGET = "INSERT INTO budgets(category, budgeted) VALUES (?,?)";
            
            jdbc.update(ADD_BUDGET, budget.getCategory(), budget.getBudget());
            
            return budget;
      
    }

    @Override
    public void editBudget(Budget budget) {
            final String EDIT_BUDGET = "UPDATE budgets SET budgeted =? WHERE category=? ";
            
            jdbc.update(EDIT_BUDGET,budget.getBudget(), budget.getCategory());
      
    }

    @Override
    @Transactional
    public void deleteBudget(Budget budget) {
        final String DELETE_TRANSACTIONS = "DELETE FROM transactions WHERE category =?";
        final String DELETE_BUDGET = "DELETE FROM budgets WHERE category =?";
        
        jdbc.update(DELETE_TRANSACTIONS, budget.getCategory());
        jdbc.update(DELETE_BUDGET, budget.getCategory());
        
    }
    
    public static final class BudgetsMapper implements RowMapper<Budget>{
        
        @Override
        public Budget mapRow(ResultSet rs, int index) throws SQLException{
            Budget budget = new Budget();
            
            Double amount = rs.getDouble("budgeted");
            budget.setBudget(BigDecimal.valueOf(amount));
            
            budget.setCategory(rs.getString("category"));
            
            Double amountSpent =rs.getDouble("spent");
            budget.setSpent(BigDecimal.valueOf(amountSpent));
            
            Double amountVaried = rs.getDouble("variation");
            budget.setVariation(BigDecimal.valueOf(amountVaried));
            
            return budget;
        }   
    }
}
