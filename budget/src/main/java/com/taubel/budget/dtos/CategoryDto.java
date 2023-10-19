package com.taubel.budget.dtos;

import com.taubel.budget.entities.Category;

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
public class CategoryDto {

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.budget = new BudgetDto(category.getBudget());
        this.user = new UserDto(category.getUser());
        this.name = category.getName();
    }
    
    private Long id;

    private BudgetDto budget;

    private UserDto user;

    private String name;

}
