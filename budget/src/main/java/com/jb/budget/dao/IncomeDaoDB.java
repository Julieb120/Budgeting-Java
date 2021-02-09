/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dao;

import com.jb.budget.dto.Income;
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
public class IncomeDaoDB implements IncomeDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<Income> getMonthlyIncome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Income> getSpecificIncome(String source) {
        try{
            final String GET_SPECIFIC_INCOME = "SELECT * FROM income WHERE incomeSource =?";
            
            return jdbc.query(GET_SPECIFIC_INCOME, new IncomeMapper(), source);
        }catch (DataAccessException ex){return null;}
    }
    
    @Override
    public List<Income> getAllIncome() {
        try{
            final String GET_ALL_INCOME ="SELECT * FROM income";
            
            return jdbc.query(GET_ALL_INCOME, new IncomeMapper());
            
        }catch (DataAccessException ex){return null;}
    }

    @Override
    @Transactional
    public Income addIncome(Income income) {
        final String ADD_INCOME = "INSERT INTO income(incomeSource, amount, dateOccured) "
                   + "VALUES(?,?,?)";
           
        jdbc.update(ADD_INCOME, income.getLocation(), income.getAmount(), income.getDateOccured());
       
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        income.setId(id);
           
        return income;
      
    }

    @Override
    public void editIncome(Income income) {
        final String EDIT_INCOME = "UPDATE income SET incomeSource =?, amount =?,dateOccured=? "
                + "WHERE id =? ";
        jdbc.update(EDIT_INCOME,income.getLocation(),income.getAmount(),income.getDateOccured(),income.getId());
    }

    @Override
    public void deleteIncome(Income income) {
      final String DELETE_INCOME = "DELETE FROM income WHERE id=?";
      
      jdbc.update(DELETE_INCOME, income.getId());
    }
        
   public static final class IncomeMapper implements RowMapper<Income> {
       @Override
       public Income mapRow(ResultSet rs, int index)throws SQLException{
           Income income = new Income();
           income.setDateOccured(rs.getDate("dateOccured").toLocalDate());
           income.setAmount(BigDecimal.valueOf(rs.getDouble("amount")));
           income.setLocation(rs.getString("incomeSource"));
           income.setId(rs.getInt("id"));
           
           return income;
       }
       
   } 
    
}
