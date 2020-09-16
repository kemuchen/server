package cn.framework.security.shiro;

import cn.framework.Constants;
import cn.framework.util.sys.SysUtil;
import cn.framework.system.dao.auto.entity.SysRightEntity;
import cn.framework.system.dao.auto.mapper.SysRightMapper;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author ：柯雷
 * @ClassName:：ShiroConfiguration
 * @Description： shiro框架配置
 * @date ：2018年9月5日 下午5:03:43
 */
@Configuration
public class ShiroConfiguration {
    /**
     * @Description: 权限过滤器配置
     * @Params: [manager, sysRightMapper]
     * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @Author: 柯雷
     * @Date: 2020-04-28 14:18
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager, SysRightMapper sysRightMapper) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(manager);
        // 配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 遍历所有权限配置访问控制
        SysRightEntity sysRightEntity = new SysRightEntity();
        sysRightEntity.setValid(Constants.YES);
        List<SysRightEntity> rights = sysRightMapper.getSysRights(sysRightEntity);
        for (SysRightEntity right : rights) {
            // 权限url不为空
            if (!SysUtil.isEmpty(right.getRight_url())) {
                if (Constants.YES.equals(right.getAuthed())) {  // 需要认证
                    filterChainDefinitionMap.put(right.getRight_url(), "perms[" + right.getRight_url() + "]");//表示需要认证才可以访问
                }
            }
        }
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

    /**
     * @Description: 配置核心安全事务管理器
     * @Params: [authRealm]
     * @return: org.apache.shiro.web.mgt.DefaultWebSecurityManager
     * @Author: 柯雷
     * @Date: 2020-04-28 14:18
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        // shiro sessionid 避免与servlet session名冲突
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("shiro.session");
        simpleCookie.setPath("/");
        sessionManager.setSessionIdCookie(simpleCookie);

        manager.setSessionManager(sessionManager);
        return manager;
    }

    /**
     * @Description: 配置自定义的权限登录器
     * @Params: [matcher]
     * @return: cn.framework.security.shiro.AuthRealm
     * @Author: 柯雷
     * @Date: 2020-04-28 14:18
     */
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    /**
     * @Description: 配置自定义的密码比较器
     * @Params: []
     * @return: cn.framework.security.shiro.CredentialsMatcher
     * @Author: 柯雷
     * @Date: 2020-04-28 14:18
     */
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    /**
     * @Description: 生命周期
     * @Params: []
     * @return: org.apache.shiro.spring.LifecycleBeanPostProcessor
     * @Author: 柯雷
     * @Date: 2020-04-28 14:19
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * @Description: 创建代理
     * @Params: []
     * @return: org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     * @Author: 柯雷
     * @Date: 2020-04-28 14:19
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * @Description:
     * @Params: [manager]
     * @return: org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @Author: 柯雷
     * @Date: 2020-04-28 14:19
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
