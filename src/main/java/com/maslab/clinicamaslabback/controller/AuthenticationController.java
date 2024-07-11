package com.maslab.clinicamaslabback.controller;

import com.maslab.clinicamaslabback.model.user.AuthenticationDTO;
import com.maslab.clinicamaslabback.model.user.LoginResponseDTO;
import com.maslab.clinicamaslabback.model.user.RegisterDTO;
import com.maslab.clinicamaslabback.model.user.Usuario;
import com.maslab.clinicamaslabback.repository.UsuarioRepository;
import com.maslab.clinicamaslabback.security.TokenService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {  // Controlador para gerenciar as operações de autenticação

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
         try {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        // Autentica o usuário com base nas credenciais fornecidas

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        // Gera um token JWT para o usuário autenticado

        return ResponseEntity.ok(new LoginResponseDTO(token));
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login failed: " + e.getMessage());
        // Retorna um token de autenticação ou uma mensagem de erro se a autenticação falhar
    }
    }

    @PostMapping("/cadastro")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        // Verifica se o usuário já existe no banco de dados

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());
        newUser.setUser_type(data.role().name());
        // Cria um novo usuário com a senha criptografada e o salva no banco de dados

        this.usuarioRepository.save(newUser);

        return ResponseEntity.ok().build();
        // Retorna uma resposta de sucesso se o registro for bem-sucedido
    }
}
