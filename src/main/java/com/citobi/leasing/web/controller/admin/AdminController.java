package com.citobi.leasing.web.controller.admin;

import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.LockStatus;
import com.citobi.leasing.domain.User;
import com.citobi.leasing.service.CarService;
import com.citobi.leasing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String PREFIX_JSP = "pages/admin/";

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Password Encoder");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName(PREFIX_JSP + "admin");

        return model;

    }

    /**
     * /create  --> Create a new user and save it in the database.
     *
     * @param username User's name
     * @param password User's password
     * @param isAdmin States if user has ADMIN credentials
     * @return A string describing if the user is succesfully created or not.
     */
    @RequestMapping(value = "createUser", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView create(@RequestParam(value = "username", required = false) String username,
                         @RequestParam(value = "password", required = false) String password,
                         @RequestParam(value = "isAdmin", required = false) boolean isAdmin) {

        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX_JSP + "createUser");

        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            try {
                User user = userService.createUser(username,password,isAdmin);
                model.addObject("msg", "User succesfully created!(id = " + user.getId() + ")");
            }
            catch (Exception ex) {
                model.addObject("error", "Error creating the user:" + ex.getMessage());
            }
        }

        return model;
    }

    @RequestMapping(value = "addCar", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView createCar(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX_JSP + "addCar");

        if (request.getMethod().equals("POST")) {
            try {
                Car car = carService.addCar();
                model.addObject("msg", "Car successfully created! (id = " + car.getId() + ")");
            } catch (Exception ex) {
                model.addObject("error", "Error creating the car:" + ex.getMessage());
            }
        }

        return model;
    }

    @RequestMapping(value = "editLockStatus", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView editLockStatus(@RequestParam(value = "carId", required = false) Long carId,
                                       @RequestParam(value = "lockStatus", required = false) LockStatus lockStatus) {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX_JSP + "editLockStatus");

        if (carId != null && lockStatus != null && carId != 0) {
            try {
                Car car = carService.getById(carId);
                car.setLockStatus(lockStatus);
                carService.updateCar(car);
                model.addObject("msg", "Car successfully changed! (id = " + car.getId() + ", lockStatus = " + car.getLockStatus().toString() + ")");
            } catch (Exception ex) {
                model.addObject("error", "Error changing car:" + ex.getMessage());
            }
        }

        model.addObject("cars", carService.getAll());

        return model;
    }
}
