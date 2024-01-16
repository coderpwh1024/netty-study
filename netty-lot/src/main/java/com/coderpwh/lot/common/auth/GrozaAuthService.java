package com.coderpwh.lot.common.auth;

/**
 * @author coderpwh
 */
public interface GrozaAuthService {
    /**
     * 验证用户名和密码是否正确
     */
    boolean checkValid(String username, String password);
}
