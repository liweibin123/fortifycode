package com.lanju.test;

import com.fortify.util.CryptoUtil;
import com.lanju.decry.FortifyRuleDecrypter;
import org.junit.Test;

import java.io.*;

/**
 * Author: li wei bin
 * Date: 2020-07-10 16:44
 * Description: <描述>
 */
public class FortifyRuleDecrypterTest {

    @Test
    public void testDecrypter() {
        FortifyRuleDecrypter fortifyRuleDecrypter = new FortifyRuleDecrypter("C:\\Users\\86176\\Desktop\\rules\\core_abap.bin", "C:\\Users\\86176\\Desktop\\");

        fortifyRuleDecrypter.doDecrypt();
    }

    @Test
    public void testCryptoUtil() throws IOException {
        InputStream inputStream = CryptoUtil.decryptCompressed(new FileInputStream(new File("C:\\Users\\86176\\Desktop\\fortify加密规则破解\\rules\\core_abap.bin")), null);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\86176\\Desktop\\cor_abap.xml"));
        int len = 0;
        char[] arr = new char[1024];
        while ((len = br.read(arr)) != -1) {
            bw.write(arr, 0, len);
            bw.flush();
        }
        br.close();
        bw.close();
        inputStream.close();
        System.out.println("测试成功");
    }
}