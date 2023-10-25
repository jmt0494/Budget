package com.taubel.budget.entities;

import jakarta.persistence.*;

import java.util.List;

import com.taubel.budget.enums.Month;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Budgets")
public class Budget {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "month", nullable = false)
    private Month month;

    @Column(name = "year", nullable = false)
    private int year;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY)
    private List<Category> categories;

}
