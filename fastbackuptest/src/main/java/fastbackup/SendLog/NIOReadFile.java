package fastbackup.SendLog;

import java.io.File;
import java.io.FileInputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by zhaoyue on 16-8-21.
 */
public class NIOReadFile {

    public void ReadFile(String fileName){
        File f = new File(fileName);
        try {
            FileChannel inChannel = new FileInputStream(f).getChannel();

            MappedByteBuffer buffer = inChannel.map(
                    FileChannel.MapMode.READ_ONLY, 0, f.length());

            Charset charset = Charset.forName("utf-8");

            buffer.clear();
            
            CharsetDecoder decoder = charset.newDecoder();

            CharBuffer charBuffer = decoder.decode(buffer);
            System.out.println(charBuffer);
            new Client(charBuffer.toString()).con(IPAdress.Serverip, IPAdress.Serverport);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
