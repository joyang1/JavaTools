package cn.tommyyang.tools.httptool.model;

/**
 * Created by TommyYang on 2018/2/24.
 */
public class User {

    private String username;

    private String nickname;

    private Integer gender;

    private String intro;

    public User(String username, String nickname, Integer gender, String intro) {
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
        this.intro = intro;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
