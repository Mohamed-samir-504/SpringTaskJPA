package org.example.springtaskjpa.Repository;

import org.example.springtaskjpa.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
