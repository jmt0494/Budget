export class Transaction {
    id: number
    amount: number
    merchant: string
    transactionDate: Date
    lineItemId: number
    userId: number

    constructor(
        id: number,
        amount: number,
        merchant: string,
        transactionDate: Date,
        lineItemId: number,
        userId: number
    ) {
        this.id = id
        this.amount = amount
        this.merchant = merchant
        this.transactionDate = transactionDate
        this.lineItemId = lineItemId
        this.userId = userId
    }
}