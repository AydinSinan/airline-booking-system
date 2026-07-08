package com.snnydn.repository;

import com.snnydn.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    boolean existsByCityCode(String cityCode);
    Page<City> findByCountryCodeIgnoreCase(String countryCode, Pageable pageable);

    @Query("""
    SELECT c FROM City c
    where lower(c.name) like lower(concat('%', :keyword, '%'))
       or lower(c.cityCode) like lower(concat('%', :keyword, '%'))
       or lower(c.countryCode) like lower(concat('%', :keyword, '%'))
       or lower(c.countryCode) like lower(concat('%', :keyword, '%'))
       or lower(c.regionCode) like lower(concat('%', :keyword, '%'))
""")
    Page<City> searchByKeyword(String keyword, Pageable pageable);
}
