import { Month } from "src/app/shared/enums/month"

class DetailedTransaction {
   id: number
   amount: number
   merchant: string
   transactionDate: string
   constructor(
      id: number,
      amount: number,
      merchant: string,
      transactionDate: string
   ) {
      this.id = id
      this.amount = amount
      this.merchant = merchant
      this.transactionDate = transactionDate
   }

}

class DetailedLineItem {
   id: number
   name: string
   budgetedAmount: number
   transactions: DetailedTransaction[]

   constructor(
      id: number,
      name: string,
      budgetedAmount: number,
      transactions: DetailedTransaction[]
   ) {
      this.id = id
      this.name = name
      this.budgetedAmount = budgetedAmount
      this.transactions = transactions
   }
}

class DetailedCategory {
   id: number
   name: string
   lineItems: DetailedLineItem[];

   constructor(id: number, name: string, lineItems: DetailedLineItem[]) {
      this.id = id
      this.name = name
      this.lineItems = lineItems
   }
}

export class DetailedBudget {
   id: number
   month: Month
   year: number
   userId: number
   categories: DetailedCategory[]
   constructor(
      id: number,
      month: Month,
      year: number,
      userId: number,
      categories: DetailedCategory[]
   ) {
      this.id = id
      this.month = month
      this.year = year
      this.userId = userId
      this.categories = categories
   }
}