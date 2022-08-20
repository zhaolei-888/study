package com.example.kafkademo;

import com.google.common.collect.Lists;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.util.List;

/**
 * 读一个文件夹所有文件，并解析标题添加至第一行，
 * 然后将所有文本合并成一个文件
 */
public class ReadFileTest {
    public static void main(String[] args) throws IOException {
        List<String> chapterNames = readFileLineChapterNames();
        List<File> files = getFiles("D:\\dir");
        StringBuffer totalStr = new StringBuffer();
        for (int i = 0; i < files.size(); i++) {
            //需要保证元素数量对应
            if (chapterNames.size() <= files.size()) {
                totalStr.append(writeFirstRowContent(chapterNames.get(i) + "\n", files.get(i)) + "\n");
            }
        }
        //将文件内容合并为一个文件
        File newFile = new File("D:\\newFile.txt");
        FileWriter fw = null;
        try {
            if(!newFile.exists()){
                System.out.println("不存在");
                newFile.createNewFile();
            }
            fw = new FileWriter(newFile);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(totalStr.toString());
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            fw.close();
        }
        System.out.println("文件操作成功！");
    }

    public static void s(String s){
        s  = "2";
    }

    /**
     * 给文件第一行写内容
     *
     * @param content 内容
     * @param file    文件
     */
    public static String writeFirstRowContent(String content, File file) throws IOException {
        if (file == null && !file.exists()) {
            return null;
        }
        StringBuffer totalStr = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
//        String line = null;
//        while ((line = bufferedReader.readLine()) != null) {
//            totalStr.append(line);
////            System.out.println(line);
//        }
        int tempchar;
        while ((tempchar = bufferedReader.read()) != -1) {
            totalStr.append((char) tempchar);
        }
        //关闭读入缓冲
        bufferedReader.close();
        String totalContent = content + totalStr;
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, "UTF-8");
        // 获取该文件的缓冲输出流
        BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
        // 写入信息
        bufferedWriter.write(totalContent);
        // 清空缓冲区
        bufferedWriter.flush();
        // 关闭输出流
        outputStream.close();
        streamWriter.close();
        bufferedWriter.close();
        System.out.println(totalContent);
        return totalContent;
    }


    /**
     * 获取文件夹下所有文件
     *
     * @param path
     * @return
     */
    public static List<File> getFiles(String path) {
        File root = new File(path);
        List<File> files = Lists.newArrayList();
        if (!root.isDirectory()) {
            files.add(root);
        } else {
            File[] subFiles = root.listFiles();
            for (File f : subFiles) {
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }


    /**
     * 获取每行章节名称
     *
     * @return
     * @throws IOException
     */
    public static List<String> readFileLineChapterNames() throws IOException {
        List<String> chapterNames = Lists.newArrayList();
        File file = new File("D:\\list.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuffer totalStr = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            totalStr.append(line);
//            System.out.println(line);
        }
        String name = null;
        String[] split = totalStr.toString().split("\\},\\{");
        for (String str : split) {
            if (StringUtils.isNotBlank(str)) {
                int start = str.indexOf("name\":");
                int end = str.indexOf(",", start);
                name = str.substring(start + 7, end - 1);
//                name = name.replaceAll("\"chapter_name\":", "");
//                name = name.replaceAll("\"", "");
                System.out.println(name);
                chapterNames.add(name);
            }
        }
        br.close();
        fis.close();
        return chapterNames;
    }
}
