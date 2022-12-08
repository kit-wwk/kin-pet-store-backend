package com.kinpetstore.restbackend.repository;

import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.entity.Pet;
import com.kinpetstore.restbackend.entity.Store;
import com.kinpetstore.restbackend.entity.enums.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends BaseRepository<Pet> {

    @Query("SELECT t FROM #{#entityName} t" +
            " WHERE t.id > 0 " +
            " AND (:name is null OR t.name LIKE %:name%)" +
            " AND (t.price >= :minPrice)" +
            " AND (t.price <= :maxPrice)" +
            " AND (t.age >= :minAge)" +
            " AND (t.age <= :maxAge)" +
            " AND (:ageGroup is null OR t.ageGroup = :ageGroup)" +
            " AND (:breed is null OR t.breed = :breed)" +
            " AND (:petType is null OR t.petType = :petType)" +
            " AND (:coat is null OR t.coat = :coat)" +
            " AND (:gender is null OR t.gender = :gender)" +
            " AND (:size is null OR t.size = :size)" +
            " AND (:status is null OR t.status = :status)" +
            " AND (:store is null OR t.store = :store)" +
            "")
    List<Pet> search(@Param("name") String name, @Param("minPrice") Double minPrice, @Param("maxPrice") Double price,
                     @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge, @Param("ageGroup") AgeGroup ageGroup,
                     @Param("breed") Breed breed, @Param("petType") PetType petType, @Param("coat") Coat coat,
                     @Param("gender") Gender gender, @Param("size") Size size, @Param("status") PetStatus status,
                     @Param("store") Store store);
}
