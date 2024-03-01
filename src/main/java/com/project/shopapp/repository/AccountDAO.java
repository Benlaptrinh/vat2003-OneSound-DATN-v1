package com.project.shopapp.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.CountAccountDTO;

public interface AccountDAO extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT a FROM Account a WHERE LOWER(a.fullname) LIKE LOWER(CONCAT('%', :fullname, '%'))")
    Page<Account> findByFullnamePage(String fullname, Pageable pageable);
    @Query("SELECT COUNT(id) FROM Account id")
    long getTotalUsers();

    @Query("SELECT new CountAccountDTO(COUNT(a) as quantity, a.createdDate) from Account a  GROUP BY a.createdDate ORDER BY a.createdDate DESC")
    List<CountAccountDTO> countByCreatedDateDESC();

    @Query("SELECT new CountAccountDTO(COUNT(a) as quantity, a.createdDate) from Account a  GROUP BY a.createdDate ORDER BY a.createdDate ASC")
    List<CountAccountDTO> countByCreatedDateAsc();

    @Query("SELECT new CountAccountDTO(COUNT(a) as quantity, a.createdDate) from Account a  GROUP BY a.createdDate ORDER BY COUNT(a) DESC ")
    List<CountAccountDTO> countByCreatedById();

    // @Query("SELECT new CountAccountDTO(COUNT(a) as quantity, a.createdDate) from
    // Account a WHERE a.createdDate = :date GROUP BY a.createdDate ORDER BY
    // a.createdDate DESC")
    // List<CountAccountDTO> countByCreatedDate(@Param("date") Date date);
    @Query("select a from Account a where a.createdDate = :date ")
    List<Account> getAllAccountByCreatedDate(@Param("date") Date date);

}
