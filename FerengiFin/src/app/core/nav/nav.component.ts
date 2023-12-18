import { BudgetService } from 'src/app/shared/services/budget.service';
import { Component, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Budget } from 'src/app/features/budget/data/models/budget';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent {

  budgetList?: Budget[]

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private budgetService: BudgetService) {}

  ngOnInit(): void {
    this.budgetService.budgetList$?.subscribe(budgets =>{
      this.budgetList = budgets;
    });
  }

}
