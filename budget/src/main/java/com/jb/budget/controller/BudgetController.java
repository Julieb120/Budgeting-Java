/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.controller;

import com.jb.budget.dao.BudgetsDao;
import com.jb.budget.dto.Budget;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author julie
 */
@Controller
public class BudgetController {
    
    @Autowired
    BudgetsDao budgetsDao;
    
    
    //GET ALL
    @GetMapping ("budgets")
    public String displayTeachers(Model model){
        List<Budget> budgets = budgetsDao.getAllBudgets();
        model.addAttribute("budgets", budgets);
        
        return "budgets";
    
    }
    
    
    //POST -- ADD, EDIT
    @PostMapping("addBudget")
    public String addBudget(HttpServletRequest request){
        String amount = request.getParameter("budget");
        String category = request.getParameter("category");
        
        
        Budget budget = new Budget();
        budget.setBudget(new BigDecimal(amount));
        budget.setCategory(category);
        
        budgetsDao.addBudget(budget);
        
        return "redirect:/budgets";
    }
    
    
    
    
    
    //DELETE
    
}
