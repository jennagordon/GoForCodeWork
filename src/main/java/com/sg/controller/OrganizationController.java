package com.sg.controller;

import com.sg.dto.Organization;
import com.sg.model.commandmodel.organization.editorganization.OrganizationEditCommandModel;
import com.sg.model.commandmodel.organization.organizationpage.OrganizationPageCreateCommandModel;
import com.sg.model.viewmodel.organization.detailsorganization.OrganizationDetailsViewModel;
import com.sg.model.viewmodel.organization.editorganization.OrganizationEditLocationViewModel;
import com.sg.model.viewmodel.organization.editorganization.OrganizationEditViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageViewModel;
import com.sg.webservice.OrganizationWebService;
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
@RequestMapping(value = "/organization")
public class OrganizationController {

    @Inject
    OrganizationWebService organizationWebService;

    @RequestMapping(value = "/mainpage")
    public String displayOrganizationMainPage(@RequestParam(required = false)Integer offset, Model model) {


        OrganizationPageViewModel viewModel = organizationWebService.retrieveOrganizationPageViewModel(5, offset, 5);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getOrganizationPageCreateCommandModel());

        return "organization/mainpage";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createOrganization(@RequestParam(required = false)Integer offset, @Valid @ModelAttribute("commandModel")OrganizationPageCreateCommandModel commandModel, BindingResult rs, Model model) {

        if(rs.hasErrors()) {
            OrganizationPageViewModel viewModel = organizationWebService.retrieveOrganizationPageViewModel(5, offset, 5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "organization/mainpage";
        }
        Organization org = organizationWebService.saveOrganizationPageCreateCommandModel(commandModel);

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/details")
    public String displayOrganizationDetails(@RequestParam Long id, Model model) {

        OrganizationDetailsViewModel viewModel = organizationWebService.retrieveOrganizationDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "organization/details";
    }

    @RequestMapping(value = "/delete")
    public String deleteOrganization(@RequestParam Long id, Model model) {

        organizationWebService.removeOrganizationViewModel(id);

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/edit")
    public String editOrganizationDisplay(@RequestParam Long id,Model model) {

        OrganizationEditViewModel viewModel = organizationWebService.retrieveOrganizationEditViewModel(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getOrganizationEditCommandModel());

        return "organization/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditOrganization(@Valid @ModelAttribute("commandModel") OrganizationEditCommandModel commandModel, BindingResult rs, Model model) {

        if(rs.hasErrors()) {
            OrganizationEditViewModel viewModel = organizationWebService.retrieveOrganizationEditViewModel(commandModel.getOrganizationId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "organization/edit";
        }

        Organization org = organizationWebService.saveOrganizationEditCommandModel(commandModel);

        return "redirect:mainpage";
    }
}
