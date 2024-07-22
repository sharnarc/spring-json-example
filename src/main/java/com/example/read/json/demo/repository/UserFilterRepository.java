package com.example.read.json.demo.repository;

import com.example.read.json.demo.entity.UserFilter;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface UserFilterRepository extends ReactiveCrudRepository<UserFilter, Integer> {
  //  @Query("SELECT * FROM ppm.userfilter u WHERE u.useremail ILIKE :userEmail AND u.businessunit ILIKE :businessUnit")
    Mono<UserFilter> findByUseremailAndBusinessunit(String userEmail, String businessUnit);

}
