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
  private final boolean isActive;
  private final List<SimpleGrantedAuthority> authorities;

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

  @Override
  public boolean isAccountNonExpired() {
    return isActive;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isActive;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isActive;
  }

  @Override
  public boolean isEnabled() {
    return isActive;
  }

  public static UserDetails fromUser(User user) {
    return new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(),
        true,true,true,true,
        user.getRole().getAuthorities()
    );
  }
}
