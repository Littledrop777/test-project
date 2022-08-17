package by.exlab.shire_test.mapper;

import by.exlab.shire_test.dto.AppUserReadDto;
import by.exlab.shire_test.entity.AppUser;

public class AppUserReadMapper implements Mapper<AppUser, AppUserReadDto> {

  @Override
  public AppUserReadDto map(AppUser object) {
    return new AppUserReadDto(
        object.getId(),
        object.getEmail(),
        object.getPassword(),
        object.getRole()
    );
  }
}
