package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
