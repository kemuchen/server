package cn.framework.security.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author ：柯雷
 * @ClassName:：CredentialsMatcher
 * @Description： shiro框架密码验证
 * @date ：2018年9月5日 下午5:03:32
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * @Description: 校验密码是否正确
     * @Params: [token, info]
     * @return: boolean
     * @Author: 柯雷
     * @Date: 2020-04-28 14:17
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        // 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(usernamePasswordToken.getPassword());
        // 获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        // 进行密码的比对
        return this.equals(inPassword, dbPassword);
    }
}
