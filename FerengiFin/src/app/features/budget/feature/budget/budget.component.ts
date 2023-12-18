import { Component, OnInit } from '@angular/core';
import { Budget } from 'src/app/features/budget/data/models/budget';
import { BudgetService } from 'src/app/shared/services/budget.service';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit {

  currentBudget?: Budget;

  constructor(private budgetService: BudgetService) { }

  ngOnInit(): void {
    this.budgetService.currentBudget$.subscribe(budget => {
      this.currentBudget = budget
    })
  }

}
