package com.taubel.budget.Dtos;


import com.taubel.budget.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    private BudgetDto budget;

    private User user;

}
