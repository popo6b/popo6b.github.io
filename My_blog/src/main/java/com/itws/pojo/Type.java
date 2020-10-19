package com.itws.pojo;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 创阿金一个类型实体表
 */
//@Entity
//@Table(name = "t_type")
public class Type implements Serializable {

//    @Id
//    @GeneratedValue
    private Long id;
    //进行一个后端校验

    @NotBlank(message = "类型名不能为空")
    private String name;

    private Integer typeUseToBlogNum;

    public Integer getTypeUseToBlogNum() {
        return typeUseToBlogNum;
    }

    public void setTypeUseToBlogNum(Integer typeUseToBlogNum) {
        this.typeUseToBlogNum = typeUseToBlogNum;
    }

    //    @OneToMany(mappedBy = "type")
    private List<Blog> blogs=new ArrayList<>();

    public Type() {
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
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
