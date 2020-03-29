package ru.he.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String title;
    private List<LessonDto> lessonDto;
}
