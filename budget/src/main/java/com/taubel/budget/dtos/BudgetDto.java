package com.taubel.budget.dtos;
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
        this.user = new UserDto(budget.getUser());
        this.year = budget.getYear();
    }

    private Long id;

    private UserDto user;

    private Month month;

    private int year;

}
