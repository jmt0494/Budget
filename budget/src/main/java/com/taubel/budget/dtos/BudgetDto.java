package com.taubel.budget.dtos;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;

import com.taubel.budget.entities.Budget;
import com.taubel.budget.enums.Month;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BudgetDto {

    public BudgetDto(Budget budget) {
        this.id = budget.getId();
        this.month = budget.getMonth();
        this.year = budget.getYear();
        this.user = new UserDto(budget.getUser());
    }

    private Long id;

    private Month month;
    
    private int year;
    
    private UserDto user;

    
}
