package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SightingDaoImpl implements SightingDao {

    JdbcTemplate jt;

    @Inject
    public SightingDaoImpl(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_CREATE = "insert into Sighting (Date, Description, LocationID) values (?, ?, ?)";
    private static final String SQL_DELETE = "delete from Sighting where SightingID = ?";
    private static final String SQL_UPDATE = "update Sighting set Date = ?, Description = ?, LocationID = ? where SightingID = ?";
    private static final String SQL_RETRIEVE_ALL = "select * from Sighting Order By Date DESC LIMIT ? OFFSET ?";
    private static final String SQL_RETRIEVE_BY_ID = "select * from Sighting where SightingID = ?";
    private static final String SQL_RETRIEVE_SIGHTING_BY_HERO = "select * from Sighting s inner join HeroSighting hs on s.SightingID = hs.SightingID where hs.HeroID = ? LIMIT ? OFFSET ?";
    private static final String SQL_RETRIEVE_SIGHTING_BY_LOCATION = "select * from Sighting where LocationID = ? LIMIT ? OFFSET ?";
    private static final String SQL_RETRIEVE_SIGHTING_BY_DATE = "select * from Sighting where Date = ? LIMIT ? OFFSET ?";


    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {

        jt.update(SQL_CREATE,
                Date.valueOf(sighting.getDate()),
                sighting.getDescription(),
                sighting.getLocation().getLocationId());

        Long id = jt.queryForObject("select LAST_INSERT_ID()", Long.class);
        sighting.setSightingId(id);

        return sighting;
    }

    @Override
    public void removeSighting(Sighting sighting) {

        jt.update(SQL_DELETE, sighting.getSightingId());

    }

    @Override
    public void updateSighting(Sighting sighting) {

        jt.update(SQL_UPDATE,
                Date.valueOf(sighting.getDate()),
                sighting.getDescription(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());

    }

    @Override
    public List<Sighting> retrieveAllSightings(int limit, int offset) {

        return jt.query(SQL_RETRIEVE_ALL, new SightingMapper(), limit, offset);
    }

    @Override
    public Sighting retrieveSighting(Long sightingId) {
        try{
          return jt.queryForObject(SQL_RETRIEVE_BY_ID, new SightingMapper(), sightingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> retrieveSightingByHero(Hero hero, int limit, int offset) {
        return jt.query(SQL_RETRIEVE_SIGHTING_BY_HERO, new SightingMapper(), hero.getHeroId(), limit, offset);
    }

    @Override
    public List<Sighting> retrieveSightingByLocation(Location location, int limit, int offset) {
        return jt.query(SQL_RETRIEVE_SIGHTING_BY_LOCATION,new SightingMapper(), location.getLocationId(),  limit, offset);
    }

    @Override
    public List<Sighting> retrieveSightingByDate(LocalDate localDate, int limit, int offset) {
        return jt.query(SQL_RETRIEVE_SIGHTING_BY_DATE, new SightingMapper(), Date.valueOf(localDate),  limit, offset);
    }

    private final static class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {

            Sighting sighting = new Sighting();
            sighting.setDate(rs.getDate("Date").toLocalDate());
            sighting.setDescription(rs.getString("Description"));
            sighting.setSightingId(rs.getLong("SightingID"));


            Long locationId = rs.getLong("LocationID");

            if(locationId != null) {
                Location location = new Location();
                location.setLocationId(locationId);
                sighting.setLocation(location);
            }

            return sighting;
        }
    }
}
