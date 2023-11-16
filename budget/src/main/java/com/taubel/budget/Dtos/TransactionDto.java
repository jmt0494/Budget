package com.taubel.budget.Dtos;

import java.time.LocalDate;

import com.taubel.budget.entities.Transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

    public TransactionDto(Transaction trans) {
        this.id = trans.getId();
        this.amount = trans.getAmount();
        this.merchant = trans.getMerchant();
        this.transactionDate = trans.getTransactionDate();
        this.userId = trans.getUser().getId();
        if (trans.getLineItem() != null) this.lineItemId = trans.getLineItem().getId();
    }
    
    private Long id;

    private Double amount;

    private String merchant;

    private LocalDate transactionDate;

    private Long lineItemId;

    private Long userId;

}
