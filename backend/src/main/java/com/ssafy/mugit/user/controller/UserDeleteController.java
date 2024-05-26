package com.ssafy.mugit.user.controller;

import com.ssafy.mugit.global.config.UserSession;
import com.ssafy.mugit.global.dto.MessageDto;
import com.ssafy.mugit.user.service.UserDeleteService;
import com.ssafy.mugit.global.dto.UserSessionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저와 관련한 API입니다.")
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    @Operation(summary = "회원탈퇴", description = "회원을 탈퇴한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원탈퇴 완료",
                    content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "401", description = "Security Filter 통과 실패 : 로그인 정보 없음",
                    content = @Content(schema = @Schema(implementation = MessageDto.class)))
    })
    @DeleteMapping
    ResponseEntity<MessageDto> delete(@UserSession UserSessionDto user) {
        userDeleteService.deleteUserEntity(user.getId());

        return ResponseEntity.ok().body(new MessageDto("회원탈퇴 완료"));
    }
}
