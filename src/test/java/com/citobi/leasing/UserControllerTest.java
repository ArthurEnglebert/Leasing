package com.citobi.leasing;

import com.citobi.leasing.service.CarService;
import com.citobi.leasing.service.ModelService;
import com.citobi.leasing.service.ReservationService;
import com.citobi.leasing.service.UserService;
import com.citobi.leasing.web.controller.user.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private UserService userService;

    @MockBean
    private ModelService modelService;

    @Test
    public void testAccessToUserLockedForAnonymous() throws Exception {
        this.mockMvc.perform(get("/user/")).andDo(print()).andExpect(status().is3xxRedirection());
    }

//    @Test
//    @WithMockUser
//    public void testAccessToUserAcceptedForLoggedUser() throws Exception {
//        this.mockMvc.perform(get("/user/")).andDo(print())
//    }
}
