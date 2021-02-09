/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dao;

import com.jb.budget.dto.Income;
import java.util.List;

/**
 *
 * @author julie
 */
public interface IncomeDao {
   
    List<Income> getMonthlyIncome();// implement later
    
    List<Income> getSpecificIncome(String source);
    public List<Income> getAllIncome();
    Income addIncome(Income income);
    void editIncome (Income income);
    void deleteIncome(Income income);
}
