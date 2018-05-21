package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.Organization;
import com.sg.dto.Power;
import com.sg.dto.Sighting;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HeroDaoImpl implements HeroDao {

    JdbcTemplate jt;

    @Inject
    public HeroDaoImpl(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_CREATE = "insert into Hero (Name, Description) values (?, ?)";
    private static final String SQL_DELETE = "delete from Hero where HeroID = ?";
    private static final String SQL_UPDATE = "update Hero set Name = ?, Description = ? where HeroID = ?";
    private static final String SQL_RETRIEVE_ALL = "select * from Hero LIMIT ? OFFSET ?";
    private static final String SQL_RETRIEVE_BY_ID = "select * from Hero where HeroID = ?";
    private static final String SQL_RETRIEVE_HERO_BY_POWER = "select * from Hero h inner join HeroPower hp on h.HeroID = hp.HeroID where hp.PowerID = ? LIMIT ? OFFSET ?";
    private static final String SQL_RETRIEVE_HERO_BY_ORGANIZATION = "select * from Hero h inner join HeroOrganization ho on h.HeroID = ho.HeroID where ho.OrganizationID = ? LIMIT ? OFFSET ?";
    private static final String SQL_RETRIEVE_HERO_BY_SIGHTING = "select * from Hero h inner join HeroSighting hs on h.HeroID = hs.HeroID where hs.SightingID = ? LIMIT ? OFFSET ?";


    @Override
    @Transactional
    public Hero addHero(Hero hero) {

        jt.update(SQL_CREATE,
                hero.getName(),
                hero.getDescription());

        Long id = jt.queryForObject("select LAST_INSERT_ID()", Long.class);
        hero.setHeroId(id);

        return hero;
    }

    @Override
    public void removeHero(Hero hero) {

        jt.update(SQL_DELETE, hero.getHeroId());

    }

    @Override
    public void updateHero(Hero hero) {

        jt.update(SQL_UPDATE,
                hero.getName(),
                hero.getDescription(),
                hero.getHeroId());
    }

    @Override
    public List<Hero> retrieveAllHero(int limit, int offset) {
        return jt.query(SQL_RETRIEVE_ALL, new HeroMapper(), limit, offset);
    }

    @Override
    public Hero retrieveHero(Long heroId) {
        try{
            return jt.queryForObject(SQL_RETRIEVE_BY_ID, new HeroMapper(), heroId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> retrieveHeroByPower(Power power, int limit, int offset) {
        return jt.query(SQL_RETRIEVE_HERO_BY_POWER, new HeroMapper(), power.getPowerId(), limit, offset);
    }

    @Override
    public List<Hero> retrieveHeroBySighting(Sighting sighting, int limit, int offset) {
        return jt.query(SQL_RETRIEVE_HERO_BY_SIGHTING, new HeroMapper(), sighting.getSightingId(), limit, offset);
    }

    @Override
    public List<Hero> retrieveHeroByOrganization(Organization organization, int limit, int offset) {
        return jt.query(SQL_RETRIEVE_HERO_BY_ORGANIZATION, new HeroMapper(), organization.getOrganizationId(), limit, offset);
    }

    private final static class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {

            Hero hero = new Hero();
            hero.setName(rs.getString("Name"));
            hero.setDescription(rs.getString("Description"));
            hero.setHeroId(rs.getLong("HeroID"));

            return hero;
        }
    }
}
