package com.itws.config;



import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.itws.pojo.User;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.session.DefaultWebSessionManager;


import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shrioConfig {

    //创建一个ShiroFilterFactoryBean
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        Map<String,String> filterMap=new LinkedHashMap();//这里最好使用linkedhashmap；因为我们设置的这个过滤是有一个优先级的；

        //无需登录就能访问后台管理资源
        filterMap.put("/admin/login.do","anon");
        filterMap.put("/admin/login","anon");

        filterMap.put("/admin/logout","anon");
        //需要认证后才能访问后台管理资源 ：注：这里一定要写**
        filterMap.put("/admin/**","authc");
        filterMap.put("/admin/logout","logout");
        //设置登录页
        bean.setLoginUrl("/admin/login");
        //设置登录成功的页面
        bean.setSuccessUrl("/admin/index");
        bean.setUnauthorizedUrl("/admin/login");
        bean.setFilterChainDefinitionMap(filterMap);

        return bean;
    }



    //创建DefaultWebSecurityManager
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;

    }


    //创建realm对象,需要我们自定一个类去继承AuthorizingRealm
    @Bean("userRealm")
    public UserRealm getUserRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher credentialsMatcher){
        UserRealm userRealm=new UserRealm();
        userRealm.setAuthenticationCachingEnabled(false);
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return  userRealm;
    }


    /**
     * 密码校验规则
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候，这个也负责对form里输入的密码进行编码
     * @return
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        credentialsMatcher.setHashIterations(3);//相当于加密次数(md5(md5(md5)))
        credentialsMatcher.setHashAlgorithmName("md5");//散列算法md5
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return  credentialsMatcher;
    }

    /**
     * 整合shrioDialect 用来整合shrio和thymeleaf
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
