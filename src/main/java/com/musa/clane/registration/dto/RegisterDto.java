package com.musa.clane.registration.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String middlename;
    private String username;
    private String bvn;
    private String kyclevel;
    private Double accountlimit;
    private Double accountBalance;
    private Double withdrawalLimit;
    private String walletAccountNumber;
}
