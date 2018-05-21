package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.Power;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PowerDaoImpl implements PowerDao {

    JdbcTemplate jt;

    @Inject
    public PowerDaoImpl(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_CREATE = "insert into Power (Name, Description) values (?, ?)";
    private static final String SQL_DELETE = "delete from Power where PowerID = ?";
    private static final String SQL_UPDATE = "update Power set Name = ?, Description = ? where PowerID = ?";
    private static final String SQL_RETRIEVE_ALL = "select * from Power LIMIT ? OFFSET ?";
    private static final String SQL_RETRIEVE_BY_ID = "select * from Power where PowerID = ?";
    private static final String SQL_RETRIEVE_POWER_BY_HERO = "select * from Power p inner join HeroPower hp on p.PowerID = hp.PowerID where hp.HeroID = ? LIMIT ? OFFSET ?";

    @Override
    @Transactional
    public Power addPower(Power power) {
        jt.update(SQL_CREATE,
                power.getName(),
                power.getDescription());

        Long id = jt.queryForObject("select LAST_INSERT_ID()", Long.class);
        power.setPowerId(id);

        return power;
    }

    @Override
    public void removePower(Power power) {

        jt.update(SQL_DELETE, power.getPowerId());

    }

    @Override
    public void updatePower(Power power) {

        jt.update(SQL_UPDATE,
                power.getName(),
                power.getDescription(),
                power.getPowerId());

    }

    @Override
    public List<Power> retrieveAllPowers(int limit, int offset) {
        return jt.query(SQL_RETRIEVE_ALL, new PowerMapper(), limit, offset);
    }

    @Override
    public Power retrievePower(Long powerId) {
        try{
           return jt.queryForObject(SQL_RETRIEVE_BY_ID, new PowerMapper(), powerId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> retrievePowerByHero(Hero hero, int limit, int offset) {
        return jt.query(SQL_RETRIEVE_POWER_BY_HERO, new PowerMapper(), hero.getHeroId(), limit, offset);
    }

    private final static class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {

            Power power = new Power();
            power.setName(rs.getString("Name"));
            power.setDescription(rs.getString("Description"));
            power.setPowerId(rs.getLong("PowerID"));

            return power;
        }
    }
}
