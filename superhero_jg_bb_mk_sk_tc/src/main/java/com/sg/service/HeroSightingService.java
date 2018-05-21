package com.sg.service;

import com.sg.dto.HeroSighting;

public interface HeroSightingService {

    HeroSighting addHeroSighting(HeroSighting heroSighting);

    void removeHeroSighting(HeroSighting heroSighting);

    void updateHeroSighting(HeroSighting heroSighting);

    HeroSighting retrieveHeroSightingById(Long heroSightingId);
}
