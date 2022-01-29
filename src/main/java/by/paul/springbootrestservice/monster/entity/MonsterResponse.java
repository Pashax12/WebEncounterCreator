package by.paul.springbootrestservice.monster.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class MonsterResponse {

  @JsonProperty("status")
  private String statusString;
  private HttpStatus statusCode;
}
