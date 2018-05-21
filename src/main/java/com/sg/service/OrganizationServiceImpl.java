package com.sg.service;

import com.sg.dao.OrganizationDao;
import com.sg.dto.Hero;
import com.sg.dto.Organization;

import javax.inject.Inject;
import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {

    OrganizationDao organizationDao;

    @Inject
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public Organization addOrganization(Organization organization) {
        return organizationDao.addOrganization(organization);
    }

    @Override
    public void removeOrganization(Organization organization) {
        organizationDao.removeOrganization(organization);

    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);

    }

    @Override
    public List<Organization> retrieveAllOrganizations(int limit, int offset) {
        return organizationDao.retrieveAllOrganizations(limit, offset);
    }

    @Override
    public Organization retrieveOrganization(Long organizationId) {
        return organizationDao.retrieveOrganization(organizationId);
    }

    @Override
    public List<Organization> retrieveOrganizationByHero(Hero hero, int limit, int offset) {
        return organizationDao.retrieveOrganizationByHero(hero, limit, offset);
    }
}
