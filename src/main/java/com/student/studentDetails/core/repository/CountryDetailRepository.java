package com.student.studentDetails.core.repository;

import com.student.studentDetails.core.entity.CountryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface CountryDetailRepository  extends JpaRepository<CountryDetail,Long> {


    @Modifying
    @Transactional
    @Query("delete from CountryDetail c where c.key in(:keys)")
    void deleteByKey(@Param("keys") Set<String> keys);
}


