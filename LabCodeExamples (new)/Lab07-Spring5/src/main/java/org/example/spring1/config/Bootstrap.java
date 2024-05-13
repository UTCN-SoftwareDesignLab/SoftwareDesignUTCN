package org.example.spring1.config;

import lombok.RequiredArgsConstructor;
import org.example.spring1.user.RoleRepository;
import org.example.spring1.user.model.ERole;
import org.example.spring1.user.model.Role;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class Bootstrap {

  private final RoleRepository roleRepository;

  @EventListener(ApplicationReadyEvent.class)
  public void bootstrapData() {
    for (ERole value : ERole.values()) {
      if (roleRepository.findByName(value).isEmpty()) {
        roleRepository.save(
            Role.builder().name(value).build()
        );
      }
    }
  }
}
