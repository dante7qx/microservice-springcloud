package org.dante.springcloud.dao;

import org.dante.springcloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

}
