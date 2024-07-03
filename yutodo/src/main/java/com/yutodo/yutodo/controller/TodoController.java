package com.yutodo.yutodo.controller;

import com.yutodo.yutodo.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.yutodo.yutodo.services.TodoService;

@Controller
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping
    public String viewAllTodoItem(Model model, @ModelAttribute("Message") String Message) {
        model.addAttribute("list", service.getAllItem());
        model.addAttribute("msg", Message);
        return "viewAllTasks";
    }
    @GetMapping("/viewAllTasks")
    public String viewAllTodoItemttt(Model model, @ModelAttribute("Message") String Message) {
        model.addAttribute("list", service.getAllItem());
        model.addAttribute("msg", Message);
        return "viewAllTasks";
    }


    @PostMapping("/updateTodoStatus/{id}")
    public String updateTodoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.isComplete(id)) {
            redirectAttributes.addFlashAttribute("Message", "Update success");
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/addForm")
    public String addTodoItem(Model model) {
        model.addAttribute("todo", new TodoItem());
        return "addForm";
    }

    @PostMapping("/addForm")
    public String saveTodoItem(@ModelAttribute("todo") TodoItem todo, RedirectAttributes redirectAttributes) {
        if (service.saveOrUpdateToDoItem(todo)) {
            redirectAttributes.addFlashAttribute("Message", "Save success");
        } else {
            redirectAttributes.addFlashAttribute("Message", "Save failure");
        }
        return "redirect:/";
    }

    @GetMapping("/editTodoItem/{id}")
    public String editTodoItem(@PathVariable Long id, Model model) {
        model.addAttribute("todo", service.getTodoItemByid(id));
        return "editTask";
    }

    @PostMapping("/editSaveTodoItem")
    public String editSaveTodoItem(@ModelAttribute("todo") TodoItem todo, RedirectAttributes redirectAttributes) {
        if (service.saveOrUpdateToDoItem(todo)) {
            redirectAttributes.addFlashAttribute("Message", "Edit success");
        } else {
            redirectAttributes.addFlashAttribute("Message", "Edit failure");
        }
        return "redirect:/";
    }

    @GetMapping("/deleteTodoItem/{id}")
    public String deleteTodoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.deleteTodoItem(id)) {
            redirectAttributes.addFlashAttribute("Message", "Delete success");
        } else {
            redirectAttributes.addFlashAttribute("Message", "Delete failure");
        }
        return "redirect:/";
    }
}
