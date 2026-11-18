import {ChangeDetectorRef, Component, OnInit} from '@angular/core';

import {CustomerService} from '../services/customer';
import {Customer, CustomerDto} from '../models/customer.model';
import {RouterLink} from '@angular/router';


@Component({
  selector: 'app-customers',
  imports: [
     RouterLink
  ],
  templateUrl: './customers.html',
  styleUrl: './customers.css',
})
export class Customers implements  OnInit{
    customers : Customer[]=[];
    searchCustomer: CustomerDto[]=[];
    customerDto: CustomerDto | null = null;
    constructor(private CustomerService : CustomerService,private cdr: ChangeDetectorRef  ) {

    }

    ngOnInit(): void {
      this.loadCustomers()
    }


    loadCustomers(): void {
      this.CustomerService.getAllCustomers().subscribe({
        next: data=> {
            this.customers= data;
            this.cdr.markForCheck();
        }, error :err => console.error(err)
      });
    }

  deleteCustomer(id:number): void{
      this.CustomerService.deleteCustomer(id).subscribe({
        next:()=>{
          this.loadCustomers();
        }, error : err => console.error(err)
      })
  }
  addCustomer(Customer: CustomerDto):void{
      this.CustomerService.addCustomer(Customer).subscribe({
        next:()=> {
          this.loadCustomers()

      }, error : err => console.error(err)
      })
  }
  updateCustomer(id: number, customer: CustomerDto): void{
        this.CustomerService.updateCustomer(id,customer).subscribe({
          next: () => {
        this.loadCustomers();
      }, error: err => console.error(err)
    })
  }
  getCustomerByName(name:string):void{
      this.CustomerService.getCustomerByName(name).subscribe({
        next: data=>{
          this.searchCustomer =data;
          this.cdr.markForCheck();
        },error: err => console.error(err)
      })
  }

  getCustomerByID(id: number):void{
    this.CustomerService.getCustomerById(id).subscribe({
      next: data=>{
        this.customerDto=data;
        this.cdr.markForCheck();
      },error: err => console.error(err)
    })
  }
}
