/* 
Made by robin on 8/3/17, 11:31 PM 
*/

package org.launchcode.controllers;

import org.launchcode.models.Project;
import org.launchcode.models.Task;
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
import java.util.List;

@Controller
@RequestMapping("task")

public class TaskController {

    @Autowired
    TaskDao taskDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("tasks", taskDao.findAll());
        model.addAttribute("title", "All my tasks");
        return "task/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddTaskForm(Model model) {
        model.addAttribute("title", "Add Task");
        model.addAttribute(new Task());
        model.addAttribute("projects", projectDao.findAll());
        return "task/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTaskForm(@ModelAttribute @Valid Task newTask, Errors errors, @RequestParam int projectId, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Task");
            model.addAttribute("projects", projectDao.findAll());
            return "task/add";
        }

        Project proj = projectDao.findOne(projectId);
        newTask.setProject(proj);
        taskDao.save(newTask);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveTaskForm(Model model) {
        model.addAttribute("tasks", taskDao.findAll());
        model.addAttribute("title", "Remove Task");
        return "task/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveTaskForm(@RequestParam int[] ids) {

        for (int id : ids) {
            taskDao.delete(id);
        }
        return "redirect:";
    }

    @RequestMapping(value = "project", method = RequestMethod.GET)
    public String project(Model model, @RequestParam int id) {

        Project proj = projectDao.findOne(id);
        List<Task> tasks = proj.getTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("title", "Tasks in Project: " + proj.getName());
        return "task/index";
    }
}
