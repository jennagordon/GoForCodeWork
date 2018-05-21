package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.Organization;

import java.util.List;

public interface OrganizationDao {

    Organization addOrganization(Organization organization);

    void removeOrganization(Organization organization);

    void updateOrganization(Organization organization);

    List<Organization> retrieveAllOrganizations(int limit, int offset);

    Organization retrieveOrganization(Long organizationId);

    List<Organization> retrieveOrganizationByHero(Hero hero, int limit, int offset);
}
