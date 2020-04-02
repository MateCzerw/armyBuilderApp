package com.czerwo.armybuilder.models;

import com.czerwo.armybuilder.models.data.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Integer> {
}
