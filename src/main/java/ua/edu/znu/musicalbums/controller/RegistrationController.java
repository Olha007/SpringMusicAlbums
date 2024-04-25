package ua.edu.znu.musicalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.znu.musicalbums.model.User;
import ua.edu.znu.musicalbums.repository.UserRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@RequestParam String username,
                           @RequestParam String password,RedirectAttributes redirectAttributes) {
        User existingUser = userRepository.findByUsername(username);
        String message;
        if (existingUser != null) {
            message = "User with username already registered!";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/registration";
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            userRepository.save(newUser);
            message = "User has successfully registered!";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/login";
        }
    }
}

