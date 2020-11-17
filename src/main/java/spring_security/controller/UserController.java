package spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring_security.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
    @GetMapping(value = "/user")
    public String userPage(Model model) {
        UserDetails user = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("user", userService.getUserByName(user.getUsername()));
        return "user-page";
    }

}
