package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.Organization;
import com.sg.dto.Power;
import com.sg.dto.Sighting;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface HeroDao {

    Hero addHero(Hero hero);

    void removeHero(Hero hero);

    void updateHero(Hero hero);

    List<Hero> retrieveAllHero(int limit, int offset);

    Hero retrieveHero(Long heroId);

    List<Hero> retrieveHeroByPower(Power power, int limit, int offset);

    List<Hero> retrieveHeroBySighting(Sighting sighting, int limit, int offset);

    List<Hero> retrieveHeroByOrganization(Organization organization, int limit, int offset);
}
