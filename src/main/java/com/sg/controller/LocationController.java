package com.sg.controller;

import com.sg.dto.Location;
import com.sg.model.commandmodel.location.editlocation.LocationEditCommandModel;
import com.sg.model.commandmodel.location.locationpage.LocationPageCreateCommandModel;
import com.sg.model.viewmodel.location.detailslocation.LocationDetailsViewModel;
import com.sg.model.viewmodel.location.editlocation.LocationEditViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationPageViewModel;
import com.sg.service.LocationService;
import com.sg.webservice.DependencyException;
import com.sg.webservice.LocationWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/location")
public class LocationController {

    @Inject
    LocationWebService locationWebService;

    @RequestMapping(value = "/mainpage")
    public String displayLocationMainPage(@RequestParam(required = false)Integer offset, Model model) {


        LocationPageViewModel viewModel = locationWebService.retrieveLocationPageViewModel(5, offset, 5);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getLocationPageCreateCommandModel());

        return "location/mainpage";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createLocation(@RequestParam(required = false)Integer offset , @Valid @ModelAttribute("commandModel")LocationPageCreateCommandModel commandModel, BindingResult rs, Model model) {

        if(rs.hasErrors()) {

            LocationPageViewModel viewModel = locationWebService.retrieveLocationPageViewModel(5, offset, 5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "location/mainpage";

        }
        Location location = locationWebService.saveLocationPageCreateCommandModel(commandModel);

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/details")
    public String displayLocationDetails(@RequestParam Long id, Model model) {

        LocationDetailsViewModel viewModel = locationWebService.retrieveLocationDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "location/details";
    }

    @RequestMapping(value = "/delete")
    public String deleteLocation(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        try {
            locationWebService.removeLocationViewModel(id);
        } catch (DependencyException ex) {
            redirectAttributes.addFlashAttribute("message", "dependency");
        }

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/edit")
    public String editLocationDisplay(@RequestParam Long id,Model model) {

        LocationEditViewModel viewModel = locationWebService.retrieveLocationEditViewModel(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getLocationEditCommandModel());

        return "location/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditPower(@Valid @ModelAttribute("commandModel") LocationEditCommandModel commandModel, BindingResult rs, Model model) {

        if(rs.hasErrors()) {
            LocationEditViewModel viewModel = locationWebService.retrieveLocationEditViewModel(commandModel.getLocationId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "location/edit";
        }

        Location location = locationWebService.saveLocationEditCommandModel(commandModel);

        return "redirect:mainpage";
    }
}
