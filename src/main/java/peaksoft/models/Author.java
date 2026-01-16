package peaksoft.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {
Long id;
 String firstName;
 String lastName;
 String email;
 String country;
 LocalDate birthDate;

 public Author(String firstName, String lastName, String email, String country, LocalDate birthDate) {
  this.firstName = firstName;
  this.lastName = lastName;
  this.email = email;
  this.country = country;
  this.birthDate = birthDate;
 }
}
