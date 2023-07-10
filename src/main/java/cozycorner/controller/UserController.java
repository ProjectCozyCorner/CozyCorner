package cozycorner.controller;

import cozycorner.domain.Address;
import cozycorner.domain.User;
import cozycorner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String userList(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "/User/TestUserList";
    }

    @GetMapping("/user/{userId}/address")
    public String userAddressList(@PathVariable("userId") Long userId, Model model){
        List<Address> userAddresses = userService.findUserAddresses(userId);

        model.addAttribute("userAddresses", userAddresses);
        return "/User/TestUserAddressList";
    }
}
