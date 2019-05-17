package com.chuangmeng.cmzc.commons.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class BackStageUtils {

    /**
     *          文件上传
     * @param uploadFile    需要上传的文件
     * @return              文件上传后的绝对地址
     */
    public static String uploadFile(MultipartFile uploadFile) throws IOException {
        String filename = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        long timeMillis = System.currentTimeMillis();
        filename = timeMillis+"Pic"+filename;
        FileOutputStream fileInputStream = null;
        FTPClient ftp = new FTPClient();
        //连接FTP服务器，默认端口是21
        ftp.connect("132.232.213.216",21);
        //匿名用户必须使用anonymous登录，密码是邮箱
        boolean login = ftp.login("anonymous", "243975576@qq.com");
        int replyCode = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            System.out.println("获取响应失败");
            return null;
        }
        //设置接收文件类型为二进制文件
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        //服务器上创建images文件夹
        boolean pub = ftp.makeDirectory("pub");
        //切换ftp默认文件夹
        boolean pub1 = ftp.changeWorkingDirectory("cmzc/project");
        //获取内存文件
        InputStream inputStream = uploadFile.getInputStream();
        System.out.println(inputStream);
        //将用户上传的图片上传到ftp服务器上
        boolean b = ftp.storeFile(filename, inputStream);
        System.out.println("文件上传"+b);
        //退出登录
        ftp.logout();
        return "http://localhost/fxmall/images/sku/"+filename;
    }

    public static String getString(int length){
        Random random = new Random();
        char[] a = {'0','1','2','3','4','5','6','7','8','9',
                    'A','B','C','D','E','F','G','H','I','J','K','L','M',
                    'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int LEN = a.length-1;
        StringBuilder result = new StringBuilder("");
        for (int i=0;i<length;i++){
            result.append(a[random.nextInt(LEN)]);
        }
        return result.toString();
    }
}
