package com.musa.clane.registration.service;

import com.musa.clane.registration.dto.RegisterDto;
import com.musa.clane.registration.dto.RegisterResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface RegistrationService {
    RegisterResponseDto registerUser(RegisterDto registerDto);
    List<RegisterResponseDto> viewAllUsers(int pageNum, int pageSize, String sortBy);
}
