package io.github.imateus.sorterapi.repository;

import io.github.imateus.sorterapi.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, Integer> {
}
