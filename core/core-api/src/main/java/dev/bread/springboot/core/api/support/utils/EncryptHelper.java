package dev.bread.springboot.core.api.support.utils;

public interface EncryptHelper {

	String encrypt(String password);

	boolean isMatch(String password, String hashed);

}
