package com.example.demo.web;

import com.example.demo.model.TaskRec;
import com.example.demo.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * Created by pasha on 25.03.18.
 */
@Controller
public class TaskController {
    ITaskService taskService;

    @Autowired
    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task/add")
    public String addTask(@RequestParam String name, @RequestParam String description, Model model, HttpServletRequest request) {
        TaskRec task = new TaskRec();
        task.setName(name);
        task.setDescription(description);
        task.setTimeAdded(Calendar.getInstance().getTime());
        taskService.save(task);
        return "redirect:/task";
    }

    @GetMapping("/task")
    public String allTask(Model model) {
        List<TaskRec> taskRecList = taskService.findAll();
        model.addAttribute("taskList", taskRecList);
        return "tasks";
    }

    @GetMapping("/task/addform")
    public String addForm(Model model) {
        return "addform";
    }

    @GetMapping("/task/modifyform")
    public String modifyForm(@RequestParam Long id, Model model) {
        Optional<TaskRec> task = taskService.findById(id);
        model.addAttribute("task", task.get());

        return "modifyform";
    }

    @GetMapping("/task/finish")
    public String finishTask(@RequestParam Long id, Model model) {
        Optional<TaskRec> task = taskService.findById(id);
        if (task.isPresent()) {
            task.get().setStatus(TaskRec.TASK_STATUS_FINISHED);
            task.get().setTimeFinished(Calendar.getInstance().getTime());
            taskService.save(task.get());
        }
        return "redirect:/task";
    }

    @PostMapping("/task/modify/{id}")
    public String modifyTask(@PathVariable Long id, @RequestParam String name, @RequestParam String description, Model model) {
        Optional<TaskRec> task = taskService.findById(id);
        if (task.isPresent()) {
            task.get().setName(name);
            task.get().setDescription(description);
            taskService.save(task.get());
        }
        return "redirect:/task";
    }

    @PostMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable Long id, Model model) {
        taskService.deleteById(id);
        return "redirect:/task";
    }


}
