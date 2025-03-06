package com.example.groom.domain.room;


import com.example.groom.domain.room.dto.*;
import com.example.groom.domain.room.roomInviteCode.RoomInviteCodeService;
import com.example.groom.domain.room.roomInviteCode.dto.CodeDto;
import com.example.groom.domain.room.roomParticipants.RoomParticipantsService;
import com.example.groom.domain.room.roomParticipants.dto.RoomParticipantsDto;
import com.example.groom.entity.domain.room.Room;
import com.example.groom.entity.domain.room.RoomParticipants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomParticipantsService roomParticipantsService;
    private final RoomInviteCodeService roomInviteCodeService;

    public RoomDetailDto joinRoomByCode(String code) {
        Room room = roomRepository.findByCode(code).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코드입니다."));
        return RoomDetailDto.of(room);
    }

    public RoomDetailDto getRoomDetailDtoByRoomId(Long id) {
        return RoomDetailDto.of(this.roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방입니다.")));
    }

    public RoomListResponseDto searchRooms(Pageable pageable, RoomSearchCondition condition) {
        return this.roomRepository.searchByCondition(pageable, condition);
    }

    public Long addParticipant(RoomParticipantsDto roomParticipantsDto) {
        this.roomParticipantsService.save(RoomParticipants.of(roomParticipantsDto.getRoomId(), roomParticipantsDto.getUserId()));
        return roomParticipantsDto.getRoomId();
    }

    public Long deleteParticipant(RoomParticipantsDto roomParticipantsDto) {
        this.roomParticipantsService.delete(roomParticipantsDto);
        return roomParticipantsDto.getRoomId();
    }

    public RoomListResponseDto getMyRoomList(Pageable pageable, Long userId) {
        RoomSearchCondition condition = new RoomSearchCondition();
        condition.setParticipantId(userId);
        return searchRooms(pageable, condition);
    }

    @Transactional
    public CodeDto postRoom(RoomPostDto roomPostDto) {
        Room room = this.roomRepository.save(Room.of(roomPostDto));
        this.roomParticipantsService.saveAll(room.getId(), roomPostDto.getRoomParticipants());

        // 코드 생성
        CodeDto codeDto = roomInviteCodeService.save(room.getId());

        return codeDto;
    }

    public void deleteRoom(Long id) {
        this.roomRepository.deleteById(id);
    }

    @Transactional
    public RoomDetailDto updateRoom(RoomUpdateDto roomUpdateDto) {
        Room room = this.roomRepository.findById(roomUpdateDto.getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방입니다."));

        return RoomDetailDto.of(room.of(roomUpdateDto));
    }
}
