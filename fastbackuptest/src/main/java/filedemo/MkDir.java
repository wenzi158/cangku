package filedemo;

import java.io.*;

/**
 * Created by zhaoyue on 16-8-21.
 */
public class MkDir {
    public void makeDir(String address,String message) {
        String finalFileUrl = FileUrl.fileurl + "/" + address;
        File judgerUrl = new File(finalFileUrl);
        if (!judgerUrl.exists()) {
            judgerUrl.mkdir();
        }
        String logFile=finalFileUrl+"/1.txt";
        OutputStream outputStream=null;
        try {
            outputStream=new FileOutputStream(logFile);
            outputStream.write(message.getBytes(),0,message.getBytes().length);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        MkDir mkDir=new MkDir();
        mkDir.makeDir("192.168.43.179","My Name Is Zhaoyue!");
    }
}
