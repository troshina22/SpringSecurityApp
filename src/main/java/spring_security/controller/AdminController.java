package spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring_security.model.User;
import spring_security.service.UserService;


@Controller
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/admin")
    public String showUsers(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("userList", userService.allUsers());
        return "show-users";
    }
    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user){
        if (user.getId() == null) {
            userService.add(user);
        } else {
            userService.edit(user);
        }
        return "redirect:/admin";
    }
    @RequestMapping(value = "/remove/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        userService.delete(id);
        return "redirect:/admin";
    }
    @RequestMapping(value = "/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-user";
    }
    @RequestMapping(value = "/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, User user, Model model) {
        User user1 = userService.getUserById(id);
        user.setRoles(user1.getRoles());
        userService.edit(user1);
        model.addAttribute("users", userService.allUsers());
        return "redirect:/admin";
    }
}
