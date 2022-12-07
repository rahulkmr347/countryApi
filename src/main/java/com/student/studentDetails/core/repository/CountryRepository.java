package com.student.studentDetails.core.repository;

import com.student.studentDetails.core.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long>{

    Country findByCountryName(String name);
}
