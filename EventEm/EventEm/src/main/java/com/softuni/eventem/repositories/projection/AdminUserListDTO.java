package com.softuni.eventem.repositories.projection;

import com.softuni.eventem.entities.enums.UserRoleEnum;

public interface AdminUserListDTO {
  String getUsername();
  UserRoleEnum getRole();
  boolean getEnabled();

}
