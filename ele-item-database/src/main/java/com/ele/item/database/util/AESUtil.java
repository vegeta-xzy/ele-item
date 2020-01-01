package com.ele.item.database.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;



public class AESUtil {

	private static final String KEY_ALGORITHM = "AES";
    private static final String CHAR_SET = "UTF-8";
    /**
     * AES的密钥长度
     */
    private static final Integer SECRET_KEY_LENGTH = 128;
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(SECRET_KEY_LENGTH, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
//            log.error("AESUtil.encrypt cause exception : " + e.getMessage(), e);
            throw new RuntimeException("AESUtil.encrypt cause exception : " + e.getMessage(), e);
        }
    }

    /**
     * AES解密操作
     *
     * @param encryptContent 加密的密文
     * @param password       解密的密钥
     * @return
     */
    public static String decrypt(String encryptContent, String password) {
        if (StringUtils.isEmpty(encryptContent) || StringUtils.isEmpty(password)) {
//            throw new ParamVerifyException("AES decryption params is null.");
        }
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(SECRET_KEY_LENGTH, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] decryptResult = cipher.doFinal(Base64.decodeBase64(encryptContent));
            return new String(decryptResult, CHAR_SET);
        } catch (Exception e) {
//            log.error("AESUtil.decrypt cause exception : " + e.getMessage(), e);
            throw new RuntimeException("AESUtil.decrypt cause exception : " + e.getMessage(), e);
        }
    }

    private static SecretKeySpec getSecretKey(final String password) throws NoSuchAlgorithmException {
        //生成指定算法密钥的生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(SECRET_KEY_LENGTH, new SecureRandom(password.getBytes()));
        //生成密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //转换成AES的密钥
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    }
    
    public static void main(String[] args) {
    	System.out.println(encrypt("root","F2@H0<zlD1>9?no"));
    	
	}
}
