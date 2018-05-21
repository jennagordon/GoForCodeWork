package com.sg.dao;

import com.sg.dto.HeroSighting;

public interface HeroSightingDao {

    HeroSighting addHeroSighting(HeroSighting heroSighting);

    void removeHeroSighting(HeroSighting heroSighting);

    void updateHeroSighting(HeroSighting heroSighting);

    HeroSighting retrieveHeroSightingById(Long heroSightingId);
}
