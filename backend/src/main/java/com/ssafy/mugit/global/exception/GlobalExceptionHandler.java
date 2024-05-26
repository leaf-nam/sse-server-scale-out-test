package com.ssafy.mugit.global.exception;

import com.ssafy.mugit.global.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    // [400]validate 오류
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<MessageDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new MessageDto("잘못된 요청값입니다."), HttpStatus.BAD_REQUEST);
    }

    // [400]cookie 오류
    @ExceptionHandler(MissingRequestCookieException.class)
    protected ResponseEntity<MessageDto> handleMissingRequestCookieException(MissingRequestCookieException e) {
        return new ResponseEntity<>(new MessageDto("요청에 필요한 쿠키가 없습니다."), HttpStatus.BAD_REQUEST);
    }

    // UserApiException 처리
    @ExceptionHandler(UserApiException.class)
    protected ResponseEntity<MessageDto> handleUserApiException(UserApiException e) {
        log.error(e.getMessage());
        return switch (e.getUserApiError()) {
            case ILLEGAL_SNS_TYPE -> new ResponseEntity<>(new MessageDto("잘못된 Sns Type 입력"), HttpStatus.BAD_REQUEST);
            case UNAUTHORIZED_BY_OAUTH -> new ResponseEntity<>(new MessageDto("OAuth 인증 실패"), HttpStatus.UNAUTHORIZED);
            case DUPLICATE_NICK_NAME -> new ResponseEntity<>(new MessageDto("중복 닉네임"), HttpStatus.CONFLICT);
            case NOT_REGISTERED_USER -> new ResponseEntity<>(new MessageDto("회원가입 필요"), HttpStatus.FOUND);
            case USER_NOT_FOUND -> new ResponseEntity<>(new MessageDto("해당 사용자 없음"), HttpStatus.NOT_FOUND);
            case NO_OAUTH_TOKEN -> new ResponseEntity<>(new MessageDto("토큰 없음"), HttpStatus.UNAUTHORIZED);
            case NOT_AUTHORIZED_USER -> new ResponseEntity<>(new MessageDto("인증 필요"), HttpStatus.UNAUTHORIZED);
            case SELF_FOLLOW -> new ResponseEntity<>(new MessageDto("본인 팔로우"), HttpStatus.BAD_REQUEST);
            case ALREADY_FOLLOW -> new ResponseEntity<>(new MessageDto("이미 팔로우"), HttpStatus.CONFLICT);
            case NOT_EXIST_FOLLOW -> new ResponseEntity<>(new MessageDto("이미 삭제된 팔로우"), HttpStatus.NO_CONTENT);
            case SELF_PROFILE -> new ResponseEntity<>(new MessageDto("본인 프로필 조회"), HttpStatus.BAD_REQUEST);
            case NOT_ALLOWED_ACCESS -> new ResponseEntity<>(new MessageDto("접근 권한 없음"), HttpStatus.FORBIDDEN);
            case NOT_EXIST_READABLE_NOTIFICATION -> new ResponseEntity<>(new MessageDto("안읽은 알림 없음"), HttpStatus.NOT_FOUND);
            case NOTIFICATION_NOT_FOUNT -> new ResponseEntity<>(new MessageDto("해당 알림 없음"), HttpStatus.NOT_FOUND);
            case DELETE_RECORD_NOT_IN_MUGITORY -> new ResponseEntity<>(new MessageDto("뮤기토리에 존재하지 않는 레코드 삭제"), HttpStatus.NOT_FOUND);
            case ALREADY_RECORDED_TO_MUGITORY -> new ResponseEntity<>(new MessageDto("뮤기토리에 이미 등록된 레코드 재등록"), HttpStatus.CONFLICT);
            case NOT_EXIST_MUGITORY -> new ResponseEntity<>(new MessageDto("해당일자 뮤지토리 없음"), HttpStatus.NOT_FOUND);
        };
    }

    @ExceptionHandler(FlowApiException.class)
    protected ResponseEntity<MessageDto> handleFlowApiException(FlowApiException e) {
        log.error(e.getMessage());
        return switch (e.getFlowApiError()) {
            case NOT_ALLOWED_ACCESS -> new ResponseEntity<>(new MessageDto("접근 권한 없음"), HttpStatus.FORBIDDEN);
            case NOT_RELEASED_FLOW -> new ResponseEntity<>(new MessageDto("릴리즈 되지 않은 플로우"), HttpStatus.BAD_REQUEST);
            case NO_RECORD -> new ResponseEntity<>(new MessageDto("레코드 없음"), HttpStatus.NOT_FOUND);
            case ALREADY_RELEASED_FLOW -> new ResponseEntity<>(new MessageDto("이미 릴리즈된 플로우"), HttpStatus.BAD_REQUEST);
            case NOT_EXIST_FLOW -> new ResponseEntity<>(new MessageDto("존재하지 않는 플로우"), HttpStatus.NOT_FOUND);
            case NO_CONTENT -> new ResponseEntity<>(new MessageDto("내용 없음"), HttpStatus.NO_CONTENT);
            case NO_MUSIC -> new ResponseEntity<>(new MessageDto("음악 파일 없음"), HttpStatus.NO_CONTENT);
        };
    }

    // [500]서버 오류
    @ExceptionHandler(InternalError.class)
    protected ResponseEntity<MessageDto> handleInternalServerException(InternalError e) {
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
