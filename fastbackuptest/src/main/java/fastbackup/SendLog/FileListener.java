package fastbackup.SendLog;

import net.contentobjects.jnotify.JNotifyListener;

/**
 * Created by zhaoyue on 16-8-21.
 */
public class FileListener implements JNotifyListener {
    public static String name="";

    public void fileRenamed(int wd, String rootPath, String oldName,
                            String newName) {
        //rename和modified是一个坑
        //当我在磁盘里写好一个文件，然后将文件mv进要监听的文件夹的时候，mv修改的是元数据，所以他改变的是数据存储的地址，不是
        //修改的数据，他只触发rename；而我在监听的文件夹里新创建文件的时候，先触发ctrate，再触发modified;
        print("renamed " + rootPath + " : " + oldName + " -> " + newName);
                String finalName=FileListener.name+"/";
        if(!finalName.equals(newName)){
            FileListener.name=newName;
            String fileName = rootPath + "/" + newName;
            NIOReadFile nioReadFile = new NIOReadFile();
            nioReadFile.ReadFile(fileName);

        }
    }

    public void fileModified(int wd, String rootPath, String name) {
        print("modified " + rootPath + " : " + name);
        //如果我不用mv命令将文件放入test时，可以在modified里继续执行操作，不过它会触发多次，设置if判断语句
        //过滤掉能够让它再次触发的名字，只让它创建一个文件
//        String finalName=FileListener.name+"/";
//        if(!finalName.equals(name)&&!name.equals(FileListener.name)){
//            FileListener.name=name;
//            String fileName = rootPath + "/" + name;
//            NIOReadFile nioReadFile = new NIOReadFile();
//            nioReadFile.ReadFile(fileName);
//
//        }

    }

    public void fileDeleted(int wd, String rootPath, String name) {
        print("deleted " + rootPath + " : " + name);
    }

    public void fileCreated(int wd, String rootPath, String name) {
        //touch文件会触发create，mv会触发rename，新建带有数据的文件会先触发create再触发modified
        print("created " + rootPath + " : " + name);
    }

    void print(String msg) {
        System.err.println(msg);
    }
}
