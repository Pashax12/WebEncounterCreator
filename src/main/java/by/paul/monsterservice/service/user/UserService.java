package by.paul.monsterservice.service.user;

import by.paul.monsterservice.dto.AuthenticationRequestDTO;
import by.paul.monsterservice.dto.UserDTO;
import by.paul.monsterservice.entity.Role;
import by.paul.monsterservice.entity.User;
import by.paul.monsterservice.exception.UserNotUniqueByUsernameException;
import by.paul.monsterservice.repository.UserRepository;
import by.paul.monsterservice.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service("registrationService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final ObjectMapper objectMapper;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  public boolean checkIsUniqueByEmail(String email) {
    return userRepository.existsUserByEmail(email);
  }

  public void addUser(User user) {
    userRepository.save(user);
  }

  @SneakyThrows
  public User registerUser(UserDTO userDTO) {
    User user = objectMapper.convertValue(userDTO, User.class);
    user.setRole(Role.USER);
    user.setActive(true);
    if (!checkIsUniqueByEmail(user.getEmail())) {
      addUser(user);
      return user;
    }else{
      throw new UserNotUniqueByUsernameException("Username not unique");
    }

  }

  public  Map<String, String> authenticate(AuthenticationRequestDTO request) {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
      String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
      Map<String, String> response = new HashMap<>();
      response.put("email", request.getEmail());
      response.put("token", token);
      return response;
  }

  public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
    securityContextLogoutHandler.logout(request, response, null);
  }

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    return userRepository.findByEmail(name).orElseThrow(() ->
        new UsernameNotFoundException("User doesn't exists"));
  }
}
