package com.taubel.budget.Dtos.FullPayload;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taubel.budget.entities.Transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FPTransactionDto {

    public FPTransactionDto(Transaction trans) {
        this.id = trans.getId();
        this.amount = trans.getAmount();
        this.merchant = trans.getMerchant();
        this.transactionDate = trans.getTransactionDate();
        this.lineItemId = trans.getLineItem().getId();
    }

    private Long id;

    private Double amount;

    private String merchant;

    private LocalDate transactionDate;

    @JsonIgnore
    private Long lineItemId;

}
