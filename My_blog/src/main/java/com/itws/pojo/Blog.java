package com.itws.pojo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 创建一个博客实体类
 */

public class Blog implements Serializable {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;//标记
    private Integer views;//浏览次数
    private boolean appreciation;
    private boolean shareStatement;//版权转载
    private boolean commentabled;//评论
    private boolean published;
    private boolean recommend;//是否推荐

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String updateTimeStr;

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    private Date createTime;

    private Date updateTime;


    private Type type;


    private List<Tag> tags=new ArrayList<>();

    //它只是我们实体类的一个属性值，是不会入数据库的
    private String tagIds;

    private List<String> tagNames;

    private String preNames;

    public String getPreNames() {
        return preNames;
    }

    public void setPreNames(String preNames) {
        this.preNames = preNames;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagName) {
        this.tagNames = tagName;
    }
//    //我们定义一个初始化方法----因为我们这个tagIds，没有保存在数据库，我们前端页面要使用就可以获取
//    public void init2(){
//        this.preNames=TagsToTagNames(this.getTags());
//
//    }
//
//    //用来将我们实体类中的list<Tag>获取出它的tagsName
//    public String TagsToTagNames(List<Tag> tags){
//        if (!tags.isEmpty()) {
//            for (String preName : preNames) {
//                for (Tag tag : tags) {
//                    tag.getName();
//                }
//            }
//
//        }
//    }

    //我们定义一个初始化方法----因为我们这个tagIds，没有保存在数据库，我们前端页面要使用就可以获取
    public void init(){
        this.preNames=TagsToTagNames(this.getTags());

    }

    //用来将我们实体类中的list<Tag>获取出它的tagsId
    public String TagsToTagNames(List<Tag> tags){
        if (!tags.isEmpty()) {
            StringBuffer ids=new StringBuffer();//用来进行拼接成字符串
            boolean flag = false;//用来进行判断
            for (Tag tag : tags) {
                if(flag){
                    ids.append(",");
                }else{
                    flag=true;
                }
                ids.append(tag.getName());
            }
            return ids.toString();
        }
        return "";
    }

    private User user;

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    private List<Comment> comments=new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
