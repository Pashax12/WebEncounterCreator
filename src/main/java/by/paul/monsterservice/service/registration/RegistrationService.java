package by.paul.monsterservice.service.registration;

import by.paul.monsterservice.dto.AuthenticationRequestDTO;
import by.paul.monsterservice.dto.UserDTO;
import by.paul.monsterservice.entity.Role;
import by.paul.monsterservice.entity.User;
import by.paul.monsterservice.repository.UserRepository;
import by.paul.monsterservice.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

  private final UserRepository userRepository;
  private final ObjectMapper objectMapper;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  @Value("${projectData.addUser.unique}")
  private String uniqueResponse;
  @Value("${projectData.addUser.notUnique}")
  private String nonUnique;


  public boolean uniqueChecker(String email) {
    return userRepository.existsUserByEmail(email);
  }

  public void addUser(User user) {
    userRepository.save(user);
  }

  public String registerUser(UserDTO userDTO) {
    User user = objectMapper.convertValue(userDTO, User.class);
    user.setRole(Role.USER);
    if (!uniqueChecker(user.getEmail())) {
      addUser(user);
      return uniqueResponse;
    }
    return nonUnique;
  }

  public ResponseEntity<?> authenticate(AuthenticationRequestDTO request) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
      String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
      Map<Object, Object> response = new HashMap<>();
      response.put("email", request.getEmail());
      response.put("token", token);
      return ResponseEntity.ok(response);
    } catch (AuthenticationException e) {
      return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
    }
  }

  public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
    securityContextLogoutHandler.logout(request, response, null);
  }


}
