package com.citobi.leasing.web.controller.user;

import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.Model;
import com.citobi.leasing.domain.Reservation;
import com.citobi.leasing.domain.User;
import com.citobi.leasing.service.CarService;
import com.citobi.leasing.service.ModelService;
import com.citobi.leasing.service.ReservationService;
import com.citobi.leasing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * /user --> User related function handlers
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final String PREFIX_JSP = "pages/user/";

    @Autowired
    CarService carService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @Autowired
    ModelService modelService;

    /**
     * /setReservation --> Handle reservation requests on GET & POST (GET for initial page show, POST to reserve something and show page back)
     * @param start date of the start of the reservation
     * @param end date of the end of the reservation
     * @param modelId the id of the model of the car chosen to reserve
     * @param principal the authenticated user
     */
    @RequestMapping(value = {"", "setReservation"}, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView setReservation(@RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                       @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
                                       @RequestParam(value = "modelId", required = false) Long modelId,
                                       Principal principal) {

        User user = userService.getUser(principal.getName());

        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX_JSP + "setReservation");
        model.addObject("title", "Reserve");

        Iterable<Car> carsAvailable = carService.getAvailables();
        List<Model> models = new ArrayList<>();
        for(Car car : carsAvailable)
            if (!models.contains(car.getModel()))
                models.add(car.getModel());

        model.addObject("modelsAvailable", models);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        model.addObject("startTime", dateFormat.format(now));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();
        model.addObject("endTime", dateFormat.format(tomorrow));

        if (start != null && end != null && modelId != null && modelId != 0) {
            try {
                Model modelOfCar = modelService.getModelById(modelId);
                Iterable<Car> cars = carService.getCarsAvailableWithModel(modelOfCar);

                Car chosen = reservationService.chooseCarForPeriod(cars, start, end);
                if (chosen == null)
                    throw new IllegalStateException("We dont have anymore cars of the model '" + modelOfCar.getBrand().getName() + " - " + modelOfCar.getName() + "' available for your period.");

                Reservation reservation = reservationService.addReservation(user, chosen, start, end);
                model.addObject("msg", "Reservation successfully created! (" +
                        "car plate = " + reservation.getCar().getNumberPlate() + ", " +
                        "start = " + dateFormat.format(reservation.getStart()) + ", " +
                        "end = " + dateFormat.format(reservation.getEnd()) + ")");
            } catch (Exception ex) {
                model.addObject("error", "Error creating the reservation: " + ex.getMessage());
            }
        }

        Iterable<Reservation> reservationsToCome = reservationService.getReservationsToComeFor(user);
        model.addObject("reservationsToCome", reservationsToCome);

        return model;
    }
}
