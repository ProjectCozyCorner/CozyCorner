package cozycorner.controller;

import cozycorner.domain.User;
import cozycorner.service.MemberService;
import cozycorner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final MemberService memberService;

    @GetMapping("/user/{userId}/myPage")
    public String userAbout(@PathVariable("userId") Long userId, Model model){
        User user = userService.findOne(userId);
        model.addAttribute("user", user);
        return "/User/userInfo";
    }


//    @GetMapping("/users")
//    public String userList(Model model){
//        List<Member> members = memberService.findUsers();
//        model.addAttribute("users", members);
//        return "/User/TestUserList";
//    }
//
//    @GetMapping("/user/{userId}/address")
//    public String userAddressList(@PathVariable("userId") Long userId, Model model){
//        List<Address> userAddresses = memberService.findUserAddresses(userId);
//
//        model.addAttribute("userAddresses", userAddresses);
//        return "/User/TestUserAddressList";
//    }

    @PostMapping("/signup")
    public String signup(User user) {
        memberService.save(user);
        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
