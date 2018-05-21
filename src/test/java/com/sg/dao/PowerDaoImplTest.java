package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.HeroPower;
import com.sg.dto.Power;
import com.sg.service.HeroPowerService;
import com.sg.service.HeroService;
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
public class PowerDaoImplTest {

    @Inject
    PowerDao powerDao;

    @Inject
    HeroService heroService;

    @Inject
    HeroPowerService heroPowerService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addPower() {

        // Arrange
        // Act
        Power createdPower = createTestPower();

        // Assert
        assertEquals("Mind Reader", createdPower.getName());
        assertEquals("Reads minds", createdPower.getDescription());
        assertNotNull(createdPower.getPowerId());
    }



    @Test
    public void removePower() {

        // Arrange
        Power createdPower = createTestPower();

        // Act
        powerDao.removePower(createdPower);
        Power readPower = powerDao.retrievePower(createdPower.getPowerId());

        // Assert
        assertNull(readPower);
    }

    @Test
    public void updatePower() {

        // Arrange
        Power createdPower = createTestPower();

        Power readPower = powerDao.retrievePower(createdPower.getPowerId());

        readPower.setName("Invisibility");
        readPower.setDescription("no one can see you");

        // Act
        powerDao.updatePower(readPower);

        // Assert
        assertEquals("Invisibility", readPower.getName());
        assertEquals("no one can see you", readPower.getDescription());
        assertEquals(createdPower.getPowerId(), readPower.getPowerId());
    }

    @Test
    public void retrieveAllPowers() {

        // Arrange
        createTestPower();

        Power createdPower = new Power();
        createdPower.setName("Invisibility");
        createdPower.setDescription("no one can see you");

        powerDao.addPower(createdPower);

        // Act
        List<Power> powerList = powerDao.retrieveAllPowers(Integer.MAX_VALUE, 0);

        // Assert
        assertEquals(2, powerList.size());

    }

    @Test
    public void retrievePower() {

        // Arrange
        Power createdPower = createTestPower();

        // Act
        Power readPower = powerDao.retrievePower(createdPower.getPowerId());

        // Assert
        assertEquals("Mind Reader", createdPower.getName());
        assertEquals("Reads minds", createdPower.getDescription());
        assertEquals(createdPower.getPowerId(), readPower.getPowerId());

    }

    @Test
    public void retrievePowerByHeroPage() {

        // test that we can get all the powers one hero has

        // Arrange
        // creating 3 but paging by 2
        int numberOfPowers = 2;
        Hero hero = createTestHero();

        Power power1 = new Power();
        power1.setName("Invisibility");
        power1.setDescription("can't see you");
        powerDao.addPower(power1);

        HeroPower heroPower1 = new HeroPower();
        heroPower1.setPower(power1);
        heroPower1.setHero(hero);
        heroPowerService.addHeroPower(heroPower1);


        Power power2 = new Power();
        power2.setName("Mind Reader");
        power2.setDescription("can read minds");
        powerDao.addPower(power2);

        HeroPower heroPower2 = new HeroPower();
        heroPower2.setPower(power2);
        heroPower2.setHero(hero);
        heroPowerService.addHeroPower(heroPower2);

        Power power3 = new Power();
        power3.setName("Flying");
        power3.setDescription("can fly");
        powerDao.addPower(power3);

        HeroPower heroPower3 = new HeroPower();
        heroPower3.setPower(power3);
        heroPower3.setHero(hero);
        heroPowerService.addHeroPower(heroPower3);


        // Act
        List<Power> powerList = powerDao.retrievePowerByHero(hero, 2, 0);

        // Assert
        assertPowerOnHeroes(hero, numberOfPowers, powerList);


    }

    @Test
    public void retrievePowerByHero() {

        // test that we can get all the powers one hero has

        // Arrange
        int numberOfPowers = 3;
        Hero hero = createTestHero();

        Power power1 = new Power();
        power1.setName("Invisibility");
        power1.setDescription("can't see you");
        powerDao.addPower(power1);

        HeroPower heroPower1 = new HeroPower();
        heroPower1.setPower(power1);
        heroPower1.setHero(hero);
        heroPowerService.addHeroPower(heroPower1);


        Power power2 = new Power();
        power2.setName("Mind Reader");
        power2.setDescription("can read minds");
        powerDao.addPower(power2);

        HeroPower heroPower2 = new HeroPower();
        heroPower2.setPower(power2);
        heroPower2.setHero(hero);
        heroPowerService.addHeroPower(heroPower2);

        Power power3 = new Power();
        power3.setName("Flying");
        power3.setDescription("can fly");
        powerDao.addPower(power3);

        HeroPower heroPower3 = new HeroPower();
        heroPower3.setPower(power3);
        heroPower3.setHero(hero);
        heroPowerService.addHeroPower(heroPower3);


        // Act
        List<Power> powerList = powerDao.retrievePowerByHero(hero, Integer.MAX_VALUE, 0);

        // Assert
        assertPowerOnHeroes(hero, numberOfPowers, powerList);


    }


    // HELPER METHODS TO CREATE TEST DATA
    private Power createTestPower() {
        Power power = new Power();
        power.setName("Mind Reader");
        power.setDescription("Reads minds");

        Power createdPower = powerDao.addPower(power);

        return createdPower;
    }

    private Hero createTestHero() {

        Hero hero = new Hero();
        hero.setName("Batman");
        hero.setDescription("Black suit with wings");

        Hero createdHero = heroService.addHero(hero);

        return createdHero;
    }

    private void assertPowerOnHeroes(Hero hero, int numberOfPowers, List<Power> resultPower) {
        assertEquals(numberOfPowers, resultPower.size());

        for (Power power : resultPower) {
            List<Hero> heroList = heroService.retrieveHeroByPower(power, Integer.MAX_VALUE, 0);

            // Loop through hero's power and verify it contains the one we added
            boolean containsHero = false;
            for (Hero her: heroList) {
                if (her.getHeroId().equals(hero.getHeroId())) {
                    containsHero = true;

                }

                assertEquals(true, containsHero);
            }

        }
    }
}