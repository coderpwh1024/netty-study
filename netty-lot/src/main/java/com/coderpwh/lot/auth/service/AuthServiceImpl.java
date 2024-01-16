package com.coderpwh.lot.auth.service;

import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.coderpwh.lot.common.auth.GrozaAuthService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.security.interfaces.RSAPrivateCrtKey;

/**
 * @author coderpwh
 */
@Service
public class AuthServiceImpl implements GrozaAuthService {

    private RSAPrivateCrtKey privateCrtKey;


    @Override
    public boolean checkValid(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            return false;
        }

        if (StringUtils.isEmpty(password)) {
            return false;
        }

        RSA rsa = new RSA(privateCrtKey, null);
        String value = rsa.encryptBcd(username, KeyType.PrivateKey);

        return value.equals(password) ? true : false;
    }


    /***
     * 初始化
     */
    @PostConstruct
    public void init() {
        privateCrtKey = IoUtil.readObj(AuthServiceImpl.class.getClassLoader().getResourceAsStream("keystore/auth-private.key"));
    }


}
