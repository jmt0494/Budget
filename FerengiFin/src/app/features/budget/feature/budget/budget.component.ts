import { Component, OnInit } from '@angular/core';
import { Budget } from 'src/app/shared/models/budget';
import { Category } from 'src/app/shared/models/category';
import { LineItem } from 'src/app/shared/models/lineitem';
import { Transaction } from 'src/app/shared/models/transaction';
import { BudgetService } from 'src/app/shared/services/budget/budget.service';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit {

  currentBudget?: Budget;

  categories: Category[] = [];

  lineItems: LineItem[] = [];

  transactions: Transaction[] = []

  constructor(private budgetService: BudgetService) { }

  ngOnInit(): void {
    this.budgetService.setBudgetList();
    this.budgetService.currentBudget$.subscribe(budget => {
      this.currentBudget = budget


    })
  }

}
