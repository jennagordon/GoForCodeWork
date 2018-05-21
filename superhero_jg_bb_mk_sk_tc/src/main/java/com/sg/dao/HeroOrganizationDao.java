package com.sg.dao;

import com.sg.dto.Hero;
import com.sg.dto.HeroOrganization;

import java.util.List;

public interface HeroOrganizationDao {

    HeroOrganization addHeroOrganization(HeroOrganization heroOrganization);

    void removeHeroOrganization(HeroOrganization heroOrganization);

    void updateHeroOrganization(HeroOrganization heroOrganization);

    HeroOrganization retrieveHeroOrganizationById(Long heroOrganizationId);


}
