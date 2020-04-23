package ru.he.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.he.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
