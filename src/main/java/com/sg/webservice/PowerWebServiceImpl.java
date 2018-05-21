package com.sg.webservice;

import com.sg.dto.HeroPower;
import com.sg.dto.Power;
import com.sg.model.commandmodel.power.editpower.PowerEditCommandModel;
import com.sg.model.commandmodel.power.powerpage.PowerPageCreateCommandModel;
import com.sg.model.viewmodel.power.detailspower.PowerDetailsViewModel;
import com.sg.model.viewmodel.power.editpower.PowerEditViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerPageViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerViewModel;
import com.sg.service.HeroPowerService;
import com.sg.service.PowerService;
import com.sg.webservice.util.PagingUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PowerWebServiceImpl implements PowerWebService {

    PowerService powerService;
    HeroPowerService heroPowerService;

    @Inject
    public PowerWebServiceImpl(PowerService powerService, HeroPowerService heroPowerService) {
        this.powerService = powerService;
        this.heroPowerService = heroPowerService;
    }

    @Override
    public PowerPageViewModel retrievePowerPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {

        if(limit == null) {
            limit = 5;
        }

        if(offset == null) {
            offset = 0;
        }

        if(pageNumbers == null) {
            pageNumbers = 5;
        }

        // INSTANTIATE
        PowerPageViewModel powerPageViewModel = new PowerPageViewModel();

        // LOOK STUFF UP
        List<Power> powers = powerService.retrieveAllPowers(limit, offset);
        PowerPageCreateCommandModel commandModel = new PowerPageCreateCommandModel();

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        // PUT STUFF
        powerPageViewModel.setPageNumber(currentPage);
        powerPageViewModel.setPageNumbers(pages);
        powerPageViewModel.setPowerPageCreateCommandModel(commandModel);
        powerPageViewModel.setPowers(translatePowerList(powers));


        return powerPageViewModel;
    }

    // ---- TRANSLATE METHODS FOR POWERPAGEVIEWMODEL ----
    private List<PowerViewModel> translatePowerList(List<Power> powers) {
        List<PowerViewModel> powerViewModels = new ArrayList<>();

        for (Power power : powers) {
            powerViewModels.add(translatePower(power));
        }
        return powerViewModels;
    }

    private PowerViewModel translatePower(Power power) {
        PowerViewModel powerViewModel = new PowerViewModel();

        powerViewModel.setId(power.getPowerId());
        powerViewModel.setName(power.getName());

        return powerViewModel;
    }

    @Override
    public PowerEditViewModel retrievePowerEditViewModel(Long id) {
        PowerEditViewModel viewModel = new PowerEditViewModel();
        Power power = powerService.retrievePower(id);

        PowerEditCommandModel commandModel = new PowerEditCommandModel();
        commandModel.setPowerId(power.getPowerId());
        commandModel.setName(power.getName());
        commandModel.setDescription(power.getDescription());

        viewModel.setPowerEditCommandModel(commandModel);

        return viewModel;
    }

    @Override
    public Power savePowerEditCommandModel(PowerEditCommandModel powerEditCommandModel) {
        Power power = powerService.retrievePower(powerEditCommandModel.getPowerId());

        power.setName(powerEditCommandModel.getName());
        power.setDescription(powerEditCommandModel.getDescription());

        powerService.updatePower(power);

        return power;
    }

    @Override
    public Power savePowerPageCreateCommandModel(PowerPageCreateCommandModel powerPageCreateCommandModel) {
        Power power = new Power();
        power.setName(powerPageCreateCommandModel.getName());
        power.setDescription(powerPageCreateCommandModel.getDescription());

        return powerService.addPower(power);
    }

    @Override
    public PowerDetailsViewModel retrievePowerDetailsViewModel(Long id) {
        PowerDetailsViewModel viewModel = new PowerDetailsViewModel();

        Power power = powerService.retrievePower(id);

        viewModel.setId(power.getPowerId());
        viewModel.setName(power.getName());
        viewModel.setDescription(power.getDescription());

        return viewModel;
    }

    @Override
    public void removePowerViewModel(Long id) {

        Power power = powerService.retrievePower(id);

        // if power has heroes related to it we must delete it from the HeroPower
        List<HeroPower> heroPowerList = heroPowerService.retrieveHeroPowerByPower(power.getPowerId());

            for (HeroPower heroPower : heroPowerList) {
                heroPowerService.removeHeroPower(heroPower);
            }


        powerService.removePower(power);

    }
}
