package com.taubel.budget.Dtos;

import com.taubel.budget.entities.LineItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LineItemDto {

    public LineItemDto(LineItem lineItem) {
        this.id = lineItem.getId();
        this.name = lineItem.getName();
        this.budgetedAmount = lineItem.getBudgetedAmount();
        this.userId = lineItem.getUser().getId();
        if (lineItem.getCategory() != null) this.categoryId = lineItem.getCategory().getId();
    }

    private Long id;

    private String name;

    private Double budgetedAmount;

    private Long userId;

    private Long categoryId;

}
