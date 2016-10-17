package fastbackup.GetLog;

import fastbackup.SendLog.IPAdress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhaoyue on 16-8-21.
 */
public class MakeDirectory {
    public void makeDir(String address, String message) {

        String finalFileUrl = IPAdress.ServerFileuri + "/" + address;
        File judgerUrl = new File(finalFileUrl);

        if (!judgerUrl.exists()) {
            judgerUrl.mkdir();
        }

        File file=new File(finalFileUrl);
        String [] str=file.list();
        int num=str.length+1;

        String logFile = finalFileUrl +"/"+num+".txt";
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(logFile);
            outputStream.write(message.getBytes(), 0, message.getBytes().length);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
