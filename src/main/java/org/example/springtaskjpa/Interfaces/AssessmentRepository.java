package org.example.springtaskjpa.Interfaces;

import org.example.springtaskjpa.Models.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

}
