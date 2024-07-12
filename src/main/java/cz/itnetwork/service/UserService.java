package cz.itnetwork.service;

import cz.itnetwork.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  /**
   * Creates new user
   *
   * @param model User to create
   * @return newly created user
   */
  UserDTO create(UserDTO model);
}
