package com.sg.service;

import com.sg.dto.HeroPower;

public interface HeroPowerService {

    HeroPower addHeroPower(HeroPower heroPower);

    void removeHeroPower(HeroPower heroPower);

    void updateHeroPower(HeroPower heroPower);

    HeroPower retrieveHeroPowerById(Long heroPowerId);
}
