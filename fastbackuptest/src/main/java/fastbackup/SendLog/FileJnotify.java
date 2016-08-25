package fastbackup.SendLog;

import net.contentobjects.jnotify.JNotify;


/**
 * Created by zhaoyue on 16-8-21.
 */
public class FileJnotify {
    public void sample() throws Exception {

        String path= IPAdress.Fileuri;

        int mask = JNotify.FILE_CREATED  |
                JNotify.FILE_DELETED  |
                JNotify.FILE_MODIFIED |
                JNotify.FILE_RENAMED;

        boolean watchSubtree = true;

        int watchID = JNotify.addWatch(path, mask, watchSubtree, new FileListener());

        Thread.sleep(1000000);

        boolean res = JNotify.removeWatch(watchID);
        if (!res) {

        }
    }

    public static void main(String[] args) {
        FileJnotify nameNodeJnotify=new FileJnotify();
        try {
            nameNodeJnotify.sample();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
