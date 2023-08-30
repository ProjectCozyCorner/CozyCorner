package cozycorner.application.user.controller;

import cozycorner.application.user.domain.CustomUserDetails;
import cozycorner.application.user.domain.User;
import cozycorner.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user/userInfo")
    public String userAbout(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails customUserDetails = (CustomUserDetails) principal;

        User user = userService.findByUserEmail(customUserDetails.getUsername());
        model.addAttribute("user", user);
        return "user/userInfo";
    }

    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }

    @GetMapping(value = "/user/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/user/signup")
    public String signup(User user) {
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/user/myPage")
    public String myPage(){
        return "user/myPage";
    }
}
