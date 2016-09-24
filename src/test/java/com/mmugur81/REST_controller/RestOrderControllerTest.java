package com.mmugur81.REST_controller;

import com.mmugur81.REST_model.RestOrder;
import com.mmugur81.model.Currency;
import com.mmugur81.model.Order;
import com.mmugur81.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Mugurel on 24.09.2016.
 * RestOrderController - test
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:import.sql")
@ComponentScan("com.mmugur81")
public class RestOrderControllerTest {

    private final String BASE_PATH = "/api/order";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private OrderService orderService;

    private MockMvc mockMvc;

    private Order order;

    @Before
    public void setup() throws Exception {
        if (this.mockMvc == null) {
            this.mockMvc = webAppContextSetup(webApplicationContext).build();
            this.createOrder();
        }
    }

    private void createOrder() {
        //order = orderService.createOrder(1L, Currency.EUR);
        order = orderService.get(1L);
        //orderService.save(order);
    }

    @Test
    public void contextLoads() {

    }

    /* TODO need to rethink REST controllers testing
    @Test
    public void getMethodTest() throws Exception {
        MvcResult result = this.mockMvc.perform(get(BASE_PATH + "/" + 100)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
                //.andExpect(content().contentType("application/json"))
                //.andExpect(jsonPath("$.id", is("1")))
                //.andExpect(jsonPath("$.userId", is("2"))
        //);
    }*/
}
