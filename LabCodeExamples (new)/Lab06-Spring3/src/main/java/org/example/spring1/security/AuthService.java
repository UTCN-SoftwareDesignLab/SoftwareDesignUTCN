package org.example.spring1.security;

import lombok.RequiredArgsConstructor;
import org.example.spring1.exceptions.EntityNotFoundException;
import org.example.spring1.security.dto.SignupRequest;
import org.example.spring1.user.RoleRepository;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.model.ERole;
import org.example.spring1.user.model.Role;
import org.example.spring1.user.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authenticationManager;

  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public void register(SignupRequest signUpRequest) {
    User user = User.builder()
        .username(signUpRequest.getUsername())
        .password(encoder.encode(signUpRequest.getPassword()))
        .email(signUpRequest.getEmail())
        .build();

    Set<String> rolesStr = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (rolesStr == null) {
      Role defaultRole = roleRepository.findByName(ERole.CUSTOMER)
          .orElseThrow(() -> new EntityNotFoundException("Cannot find CUSTOMER role"));
      roles.add(defaultRole);
    } else {
      rolesStr.forEach(r -> {
        Role ro = roleRepository.findByName(ERole.valueOf(r))
            .orElseThrow(() -> new EntityNotFoundException("Cannot find role: " + r));
        roles.add(ro);
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
  }

  public Authentication authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
    return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
  }
}
