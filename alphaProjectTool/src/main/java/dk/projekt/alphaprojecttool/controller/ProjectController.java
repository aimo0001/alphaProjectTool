package dk.projekt.alphaprojecttool.controller;

import dk.projekt.alphaprojecttool.model.Project;
import dk.projekt.alphaprojecttool.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "projects/list";
    }

    @GetMapping("/{id}")
    public String showProject(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Project> projectOpt = projectService.findById(id);
        if (projectOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Projekt ikke fundet");
            return "redirect:/projects";
        }
        model.addAttribute("project", projectOpt.get());
        return "projects/detail";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new Project());
        return "projects/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Project> projectOpt = projectService.findById(id);
        if (projectOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Projekt ikke fundet");
            return "redirect:/projects";
        }
        model.addAttribute("project", projectOpt.get());
        return "projects/form";
    }

    @PostMapping
    public String saveProject(@Valid @ModelAttribute("project") Project project,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "projects/form";
        }
        projectService.save(project);
        redirectAttributes.addFlashAttribute("message", "Projekt gemt");
        return "redirect:/projects";
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        projectService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Projekt slettet");
        return "redirect:/projects";
    }
}
