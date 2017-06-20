package com.citobi.leasing.web.controller.admin;

import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.LockStatus;
import com.citobi.leasing.domain.Model;
import com.citobi.leasing.domain.User;
import com.citobi.leasing.service.CarService;
import com.citobi.leasing.service.ModelService;
import com.citobi.leasing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * /admin --> admin related functions handlers
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String PREFIX_JSP = "pages/admin/";

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    ModelService modelService;

    /**
     * /create  --> Create a new user and save it in the database.
     *
     * @param username User's name
     * @param password User's password
     * @param isAdmin States if user has ADMIN credentials
     */
    @RequestMapping(value = {"", "createUser"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView create(@RequestParam(value = "username", required = false) String username,
                         @RequestParam(value = "password", required = false) String password,
                         @RequestParam(value = "isAdmin", required = false) boolean isAdmin) {

        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX_JSP + "createUser");
        model.addObject("title", "Add user");

        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            try {
                User user = userService.createUser(username,password,isAdmin);
                model.addObject("msg", "User succesfully created!(id = " + user.getId() + ")");
            }
            catch (Exception ex) {
                model.addObject("error", "Error creating the user: " + ex.getMessage());
            }
        }

        return model;
    }

    /**
     * /addCar --> add a new car to the park
     * @param plate the plate numbers of the new car
     * @param model_id the model id of the new car
     */
    @RequestMapping(value = "addCar", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView createCar(@RequestParam(value = "plate", required = false) String plate,
                                  @RequestParam(value = "model_id", required = false) Long model_id) {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX_JSP + "addCar");
        model.addObject("title", "Add car");

        model.addObject("models", modelService.getAll());

        if (plate != null && model_id != null && !plate.isEmpty() && model_id != 0) {
            try {
                Model modelOfCar = modelService.getModelById(model_id);
                Car car = carService.addCar(plate, modelOfCar);
                model.addObject("msg", "Car successfully created! (plate = " + car.getNumberPlate() + ")");
            } catch (Exception ex) {
                model.addObject("error", "Error creating the car: " + ex.getMessage());
            }
        }

        return model;
    }

    /**
     * /editLockStatus --> change the lock status of a car of the park, locking it for the time being (being removed of the available list) or unlocking it
     * @param carId the id of the car to change status
     * @param lockStatus the new status
     */
    @RequestMapping(value = "editLockStatus", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView editLockStatus(@RequestParam(value = "carId", required = false) Long carId,
                                       @RequestParam(value = "lockStatus", required = false) LockStatus lockStatus) {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX_JSP + "editLockStatus");
        model.addObject("title", "Lock cars");

        if (carId != null && lockStatus != null && carId != 0) {
            try {
                Car car = carService.getById(carId);
                car.setLockStatus(lockStatus);
                carService.update(car);
                model.addObject("msg", "Car successfully changed! (id = " + car.getId() + ", lockStatus = " + car.getLockStatus().toString() + ")");
            } catch (Exception ex) {
                model.addObject("error", "Error changing car: " + ex.getMessage());
            }
        }

        model.addObject("cars", carService.getAll());

        return model;
    }
}
