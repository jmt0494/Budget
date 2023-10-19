package com.taubel.budget.dtos;

import java.time.LocalDate;

import com.taubel.budget.entities.Transaction;

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
public class TransactionDto {

    public TransactionDto(Transaction transaction) {
        this.id = transaction.getId();
        this.lineitem = new LineItemDto(transaction.getLineItem());
        this.amount = transaction.getAmount();
        this.merchant = transaction.getMerchant();
        this.user = new UserDto(transaction.getUser());
    }
    
    private Long id;

    private LineItemDto lineitem;

    private LocalDate transationData;

    private double amount;

    private String merchant;

    private UserDto user;
}
