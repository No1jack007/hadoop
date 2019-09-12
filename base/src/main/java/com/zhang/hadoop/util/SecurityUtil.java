//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhang.hadoop.util;

import java.security.MessageDigest;
import java.util.Random;

public class SecurityUtil {
   public SecurityUtil() {
   }

   public static final String getMd5(String s) {
      char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

      try {
         byte[] strTemp = s.getBytes("UTF-8");
         MessageDigest mdTemp = MessageDigest.getInstance("MD5");
         mdTemp.update(strTemp);
         byte[] md = mdTemp.digest();
         int j = md.length;
         char[] str = new char[j * 2];
         int k = 0;

         for(int i = 0; i < j; ++i) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 15];
            str[k++] = hexDigits[byte0 & 15];
         }

         return new String(str);
      } catch (Exception var10) {
         return null;
      }
   }

   public static String getRandomString(int length) {
      StringBuffer buffer = new StringBuffer("0123456789");
      StringBuffer sb = new StringBuffer();
      Random r = new Random();
      int range = buffer.length();

      for(int i = 0; i < length; ++i) {
         sb.append(buffer.charAt(r.nextInt(range)));
      }

      return sb.toString();
   }

   public static boolean getDecryptFlag() {
      return true;
   }
}
