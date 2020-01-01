package com.ele.item.common.config.util.encryption;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * 
 * @author nec
 * @DEC PBKDF2加密
 *
 */
public class PasswordEncryption {

	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
	 
	/**
	 * 盐的长度
	 */
	public static final int SALT_BYTE_SIZE = 32 / 2;
 
	/**
	 * 生成密文的长度
	 */
	public static final int HASH_BIT_SIZE = 128 * 4;
 
	/**
	 * 迭代次数
	 */
	public static final int PBKDF2_ITERATIONS = 1000;
 
	/**
	 * 对输入的密码进行验证
	 * 
	 * @param attemptedPassword
	 *            待验证的密码
	 * @param encryptedPassword
	 *            密文
	 * @param salt
	 *            盐值
	 * @return 是否验证成功
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static boolean authenticate(String attemptedPassword, String encryptedPassword, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 用相同的盐值对用户输入的密码进行加密
		String encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
		// 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
		return encryptedAttemptedPassword.equals(encryptedPassword);
	}
 
	/**
	 * 生成密文
	 * 
	 * @param password
	 *            明文密码
	 * @param salt
	 *            盐值
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static String getEncryptedPassword(String password, String salt) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), fromHex(salt), PBKDF2_ITERATIONS, HASH_BIT_SIZE);
		SecretKeyFactory f = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return toHex(f.generateSecret(spec).getEncoded());
	}
 
	/**
	 * 通过提供加密的强随机数生成器 生成盐
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);
		return toHex(salt);
	}
 
	/**
	 * 十六进制字符串转二进制字符串
	 * 
	 * @param   hex         the hex string
	 * @return              the hex string decoded into a byte array      
	 */
	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}
 
	/**
	 * 二进制字符串转十六进制字符串
	 * 
	 * @param   array       the byte array to convert
	 * @return              a length*2 character string encoding the byte array      
	 */
	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}
	
	public static void main(String[] args) throws Exception{
		String salt = generateSalt();
		System.out.println(salt.length()+"盐--->"+salt);//获取盐
		String passwordEnc =  getEncryptedPassword("123456", salt);
		System.out.println(passwordEnc.length()+"<===>"+passwordEnc.length()+"加密后---->"+passwordEnc);
		System.out.println(authenticate("123456", "f08259d1e252a877e276f25d547f408fa82d8416f029a7e22d57982e8b1cc83ad5e8b869ec686dba3144cdf2e64f3913d3db48180603e96e7cd3075b9a0ad64d", "c75fda19025937300e1f31126b240dd7"));
		
		//jwt KEY 加密
		
		
		
		
	}
}
