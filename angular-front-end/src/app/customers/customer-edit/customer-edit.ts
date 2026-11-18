import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CustomerService} from '../../services/customer';
import {ActivatedRoute, Router} from '@angular/router';
import{FormsModule} from '@angular/forms';
import {CustomerDto} from '../../models/customer.model';


@Component({
  selector: 'app-customer-edit',
  imports: [FormsModule],
  templateUrl: './customer-edit.html',
  styleUrl: './customer-edit.css',
})
export class CustomerEdit implements OnInit{
  id:number=0;
  customerDto: CustomerDto | null = null;

  constructor(private customerService: CustomerService, private cdr: ChangeDetectorRef,private router:Router, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
        this.id= Number(this.route.snapshot.paramMap.get('id'));

        this.customerService.getCustomerById(this.id).subscribe({
          next: data=>{ this.customerDto= data;this.cdr.markForCheck();}
        ,error: err => console.error()});
  }
  saveCustomer(): void{
    if(!this.customerDto) return;
    if( !this.customerDto.firstname.trim() ||
        !this.customerDto.lastname.trim() ||
        !this.customerDto.email.trim()) return;
    this.customerService.updateCustomer(this.id,this.customerDto).subscribe(
      {
        next: ()=> this.router.navigate(['/customers'])
      ,
      error: err=> console.error(err)
      }
    );
  }

}
