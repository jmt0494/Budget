package com.taubel.budget.Dtos;

import java.time.LocalDate;

import com.taubel.budget.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {
    
    private Long id;

    private double amount;

    private String merchant;

    private LocalDate transactionDate;

    private User user;

    private LineItemDto lineItem;

}
