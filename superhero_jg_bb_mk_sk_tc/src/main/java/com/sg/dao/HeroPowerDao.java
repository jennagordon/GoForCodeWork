package com.sg.dao;

import com.sg.dto.HeroPower;

public interface HeroPowerDao {

    HeroPower addHeroPower(HeroPower heroPower);

    void removeHeroPower(HeroPower heroPower);

    void updateHeroPower(HeroPower heroPower);

    HeroPower retrieveHeroPowerById(Long heroPowerId);
}
