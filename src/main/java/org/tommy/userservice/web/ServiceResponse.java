package org.tommy.userservice.web;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Getter
class ServiceResponse {

  private String uuid;

  private String message;

  static ResponseEntity<ServiceResponse> generateResponse(String message, HttpStatus httpStatus) {
    return ResponseEntity.status(httpStatus).body(new ServiceResponse(generateUuid(), message));
  }

  private static String generateUuid() {
    return UUID.randomUUID().toString();
  }
}
