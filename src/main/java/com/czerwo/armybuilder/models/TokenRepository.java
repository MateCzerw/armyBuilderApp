package com.czerwo.armybuilder.models;


import com.czerwo.armybuilder.models.data.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByValue(String value);

}
