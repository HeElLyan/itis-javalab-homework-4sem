package ru.he.repositories;


import ru.he.models.Course;

import java.util.List;

public interface CoursesRepository extends CrudRepository<Long, Course> {

    List<Course> findAll();
}
