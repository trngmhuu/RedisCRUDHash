package com.fit.se.repository;

import com.fit.se.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private HashOperations<String, Integer, Employee> hashOperations;

    @Autowired
    public EmployeeRepository(HashOperations<String, Integer, Employee> hashOperations, RedisTemplate<String, Employee> redisTemplate) {
        this.hashOperations = hashOperations;
    }

    public void saveEmployee(Employee employee) {
        hashOperations.put("EMPLOYEE", employee.getId(), employee);
    }

    public List<Employee> findAll() {
        return hashOperations.values("EMPLOYEE");
    }

    public Employee findById(Integer id) {
        return (Employee) hashOperations.get("EMPLOYEE", id);
    }

    public void update(Employee employee) {
        saveEmployee(employee);
    }

    public void delete(Integer id) {
        hashOperations.delete("EMPLOYEE", id);
    }

}