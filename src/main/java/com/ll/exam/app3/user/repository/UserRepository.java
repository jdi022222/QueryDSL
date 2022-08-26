package com.ll.exam.app3.user.repository;

import com.ll.exam.app3.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long>, UserRepositoryCustom {


    @Transactional
    @Modifying
    @Query(value = "truncate site_user", nativeQuery = true)
    void truncate();
}