package ua.com.yaminsky.bank.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private Integer id;
    private String requisites;
    private String personType;
    private Integer bankId;
    private Integer clientId;
}
