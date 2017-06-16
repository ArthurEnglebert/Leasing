package com.citobi.leasing.web.controller.user;

import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.Reservation;
import com.citobi.leasing.domain.User;
import com.citobi.leasing.service.CarService;
import com.citobi.leasing.service.ReservationService;
import com.citobi.leasing.service.UserService;
import com.citobi.leasing.tools.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Password Encoder");
        model.addObject("message", "This page is for ROLE_USER and ROLE_ADMIN only!");
        model.setViewName(PREFIX_JSP + "user");

        return model;
    }

    @RequestMapping(value = "setReservation", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView setReservation(@RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                       @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
                                       @RequestParam(value = "carId", required = false) Long carId,
                                       Principal principal) {

        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX_JSP + "setReservation");
        model.addObject("cars", carService.getAvailables());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        model.addObject("startTime", dateFormat.format(now));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();
        model.addObject("endTime", dateFormat.format(tomorrow));

        if (start != null && end != null && carId != null && carId != 0 && datesAreCorrect(start, end)) {
            try {
                User user = userService.getUser(principal.getName());
                Car car = carService.getById(carId);
                Reservation reservation = reservationService.addReservation(user, car, start, end);
                model.addObject("msg", "Reservation successfully created! (userid = " + reservation.getUser().getId() + ", " +
                        "carid = " + reservation.getCar().getId() + ", " +
                        "start = " + dateFormat.format(reservation.getStart()) + ", " +
                        "end = " + dateFormat.format(reservation.getEnd()) + ")");
            } catch (Exception ex) {
                model.addObject("error", "Error creating the reservation:" + ex.getMessage());
            }
        }

        return model;
    }

    private boolean datesAreCorrect(Date start, Date end) {
        if (end.before(start))
            return false;

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        Calendar startDay = Calendar.getInstance();
        startDay.setTime(start);
        if (startDay.get(Calendar.YEAR) < today.get(Calendar.YEAR) || startDay.get(Calendar.DAY_OF_YEAR) < today.get(Calendar.DAY_OF_YEAR))
            return false;

        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(new Date());
        tomorrow.add(Calendar.DATE, 1);
        Calendar endDay = Calendar.getInstance();
        endDay.setTime(end);
        if (startDay.get(Calendar.YEAR) < today.get(Calendar.YEAR) || startDay.get(Calendar.DAY_OF_YEAR) < today.get(Calendar.DAY_OF_YEAR))
            return false;

        return true;
    }
}
