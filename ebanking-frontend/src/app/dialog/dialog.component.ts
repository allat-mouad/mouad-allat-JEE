import {Component, Inject, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validator, Validators} from "@angular/forms";
import {MatDialogRef,MAT_DIALOG_DATA} from "@angular/material/dialog";
import {AccountService} from "../services/account.service";

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  accountForm!: FormGroup;

  constructor( @Inject(MAT_DIALOG_DATA)public editData:any,private api:AccountService, private  FormBuilder:FormBuilder,private dialogRef : MatDialogRef<DialogComponent>) { }

  ngOnInit(): void {
    this.accountForm= this.FormBuilder.group({
      id :['',Validators.required],
      balance :['',Validators.required],
      type :['',Validators.required],
      ammont :['',Validators.required],
      date :['',Validators.required],
      createdAt :['',Validators.required]
    })
  }

  addBankAccount() {
    if(this.accountForm.valid){

      this.api.saveBankAccount(this.accountForm.value.id,
        this.accountForm.value.balance,
        this.accountForm.value.type,
        this.accountForm.value.date,
        this.accountForm.value.amount,
        1
        ).subscribe({
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
}
