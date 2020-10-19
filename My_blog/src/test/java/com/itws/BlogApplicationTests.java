package com.itws;

import com.itws.pojo.Blog;
import com.itws.pojo.Tag;
import com.itws.pojo.User;
import com.itws.service.BlogService;
import com.itws.service.UserService;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    private BlogService blogService;

    @Test
    void contextLoads() throws SQLException {
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        tag.setName("张三");
        tags.add(tag);
        Tag tag1 = new Tag();
        tag.setName("李四");
        tags.add(tag1);
        updateTag(tags);
        System.out.println(tags);
    }

    void updateTag(List<Tag> tags) {
        for (Tag tag : tags) {
            tag.setName("小二");
        }
    }


    @Test
    public void test2() {
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        tag.setName("张三");
        tags.add(tag);
        Tag tag1 = new Tag();
        tag.setName("李四");
        tags.add(tag1);
        List<Tag> tagList = new ArrayList<>();
        tagList.addAll(tags);
        System.out.println(tags);
        System.out.println("====");
        System.out.println(tagList);
        System.out.println("清空tags");
        tags.clear();
        System.out.println(tagList);
    }
}