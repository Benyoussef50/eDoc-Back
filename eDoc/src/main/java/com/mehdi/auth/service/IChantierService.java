package com.mehdi.auth.service;

import java.util.List;

import com.mehdi.auth.models.Chantier;

public interface IChantierService {
	List<Chantier> findChanByUsername(String username);
}
