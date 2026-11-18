import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Customer, CustomerDto} from '../models/customer.model';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
    private CustomersURL="http://localhost:8888/CUSTOMER-SERVICE2/Customers"

    constructor(private http: HttpClient) {
    }

    getAllCustomers():Observable<Customer[]>{
      return this.http.get<Customer[]>(`${this.CustomersURL}/getAllCustomers`);
    }

    getCustomerById(id: number): Observable<CustomerDto>{
      return this.http.get<CustomerDto>(`${this.CustomersURL}/getCustomerById/${id}`)
    }
    getCustomerByName(name: string):Observable<CustomerDto[]>{
      return  this.http.get<CustomerDto[]>(this.CustomersURL+"/getCustomerByName/"+name);
    }
    addCustomer(customer : CustomerDto): Observable<void>{
      return this.http.post<void>(this.CustomersURL+"/addCustomer",customer);
    }


    deleteCustomer(id: number):Observable<void>{
      return this.http.delete<void>(this.CustomersURL+"/deleteCustomer/"+id);
    }

    updateCustomer(id: number, customer: CustomerDto):Observable<CustomerDto>{
      return this.http.put<CustomerDto>(`${this.CustomersURL}/updateCustomer/${id}`, customer);
    }
}
