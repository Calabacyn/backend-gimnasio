package com.backend_gimnasio.backend_gimnasio.exceptions;

public class MembershipNotFoundException extends RuntimeException {

  public MembershipNotFoundException() {
    super("Membership not found.");
  }

  public MembershipNotFoundException(Long id) {
    super("Membership not found with id: " + id);
  }
}
