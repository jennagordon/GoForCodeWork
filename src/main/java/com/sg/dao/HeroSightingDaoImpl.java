package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.HeroSighting;
import com.sg.dto.Sighting;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HeroSightingDaoImpl implements HeroSightingDao {

    JdbcTemplate jt;

    @Inject
    public HeroSightingDaoImpl(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_CREATE = "insert into HeroSighting (HeroID, SightingID) values (?, ?)";
    private static final String SQL_DELETE = "delete from HeroSighting where HeroSightingID = ?";
    private static final String SQL_UPDATE = "update HeroSighting set HeroID = ?, SightingID = ? where HeroSightingID = ?";
    private static final String SQL_RETRIEVE_BY_ID = "select * from HeroSighting where HeroSightingID = ?";
    private static final String SQL_RETRIEVE_BY_SIGHTING = "select * from HeroSighting where SightingID = ?";
    private static final String SQL_RETRIEVE_BY_HERO = "select * from HeroSighting where HeroID = ?";

    @Override
    @Transactional
    public HeroSighting addHeroSighting(HeroSighting heroSighting) {

        jt.update(SQL_CREATE,
                heroSighting.getHero().getHeroId(),
                heroSighting.getSighting().getSightingId());

        Long id = jt.queryForObject("select LAST_INSERT_ID()", Long.class);
        heroSighting.setHeroSightingId(id);

        return heroSighting;
    }

    @Override
    public void removeHeroSighting(HeroSighting heroSighting) {

        jt.update(SQL_DELETE, heroSighting.getHeroSightingId());

    }

    @Override
    public void updateHeroSighting(HeroSighting heroSighting) {

        jt.update(SQL_UPDATE,
                heroSighting.getHero().getHeroId(),
                heroSighting.getSighting().getSightingId(),
                heroSighting.getHeroSightingId());

    }

    @Override
    public HeroSighting retrieveHeroSightingById(Long heroSightingId) {
        try{
            return jt.queryForObject(SQL_RETRIEVE_BY_ID, new HeroSightingMapper(), heroSightingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<HeroSighting> retrieveHeroSightingBySighting(Long sightingId) {
        return jt.query(SQL_RETRIEVE_BY_SIGHTING, new HeroSightingMapper(), sightingId);
    }

    @Override
    public List<HeroSighting> retrieveHeroSightingByHero(Long heroId) {
        return jt.query(SQL_RETRIEVE_BY_HERO, new HeroSightingMapper(), heroId);
    }

    private final static class HeroSightingMapper implements RowMapper<HeroSighting> {

        @Override
        public HeroSighting mapRow(ResultSet rs, int i) throws SQLException {

            HeroSighting heroSighting = new HeroSighting();
            heroSighting.setHeroSightingId(rs.getLong("HeroSightingID"));

            // getting hero id
            Long heroId = rs.getLong("HeroID");

            if(heroId != null) {
                Hero hero = new Hero();
                hero.setHeroId(heroId);
                heroSighting.setHero(hero);
            }

            // getting sighting id
            Long sightingId = rs.getLong("SightingID");

            if(sightingId != null) {
                Sighting sighting = new Sighting();
                sighting.setSightingId(sightingId);
                heroSighting.setSighting(sighting);

            }

            return heroSighting;
        }
    }
}
