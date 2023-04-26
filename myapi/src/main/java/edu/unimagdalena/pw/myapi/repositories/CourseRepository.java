//jjpizarro.devcloud
package edu.unimagdalena.pw.myapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.unimagdalena.pw.myapi.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
