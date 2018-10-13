package utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class doOCR {

    public  static  String seeOCR(String srcUrl) {
        OutputStream out = null;
        InputStream in = null;
        try {
            URL url = new URL(srcUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(2 * 1000);
            // 获取HTTP状态码
            int httpStatusCode = con.getResponseCode();
            if (httpStatusCode != 200) {
                throw new RuntimeException("异常");
            }
            in = con.getInputStream();
            // 获取文件长度
            int len = con.getContentLength();
            // 保存文件
            saveFileByByte(in, "./captures/abc.jpg", len);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String code = "";
        try {
            File pic = new File("./captures/abc.jpg");
            Tesseract tesseract = new Tesseract();
            tesseract.setTessVariable("tessedit_char_whitelist","0123456789");
            tesseract.setDatapath("d:/tesseract");
            code = tesseract.doOCR(pic);
            System.out.println("code is: " + code);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return code;
    }

    //写文件
    private static void saveFileByByte(InputStream in, String path, int len) throws Exception {
        byte[] byteDatas = new byte[len];
        BufferedOutputStream bw = null;
        try {
            // 创建文件对象
            File f = new File(path);
            // 创建文件路径
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            // 写入文件
            bw = new BufferedOutputStream(new FileOutputStream(path));
            int bytesRead = 0;
            while ((bytesRead = in.read(byteDatas, 0, byteDatas.length)) != -1) {
                bw.write(byteDatas, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public static void main(String[] args) {
//        String code = "";
////        try {
////            File pic = new File("./captures/abc.jpg");
////            ITesseract tesseract = new Tesseract();
////            tesseract.setDatapath("d:/tesseract");
////            code = tesseract.doOCR(pic);
////            System.out.println("code is: " + code);
////        } catch (TesseractException e) {
////            e.printStackTrace();
////        }
////        System.out.println("code is: " + code);
        int n = 100;
        while (n > 2) {
            seeOCR("https://passport.hualala.com/getDynamicCode?t=0.8258010439457748");
            n--;
        }
    }
}
