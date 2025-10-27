package com.example.springPR.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springPR.Model.entity.Person;



public interface PersonRepo extends JpaRepository<Person, Long> {

}
