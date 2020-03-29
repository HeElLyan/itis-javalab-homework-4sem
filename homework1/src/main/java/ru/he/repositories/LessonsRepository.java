package ru.he.repositories;


import ru.he.models.Lesson;

import java.util.List;

public interface LessonsRepository extends CrudRepository<Long, Lesson> {

    List<Lesson> findByCourseId(Long courseId);
    List<Lesson> findAll();

}
