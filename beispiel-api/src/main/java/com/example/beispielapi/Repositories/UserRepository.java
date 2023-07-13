package com.example.beispielapi.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.beispielapi.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
}
