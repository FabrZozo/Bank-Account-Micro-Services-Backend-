import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-accounts',
  imports: [],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})
export class Accounts implements OnInit{
    accounts: any
    constructor(private htpp: HttpClient) {
    }
    ngOnInit(): void {
        this.htpp.get("http://localhost:8888/ACCOUNT-SERVICE/bankAccount/getListBankAccount")
          .subscribe({
            next: data=> {
               this.accounts=data;
            },
            error : err => {
              console.log(err)
            }
          })


    }

}
