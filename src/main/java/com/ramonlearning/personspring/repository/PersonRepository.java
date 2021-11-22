package com.ramonlearning.personspring.repository;

import com.ramonlearning.personspring.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
