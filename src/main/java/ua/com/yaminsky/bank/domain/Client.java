package ua.com.yaminsky.bank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer countryId;
}
