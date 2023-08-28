package com.kkhome.excel.choose;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.io.File;

@Data
public class FileChoose {

    private String path;

    public String doSelect() {
//        int result = 0;
//        File file = null;
//        String path.txt = null;
//        FileChooser fileChooser = new FileChooser();
//        //注意了，这里重要的一句
//        FileSystemView fsv = FileSystemView.getFileSystemView();
//        //得到桌面路径
//        System.out.println(fsv.getHomeDirectory());
//        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
//        fileChooser.setDialogTitle("请选择要上传的文件...");
//        fileChooser.setApproveButtonText("确定");
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        result = fileChooser.showOpenDialog(chatFrame);
//        if (JFileChooser.APPROVE_OPTION == result) {
//            path.txt = fileChooser.getSelectedFile().getPath();
//            System.out.println("path.txt: " + path.txt);
//        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File("E:\\谷歌网页下载"));
        // 保存上一次文件选择的路径
        //String path = PathUtils.read();
        if (StringUtils.isNotEmpty(path)) {
            fileChooser.setCurrentDirectory(new File(path));
        }
        int returnVal = fileChooser.showOpenDialog(fileChooser);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //这个就是你选择的文件夹的路径
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            //PathUtils.write(filePath);
            return filePath;
        }
        return "";
    }
}