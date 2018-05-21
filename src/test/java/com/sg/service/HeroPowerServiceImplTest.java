package com.sg.service;

import com.sg.dao.HeroPowerDao;
import com.sg.dto.Hero;
import com.sg.dto.HeroPower;
import com.sg.dto.Power;
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
public class HeroPowerServiceImplTest {

    @Inject
    HeroPowerService heroPowerService;

    @Inject
    HeroService heroService;

    @Inject
    PowerService powerService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addHeroPower() {

        // Arrange
        // Act
        Power power = createTestPower();
        Hero hero = createTestHero();
        HeroPower createdHeroPower = createTestHeroPower(hero, power);

        // Assert
        assertEquals(power.getPowerId(), createdHeroPower.getPower().getPowerId());
        assertEquals(hero.getHeroId(), createdHeroPower.getHero().getHeroId());
        assertNotNull(createdHeroPower.getHeroPowerId());
    }

    @Test
    public void removeHeroPower() {

        // Arrange
        Power power = createTestPower();
        Hero hero = createTestHero();
        HeroPower createdHeroPower = createTestHeroPower(hero, power);

        // Act
        heroPowerService.removeHeroPower(createdHeroPower);
        HeroPower readHeroPower = heroPowerService.retrieveHeroPowerById(createdHeroPower.getHeroPowerId());

        // Assert
        assertNull(readHeroPower);
    }

    @Test
    public void updateHeroPower() {

        // Arrange
        Power power = createTestPower();
        Hero hero = createTestHero();
        HeroPower createdHeroPower = createTestHeroPower(hero, power);

        HeroPower readHeroPower = heroPowerService.retrieveHeroPowerById(createdHeroPower.getHeroPowerId());

        // creating updates
        Power power2 = new Power();
        power2.setName("Mind Reader");
        power2.setDescription("Reads minds");

        powerService.addPower(power2);

        Hero hero2 = new Hero();
        hero2.setName("Superman");
        hero2.setDescription("Red and Blue suit with cape");
        heroService.addHero(hero2);

        readHeroPower.setPower(power2);
        readHeroPower.setHero(hero2);

        // Act
        heroPowerService.updateHeroPower(readHeroPower);

        // Assert
        assertEquals(power2.getPowerId(), readHeroPower.getPower().getPowerId());
        assertEquals(hero2.getHeroId(), readHeroPower.getHero().getHeroId());
        assertEquals(createdHeroPower.getHeroPowerId(), readHeroPower.getHeroPowerId());

    }

    @Test
    public void retrieveHeroPowerById() {

        // Arrange
        Power power = createTestPower();
        Hero hero = createTestHero();
        HeroPower createdHeroPower = createTestHeroPower(hero, power);

        // Act
        HeroPower readHeroPower = heroPowerService.retrieveHeroPowerById(createdHeroPower.getHeroPowerId());

        // Assert
        assertEquals(power.getPowerId(), readHeroPower.getPower().getPowerId());
        assertEquals(hero.getHeroId(), readHeroPower.getHero().getHeroId());
        assertEquals(createdHeroPower.getHeroPowerId(), readHeroPower.getHeroPowerId());
    }


    @Test
    public void retrieveHeroPowerByPower() {
        Power power = createTestPower();
        Hero hero = createTestHero();
        HeroPower heroPower = createTestHeroPower(hero, power);

        // Act
        List<HeroPower> readHP = heroPowerService.retrieveHeroPowerByPower(power.getPowerId());

        // Assert
        assertEquals(1, readHP.size());
        assertEquals(power.getPowerId(), readHP.get(0).getPower().getPowerId());
        assertEquals(hero.getHeroId(), readHP.get(0).getHero().getHeroId());

    }

    @Test
    public void retrieveHeroPowerByHero() {

        Power power = createTestPower();
        Hero hero = createTestHero();
        HeroPower heroPower = createTestHeroPower(hero, power);

        // Act
        List<HeroPower> readHP = heroPowerService.retrieveHeroPowerByHero(hero.getHeroId());

        // Assert
        assertEquals(1, readHP.size());
        assertEquals(power.getPowerId(), readHP.get(0).getPower().getPowerId());
        assertEquals(hero.getHeroId(), readHP.get(0).getHero().getHeroId());

    }


    // HELPER METHODS TO CREATE TEST DATA

    private Hero createTestHero() {

        Hero hero = new Hero();
        hero.setName("Batman");
        hero.setDescription("Black suit with wings");

        Hero createdHero = heroService.addHero(hero);

        return createdHero;
    }

    private Power createTestPower() {
        Power power = new Power();
        power.setName("Mind Reader");
        power.setDescription("Reads minds");

        Power createdPower = powerService.addPower(power);

        return createdPower;
    }

    private HeroPower createTestHeroPower(Hero hero, Power power) {

        HeroPower heroPower = new HeroPower();
        heroPower.setHero(hero);
        heroPower.setPower(power);

        HeroPower createdHeroPower = heroPowerService.addHeroPower(heroPower);

        return createdHeroPower;
    }
}