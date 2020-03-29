package ru.he;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.he.config.ApplicationConfiguration;
import ru.he.dto.CourseDto;
import ru.he.services.CoursesService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        CoursesService coursesService = context.getBean("courseService", CoursesService.class);
        CourseDto courseDto = coursesService.getCourse(1L);
        System.out.println(courseDto);
    }

}
