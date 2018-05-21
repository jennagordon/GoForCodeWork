package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.HeroOrganization;
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

public class HeroOrganizationDaoImpl implements HeroOrganizationDao {

    JdbcTemplate jt;

    @Inject
    public HeroOrganizationDaoImpl(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_CREATE = "insert into HeroOrganization (HeroID, OrganizationID) values (?, ?)";
    private static final String SQL_DELETE = "delete from HeroOrganization where HeroOrganizationID = ?";
    private static final String SQL_UPDATE = "update HeroOrganization set HeroID = ?, OrganizationID = ? where HeroOrganizationID = ?";
    private static final String SQL_RETRIEVE_BY_ID = "select * from HeroOrganization where HeroOrganizationID = ?";
    static final String SQL_RETRIEVE_BY_SIGHTING = "select * from HeroOrganization where OrganizationID = ?";
    private static final String SQL_RETRIEVE_BY_HERO = "select * from HeroOrganization where HeroID = ?";


    @Override
    @Transactional
    public HeroOrganization addHeroOrganization(HeroOrganization heroOrganization) {

        jt.update(SQL_CREATE,
                heroOrganization.getHero().getHeroId(),
                heroOrganization.getOrganization().getOrganizationId());

        Long id = jt.queryForObject("select LAST_INSERT_ID()", Long.class);
        heroOrganization.setHeroOrganizationId(id);

        return heroOrganization;
    }

    @Override
    public void removeHeroOrganization(HeroOrganization heroOrganization) {

        jt.update(SQL_DELETE, heroOrganization.getHeroOrganizationId());

    }

    @Override
    public void updateHeroOrganization(HeroOrganization heroOrganization) {

        jt.update(SQL_UPDATE,
                heroOrganization.getHero().getHeroId(),
                heroOrganization.getOrganization().getOrganizationId(),
                heroOrganization.getHeroOrganizationId());

    }

    @Override
    public HeroOrganization retrieveHeroOrganizationById(Long heroOrganizationId) {
        try {
            return jt.queryForObject(SQL_RETRIEVE_BY_ID, new HeroOrganizationMapper(), heroOrganizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<HeroOrganization> retrieveHeroOrganizationByOrganization(Long organizationId) {
        return jt.query(SQL_RETRIEVE_BY_SIGHTING, new HeroOrganizationMapper(), organizationId);
    }

    @Override
    public List<HeroOrganization> retrieveHeroOrganizationByHero(Long heroId) {
        return jt.query(SQL_RETRIEVE_BY_HERO, new HeroOrganizationMapper(), heroId);
    }

    private final static class HeroOrganizationMapper implements RowMapper<HeroOrganization> {

        @Override
        public HeroOrganization mapRow(ResultSet rs, int i) throws SQLException {

            HeroOrganization heroOrganization = new HeroOrganization();
            heroOrganization.setHeroOrganizationId(rs.getLong("HeroOrganizationID"));

            // getting organization id
            Long organizationId = rs.getLong("OrganizationID");

            if(organizationId != null) {
                Organization organization = new Organization();
                organization.setOrganizationId(organizationId);
                heroOrganization.setOrganization(organization);
            }

            // getting hero id
            Long heroId = rs.getLong("HeroID");

            if(heroId != null) {
                Hero hero = new Hero();
                hero.setHeroId(heroId);
                heroOrganization.setHero(hero);
            }


            return heroOrganization;
        }
    }
}
