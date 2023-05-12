package com.api.service;

import com.api.entity.Party;
import com.api.repository.PartyRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
@Slf4j
@NoArgsConstructor
public class PartyService {


    @Autowired
    private PartyRepository partyRepo;


    public boolean initParty(String name, Timestamp buildDate) {
        Party insert = Party.builder().name(name).buildDate(buildDate).build();
        try {
            partyRepo.save(insert);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}