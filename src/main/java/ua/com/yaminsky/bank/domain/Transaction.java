package ua.com.yaminsky.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Integer id;
    private String event;
    private Float sumInDollars;
    private String bankAccountBuyerId;
    private String bankAccountSellerId;
}
