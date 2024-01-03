export class User {
    username: string
    email: string
    id: number
  
    constructor(username: string, email: string, id: number) {
        this.username = username
        this.email = email
        this.id = id
  }
}