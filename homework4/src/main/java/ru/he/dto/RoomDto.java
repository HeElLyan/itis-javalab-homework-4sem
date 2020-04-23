package ru.he.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.he.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {

    private Long id;
    private String name;

    @Nullable
    private List<MessageDto> messageDtoList;

    public static RoomDto from(Room room){
        return RoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .build();
    }

    public static List<RoomDto> from(List<Room> roomList) {
        return roomList.stream().map(RoomDto::from).collect(Collectors.toList());
    }

}
