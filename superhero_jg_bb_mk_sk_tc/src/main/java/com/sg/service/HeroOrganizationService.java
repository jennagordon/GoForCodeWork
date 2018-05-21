package com.sg.service;

import com.sg.dto.HeroOrganization;

public interface HeroOrganizationService {

    HeroOrganization addHeroOrganization(HeroOrganization heroOrganization);

    void removeHeroOrganization(HeroOrganization heroOrganization);

    void updateHeroOrganization(HeroOrganization heroOrganization);

    HeroOrganization retrieveHeroOrganizationById(Long heroOrganizationId);


}
