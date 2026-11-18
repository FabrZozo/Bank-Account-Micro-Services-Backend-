import {Component, OnInit} from '@angular/core';
import {CustomerService} from '../../services/customer';
import {FormsModule} from '@angular/forms';
import {CustomerDto} from '../../models/customer.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-customer-create',
  imports: [FormsModule],
  templateUrl: './customer-create.html',
  styleUrl: './customer-create.css',
})
export class CustomerCreate{

    customerCreate1: CustomerDto = {
      firstname:'',
      lastname: '',
      email: ''
    };
    constructor(private customerService: CustomerService,private route :Router) {
    }


    customeradd():void{
      this.customerService.addCustomer(this.customerCreate1).subscribe({
        next:()=>{
          this.route.navigate(['/customers']);
        },error: err => console.error(err)
      })
    }
}
