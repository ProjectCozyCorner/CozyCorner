package cozycorner.controller;

import cozycorner.model.User;
import cozycorner.repo.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    public MainController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("/")
    public String welcomePage() {
        return "index";
    }

    private UserRepo userRepo;

    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        User user = new User();
        user.setUid("Hello");
        user.setPw("World");
        userRepo.save(user);
        List<User> userList = userRepo.findAll();
        model.addAttribute(userList);
        return "welcome";
    }
}
