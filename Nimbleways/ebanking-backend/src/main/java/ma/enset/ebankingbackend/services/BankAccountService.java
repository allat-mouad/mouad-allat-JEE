package ma.enset.ebankingbackend.services;

import ma.enset.ebankingbackend.dtos.CustomerDTO;
import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.enset.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);
    SavingAccount saveSavingBankAccount(double initialBalance, double intrestRate, Long customerId);
    List<CustomerDTO> listCustomers();
    List<BankAccount> bankAccountList();
     BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer (String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException;

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
}
