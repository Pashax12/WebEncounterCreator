package by.paul.monsterservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler({AuthenticationException.class, UsernameNotFoundException.class})
  public ResponseEntity<String> handleException(AuthenticationException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
  }


}
