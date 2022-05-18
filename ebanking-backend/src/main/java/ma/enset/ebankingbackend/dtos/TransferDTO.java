package ma.enset.ebankingbackend.dtos;

import lombok.Data;

@Data
public class TransferDTO {
    private String accountId;
    private double amount;
    private String description;
}
