package com.taubel.budget.Dtos;


import com.taubel.budget.entities.Category;
import com.taubel.budget.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.userId = category.getUser().getId();
        if (category.getBudget() != null) this.budgetId = category.getBudget().getId();
    }

    private Long id;

    private String name;

    private Long userId;

    private Long budgetId;

}
