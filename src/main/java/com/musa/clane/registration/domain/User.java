package com.musa.clane.registration.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String middlename;
    private String username;
    private String bvn;
    private String walletAccountNumber;
    private String kyclevel;
    private Double accountlimit;
    private Double accountBalance;
    private Double withdrawalLimit;
    private Date createdDate;

}
