package by.paul.monsterservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotUniqueByUsernameException extends Exception{

  public UserNotUniqueByUsernameException(String message) {
    super(message);
  }

}
