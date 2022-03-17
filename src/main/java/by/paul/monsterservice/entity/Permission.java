package by.paul.monsterservice.entity;

public enum Permission {
  MONSTER_CHANGE("access:change"),
  MONSTER_ADD("access:write");

  private final String permission;

  Permission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}
