package ru.he.repositories.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.he.models.Lesson;
import ru.he.repositories.LessonsRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class LessonRepositoryJdbcTemplateImpl implements LessonsRepository {

    //language=SQL
    public static final String SQL_SELECT_BY_ID = "select * from `javalab-spring-homework`.lesson where id = ?";

    //language=SQL
    public static final String SQL_SELECT_ALL = "select * from `javalab-spring-homework`.lesson";

    //language=SQL
    public static final String SQL_SELECT_BY_COURSE_ID = "select * from `javalab-spring-homework`.lesson where course_id = ?";

    //language=SQL
    private static final String SQL_INSERT = "insert into `javalab-spring-homework`.lesson(name, course_id) values (?, ?)";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from `javalab-spring-homework`.lesson where id = ?";

    //language=SQL
    private static final String SQL_UPDATE = "update `javalab-spring-homework`.lesson set course_id = ?, name = ? where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Lesson> lessonRowMapper = (row, rowNumber) ->
            Lesson.builder()
                    .id(row.getBigDecimal("id").longValue())
                    .name(row.getString("name"))
                    .courseId(row.getLong("course_id"))
                    .build();

    @Override
    public Optional<Lesson> find(Long id) {
        try {
            Lesson lesson = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, lessonRowMapper);
            return Optional.ofNullable(lesson);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Lesson entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getName(), entity.getCourseId(), entity.getId());
    }

    @Override
    public List<Lesson> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, lessonRowMapper);
    }

    @Override
    public void save(Lesson entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getCourseId());
            return statement;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public List<Lesson> findByCourseId(Long courseId) {
        return jdbcTemplate.query(SQL_SELECT_BY_COURSE_ID, lessonRowMapper, courseId);
    }
}
