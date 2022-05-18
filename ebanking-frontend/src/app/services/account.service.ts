import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";
import {environment} from "../../environments/environment";
import {AccountHistory} from "../model/account.model";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http:HttpClient) { }

  public getAccount(accountId:string,page:number,size:number):Observable<AccountHistory>{
    return this.http.get<AccountHistory>(environment.backendHost+"/accounts/"+accountId+"/pageOperations?" +
      "page="+page+"&size"+size);
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
