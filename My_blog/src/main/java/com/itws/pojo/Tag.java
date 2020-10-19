package com.itws.pojo;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 创建一个标签实体表
 */
//@Entity
//@Table(name = "t_tag")
public class Tag implements Serializable {
//    @Id
//    @GeneratedValue
    private Long id;
    @NotBlank(message = "标签名不能为空")
    private String name;

//    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs=new ArrayList<>();

    private Integer useTagToBlogNum;

    public Integer getUseTagToBlogNum() {
        return useTagToBlogNum;
    }

    public void setUseTagToBlogNum(Integer useTagToBlogNum) {
        this.useTagToBlogNum = useTagToBlogNum;
    }

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
////        if (o == null || getClass() != o.getClass()) return false;
//        Tag tag = (Tag) o;
//        return Objects.equals(name, tag.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash( name);
//    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
