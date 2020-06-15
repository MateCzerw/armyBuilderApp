package com.czerwo.armybuilder;

import com.czerwo.armybuilder.models.ArmyRepository;
import com.czerwo.armybuilder.models.data.Army;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
//@AutoConfigureTestDatabase
@Sql(scripts = "classpath:db/testScripts/test.sql")
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=create-drop",
//        "spring.flyway.enabled=false"
//})
public class IntegrationTestArmy
{

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    ArmyRepository armyRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }


    @Test
    public void ShouldReturnOkStatusWhenNewArmyCreated() throws Exception {

        mockMvc.perform(post("/admin/armies/add")
                .param("name", "Ninjas"))
                .andExpect(status().is3xxRedirection());

        Optional<Army> armyByName = armyRepository.findByName("Ninjas");





        String name = armyByName.map(u -> u.getName()).orElseThrow(RuntimeException::new);


        assertEquals("Ninjas", name);


    }

}
