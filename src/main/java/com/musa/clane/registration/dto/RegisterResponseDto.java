package com.musa.clane.registration.dto;

import lombok.Data;

@Data
public class RegisterResponseDto {
    private String firstname;
    private String lastname;
    private String middlename;
    private String username;
    private String walletAccountNumber;
}
