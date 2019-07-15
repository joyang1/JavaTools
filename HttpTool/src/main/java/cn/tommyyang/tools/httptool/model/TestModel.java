package cn.tommyyang.tools.httptool.model;

import java.util.Date;
import java.util.List;

/**
 * Created by TommyYang on 2018/4/17.
 */
public class TestModel {

    private String pushId;
    private int taskID;
    private String appName;
    private String pushTitle;
    private String platformType;
    private String pushType;
    private String infoType;
    private String channelID;
    private String playID;
    private int typeID;
    private Date pushTime;
    private Date expirationTime;
    private List<String> tokenIds;
    private int badgeNum;
    private int week;
    private String actionType;
    private String action;
    private String actionParam;
    private String sectionid;
    private String imageUrl;

    public TestModel(String pushId, int taskID, String appName, String pushTitle, String platformType, String pushType, String infoType, String channelID, String playID, int typeID, Date pushTime, Date expirationTime, List<String> tokenIds, int badgeNum, int week, String actionType, String action, String actionParam, String sectionid, String imageUrl) {
        this.pushId = pushId;
        this.taskID = taskID;
        this.appName = appName;
        this.pushTitle = pushTitle;
        this.platformType = platformType;
        this.pushType = pushType;
        this.infoType = infoType;
        this.channelID = channelID;
        this.playID = playID;
        this.typeID = typeID;
        this.pushTime = pushTime;
        this.expirationTime = expirationTime;
        this.tokenIds = tokenIds;
        this.badgeNum = badgeNum;
        this.week = week;
        this.actionType = actionType;
        this.action = action;
        this.actionParam = actionParam;
        this.sectionid = sectionid;
        this.imageUrl = imageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getPlayID() {
        return playID;
    }

    public void setPlayID(String playID) {
        this.playID = playID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public List<String> getTokenIds() {
        return tokenIds;
    }

    public void setTokenIds(List<String> tokenIds) {
        this.tokenIds = tokenIds;
    }

    public int getBadgeNum() {
        return badgeNum;
    }

    public void setBadgeNum(int badgeNum) {
        this.badgeNum = badgeNum;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionParam() {
        return actionParam;
    }

    public void setActionParam(String actionParam) {
        this.actionParam = actionParam;
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}


