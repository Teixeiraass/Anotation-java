package com.notes.Notes.Project.controller;

import com.notes.Notes.Project.config.security.TokenService;
import com.notes.Notes.Project.model.user.AuthenticationDTO;
import com.notes.Notes.Project.model.user.LoginResponseDTO;
import com.notes.Notes.Project.model.user.RegisterDTO;
import com.notes.Notes.Project.model.user.User;
import com.notes.Notes.Project.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usenamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usenamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity resgister(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), data.login(), encriptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
