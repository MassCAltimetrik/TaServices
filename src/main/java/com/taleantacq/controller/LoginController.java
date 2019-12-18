package com.taleantacq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String doLogin() {
        return "login";
    }
    
    @GetMapping("/upload")
    public ModelAndView doUpload(@RequestParam("taId") String id, Model model ) {
        model.addAttribute("taid", id);
        return new ModelAndView("upload");
    }
}
