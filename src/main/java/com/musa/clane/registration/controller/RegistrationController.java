package com.musa.clane.registration.controller;

import com.musa.clane.registration.dto.RegisterDto;
import com.musa.clane.registration.dto.RegisterResponseDto;
import com.musa.clane.registration.service.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    RegistrationServiceImpl registrationService;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/save")
    public RegisterResponseDto createUser(HttpServletRequest request, @Valid @RequestBody RegisterDto registerDto){
        RegisterResponseDto responseDto = new RegisterResponseDto();
        responseDto = registrationService.registerUser(registerDto);
        return responseDto;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<RegisterResponseDto>> fetchUsers(HttpServletRequest request, HttpServletResponse response){
        List<RegisterResponseDto> list = new ArrayList<>();
        try {
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            list = registrationService.viewAllUsers(pageNumber, pageSize, "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(list);
    }
}
