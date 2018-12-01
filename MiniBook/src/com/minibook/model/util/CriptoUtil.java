package com.minibook.model.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class CriptoUtil {
  private static final String METODO_ENCRIPTACAO = "AES";
  public static final byte[] CHAVE = {85, 10, 0, -25, 68, 88, 46, 37, 107, 48, 10, -1, -37, -90,
      70, -36};;

  public static String encriptar(byte[] key, String value) throws CriptoException {

    try {
      SecretKeySpec skeySpec = new SecretKeySpec(key, METODO_ENCRIPTACAO);

      Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACAO);
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
      byte[] encrypted = cipher.doFinal(value.getBytes());

      return new BASE64Encoder().encode(encrypted);
    } catch (Exception e) {
      throw new CriptoException("Erro ao criptografar informações " + e.getMessage());
    }
  }

  public static String decriptar(byte[] key, String encrypted) throws CriptoException {

    byte[] decrypted = null;

    try {
      SecretKeySpec skeySpec = new SecretKeySpec(key, METODO_ENCRIPTACAO);

      byte[] decoded = new BASE64Decoder().decodeBuffer(encrypted);

      Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACAO);
      cipher.init(Cipher.DECRYPT_MODE, skeySpec);
      decrypted = cipher.doFinal(decoded);
    } catch (Exception e) {
      throw new CriptoException("Erro ao descriptografar informações " + e.getMessage());
    }

    return new String(decrypted);
  }
}
