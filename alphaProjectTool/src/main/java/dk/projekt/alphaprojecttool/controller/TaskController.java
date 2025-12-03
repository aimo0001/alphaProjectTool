package dk.projekt.alphaprojecttool.controller;

import dk.projekt.alphaprojecttool.model.Project;
import dk.projekt.alphaprojecttool.model.Task;
import dk.projekt.alphaprojecttool.service.ProjectService;
import dk.projekt.alphaprojecttool.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @ModelAttribute("project")
    public Project loadProject(@PathVariable Long projectId) {
        return projectService.findById(projectId).orElseThrow();
    }

    @GetMapping
    public String listTasks(@ModelAttribute("project") Project project, Model model) {
        model.addAttribute("tasks", taskService.findByProject(project));
        return "tasks/list";
    }

    @GetMapping("/new")
    public String showCreateForm(@ModelAttribute("project") Project project, Model model) {
        Task task = new Task();
        task.setProject(project);
        model.addAttribute("task", task);
        return "tasks/form";
    }

    @GetMapping("/{taskId}/edit")
    public String showEditForm(@PathVariable Long taskId,
                               @ModelAttribute("project") Project project,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        Optional<Task> taskOpt = taskService.findById(taskId);
        if (taskOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Opgave ikke fundet");
            return "redirect:/projects/" + project.getId() + "/tasks";
        }
        model.addAttribute("task", taskOpt.get());
        return "tasks/form";
    }

    @PostMapping
    public String saveTask(@ModelAttribute("project") Project project,
                           @Valid @ModelAttribute("task") Task task,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tasks/form";
        }
        task.setProject(project);
        taskService.save(task);
        redirectAttributes.addFlashAttribute("message", "Opgave gemt");
        return "redirect:/projects/" + project.getId() + "/tasks";
    }

    @PostMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId,
                             @ModelAttribute("project") Project project,
                             RedirectAttributes redirectAttributes) {
        taskService.deleteById(taskId);
        redirectAttributes.addFlashAttribute("message", "Opgave slettet");
        return "redirect:/projects/" + project.getId() + "/tasks";
    }
}
