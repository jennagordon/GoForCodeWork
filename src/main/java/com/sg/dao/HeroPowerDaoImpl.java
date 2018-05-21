package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.HeroPower;
import com.sg.dto.Power;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HeroPowerDaoImpl implements HeroPowerDao {

    JdbcTemplate jt;

    @Inject
    public HeroPowerDaoImpl(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_CREATE = "insert into HeroPower (HeroID, PowerID) values (?, ?)";
    private static final String SQL_DELETE = "delete from HeroPower where HeroPowerID = ?";
    private static final String SQL_UPDATE = "update HeroPower set HeroID = ?, PowerID = ? where HeroPowerID = ?";
    private static final String SQL_RETRIEVE_BY_ID = "select * from HeroPower where HeroPowerID = ?";
    private static final String SQL_RETRIEVE_BY_POWER = "select * from HeroPower where PowerID = ?";
    private static final String SQL_RETRIEVE_BY_HERO = "select * from HeroPower where HeroID = ?";

    @Override
    @Transactional
    public HeroPower addHeroPower(HeroPower heroPower) {

        jt.update(SQL_CREATE,
                heroPower.getHero().getHeroId(),
                heroPower.getPower().getPowerId());

        Long id = jt.queryForObject("select LAST_INSERT_ID()", Long.class);
        heroPower.setHeroPowerId(id);

        return heroPower;
    }

    @Override
    public void removeHeroPower(HeroPower heroPower) {

        jt.update(SQL_DELETE, heroPower.getHeroPowerId());

    }

    @Override
    public void updateHeroPower(HeroPower heroPower) {

        jt.update(SQL_UPDATE,
                heroPower.getHero().getHeroId(),
                heroPower.getPower().getPowerId(),
                heroPower.getHeroPowerId());

    }

    @Override
    public HeroPower retrieveHeroPowerById(Long heroPowerId) {
        try{
           return jt.queryForObject(SQL_RETRIEVE_BY_ID, new HeroPowerMapper(), heroPowerId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<HeroPower> retrieveHeroPowerByPower(Long powerId) {
        return jt.query(SQL_RETRIEVE_BY_POWER, new HeroPowerMapper(), powerId);
    }

    @Override
    public List<HeroPower> retrieveHeroPowerByHero(Long heroId) {
        return jt.query(SQL_RETRIEVE_BY_HERO, new HeroPowerMapper(), heroId);
    }

    private final static class HeroPowerMapper implements RowMapper<HeroPower> {

        @Override
        public HeroPower mapRow(ResultSet rs, int i) throws SQLException {

            HeroPower heroPower = new HeroPower();
            heroPower.setHeroPowerId(rs.getLong("HeroPowerID"));

            // getting hero id
            Long heroId = rs.getLong("HeroID");

            if(heroId != null) {
                Hero hero = new Hero();
                hero.setHeroId(heroId);
                heroPower.setHero(hero);
            }

            // getting power id
            Long powerId = rs.getLong("PowerID");

            if(powerId != null) {
                Power power = new Power();
                power.setPowerId(powerId);
                heroPower.setPower(power);
            }
            return heroPower;
        }
    }
}
