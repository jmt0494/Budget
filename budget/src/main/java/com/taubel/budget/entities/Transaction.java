package com.taubel.budget.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "merchant", length = 30)
    private String merchant;

    @ManyToOne
    @JoinColumn(name = "line_item_id")
    private LineItem lineItem;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

}