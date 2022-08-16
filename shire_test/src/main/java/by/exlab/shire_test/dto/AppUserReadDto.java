package by.exlab.shire_test.dto;

import by.exlab.shire_test.entity.Role;
import lombok.Value;

@Value
public class AppUserReadDto {

  Long id;
  String email;
  String password;
  Role role;
}
