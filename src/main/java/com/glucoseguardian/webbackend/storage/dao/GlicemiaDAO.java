package com.glucoseguardian.webbackend.storage.dao;

import com.glucoseguardian.webbackend.storage.entity.Feedback;
import com.glucoseguardian.webbackend.storage.entity.Glicemia;
import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GlicemiaDAO extends CrudRepository<Glicemia, Long> {

}
