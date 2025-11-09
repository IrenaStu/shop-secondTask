package org.example.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
  private Long userId;
  private String firstName;
  private String lastName;
  private String email;

  @Override
  public String toString() {
    return firstName + " " + lastName;
  }
}
