package com.mehdi.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mehdi.auth.models.Chantier;
import com.mehdi.auth.repository.ChantierRepository;



@Service
@Transactional
public class ChantierService implements IChantierService {
    @Autowired
	ChantierRepository chrepo ; 
	
	
	@Override
	public List<Chantier> findChanByUsername(String username) {
				return chrepo.findChantierByUsername(username);
	}

}
