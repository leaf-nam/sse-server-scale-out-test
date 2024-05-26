package com.ssafy.mugit.user.service;

import com.ssafy.mugit.global.exception.UserApiException;
import com.ssafy.mugit.global.exception.error.UserApiError;
import com.ssafy.mugit.user.dto.request.RequestModifyUserInfoDto;
import com.ssafy.mugit.user.dto.response.ResponseUserProfileDto;
import com.ssafy.mugit.user.entity.Profile;
import com.ssafy.mugit.user.repository.ProfileRepository;
import com.ssafy.mugit.user.repository.UserRepository;
import com.ssafy.mugit.user.util.UserCookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final FollowService followService;
    private final UserCookieUtil userCookieUtil;

    public ResponseUserProfileDto getProfileById(Long userId) {
        // 해당 프로필 조회 + 예외처리
        ResponseUserProfileDto userDto = userRepository.findUserProfileDtoByUserId(userId);
        if (userDto == null) throw new UserApiException(UserApiError.USER_NOT_FOUND);
        // 팔로우 숫자 설정
        userDto.setFollowCount(followService.countMyFollowers(userId), followService.countMyFollowings(userId));
        return userDto;
    }

    public ResponseUserProfileDto getProfileById(Long myId, Long userId) {
        // 1명 조회할때와 동일한 로직 사용
        ResponseUserProfileDto userDto = getProfileById(userId);
        // 본인 조회했는지 확인
        if (myId.equals(userId)) userDto.setIsMyProfile(true);
        // 팔로우 여부 + 숫자 설정
        userDto.setIsFollow(followService.checkIsFollower(myId, userId), followService.checkIsFollower(userId, myId));
        return userDto;
    }

    @Transactional
    public HttpHeaders updateProfile(Long userId, RequestModifyUserInfoDto dto) {
        // 프로필 조회
        Profile profileInDB = profileRepository.findByUserId(userId);
        // 본인 닉네임이 아니면서 존재하는 닉네임 중복 처리
        if (!profileInDB.getNickName().equals(dto.getNickName()) && profileRepository.existsByNickName(dto.getNickName()))
            throw new UserApiException(UserApiError.DUPLICATE_NICK_NAME);
        // 업데이트
        profileInDB.update(dto.getNickName(), dto.getProfileText(), dto.getProfileImagePath());
        return userCookieUtil.getProfileCookieHeader(profileInDB);
    }
}
