package ma.enset.ebankingbackend.dtos;

import lombok.Data;
import ma.enset.ebankingbackend.entities.AccountOperation;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.enums.AccountStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus accountStatus;
    private CustomerDTO customerDTO;
    private double interestRate;

}
