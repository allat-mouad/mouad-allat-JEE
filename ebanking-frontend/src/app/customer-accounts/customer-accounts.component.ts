import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../model/customer.model";
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {catchError, Observable, throwError} from "rxjs";
import {AccountDTO, AccountHistory} from "../model/account.model";
import {AccountService} from "../services/account.service";
import {DialogComponent} from "../dialog/dialog.component";

@Component({
  selector: 'app-customer-accounts',
  templateUrl: './customer-accounts.component.html',
  styleUrls: ['./customer-accounts.component.css']
})
export class CustomerAccountsComponent implements OnInit {
  customerId!:number;
  custumer!:Customer;
  accounts!: Observable<Array<AccountDTO>>;
  errorMessage! :string;


  constructor(private dialog:MatDialog,private accountService:AccountService,private route:ActivatedRoute,private router:Router) {
    this.custumer=this.router.getCurrentNavigation()?.extras.state as Customer;

  }

  ngOnInit(): void {

  this.customerId=this.route.snapshot.params["id"];
    this.accounts=this.accountService.getAccountByCustomerID(this.customerId).pipe(
      catchError(err=>{
        this.errorMessage=err.message;
        return throwError(err);
      })
    );

  }

  handleAccountsOperations(account:AccountDTO){
    this.router.navigateByUrl('/accounts/'+account.id,{state:account});
  }
  //dialog
  openDialog() {
    this.dialog.open(DialogComponent, {
      width:'30%',
      data: {
      customer:this.custumer,

      }
    }).afterClosed().subscribe(val=>{
      if(val=='save'){
        this.accounts=this.accountService.getAccountByCustomerID(this.customerId).pipe(
          catchError(err=>{
            this.errorMessage=err.message;
            return throwError(err);
          })
        );
      }
    })
  }


}
