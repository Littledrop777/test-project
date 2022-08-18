package by.exlab.shire_test.dto;

import by.exlab.shire_test.entity.Status;
import lombok.Value;

@Value
public class CardReadDto {

  Long id;
  String topic;
  Status status;
  String description;
  String offer;
  AppUserReadDto userOwner;

}
