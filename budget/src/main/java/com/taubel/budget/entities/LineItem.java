package com.taubel.budget.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LineItems")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_item_id")
    private Long lineItemId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne  
    @JoinColumn(name = "user_id")
    private User user;  

    private String name;

    @Column(name = "budgeted_amount")
    private double budgetedAmount;
}

