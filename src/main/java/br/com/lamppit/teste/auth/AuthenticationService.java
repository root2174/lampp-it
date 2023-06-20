package br.com.lamppit.teste.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lamppit.teste.model.User;
import br.com.lamppit.teste.repository.UserRepository;
import br.com.lamppit.teste.util.JwtUtilities;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JwtUtilities jwtUtilities;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(String email, String password) throws RuntimeException {

       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       email,
                       password
               )
       );

       var user = userRepository.findByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        var jwtToken = jwtUtilities.generateToken(
                user
        );

        return AuthenticationResponse
                .builder()
                .id(user.getId())
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse register(String email, String password) {
        userRepository
                .findByEmail(email)
                .ifPresent(user -> {
                    throw new RuntimeException("Usuário já cadastrado");
                });

       var hashedPassword = encoder.encode(password);

       var user = User
               .builder()
               .password(hashedPassword)
               .email(email)
               .role(Role.CUSTOMER)
               .build();

       userRepository.save(user);

       var jwtToken = jwtUtilities.generateToken(
               user
       );

       return AuthenticationResponse
               .builder()
               .id(user.getId())
               .token(jwtToken)
               .build();
    }
}
