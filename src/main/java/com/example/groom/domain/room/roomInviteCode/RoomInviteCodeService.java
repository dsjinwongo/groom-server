package com.example.groom.domain.room.roomInviteCode;


import com.example.groom.domain.room.roomInviteCode.dto.CodeDto;
import com.example.groom.entity.domain.room.RoomInviteCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomInviteCodeService {
    private final RoomInviteCodeRepository roomInviteCodeRepository;

    public CodeDto findByRoomId(Long roomId) {
        return CodeDto.of(this.roomInviteCodeRepository.findByRoomId(roomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방 코드입니다.")));
    }

    public CodeDto save(Long roomId) {
        RoomInviteCode code = RoomInviteCode.of(roomId);
        code = this.roomInviteCodeRepository.save(code);
        return CodeDto.of(code);
    }
}
