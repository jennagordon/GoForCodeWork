package com.sg.controller;

import com.sg.model.viewmodel.homepage.HomePageViewModel;
import com.sg.webservice.HomePageWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class HomePageController {

    @Inject
    HomePageWebService homePageWebService;


    @RequestMapping(value = "/")
    public String displayHomePage(Model model) {

        HomePageViewModel viewModel = homePageWebService.retrieveHomePageViewModel(10);

        model.addAttribute("viewModel", viewModel);

        return "index";
    }

}
