package by.paul.monsterservice.config;

import by.paul.monsterservice.service.monster.ExpCounter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DefaultConfig {

  @Bean
  public ExpCounter expCounter() {
    return new ExpCounter();
  }
}
