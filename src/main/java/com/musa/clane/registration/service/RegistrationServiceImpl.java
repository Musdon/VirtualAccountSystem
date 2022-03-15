package com.musa.clane.registration.service;

import com.musa.clane.registration.domain.User;
import com.musa.clane.registration.dto.RegisterDto;
import com.musa.clane.registration.dto.RegisterResponseDto;
import com.musa.clane.registration.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    MessageSource messageSource;

    @Override
    public RegisterResponseDto registerUser(RegisterDto registerDto) {
        RegisterResponseDto responseDto = new RegisterResponseDto();
        try {
            User user = null;
            if (registerDto.getId()>0){
                user = registrationRepository.findById(registerDto.getId()).get();
            }
            if (user==null){
                user = new User();
                user.setId(0L);
                user.setCreatedDate(new Date());
            }
            user.setFirstname(registerDto.getFirstname());
            user.setLastname(registerDto.getLastname());
            user.setEmail(registerDto.getEmail());
            user.setMiddlename(registerDto.getMiddlename());
            user.setUsername(registerDto.getUsername());
            user.setBvn(registerDto.getBvn());
            user.setKyclevel(setKycLevel(registerDto.getBvn()));
            user.setAccountlimit(setAccountLimit(registerDto.getBvn()));
            user.setAccountBalance(10000.00);
            user.setWithdrawalLimit(setWithdrawalLimit(registerDto.getBvn()));
            user.setWalletAccountNumber(generateWalletAccountNumber(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RegisterResponseDto> viewAllUsers(int pageNum, int pageSize, String sortBy) {
        List<RegisterResponseDto> list = new ArrayList<>();
        try {
            Pageable pageableRequest = PageRequest.of(pageNum, pageSize);
            Page<User> listB = registrationRepository.findAll(pageableRequest);
            if (listB!=null && listB.getSize()>0){
                for (User user: listB.toList()){
                    try {
                        RegisterResponseDto rrd = new RegisterResponseDto();
                        rrd.setFirstname(user.getFirstname());
                        rrd.setLastname(user.getLastname());
                        rrd.setMiddlename(user.getMiddlename());
                        rrd.setUsername(user.getUsername());
                        rrd.setWalletAccountNumber(user.getWalletAccountNumber());

                        list.add(rrd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private String setKycLevel(String bvn){
        String kycLevel = "";
        if (bvn == null){
            kycLevel = ("Level 1");
        }
        kycLevel = "Level 2";
        return kycLevel;
    }

    private Double setAccountLimit(String bvn){
        Double accountLimit = 100000.00;
        if (!bvn.equals("")){
            accountLimit = 1000000.00;
        }
        return accountLimit;
    }

    private Double setWithdrawalLimit(String bvn){
        Double withdrawalLimit = 95000.00;
        if (!bvn.equals("")){
            withdrawalLimit = 10000000.00;
        }
        return withdrawalLimit;
    }

    private String generateWalletAccountNumber(int len){
        String finalString = "";
        int x = 0;
        char[] stringChars = new char[len];
        for (int i = 0; i < len; i++) //4
        {
            Random random = new Random();
            x = random.nextInt(9);

            stringChars[i] = Integer.toString(x).toCharArray()[0];
        }


        finalString = new String(stringChars);

        return finalString.trim();

    }
}
