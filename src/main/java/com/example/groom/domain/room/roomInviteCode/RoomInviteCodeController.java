package com.example.groom.domain.room.roomInviteCode;

import com.example.groom.domain.room.roomInviteCode.dto.CodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room/{roomId}/code")
@RequiredArgsConstructor
public class RoomInviteCodeController {

    private final RoomInviteCodeService roomInviteCodeService;

    @GetMapping
    public CodeDto findByRoomId(@PathVariable Long roomId){
        return this.roomInviteCodeService.findByRoomId(roomId);
    }

    @PostMapping
    public CodeDto save(@PathVariable Long roomId){
        return this.roomInviteCodeService.save(roomId);
    }
}
