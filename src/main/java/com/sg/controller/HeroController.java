package com.sg.controller;

import com.sg.dto.Hero;
import com.sg.model.commandmodel.hero.edithero.HeroEditCommandModel;
import com.sg.model.commandmodel.hero.heropage.HeroPageCreateCommandModel;
import com.sg.model.viewmodel.hero.detailshero.HeroDetailsViewModel;
import com.sg.model.viewmodel.hero.edithero.HeroEditViewModel;
import com.sg.model.viewmodel.hero.heropage.HeroPageViewModel;
import com.sg.webservice.HeroWebService;
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
@RequestMapping(value = "/hero")
public class HeroController {

    @Inject
    HeroWebService heroWebService;

    @RequestMapping(value = "/mainpage")
    public String displayHeroMainPage(@RequestParam(required = false)Integer offset, Model model) {


        HeroPageViewModel viewModel = heroWebService.retrieveHeroPageViewModel(5, offset, 5);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getHeroPageCreateCommandModel());

        return "hero/mainpage";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createHero(@RequestParam(required = false)Integer offset, @Valid @ModelAttribute("commandModel")HeroPageCreateCommandModel commandModel, BindingResult rs, Model model) {

        if (rs.hasErrors()) {
            HeroPageViewModel viewModel = heroWebService.retrieveHeroPageViewModel(5, offset, 5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "hero/mainpage";
        }
        Hero hero = heroWebService.saveHeroPageCreateCommandModel(commandModel);

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/details")
    public String displayHeroDetails(@RequestParam Long id, Model model) {

        HeroDetailsViewModel viewModel = heroWebService.retrieveHeroDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "hero/details";
    }

    @RequestMapping(value = "/delete")
    public String deleteHero(@RequestParam Long id) {

        heroWebService.removeHeroViewModel(id);

        return "redirect:mainpage";
    }

    @RequestMapping(value = "/edit")
    public String editHeroDisplay(@RequestParam Long id, Model model) {

        HeroEditViewModel viewModel = heroWebService.retrieveHeroEditViewModel(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getHeroEditCommandModel());

        return "hero/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditHero(@Valid @ModelAttribute("commandModel") HeroEditCommandModel commandModel, BindingResult rs, Model model) {

        if (rs.hasErrors()) {
            HeroEditViewModel viewModel = heroWebService.retrieveHeroEditViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);

            return "hero/edit";
        }
        Hero hero = heroWebService.saveHeroEditCommandModel(commandModel);

        return "redirect:mainpage";
    }


}
