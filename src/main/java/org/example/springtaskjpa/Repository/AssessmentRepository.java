package org.example.springtaskjpa.Repository;

import org.example.springtaskjpa.Model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

}
