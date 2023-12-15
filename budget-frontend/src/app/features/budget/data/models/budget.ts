import { Month } from "../enums/month"


export class Budget { 
    id: number
    month: Month
    year: number
    userId: number

  constructor(id: number, month: Month, year: number, userId: number) {
    this.id = id
    this.month = month
    this.year = year
    this.userId = userId
  }    
}