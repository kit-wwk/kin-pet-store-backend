package com.kinpetstore.restbackend.repository;

import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.entity.Pet;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends BaseRepository<Pet> {
}
