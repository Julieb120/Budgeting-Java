/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dao;

import com.jb.budget.dto.Budget;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
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
public class BudgetsDaoDBTest {
    
    @Autowired
    BudgetsDao budgetsDao;
    
    public BudgetsDaoDBTest() {
    }

    
    @Before
    public void setUp() {
        List<Budget> budgets =  budgetsDao.getAllBudgets();
        for(Budget budget: budgets){
            budgetsDao.deleteBudget(budget);
        }
    }
    

    @Test
    public void testAddandGetBudgets() {
        Budget budget = new Budget();
        budget.setCategory("food");
        budget.setBudget(BigDecimal.valueOf(100.50));
        budgetsDao.addBudget(budget);
        
        List<Budget> budgets =  budgetsDao.getAllBudgets();
        
        assertEquals(budgets.size(),1);
        
        Budget test = budgetsDao.getSpecificBudget("food");
        assertEquals(test.getCategory(), budget.getCategory());
        assertEquals(test.getBudget(), budget.getBudget());
    }


    @Test
    public void testEditBudget() {
        Budget budget = new Budget();
        budget.setCategory("food");
        budget.setBudget(BigDecimal.valueOf(100.50));
        budgetsDao.addBudget(budget);
        
        budget.setBudget(BigDecimal.valueOf(50.00));
        
        budgetsDao.editBudget(budget);
        Budget edited = budgetsDao.getSpecificBudget("food");
        
        assertEquals(edited.getBudget(),BigDecimal.valueOf(50.00));
    }

    @Test
    public void testDeleteBudget() {
        Budget budget = new Budget();
        budget.setCategory("food");
        budget.setBudget(BigDecimal.valueOf(100.50));
        budgetsDao.addBudget(budget);
        
        budgetsDao.deleteBudget(budget);
        List<Budget> budgets = budgetsDao.getAllBudgets();
        
        assertTrue(budgets.isEmpty());
    }
    
}
