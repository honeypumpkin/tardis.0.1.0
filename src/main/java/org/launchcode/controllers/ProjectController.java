/* 
Made by robin on 8/3/17, 9:50 PM 
*/

package org.launchcode.controllers;


import org.launchcode.models.Project;
import org.launchcode.models.data.ProjectDao;
import org.launchcode.models.data.TaskDao;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("project")
public class ProjectController {

    @Autowired
    ProjectDao projectDao;

    @Autowired
    TaskDao taskDao;

    @Autowired
    UserDao userDao;

    // Request path: /project
    @RequestMapping(value = "index")
    public String index(Model model) {

        model.addAttribute("projects", projectDao.findAll());
        model.addAttribute("title", "My Projects");

        return "project/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddProjectForm(Model model) {

        model.addAttribute("title", "Add Project");
        model.addAttribute(new Project());

        return "project/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddProjectForm(@ModelAttribute @Valid Project newProject, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Project");
            return "project/add";
        }

        return "project/index";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveProjectForm(Model model) {

        model.addAttribute("projects", projectDao.findAll());
        model.addAttribute("title", "Remove Project");
        return "project/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveProjectForm(@RequestParam int[] ids) {

        for (int id : ids) {
            projectDao.delete(id);
        }

        return "redirect";
    }
}
