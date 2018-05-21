package com.sg.webservice;

import com.sg.dto.Power;
import com.sg.model.commandmodel.power.editpower.PowerEditCommandModel;
import com.sg.model.commandmodel.power.powerpage.PowerPageCreateCommandModel;
import com.sg.model.viewmodel.power.detailspower.PowerDetailsViewModel;
import com.sg.model.viewmodel.power.editpower.PowerEditViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerPageViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingPageViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class PowerWebServiceImplTest {

    @Inject
    PowerWebService powerWebService;

    @Inject
    TestDataHelperWebService testData;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrievePowerPageViewModel() {

        // Arrange
        List<Power> powers = testData.createTestPowers(12);

        // Act
        PowerPageViewModel viewModel = powerWebService.retrievePowerPageViewModel(Integer.MAX_VALUE, 0, 1);

        // Assert
        assertEquals(powers.size(), viewModel.getPowers().size());
        assertNotNull(viewModel.getPowerPageCreateCommandModel());

        for (PowerViewModel powerViewModel : viewModel.getPowers()) {
            assertNotNull(powerViewModel.getId());
            assertNotNull(powerViewModel.getName());
        }
    }

    @Test
    public void retrievePowerPageViewModelPaging() {

        // Arrange
        List<Power> powers = testData.createTestPowers(12);
        int numberOfPowers = 3;

        // Act
        PowerPageViewModel viewModel = powerWebService.retrievePowerPageViewModel(3, 0, 1);

        // Assert
        assertEquals(numberOfPowers, viewModel.getPowers().size());
        assertNotNull(viewModel.getPowerPageCreateCommandModel());

        for (PowerViewModel powerViewModel : viewModel.getPowers()) {
            assertNotNull(powerViewModel.getId());
            assertNotNull(powerViewModel.getName());
        }
    }

    @Test
    public void retrievePowerEditViewModel() {

        // Arrange
        Power power = testData.createTestPower();

        // Act
        PowerEditViewModel powerEditViewModel = powerWebService.retrievePowerEditViewModel(power.getPowerId());

        // Assert
        assertEquals(power.getPowerId(), powerEditViewModel.getPowerEditCommandModel().getPowerId());
        assertEquals(power.getName(), powerEditViewModel.getPowerEditCommandModel().getName());
        assertEquals(power.getDescription(), powerEditViewModel.getPowerEditCommandModel().getDescription());
    }

    @Test
    public void savePowerEditCommandModel() {

        // Arrange
        Power power = testData.createTestPower();

        PowerEditCommandModel commandModel = new PowerEditCommandModel();
        commandModel.setPowerId(power.getPowerId());
        commandModel.setName("Flight");
        commandModel.setDescription("allows you to fly");

        // Act
        Power updatedPower = powerWebService.savePowerEditCommandModel(commandModel);

        // Assert
        assertEquals(commandModel.getPowerId(), updatedPower.getPowerId());
        assertEquals("Flight", updatedPower.getName());
        assertEquals("allows you to fly", updatedPower.getDescription());
    }

    @Test
    public void savePowerPageCreateCommandModel() {

        // Arrange
        PowerPageCreateCommandModel commandModel = new PowerPageCreateCommandModel();
        commandModel.setName("Mind Reader");
        commandModel.setDescription("allows you to read minds");

        // Act
        Power power = powerWebService.savePowerPageCreateCommandModel(commandModel);

        // Assert
        assertNotNull(power.getPowerId());
        assertEquals("Mind Reader", power.getName());
        assertEquals("allows you to read minds", power.getDescription());
    }

    @Test
    public void retrievePowerDetailsViewModel() {

        // Arrange
        Power power = testData.createTestPower();

        // Act
        PowerDetailsViewModel viewModel = powerWebService.retrievePowerDetailsViewModel(power.getPowerId());

        // Assert
        assertEquals(power.getPowerId(), viewModel.getId());
        assertEquals(power.getName(), viewModel.getName());
        assertEquals(power.getDescription(), viewModel.getDescription());
    }

    @Test
    public void removePowerViewModel() {

        // Arrange
        Power power = testData.createTestPower();

        // Act
        PowerPageViewModel viewModel = powerWebService.retrievePowerPageViewModel(Integer.MAX_VALUE, 0, 1);
        powerWebService.removePowerViewModel(power.getPowerId());

        PowerPageViewModel readViewModel = powerWebService.retrievePowerPageViewModel(Integer.MAX_VALUE, 0, 1);

        // Assert
        assertNotEquals(viewModel.getPowers().size(), readViewModel.getPowers().size());
        assertEquals(0, readViewModel.getPowers().size());

    }
}