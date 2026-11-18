import { Routes } from '@angular/router';
import {Customers} from './customers/customers';
import {Accounts} from './accounts/accounts';
import {CustomerEdit} from './customers/customer-edit/customer-edit';
import {CustomerCreate} from './customers/customer-create/customer-create';

export const routes: Routes = [
  { path : "customers" , component : Customers },
  { path : "accounts" , component : Accounts },
  { path : "customers/:id/edit", component: CustomerEdit},
  { path : "customers/create", component:CustomerCreate}
];
