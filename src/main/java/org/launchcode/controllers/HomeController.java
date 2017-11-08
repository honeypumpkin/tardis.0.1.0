/* 
Made by robin on 8/4/17, 12:36 PM 
*/

package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Welcome to TaRDIS");
        return "index";
    }

}
//TODO Send to initial log in page if no user cookie in session
