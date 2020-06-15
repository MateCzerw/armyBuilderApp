package com.czerwo.armybuilder.controllers;


import com.czerwo.armybuilder.models.data.Army;
import com.czerwo.armybuilder.services.ArmyService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminArmiesControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private ArmyService armyService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    public void ShouldAddArmiesToModelAndRenderIndexView() throws Exception {

        Army first = new Army();
        first.setName("Dark Elves");
        first.setId(1);

        Army second = new Army();
        second.setName("Vampire Covenent");
        second.setId(2);

        List<Army> armies = Arrays.asList(first, second);

        when(armyService.findAll()).thenReturn(armies);

        mockMvc.perform(get("/admin/armies"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/armies/index"))
                .andExpect(model().attribute("armies", hasSize(2)))
                .andExpect(model().attribute("armies", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("name", is("Dark Elves"))
                        )
                )))
                .andExpect(model().attribute("armies", hasItem(
                        allOf(
                                hasProperty("id", is(2)),
                                hasProperty("name", is("Vampire Covenent"))
                        )
                )));
    }

    @Test
    public void ShouldCreateInitFormAndHaveEmptyArmyAsModel() throws Exception {
        mockMvc.perform(get("/admin/armies/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("army"))
                .andExpect(view().name("admin/armies/add"));
    }

    @Test
    public void ShouldHaveFieldErrorSizeLowerThen3() throws Exception {

        mockMvc.perform(post("/admin/armies/add")
                .param("name", "as"))
                .andExpect(model().attributeHasFieldErrors("army", "name"))
                .andExpect(view().name("admin/armies/add"));
    }

    @Test
    public void ShouldHaveFieldErrorNotBlank() throws Exception {

        mockMvc.perform(post("/admin/armies/add")
                .param("name", ""))
                .andExpect(model().attributeHasFieldErrors("army", "name"))
                .andExpect(view().name("admin/armies/add"));

    }


    @Test
    public void ShouldRedirectAfterSuccessfulCreationOfArmyToArmyIndex() throws Exception {

        MockMultipartFile jpgFile = new MockMultipartFile("file", "test.jpg",
                "image/jpeg", "test image content".getBytes());
        mockMvc.perform(post("/admin/armies/add")
                .param("name", "Vampiry"))
                .andExpect(flash().attribute("message", "Army added"))
                .andExpect(flash().attribute("alertClass", "alert-success"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void ShouldRedirectAfterArmyAlreadyExist() throws Exception {


        Army army = new Army();
        army.setName("Vampiry");
        army.setId(1);

        when(armyService.findByName("Vampiry")).thenReturn(Optional.of(army));


        mockMvc.perform(post("/admin/armies/add")
                .param("name", "Vampiry"))
                .andExpect(flash().attribute("message", "Army Vampiry already exists. Please add a new one!"))
                .andExpect(flash().attribute("alertClass", "alert-danger"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/armies/add"));
    }


    @Test
    public void ShouldDeleteWhenArmyExist() throws Exception {

        Army army = new Army();

        doNothing().when(armyService).deleteById(anyInt());
        when(armyService.findById(anyInt())).thenReturn(Optional.of(army));

        mockMvc.perform(get("/admin/armies/delete/1"))
                .andExpect(flash().attribute("message", "Army deleted"))
                .andExpect(flash().attribute("alertClass", "alert-success"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/armies"));

        verify(armyService, times(1)).deleteById(anyInt());
    }

    @Test
    public void ShouldThrowExceptionWhenArmyNotExist() throws Exception {


        when(armyService.findById(anyInt())).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(get("/admin/armies/delete/500"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("errors/norequestedarmy"));


    }


    @Test
    public void ShouldReturnEditedModelAndView() throws Exception {

        Army army = new Army();
        army.setId(1);
        army.setName("Vampires");

        when(armyService.findById(anyInt())).thenReturn(Optional.of(army));


        mockMvc.perform(get("/admin/armies/edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("army", hasProperty("id", is(1))))
                .andExpect(model().attribute("army", hasProperty("name", is("Vampires"))))
                .andExpect(view().name("admin/armies/edit"));


    }

    @Test
    public void ShouldReturnExceptionWhenEditedArmyNotExist() throws Exception {


        when(armyService.findById(anyInt())).thenReturn(Optional.ofNullable(null));


        mockMvc.perform(get("/admin/armies/edit/1"))
                .andExpect(status().is4xxClientError())
                .andExpect(model().attributeDoesNotExist("army"))
                .andExpect(view().name("errors/norequestedarmy"));


    }

    @Test
    public void ShouldHaveFieldErrorSizeInEdit() throws Exception {

        mockMvc.perform(post("/admin/armies/edit")
                .param("name", "as"))
                .andExpect(model().attributeHasFieldErrors("army", "name"))
                .andExpect(view().name("admin/armies/edit"));
    }

    @Test
    public void ShouldHaveFieldErrorNotBlankInEdit() throws Exception {

        mockMvc.perform(post("/admin/armies/edit")
                .param("name", ""))
                .andExpect(model().attributeHasFieldErrors("army", "name"))
                .andExpect(view().name("admin/armies/edit"));

    }

    @Test
    public void ShouldSaveArmyWhenPresentInDB() throws Exception {

        Army army = new Army();
        army.setId(1);
        army.setName("Vampires");

        when(armyService.findById(anyInt())).thenReturn(Optional.of(army));
        doNothing().when(armyService).save(any());

        mockMvc.perform(post("/admin/armies/edit")
                .param("name", "Vampires")
                .param("id", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("message", "Army edited"))
                .andExpect(flash().attribute("alertClass", "alert-success"))
                .andExpect(view().name("redirect:/admin/armies"));


        verify(armyService, times(1)).save(any());
    }

    @Test
    public void ShouldThrowNoRequestedExceptionWhenNotPresentInDB() throws Exception {



        when(armyService.findById(anyInt())).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(post("/admin/armies/edit")
                .param("name", "Vampires")
                .param("id", "5"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("errors/norequestedarmy"));

    }


}