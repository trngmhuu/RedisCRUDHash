package com.fit.se.repository;

import com.fit.se.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeMongoRepository extends MongoRepository<Employee, Integer> {

}
