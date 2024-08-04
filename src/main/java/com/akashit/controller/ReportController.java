package com.akashit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.akashit.entity.CitizenPlan;
import com.akashit.request.SearchRequest;
import com.akashit.service.ReportService;

@Controller
public class ReportController {

    @Autowired
    private ReportService service;

    @PostMapping("/search")
    public String handleSearch(@ModelAttribute("search") SearchRequest request, Model model) {
        List<CitizenPlan> plans = service.search(request);
        model.addAttribute("plans", plans);
        model.addAttribute("search", request);
        model.addAttribute("searchPerformed", true); // Flag to indicate search was performed
        init(model);
        return "index";
    }

    @GetMapping("/")
    public String getPage(Model model) {
        SearchRequest searchObj = new SearchRequest();
        model.addAttribute("search", searchObj);
        model.addAttribute("searchPerformed", false); // Flag to indicate no search was performed
        init(model);
        return "index";
    }


    private void init(Model model) {
        model.addAttribute("names", service.getPlanNames());
        model.addAttribute("status", service.getPlanStatus());
    }

}
