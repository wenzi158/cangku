package fastbackup.SendLog;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sun.invoke.empty.Empty;

import java.io.File;

/**
 * Created by Li on 2016/6/23.
 */
public class IPAdress {
//    public static  String Clientip="192.168.43.27";
//    public static String Serverip="192.168.43.157";
//    public static int Serverport=8888;
    public static  String Clientip=getClientip(test());
    public static String Serverip=getServerip(test());
    public static int Serverport=getServerport(test());
    public static String Fileuri=getFileUri(test());
    public static String ServerFileuri=getServerFileUri(test());

    public static Element test(){
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File("src/servercon.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element node = document.getRootElement();
        return node ;
    }

    public static String getClientip(Element node){
        Element element = node.element("httpclientip");
        Element newElement = element.element("ip");
        String ip= newElement.getText();
        return ip;
    }

    public static String getServerip(Element node){
        Element element = node.element("httpserverip");
        Element newElement = element.element("ip");
        String ip= newElement.getText();
        return ip;
    }

    public static int getServerport(Element node){
        test();
        Element element = node.element("httpserverip");
        Element newElement = element.element("port");
        int port= Integer.parseInt(newElement.getText());
        return port;
    }

    public static String getFileUri(Element node){
        Element element = node.element("fileuri");
        Element newElement = element.element("clientfile");
        String fileUri=newElement.getText();
        return fileUri;
    }
    public static String getServerFileUri(Element node){
        Element element = node.element("fileuri");
        Element newElement = element.element("serverfile");
        String fileUri=newElement.getText();
        return fileUri;
    }
}
