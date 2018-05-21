package com.sg.controller;

import com.sg.dto.Power;
import com.sg.model.commandmodel.power.editpower.PowerEditCommandModel;
import com.sg.model.commandmodel.power.powerpage.PowerPageCreateCommandModel;
import com.sg.model.viewmodel.power.detailspower.PowerDetailsViewModel;
import com.sg.model.viewmodel.power.editpower.PowerEditViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerPageViewModel;
import com.sg.service.PowerService;
import com.sg.webservice.PowerWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/power")
public class PowerController {

    @Inject
    PowerWebService powerWebService;


    @RequestMapping(value = "/mainpage")
    public String displayPowerMainPage(@RequestParam(required = false)Integer offset, Model model) {
        System.out.println("displayPowerMainPage");

       PowerPageViewModel viewModel = powerWebService.retrievePowerPageViewModel(5, offset, 5);

       model.addAttribute("viewModel", viewModel);
       model.addAttribute("commandModel", viewModel.getPowerPageCreateCommandModel());

        return "power/mainpage";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPower(@RequestParam(required = false)Integer offset, @Valid @ModelAttribute("commandModel") PowerPageCreateCommandModel commandModel, BindingResult rs, Model model) {

        if (rs.hasErrors()) {
            PowerPageViewModel viewModel = powerWebService.retrievePowerPageViewModel(5, offset, 5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "power/mainpage";
        }

        Power power = powerWebService.savePowerPageCreateCommandModel(commandModel);

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/details")
    public String displayPowerDetails(@RequestParam Long id, Model model) {

        PowerDetailsViewModel viewModel = powerWebService.retrievePowerDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "power/details";
    }

    @RequestMapping(value = "/delete")
    public String deletePower(@RequestParam Long id, Model model) {

        powerWebService.removePowerViewModel(id);

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/edit")
    public String editPowerDisplay(@RequestParam Long id,Model model) {

        PowerEditViewModel viewModel = powerWebService.retrievePowerEditViewModel(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getPowerEditCommandModel());

        return "power/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditPower(@Valid @ModelAttribute("commandModel") PowerEditCommandModel commandModel, BindingResult rs, Model model) {

        if (rs.hasErrors()) {
            PowerEditViewModel viewModel = powerWebService.retrievePowerEditViewModel(commandModel.getPowerId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "power/edit";
        }

        Power power = powerWebService.savePowerEditCommandModel(commandModel);

        return "redirect:mainpage";
    }


}
