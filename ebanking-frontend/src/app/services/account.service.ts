import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";
import {environment} from "../../environments/environment";
import {AccountDTO, AccountHistory, AccountOperationDTO} from "../model/account.model";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http:HttpClient) { }

  public getAccount(accountId:string,page:number,size:number):Observable<AccountHistory>{
    return this.http.get<AccountHistory>(environment.backendHost+"/accounts/"+accountId+"/pageOperations?" +
      "page="+page+"&size"+size);
  }
  public saveBankAccount(bankAccount:AccountDTO){

    return this.http.post(environment.backendHost+"/accounts",bankAccount);
  }

  public getAccountByCustomerID(customerID: number):Observable<Array<AccountDTO>>{
    return this.http.get<Array<AccountDTO>>(environment.backendHost+"/account/"+customerID);
  }
  public debit(accountId:string,amount:number,description:string){
  let data={accountId,amount ,description}
  return this.http.post(environment.backendHost+"/accounts/debit",data);
}
public credit(accountId:string,amount:number,description:string){
  let data={accountId,amount,description}
  return this.http.post(environment.backendHost+"/accounts/credit",data);
}
public transfer(accountSourceId:string,accountDestinationId:string,amount:number){
let data={accountSourceId,accountDestinationId,amount}
return this.http.post(environment.backendHost+"/accounts/transfer",data);
}
}
