package com.citobi.leasing.web.controller.admin;

import com.citobi.leasing.domain.User;
import com.citobi.leasing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String PREFIX_JSP = "pages/admin/";

    @Autowired
    UserService userService;

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
    @RequestMapping(value = "createUser", method = RequestMethod.GET)
    @ResponseBody
    public String create(String username, String password, boolean isAdmin) {
        User user = null;
        try {
            user = userService.createUser(username,password,isAdmin);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    @RequestMapping(value = "/test")
    public ModelAndView test() {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX_JSP + "test");

        return model;
    }
}
