package dk.projekt.alphaprojecttool.controller;

import dk.projekt.alphaprojecttool.model.Project;
import dk.projekt.alphaprojecttool.service.ProjectService;
import dk.projekt.alphaprojecttool.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;
    private final ProjectService projectService;

    public ReportController(ReportService reportService, ProjectService projectService) {
        this.reportService = reportService;
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String projectOverview(Model model) {
        model.addAttribute("projectSummaries", reportService.getProjectSummaries());
        return "reports/projects";
    }

    @GetMapping("/projects/{projectId}")
    public String projectReport(@PathVariable Long projectId, Model model) {
        Project project = projectService.findById(projectId).orElseThrow();
        model.addAttribute("project", project);
        model.addAttribute("report", reportService.getProjectReport(projectId));
        return "reports/project-detail";
    }
}
