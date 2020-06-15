package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, Integer> {

    Optional<Option> findByName(String name);
}
