import { Component, OnInit } from '@angular/core';
import { Budget } from 'src/app/features/budget/data/models/budget';
import { BudgetService } from 'src/app/shared/services/budget.service';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit {

  test?: Budget[];

  constructor(private budgetService: BudgetService) { }

  ngOnInit(): void {
    this.budgetService.getBudgetList().subscribe(budgets => {
      this.budgetService.budgetList = budgets;
      this.test = budgets;
    })
  }

}
