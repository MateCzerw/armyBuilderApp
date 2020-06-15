package com.czerwo.armybuilder.services;

import com.czerwo.armybuilder.exceptions.DuplicateArmyException;
import com.czerwo.armybuilder.exceptions.NoRequestedArmyException;
import com.czerwo.armybuilder.models.ArmyRepository;
import com.czerwo.armybuilder.models.data.Army;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArmyServiceTest {

    @Mock
    ArmyRepository armyRepository;

    @InjectMocks
    private ArmyService armyService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void ShouldReturnListOfArmiesFromRepoWhenFindAll(){
        Army first = new Army();
        first.setName("Dark Elves");
        first.setId(1);

        Army second = new Army();
        second.setName("Vampire Covenent");
        second.setId(2);

        List<Army> armies = Arrays.asList(first,second);

        when(armyRepository.findAll()).thenReturn(armies);

        List<Army> armiesFromService = armyService.findAll();

        assertEquals("Dark Elves", armiesFromService.get(0).getName());
        assertEquals("Vampire Covenent", armiesFromService.get(1).getName());

    }


    @Test
    public void ShouldReturnSingleElementFromRepoWhenFindById(){

        Army second = new Army();
        second.setName("Vampire Covenent");
        second.setId(2);


        when(armyRepository.findById(2)).thenReturn(Optional.of(second));

        Optional<Army> armyServiceById = armyService.findById(2);
        String name = armyServiceById.get().getName();

        assertEquals("Vampire Covenent", name);

        verify(armyRepository, times(1)).findById(anyInt());


    }

    @Test
    public void ShouldReturnSingleElementFromRepoWhenFindByName(){

        Army second = new Army();
        second.setName("Vampire Covenent");
        second.setId(2);


        when(armyRepository.findByName("Vampire Covenent")).thenReturn(Optional.of(second));

        Optional<Army> armyServiceByName = armyService.findByName("Vampire Covenent");
        String name = armyServiceByName.get().getName();

        assertEquals("Vampire Covenent", name);

        verify(armyRepository, times(1)).findByName(anyString());

    }


    @Test
    public void ShouldSaveWhenNotExistingInDb(){

        //given
        Army army = new Army();
        army.setName("Dark Elves");
        army.setId(1);

        //when



        when(armyService.findByName(any())).thenReturn(Optional.ofNullable(null));
        when(armyRepository.save(any())).thenReturn(any(Army.class));

        armyService.save(army);
        //then
        verify(armyRepository, times(1)).save(army);

    }




    @Test
    public void ShouldThrowErrorWhenSaveExistingElement(){

        //given
        Army army = new Army();
        army.setName("Dark Elves");
        army.setId(1);

        //when
        when(armyService.findByName(any())).thenReturn(Optional.ofNullable(army));
        expectedException.expect(DuplicateArmyException.class);
        expectedException.expectMessage("Army with name: " + army.getName() + " is duplicated!");

        armyService.save(army);
        //then
        verify(armyRepository, times(0)).save(army);


    }

    @Test
    public void ShouldUpdate(){

        //given
        Army army = new Army();
        army.setName("Dark Elves");
        army.setId(1);

        //when
        when(armyRepository.findByName(any())).thenReturn(Optional.of(army));
        when(armyRepository.save(any())).thenReturn(any(Army.class));

        armyService.update(army);
        //then
        verify(armyRepository, times(1)).save(army);

    }

    @Test
    public void ShouldThrowErrorWhenUpdateNotExistingElement(){

        //given
        Army army = new Army();
        army.setName("Dark Elves");
        army.setId(1);

        Army armyByName = new Army();
        army.setName("Dark Elves");
        army.setId(2);


        //when
        when(armyRepository.findByName(any())).thenReturn(Optional.of(armyByName));
        expectedException.expect(DuplicateArmyException.class);
        expectedException.expectMessage("Army with name: " + army.getName() + " is duplicated!");

        armyService.update(army);
        //then


        verify(armyRepository, times(0)).save(army);

    }





}