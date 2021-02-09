/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dao;

import com.jb.budget.dto.Income;
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
public class IncomeDaoDBTest {
    
    @Autowired
    IncomeDao incomeDao;
    
    public IncomeDaoDBTest() {
    }
    

    @Before
    public void setUp() {
        List<Income> incomes = incomeDao.getAllIncome();
        for(Income income:incomes){
            incomeDao.deleteIncome(income);
        }
    }
    

//    @Test
//    public void testGetMonthlyIncome() {
//        System.out.println("getMonthlyIncome");
//        IncomeDaoDB instance = new IncomeDaoDB();
//        List<Income> expResult = null;
//        List<Income> result = instance.getMonthlyIncome();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testAddAndGetSpecificIncome() {
        Income income = new Income();
        income.setAmount(BigDecimal.valueOf(500.50));
        income.setDateOccured(LocalDate.now());
        income.setLocation("Work");
        
        incomeDao.addIncome(income);
        
        List<Income> all = incomeDao.getSpecificIncome("Work");
        
        assertEquals(all.size(),1);
        Income found = all.get(0);
        
        assertEquals(found,income);
    }

    @Test
    public void testAddandGetAllIncome() {
        Income income = new Income();
        income.setAmount(BigDecimal.valueOf(500.50));
        income.setDateOccured(LocalDate.now());
        income.setLocation("Work");
        
        incomeDao.addIncome(income);
        
        Income income2 = new Income();
        income2.setAmount(BigDecimal.valueOf(350.50));
        income2.setDateOccured(LocalDate.parse("2021-01-28"));
        income2.setLocation("Tips");
        
        incomeDao.addIncome(income2);
        
        List<Income> allIncomes = incomeDao.getAllIncome();
        assertEquals(allIncomes.size(),2);
    }

    @Test
    public void testEditIncome() {
        Income income = new Income();
        income.setAmount(BigDecimal.valueOf(500.50));
        income.setDateOccured(LocalDate.now());
        income.setLocation("Work");
        
        incomeDao.addIncome(income);
        assertEquals(income.getAmount(), BigDecimal.valueOf(500.50));
        
        income.setAmount(BigDecimal.valueOf(250.25));
        
        incomeDao.editIncome(income);
        List<Income> found = incomeDao.getSpecificIncome("Work");
        Income result = found.get(0);
        assertEquals(result.getAmount(), BigDecimal.valueOf(250.25));
    }

    @Test
    public void testDeleteIncome() {
        Income income = new Income();
        income.setAmount(BigDecimal.valueOf(500.50));
        income.setDateOccured(LocalDate.now());
        income.setLocation("Work");
        incomeDao.addIncome(income);
        
        List<Income> found = incomeDao.getAllIncome();
        assertEquals(found.size(),1);
        
        incomeDao.deleteIncome(income);
        List<Income> found2 = incomeDao.getAllIncome();
        assertEquals(found2.size(),0);
        
    }
    
}
