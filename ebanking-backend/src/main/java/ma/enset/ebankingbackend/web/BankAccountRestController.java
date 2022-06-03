package ma.enset.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.dtos.*;
import ma.enset.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.enset.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class BankAccountRestController {
    private BankAccountService bankAccountService;


    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) {
        return bankAccountService.getBankAccount(accountId);
    }


    @GetMapping("/account/{customerId}")
    public List<BankAccountDTO> getBankAccountByCustomer(@PathVariable Long customerId) {
        return bankAccountService.getBankAccountByCustomerId(customerId);
    }



    @GetMapping("/accounts")
    public List<BankAccountDTO> getBankAccount() {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5")int size) {
         return bankAccountService.getAccountHistory(accountId,page,size);
    }

    @PostMapping("/accounts")
    public BankAccountDTO saveBankAccount(@RequestBody BankAccountReqDTO bankAccountDTO) {
        BankAccountDTO dto;
        if (bankAccountDTO.getType().equals("SavingAccount")) {
            this.bankAccountService.getCustomer(bankAccountDTO.getCustomer().getId());
            dto = bankAccountService.saveSavingBankAccount(
                    bankAccountDTO.getBalance(),
                    bankAccountDTO.getInterestRate(),
                    bankAccountDTO.getCustomer().getId()
            );
        } else {
            this.bankAccountService.getCustomer(bankAccountDTO.getCustomer().getId());
            dto = bankAccountService.saveCurrentBankAccount(
                    bankAccountDTO.getBalance(),
                    bankAccountDTO.getOverDraft(),
                    bankAccountDTO.getCustomer().getId()
            );
        }
        return dto;
    }







}
