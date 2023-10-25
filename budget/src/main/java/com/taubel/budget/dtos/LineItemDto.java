package com.taubel.budget.dtos;

import java.util.List;
import java.util.stream.Collectors;

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
        this.transactions = lineItem.getTransactions().stream()
            .map(TransactionDto::new)
            .collect(Collectors.toList());
    }

    private Long id;

    
    private String name;
    
    private double budgetedAmount;

    private List<TransactionDto> transactions;
    
    private CategoryDto category;

    private UserDto user;

}
