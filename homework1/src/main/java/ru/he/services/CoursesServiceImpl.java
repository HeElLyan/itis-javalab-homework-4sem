package ru.he.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.he.dto.CourseDto;
import ru.he.dto.LessonDto;
import ru.he.models.Course;
import ru.he.models.Lesson;
import ru.he.repositories.CoursesRepository;
import ru.he.repositories.LessonsRepository;

import java.util.ArrayList;
import java.util.List;

@Component("courseService")
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private LessonsRepository lessonsRepository;

    @Override
    public CourseDto getCourse(Long id) {
        Course course = coursesRepository.find(id).get();
        List<Lesson> lessons = lessonsRepository.findByCourseId(course.getId());

        List<LessonDto> lessonDto = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonDto.add(new LessonDto(lesson.getId(), lesson.getName()));
        }
        return new CourseDto(course.getId(), course.getTitle(), lessonDto);
    }

}
