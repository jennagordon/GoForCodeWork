package com.sg.dao;

import com.sg.dto.Location;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocationDaoImpl implements LocationDao {

    JdbcTemplate jt;

    @Inject
    public LocationDaoImpl(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_CREATE = "insert into Location (Name, Description, Street, City, State, Zip, Longitude, Latitude) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "delete from Location where LocationID = ?";
    private static final String SQL_UPDATE = "update Location set Name = ?, Description = ?, Street = ?, City = ?, State = ?, Zip = ?, Longitude = ?, Latitude = ? where LocationID = ?";
    private static final String SQL_RETRIEVE_ALL = "select * from Location LIMIT ? OFFSET ?";
    private static final String SQL_RETRIEVE_BY_ID = "select * from Location where LocationID = ?";

    @Override
    @Transactional
    public Location addLocation(Location location) {
        jt.update(SQL_CREATE,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLongitude(),
                location.getLatitude());

        Long id = jt.queryForObject("Select LAST_INSERT_ID()", Long.class);
        location.setLocationId(id);

        return location;
    }

    @Override
    public void removeLocation(Location location) {

        jt.update(SQL_DELETE, location.getLocationId());

    }

    @Override
    public void updateLocation(Location location) {
        jt.update(SQL_UPDATE,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLongitude(),
                location.getLatitude(),
                location.getLocationId());

    }

    @Override
    public List<Location> retrieveAllLocations(int limit, int offset) {
        return jt.query(SQL_RETRIEVE_ALL, new LocationMapper(), limit, offset);
    }

    @Override
    public Location retrieveLocation(Long locationId) {
        try {
            return jt.queryForObject(SQL_RETRIEVE_BY_ID, new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private final static class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {

            Location location = new Location();

            location.setName(rs.getString("Name"));
            location.setDescription(rs.getString("Description"));
            location.setStreet(rs.getString("Street"));
            location.setCity(rs.getString("City"));
            location.setState(rs.getString("State"));
            location.setZip(rs.getString("Zip"));
            location.setLongitude(rs.getString("Longitude"));
            location.setLatitude(rs.getString("Latitude"));
            location.setLocationId(rs.getLong("LocationID"));

            return location;
        }
    }
}
