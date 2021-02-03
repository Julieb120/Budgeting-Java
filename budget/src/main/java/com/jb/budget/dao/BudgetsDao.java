/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dao;

import com.jb.budget.dto.Budget;
import java.util.List;

/**
 *
 * @author julie
 */
public interface BudgetsDao {
    List<Budget> getAllBudgets();
    Budget getSpecificBudget(String category);
    Budget addBudget(Budget budget);
    void editBudget(Budget budget);
    void deleteBudget(Budget budget);
    
    
}
