package com.taubel.budget.Dtos.FullPayload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taubel.budget.entities.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FPCategoryDto {

    public FPCategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.budgetId = category.getBudget().getId();
    }

    private Long id;

    private String name;

    private List<FPLineItemDto> lineItems;

    @JsonIgnore
    private Long budgetId;
}
