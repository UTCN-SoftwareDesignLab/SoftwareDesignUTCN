package org.example.spring1.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

  private String jwtSecret;
  private long jwtExpirationMs;

}
