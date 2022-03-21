package by.paul.monsterservice.entity;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
  USER(Set.of(Permission.MONSTER_ADD)),
  ADMIN(Set.of(Permission.MONSTER_CHANGE, Permission.MONSTER_ADD));

  private final Set<Permission> permissions;

  Role(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public Set<SimpleGrantedAuthority> getAuthorities() {
    return getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toSet());
  }
}
