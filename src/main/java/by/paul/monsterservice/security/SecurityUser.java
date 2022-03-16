package by.paul.monsterservice.security;

import by.paul.monsterservice.entity.User;
import java.util.List;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class SecurityUser implements UserDetails {

  private final String username;
  private final String password;
  private final List<SimpleGrantedAuthority> authorities;
  private final boolean isActive;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  //А если пользователь условно не может заиметь бан?

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public static UserDetails fromUser(User user) {
    return new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(),
        user.getRole().getAuthorities()
    );
  }
}
