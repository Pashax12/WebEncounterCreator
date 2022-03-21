package by.paul.monsterservice.controller;

import by.paul.monsterservice.dto.AuthenticationRequestDTO;
import by.paul.monsterservice.dto.UserDTO;
import by.paul.monsterservice.service.registration.RegistrationService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class RegistrationController {

  private final RegistrationService registrationService;
  @PostMapping("/singup")
  public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO userDTO) {
    return ResponseEntity.ok(registrationService.registerUser(userDTO));
  }
  @PostMapping("/singin")
  public ResponseEntity<Map<String, String>> authenticate(@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
    return ResponseEntity.ok(registrationService.authenticate(authenticationRequestDTO));
  }
  @PostMapping("/logout")
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    registrationService.logoutUser(request, response);

  }

}
