import {Component, Inject, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validator, Validators} from "@angular/forms";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';import {AccountService} from "../services/account.service";
import {BankAccountDTO} from "../model/account.model";

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  accountForm!: FormGroup;
  selectedType!: string ;
  account!: BankAccountDTO;



  constructor( @Inject(MAT_DIALOG_DATA)public editData:any,private api:AccountService, private  FormBuilder:FormBuilder,private dialogRef : MatDialogRef<DialogComponent>) { }

  ngOnInit(): void {
    this.account=this.editData;
    this.accountForm= this.FormBuilder.group({
      type :['',Validators.required],
      id :['',Validators.required],
      balance :['',Validators.required],
      createdAt :['',Validators.required],
    })
  }

  onCurrentAccountSelected() {
    this.selectedType = 'CurrentAccount';

      this.accountForm = this.FormBuilder.group({
      type: this.FormBuilder.control('CurrentAccount'), // intial value
        id :['',Validators.required],

        balance: this.FormBuilder.control('', [
        Validators.required
      ]),
        createdAt :['',Validators.required],



        overDraft: this.FormBuilder.control(0, [
        Validators.required,
      ]),
    });
  }

  onSavingAccountSelected() {

      this.selectedType = 'SavingAccount';
    this.accountForm = this.FormBuilder.group({
      type: this.FormBuilder.control('SavingAccount'), // intial value
      id :['',Validators.required],

      balance: this.FormBuilder.control('',[
        Validators.required
      ]),


      createdAt :['',Validators.required],

      interestRate: this.FormBuilder.control(0, [
        Validators.min(0),
        Validators.max(100),
        Validators.required,
      ]),
    });
  }
  addBankAccount() {
    console.log("sjfkjs");

console.log(this.editData.customer);
console.log("sjfkjs");

      this.account.customer= this.editData.customer;
    this.account.balance = this.accountForm.value.balance;
    this.account.type = this.selectedType;
    this.account.createdAt = this.accountForm.value.createdAt;
    this.account.interestRate= this.accountForm?.value.interestRate;
    this.account.overDraft= this.accountForm?.value.overDraft;
    this.account.id = this.accountForm?.value.id;


      this.api.saveBankAccount(this.account).subscribe({
          next:(res)=>{
            alert("account added successfully");
            this.accountForm.reset();
            this.dialogRef.close("save");
          },
          error:()=>{
            alert("something went wrong")

          }
        })

  }
}
