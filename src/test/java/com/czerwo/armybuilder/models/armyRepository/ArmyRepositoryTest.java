package com.czerwo.armybuilder.models.armyRepository;

import com.czerwo.armybuilder.exceptions.NoRequestedArmyException;
import com.czerwo.armybuilder.models.ArmyRepository;
import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.models.data.Unit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:db/testScripts/test.sql")
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=create-drop",
//        "spring.flyway.enabled=false"
//})
public class ArmyRepositoryTest {

    @Autowired
    private ArmyRepository armyRepository;


    @Test
    public void shouldReturnBeastHeardWhenFindByNameBeastHeardCalled(){
        Optional<Army> army = armyRepository.findByName("Beast Herds");

      String name = army.map(u -> u.getName()).orElseThrow(RuntimeException::new);


        assertEquals("Beast Herds", name);

    }


    @Test
    public void shouldReturnArmyById(){
        Optional<Army> army = armyRepository.findById(13);

        String name = army.map(u -> u.getName()).orElseThrow(RuntimeException::new);


        assertEquals("Vampire Covenant", name);

    }

    @Test
    public void shouldReturnArmyByIdAndReturnUnits(){

        List<String> data = Arrays.asList(
                "Vampire Count",
                "Vampire Courtier",
                "Necromancer",
                "Barrow king",
                "Fell Wraith",
                "Banshee",
                "Zombies",
                "Skeletons",
                "Ghouls",
                "Bat Swarm",
                "Dire Wolves",
                "Great Bats",
                "Ghasts",
                "Barrow Guard",
                "Barrow Knights",
                "Cadavar Wagon",
                "Dark Coach",
                "Court of the Damned",
                "Altar of Undeath",
                "Phantom Hosts",
                "Wraiths",
                "Spectral Hunters",
                "Vampire Knights",
                "Vampire Spawn",
                "Varkolak",
                "Winged Reapers",
                "Shrieking Horror"
        );


        Optional<Army> army = armyRepository.findByIdWithUnits(13);

        List<Unit> units = army.map(Army::getUnits).orElseThrow(RuntimeException::new);
        List<String> unitNames = units.stream()
                .map(unit -> unit.getName())
                .collect(Collectors.toList());

        assertEquals(data,unitNames);


    }





}