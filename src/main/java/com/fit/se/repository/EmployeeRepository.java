package com.fit.se.repository;

import com.fit.se.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;
    @Autowired
    private EmployeeMongoRepository employeeMongoRepository;

    @Autowired
    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void saveEmployee(Employee employee) {
        hashOperations.put("EMPLOYEE", employee.getId(), employee);
        employeeMongoRepository.save(employee);
    }

    public List<Employee> findAll() {
        return hashOperations.values("EMPLOYEE");
    }

    public Employee findById(Integer id) {
        return (Employee) hashOperations.get("EMPLOYEE", id);
    }

    public void update(Employee employee) {
        saveEmployee(employee);
        employeeMongoRepository.save(employee);
    }

    public void delete(Integer id) {
        hashOperations.delete("EMPLOYEE", id);
        employeeMongoRepository.deleteById(id);
    }

}
