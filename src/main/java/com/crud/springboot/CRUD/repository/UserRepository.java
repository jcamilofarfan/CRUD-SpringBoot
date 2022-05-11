package com.crud.springboot.CRUD.repository;

import com.crud.springboot.CRUD.dto.UserDto;
import com.crud.springboot.CRUD.entity.User;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u WHERE u.email = ?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);
    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthdateBetween(LocalDate begin, LocalDate end);

    //List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query("SELECT new com.crud.springboot.CRUD.dto.UserDto(u.id, u.name, u.birthdate)" +
            " FROM User u " +
            " where u.birthdate=:dateParameter " +
            " and u.email=:emailParameter ")
    Optional<UserDto> getAllByBirthdateAndEmail(@Param("dateParameter") LocalDate date,
                                                @Param("emailParameter") String email);

}
