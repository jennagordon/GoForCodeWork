package com.sg.controller;

import com.sg.dto.Sighting;
import com.sg.model.commandmodel.sighting.editsighting.SightingEditCommandModel;
import com.sg.model.commandmodel.sighting.sightingpage.SightingPageCreateCommandModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageViewModel;
import com.sg.model.viewmodel.sighting.detailsighting.SightingDetailsViewModel;
import com.sg.model.viewmodel.sighting.editsighting.SightingEditViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingPageViewModel;
import com.sg.webservice.SightingWebService;
import com.sg.webservice.SightingWebServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/sighting")
public class SightingController {

    @Inject
    SightingWebService sightingWebService;

    @RequestMapping(value = "/mainpage")
    public String displaySightingMainPage(@RequestParam(required = false)Integer offset, Model model) {


        SightingPageViewModel viewModel = sightingWebService.retrieveSightingPageViewModel(5, offset, 5);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getSightingPageCreateCommandModel());

        return "sighting/mainpage";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createSighting(@RequestParam(required = false)Integer offset, @Valid @ModelAttribute("commandModel")SightingPageCreateCommandModel commandModel, BindingResult rs, Model model) {

        if (rs.hasErrors()) {
            SightingPageViewModel viewModel = sightingWebService.retrieveSightingPageViewModel(5, offset, 5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "sighting/mainpage";

        }

        Sighting sighting = sightingWebService.saveSightingPageCreateCommandModel(commandModel);

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/details")
    public String displaySightingDetails(@RequestParam Long id, Model model) {

        SightingDetailsViewModel viewModel = sightingWebService.retrieveSightingDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "sighting/details";
    }

    @RequestMapping(value = "/delete")
    public String deleteSighting(@RequestParam Long id, Model model) {

        sightingWebService.removeSightingViewModel(id);

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/edit")
    public String editSightingDisplay(@RequestParam Long id,Model model) {

        SightingEditViewModel viewModel = sightingWebService.retrieveSightingEditViewModel(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getSightingEditCommandModel());

        return "sighting/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditSighting(@Valid @ModelAttribute("commandModel") SightingEditCommandModel commandModel, BindingResult rs, Model model) {

        if(rs.hasErrors()) {

            SightingEditViewModel viewModel = sightingWebService.retrieveSightingEditViewModel(commandModel.getSightingId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "sighting/edit";
        }
        Sighting sighting = sightingWebService.saveSightingEditCommandModel(commandModel);

        return "redirect:mainpage";
    }
}
