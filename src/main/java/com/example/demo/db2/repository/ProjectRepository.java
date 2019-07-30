package com.example.demo.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db2.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
