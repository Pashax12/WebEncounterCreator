package by.paul.monsterservice.exception;

public class UserNotUniqueByUsernameException extends RuntimeException {

  public UserNotUniqueByUsernameException(String message) {
    super(message);
  }

}
