package com.taubel.budget.Dtos.FullPayload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taubel.budget.entities.LineItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FPLineItemDto {

    public FPLineItemDto(LineItem lineItem) {
        this.id = lineItem.getId();
        this.name = lineItem.getName();
        this.budgetedAmount = lineItem.getBudgetedAmount();
        this.categoryId = lineItem.getCategory().getId();
    }

    private Long id;

    private String name;

    private Double budgetedAmount;

    private List<FPTransactionDto> Transactions;
    
    @JsonIgnore
    private Long categoryId;
}
