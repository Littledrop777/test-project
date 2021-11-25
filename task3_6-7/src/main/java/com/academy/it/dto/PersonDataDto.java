package com.academy.it.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PersonDataDto {

    private Long id;
    private String name;
    private String surname;
    private String city;
    private String street;
    private String postalCode;
    private String companyName;
}
