package com.taubel.budget.dtos;

import com.taubel.budget.entities.LineItem;

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
public class LineItemDto {

    public LineItemDto(LineItem lineItem) {
        this.id = lineItem.getId();
        this.category = new CategoryDto(lineItem.getCategory());
        this.user = new UserDto(lineItem.getUser());
        this.name = lineItem.getName();
        this.budgetedAmount = lineItem.getBudgetedAmount();
    }

    private Long id;

    private CategoryDto category;

    private UserDto user;

    private String name;

    private double budgetedAmount;
    
}
