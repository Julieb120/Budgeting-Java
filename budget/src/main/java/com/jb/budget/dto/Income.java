/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jb.budget.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author julie
 */
public class Income {
    LocalDate dateOccured = LocalDate.now();
    BigDecimal incomeSource;
    String location;

    public LocalDate getDateOccured() {
        return dateOccured;
    }

    public void setDateOccured(LocalDate dateOccured) {
        this.dateOccured = dateOccured;
    }

    public BigDecimal getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(BigDecimal incomeSource) {
        this.incomeSource = incomeSource;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.dateOccured);
        hash = 47 * hash + Objects.hashCode(this.incomeSource);
        hash = 47 * hash + Objects.hashCode(this.location);
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
        final Income other = (Income) obj;
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.dateOccured, other.dateOccured)) {
            return false;
        }
        if (!Objects.equals(this.incomeSource, other.incomeSource)) {
            return false;
        }
        return true;
    }
    
    
}
