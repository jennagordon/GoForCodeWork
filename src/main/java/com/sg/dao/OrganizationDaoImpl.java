package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrganizationDaoImpl implements OrganizationDao {

    JdbcTemplate jt;

    @Inject
    public OrganizationDaoImpl(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_CREATE = "insert into Organization (Name, Description, LocationID) values (?, ?, ?)";
    private static final String SQL_DELETE = "delete from Organization where OrganizationID = ?";
    private static final String SQL_UPDATE = "update Organization set Name = ?, Description = ?, LocationID = ? where OrganizationID = ?";
    private static final String SQL_RETRIEVE_ALL = "select * from Organization LIMIT ? OFFSET ?";
    private static final String SQL_RETRIEVE_BY_ID = "select * from Organization where OrganizationID = ?";
    private static final String SQL_RETRIEVE_ORGANIZATION_BY_HERO = "select * from Organization o inner join HeroOrganization ho on o.OrganizationID = ho.OrganizationID where ho.HeroID = ? LIMIT ? OFFSET ?";

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {

        Long locationId = null;

        // doing this because an organization may not have a location
        if(organization.getLocation() != null) {
            locationId = organization.getLocation().getLocationId();
        }

        jt.update(SQL_CREATE,
                organization.getName(),
                organization.getDescription(),
                locationId);

        Long id = jt.queryForObject("select LAST_INSERT_ID()", Long.class);
        organization.setOrganizationId(id);

        return organization;
    }

    @Override
    public void removeOrganization(Organization organization) {

        jt.update(SQL_DELETE, organization.getOrganizationId());

    }

    @Override
    public void updateOrganization(Organization organization) {

        Long organizationId = null;

        // doing this because an organization may not have a location
        if(organization.getLocation() != null) {
            organizationId = organization.getLocation().getLocationId();
        }

        jt.update(SQL_UPDATE,
                organization.getName(),
                organization.getDescription(),
                organizationId,
                organization.getOrganizationId());

    }

    @Override
    public List<Organization> retrieveAllOrganizations(int limit, int offset) {
        return jt.query(SQL_RETRIEVE_ALL, new OrganizationMapper(), limit, offset);
    }

    @Override
    public Organization retrieveOrganization(Long organizationId) {
        try{
            return jt.queryForObject(SQL_RETRIEVE_BY_ID, new OrganizationMapper(), organizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> retrieveOrganizationByHero(Hero hero, int limit, int offset) {
        return jt.query(SQL_RETRIEVE_ORGANIZATION_BY_HERO,
                new OrganizationMapper(),
                hero.getHeroId(),
                limit,
                offset);
    }

    private final static class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {

            Organization organization = new Organization();
            organization.setName(rs.getString("Name"));
            organization.setDescription(rs.getString("Description"));
            organization.setOrganizationId(rs.getLong("OrganizationID"));

            Long locationId = rs.getLong("LocationID");

            if(locationId != null) {
                Location location = new Location();
                location.setLocationId(locationId);
                organization.setLocation(location);
            }

            return organization;
        }
    }
}
