package com.kinpetstore.restbackend.repository;

import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findBySub(String sub);

    @Query("SELECT t FROM #{#entityName} t" +
            " WHERE t.id > 0 " +
            " AND (:sub is null OR t.sub LIKE %:sub%)" +
            " AND (:givenName is null OR t.givenName LIKE %:givenName%)" +
            " AND (:email is null OR t.email LIKE %:email%)" +
            " AND (:nickname is null OR t.nickname LIKE %:nickname%)" +
            " AND (:name is null OR t.name LIKE %:name%)" +
            " AND (:picture is null OR t.picture LIKE %:picture%)" +
            "")
    List<User> search(@Param("sub") String sub, @Param("givenName") String givenName, @Param("email") String email,
                      @Param("name") String name, @Param("nickname") String nickname, @Param("picture") String picture);
}
