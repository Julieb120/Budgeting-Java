/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.controller;

import com.jb.budget.dao.IncomeDao;
import com.jb.budget.dto.Income;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author julie
 */
@Controller
public class IncomeController {
    
    @Autowired
    IncomeDao incomeDao;
    //GET 
        //GET SPECIFIC" INCOME
    
    @GetMapping("income/all")
    public String GetAllIncome(Model model){
        List<Income> allIncome =  incomeDao.getAllIncome();
        
        model.addAttribute("allIncome",allIncome);
        
        return "allIncome";
    }
    
    
    
       //GET ALL INCOME
    //POST -- ADD, EDIT
        //ADD INCOME
        //EDIT INCOME
    //DELETE
}
