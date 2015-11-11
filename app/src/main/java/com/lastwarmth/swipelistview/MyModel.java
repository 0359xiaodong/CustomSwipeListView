package com.lastwarmth.swipelistview;

public class MyModel {

    String imageUrl; // 头像Url
    String groupName; // 群名称
    String content; // 聊天内容

    public MyModel(String imageUrl, String groupName, String content) {
        this.imageUrl = imageUrl;
        this.groupName = groupName;
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getContent() {
        return content;
    }

}
