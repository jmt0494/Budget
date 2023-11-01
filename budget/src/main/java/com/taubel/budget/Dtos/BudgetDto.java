package com.taubel.budget.Dtos;

import com.taubel.budget.entities.User;
import com.taubel.budget.enums.Month;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetDto {

    private Long id;

    private Month month;

    private int year;

    private User user;

}
