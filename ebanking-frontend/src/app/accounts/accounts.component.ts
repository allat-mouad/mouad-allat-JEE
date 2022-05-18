import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountService} from "../services/account.service";
import {catchError, Observable, throwError} from "rxjs";
import {Customer} from "../model/customer.model";
import {AccountHistory} from "../model/account.model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  accountFormGroup!:FormGroup;
  operationFormGroup!:FormGroup;
  currentPage:number=0;
  pageSize:number=5;
  errorMessage! :string;
  accounts!: Observable<AccountHistory>;

  constructor(private fb:FormBuilder,private accountService:AccountService) { }

  ngOnInit(): void {
    this.accountFormGroup=this.fb.group({
      accountId:this.fb.control("")
    });
    this.operationFormGroup=this.fb.group({
      operationType:this.fb.control(null),
      amount:this.fb.control(0),
      description:this.fb.control(null),
      accountDestination:this.fb.control(null)

    });

  }
  handleSearchAccount(){
    let accountId:string=this.accountFormGroup.value.accountId;
    this.accounts=this.accountService.getAccount(accountId,this.currentPage,this.pageSize).pipe(
      catchError(err=>{
        this.errorMessage=err.message;
        return throwError(err);
      })
    );

  }
  goToPage(page:number){
    this.currentPage=page;
    this.handleSearchAccount();
  }

  handleAccountOperation() {
    let accountId:string=this.accountFormGroup.value.accountId;
    let amount:number=this.operationFormGroup.value.amount;
    let description:string=this.operationFormGroup.value.description;
    let operationType:string=this.operationFormGroup.value.operationType;
    let accountDestination:string=this.operationFormGroup.value.accountDestination;
    if(operationType=="DEBIT")
    {
        this.accountService.debit(accountId,amount,description).subscribe( {
          next:(data)=>{
            alert("debit success")
            this.operationFormGroup.reset();

            this.handleSearchAccount();

          },
          error:(err => {
            alert("debit not functioning properly")
          })
          }

        );
    }
    else  if (operationType=="CREDIT"){
      this.accountService.credit(accountId,amount,description).subscribe( {
          next:(data)=>{
            alert("credit success")
            this.operationFormGroup.reset();

            this.handleSearchAccount();

          },
          error:(err => {
            alert("credit not functioning properly")
          })
        }

      );
    }
    else if (operationType=="TRANSFER"){
      console.log(accountId+" "+accountDestination)
      this.accountService.transfer(accountId,accountDestination,amount).subscribe( {
          next:(data)=>{
            alert("transfer success")
            this.operationFormGroup.reset();

            this.handleSearchAccount();

          },
          error:(err => {
            alert("transfer not functioning properly")
          })
        }

      );
    }

  }
}
