package com.taubel.budget.Dtos;

import java.time.LocalDate;

import com.taubel.budget.entities.LineItem;
import com.taubel.budget.entities.Transaction;
import com.taubel.budget.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

    /**
     * Creates a new TransactionDto with the internal values set. Relational values will have to be set using the setter methods.
     * 
     * @param trans
     */
    public TransactionDto(Transaction trans) {
        this.id = trans.getId();
        this.amount = trans.getAmount();
        this.merchant = trans.getMerchant();
        this.transactionDate = trans.getTransactionDate();
        this.userId = trans.getUser().getId();
        if (trans.getLineItem() != null) this.lineItemId = trans.getLineItem().getId();
    }
    
    private Long id;

    private double amount;

    private String merchant;

    private LocalDate transactionDate;

    private Long lineItemId;

    private Long userId;

}
