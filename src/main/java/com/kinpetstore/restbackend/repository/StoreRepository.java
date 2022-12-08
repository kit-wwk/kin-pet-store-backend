package com.kinpetstore.restbackend.repository;

import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.entity.District;
import com.kinpetstore.restbackend.entity.Store;
import com.kinpetstore.restbackend.entity.enums.StoreStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends BaseRepository<Store> {
    @Query(value = "SELECT t, t.id FROM Store t" +
            " WHERE t.id > 0 " +
            " AND (:phoneNumber is null OR t.phoneNumber LIKE %:phoneNumber%)" +
            " AND (:facebookUrl is null OR t.facebookUrl LIKE %:facebookUrl%)" +
            " AND (:instagramUrl is null OR t.instagramUrl LIKE %:instagramUrl%)" +
            " AND (:webUrl is null OR t.webUrl LIKE %:webUrl%)" +
            " AND (:district is null OR t.district = :district)" +
            " AND (:status is null OR t.status = :status)"
    )
    List<Store> search(@Param("phoneNumber") String phoneNumber, @Param("facebookUrl") String facebookUrl,
                       @Param("instagramUrl") String instagramUrl, @Param("webUrl") String webUrl,
                       @Param("district") District district, @Param("status") StoreStatus status);

    @Query(value = "SELECT t, t.id FROM Store t" +
            " WHERE t.id IN :ids " +
            " AND (:phoneNumber is null OR t.phoneNumber LIKE %:phoneNumber%)" +
            " AND (:facebookUrl is null OR t.facebookUrl LIKE %:facebookUrl%)" +
            " AND (:instagramUrl is null OR t.instagramUrl LIKE %:instagramUrl%)" +
            " AND (:webUrl is null OR t.webUrl LIKE %:webUrl%)" +
            " AND (:district is null OR t.district = :district)" +
            " AND (:status is null OR t.status = :status)"
    )
    List<Store> searchWithIds(@Param("ids") List<Long> ids,
                              @Param("phoneNumber") String phoneNumber, @Param("facebookUrl") String facebookUrl,
                              @Param("instagramUrl") String instagramUrl, @Param("webUrl") String webUrl,
                              @Param("district") District district, @Param("status") StoreStatus status);
}
