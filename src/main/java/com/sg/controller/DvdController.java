package com.sg.controller;

import com.sg.dao.DvdDao;
import com.sg.model.DVD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DvdController {

    DvdDao dao;

    @Inject
    public DvdController(DvdDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayDvds(Model model) {

        List<DVD> dvdList = dao.retrieveAllDvds();

        model.addAttribute("dvdList", dvdList);

        return "index";
    }

    @RequestMapping(value = "/displayAddForm", method = RequestMethod.GET)
    public String displayAddForm(@ModelAttribute("dvd") DVD dvd, BindingResult result) {

        System.out.println("displayAddForm");
        return "addDvdForm";
    }

    @RequestMapping(value = "addDvd", method = RequestMethod.POST)
    public String addDvd(@Valid @ModelAttribute("dvd")DVD dvd, BindingResult result, HttpServletRequest request) {
        System.out.println("addForm");
        if(result.hasErrors()){
            return "addDvdForm";
        }

        // don't need this code because we are using model attribute and passing in dvd
//        DVD createDvd = new DVD();
//        createDvd.setTitle(request.getParameter("title"));
//        createDvd.setDirector(request.getParameter("director"));
//
//        String releaseYearString = request.getParameter("releaseYear");
//        LocalDate releaseYear = LocalDate.parse(releaseYearString);
//        createDvd.setReleaseYear(releaseYear);
//
//        createDvd.setNotes(request.getParameter("notes"));
//        createDvd.setRating(request.getParameter("rating"));

        dao.addDvd(dvd);

        return "redirect:/";
    }

    // display dvd for confirmation before delete
    @RequestMapping(value = "displayDvd", method = RequestMethod.GET)
    public String displayDvd(HttpServletRequest request, Model model) {

        String dvdIdString = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(dvdIdString);


        model.addAttribute("dvd", dao.retrieveDvdById(dvdId));

        return "displayDvd";
    }

    // delete
    @RequestMapping(value = "deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request) {

        String dvdIdParameter = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(dvdIdParameter);

        dao.removeDvd(dvdId);

        return "redirect:/";
    }

    @RequestMapping(value = "displayEditForm", method = RequestMethod.GET)
    public String displayEditForm(HttpServletRequest request, Model model) {

        String dvdIdString = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(dvdIdString);


        model.addAttribute("dvd", dao.retrieveDvdById(dvdId));

        return "editDvdForm";
    }

    @RequestMapping(value = "editDvd", method = RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd") DVD dvd, BindingResult result) {

        if(result.hasErrors()) {
            return "editDvdForm";
        }
        dao.updateDvd(dvd);

        return "redirect:/";
    }

    @RequestMapping(value = "searchDvd", method = RequestMethod.GET)
    public String searchDvd(HttpServletRequest request, Model model, RedirectAttributes redirectAttrs) {
        System.out.println("search dvd");


        String category = request.getParameter("category");
        String searchTerm = request.getParameter("searchTerm");

        if(searchTerm.equals("")) {
            redirectAttrs.addFlashAttribute("message", "search.item.needed");
            return "redirect:/";
        }

        List<DVD> dvdSearchList = dao.searchContacts(searchTerm, category);

        model.addAttribute("dvdSearchList", dvdSearchList);

        return "dvdSearch";
    }


}
