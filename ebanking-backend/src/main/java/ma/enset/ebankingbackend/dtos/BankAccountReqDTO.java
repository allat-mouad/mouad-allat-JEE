package ma.enset.ebankingbackend.dtos;

import lombok.Data;

import java.util.Date;


@Data

public class BankAccountReqDTO {
    private String id;

    private double balance;

    private Date createdAt;

    private CustomerDTO customer;


    private double overDraft;

    private String type;

    private double interestRate;


}
