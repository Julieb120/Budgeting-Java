/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author julie
 */
public class Budget {
    BigDecimal budget;
    String category;
    BigDecimal spent;
    BigDecimal variation;
    
    public Budget(){
        this.spent = new BigDecimal("0");
        this.variation = new BigDecimal("0");
    
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public void setSpent(BigDecimal spent) {
        this.spent = spent;
    }

    public BigDecimal getVariation() {
        return variation;
    }

    public void setVariation(BigDecimal variation) {
        this.variation = variation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.budget);
        hash = 53 * hash + Objects.hashCode(this.category);
        hash = 53 * hash + Objects.hashCode(this.spent);
        hash = 53 * hash + Objects.hashCode(this.variation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Budget other = (Budget) obj;
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.budget, other.budget)) {
            return false;
        }
        if (!Objects.equals(this.spent, other.spent)) {
            return false;
        }
        if (!Objects.equals(this.variation, other.variation)) {
            return false;
        }
        return true;
    }
    
    
}
