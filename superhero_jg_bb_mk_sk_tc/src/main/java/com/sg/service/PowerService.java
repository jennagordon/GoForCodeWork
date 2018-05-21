package com.sg.service;

import com.sg.dto.Hero;
import com.sg.dto.Power;

import java.util.List;

public interface PowerService {

    Power addPower(Power power);

    void removePower(Power power);

    void updatePower(Power power);

    List<Power> retrieveAllPowers(int limit, int offset);

    Power retrievePower(Long powerId);

    List<Power> retrievePowerByHero(Hero hero, int limit, int offset);
}
