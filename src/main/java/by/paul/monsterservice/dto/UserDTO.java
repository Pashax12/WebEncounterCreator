package by.paul.monsterservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull
public class UserDTO {
  @JsonProperty("email")
  private String email;
  @JsonProperty("password")
  private String password;
  @JsonProperty("name")
  private String name;
}
