package com.ssafy.mugit.fixure;

import com.ssafy.mugit.user.entity.Profile;
import com.ssafy.mugit.user.entity.User;

public enum ProfileFixture {
    PROFILE(null, "leaf", "프로필", "http://localhost:8080/profile/1"),
    PROFILE_2(null, "leaf2", "프로필2", "http://localhost:8080/profile/2"),
    PROFILE_3(null, "jindol", "프로필3", "http://localhost:8080/profile/3"),
    NO_INPUT_PROFILE(null, "leaf", "", "");

    private final Long id;
    private final String nickName;
    private final String profileText;
    private final String profileImagePath;

    ProfileFixture(Long id, String nickName, String profileText, String profileImagePath) {
        this.id = id;
        this.nickName = nickName;
        this.profileText = profileText;
        this.profileImagePath = profileImagePath;
    }

    public Profile getFixture(){
        return new Profile(id, nickName, profileText, profileImagePath, null);
    }

    public Profile getFixture(User user){
        return new Profile(id, nickName, profileText, profileImagePath, user);
    }
}
