package com.qim.loan.util.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
     * 类名:FileOperateUtil
     * 描述:文件操作类	 
     * 创建者:冯子文
     * 创建时间: 2016年4月21日 上午9:39:08 
	 * 更新者:冯子文   
	 * 更新时间: 2016年4月21日 上午9:39:08
 */
public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);	
	/**
	 * 
	     * 方法名:isExists
	     * 功能描述:文件是否存在	 
	     * 创建者:冯子文
	     * 创建时间: 2017年4月17日 上午10:37:52 
		 * 更新者:冯子文  
		 * 更新时间: 2017年4月17日 上午10:37:52
	 */
	public  static boolean isExists(String path){
	    File file = new File(path);
	    if (file.exists()) 
	    	return true;
	    return false;
	}
	
	
	/**
	     * 方法名:createFile
	     * 功能描述:创建文件 
	     * 创建者:冯子文
	     * 创建时间: 2016年4月21日 上午9:38:45 
		 * 更新者:冯子文   
		 * 更新时间: 2016年4月21日 上午9:38:45
	 */
	public static boolean createFile(String path) {
		boolean flag=false;
		File file = new File(path);
		try {
		    if (!file.exists()) 
			   file.createNewFile();
			flag=true;
		} catch (Exception e) {
			MessageUtil.error(logger, "创建文件", e);
		}
		return flag;
	}
	
	public static boolean createFile(String path, String fileContent) {	     
		return createFile(path,fileContent,"UTF-8");
    }
	public static boolean createFile(String path, String fileContent, String encoding) {
		boolean flag=false;
		File myFilePath = new File(path);
        try {
        	createFile(path);
            PrintWriter myFile = new PrintWriter(myFilePath,encoding);
            String strContent = fileContent;
            myFile.println(strContent);
            myFile.flush();
            myFile.close();
            flag=true;
        }catch (Exception e) {
        	MessageUtil.error(logger, "创建文件操作", e);
        }
        return flag;
    }	
	/**
	     * 方法名:createDirectory
	     * 功能描述:创建文件夹	 
	     * 创建者:冯子文
	     * 创建时间: 2016年4月21日 上午9:38:37 
		 * 更新者:冯子文   
		 * 更新时间: 2016年4月21日 上午9:38:37
	 */
	public static boolean createDir(String path) {
		boolean flag=false;
		File file = new File(path);
		try {
			if (!file.exists()) 
				file.mkdirs();
			flag=true;
		} catch (Exception e) {
			MessageUtil.error(logger, "创建文件夹", e);
		}
		return flag;
	}	

	/**
	 * 
	     * 方法名:deleteFile
	     * 功能描述:删除空目录	 
	     * 创建者:冯子文
	     * 创建时间: 2016年4月21日 上午9:46:27 
		 * 更新者:冯子文   
		 * 更新时间: 2016年4月21日 上午9:46:27
	 */
	public static boolean deleteFile(String path) {
		boolean flag=false;
		File file = new File(path);
		try {
	        if (file.exists()) 
	        	flag =file.delete();
		} catch (Exception e) {
			MessageUtil.error(logger, "删除文件夹", e);
		}
		return flag;
    }	
	/**
	     * 方法名:deleteRecursionDir
	     * 功能描述:递归删除目录下的所有文件及子目录下所有文件	 
	     * 创建者:冯子文
	     * 创建时间: 2016年4月21日 下午1:45:31 
		 * 更新者:冯子文   
		 * 更新时间: 2016年4月21日 下午1:45:31
	 */
	public static boolean deleteRecurDir(File dir) {
        if (dir.isDirectory()) {//文件夹
            String[] children = dir.list();
            if(children!=null && children.length>0)
            for (int i=0; i<children.length; i++) {
                boolean success = deleteRecurDir(new File(dir, children[i]));
                if (!success) 
                    return false;
            }
        }
        return dir.delete();
    }
	
	/**
	     * 方法名:copyFile
	     * 功能描述:复制单个文件	 
	     * 创建者:冯子文
	     * 创建时间: 2016年4月21日 下午1:54:58 
		 * 更新者:冯子文   
		 * 更新时间: 2016年4月21日 下午1:54:58
	 */
	public static boolean copySingleFile(String oldPath, String newPath) {
    	boolean flag=false;
		createFile(newPath);
        File oldfile = new File(oldPath);
    	try {
            if (oldfile.exists())  //文件存在时
                return copyFile(oldPath,newPath);
        }catch (Exception e) {
        	MessageUtil.error(logger, "复制单个文件操作", e);
        }
    	return flag;
    }
    
    private static boolean copyFile(String oldPath, String newPath){
    	try {
	    	int bytesum = 0;
	        int byteread = 0;
	    	InputStream inStream = new FileInputStream(oldPath); //读入原文件
	        FileOutputStream fs = new FileOutputStream(newPath);
	        byte[] buffer = new byte[1024];
	        while((byteread = inStream.read(buffer)) != -1){
	            bytesum += byteread; //字节数 文件大小
	            System.out.println(bytesum);
	            fs.write(buffer, 0, byteread);
	        }
	        fs.flush();
	        fs.close();
	        inStream.close();
	        return true;
        }catch (Exception e) {
        	MessageUtil.error(logger, "复制文件内容操作", e);
        	return false;
        }
    }
    
    
    /**
         * 方法名:copyFolder
         * 功能描述:复制整个文件夹的内容	 
         * 创建者:冯子文
         * 创建时间: 2016年4月21日 下午1:58:03 
    	 * 更新者:冯子文   
    	 * 更新时间: 2016年4月21日 下午1:58:03
     */
    public static void copyDir(String oldPath, String newPath) {
    	File a=new File(oldPath);
    	createDir(newPath);
    	try {
            String[] file=a.list();
            File temp=null;
            if(file!=null && file.length>0)
            for (int i = 0; i < file.length; i++) {
                if(oldPath.endsWith(File.separator)){
                    temp=new File(oldPath+file[i]);
                }else{
                    temp=new File(oldPath+File.separator+file[i]);
                }
                if(temp.isFile())//文件
                	copyFile(oldPath+file[i],newPath + "/" +(temp.getName()).toString());
                if(temp.isDirectory())//如果是子文件夹
                    copyDir(oldPath+"/"+file[i],newPath+"/"+file[i]);
            }
        }catch (Exception e) {
        	MessageUtil.error(logger, "复制整个文件夹内容操作", e);
        }
    }
    /**
         * 方法名:moveFile
         * 功能描述:移动文件	 
         * 创建者:冯子文
         * 创建时间: 2016年4月21日 下午2:00:31 
    	 * 更新者:冯子文   
    	 * 更新时间: 2016年4月21日 下午2:00:31
     */
    public static void moveFile(String oldPath, String newPath) {
    	copySingleFile(oldPath, newPath);
    	deleteFile(oldPath);
    }
    /**
     * 
         * 方法名:moveDirectory
         * 功能描述:移动目录	 
         * 创建者:冯子文
         * 创建时间: 2016年4月21日 下午2:03:38 
    	 * 更新者:冯子文   
    	 * 更新时间: 2016年4月21日 下午2:03:38
     */
    public void moveDir(String oldPath, String newPath) {
    	copyDir(oldPath, newPath);
        deleteFile(oldPath);
    }
 	
	
	/**
	     * 方法名:write
	     * 功能描述:写文件	 
	     * 创建者:冯子文
	     * 创建时间: 2016年4月21日 下午2:05:01 
		 * 更新者:冯子文   
		 * 更新时间: 2016年4月21日 下午2:05:01
	 */
	
    public static void write(String path, String content){
    	write(path,content,"UTF-8");
    }
    
	public static void write(String path, String content, String encoding){  
		File file = new File(path);  
        file.delete();
        createFile(path);	
		try {  
	        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));  
	        writer.write(content);
	        writer.flush();
	        writer.close();
        }catch (Exception e) {
        	MessageUtil.error(logger, "写文件操作", e);
        }        
    }  
    /**
         * 方法名:
         * 功能描述:	 
         * 创建者:冯子文
         * 创建时间: 2016年4月21日 下午2:05:18 
    	 * 更新者:冯子文   
    	 * 更新时间: 2016年4月21日 下午2:05:18
     */	
	public static String read(String path){  
		return read( path,"\r\n","UTF-8");
	}	
	
	public static String read(String path,String separator){  
		return read( path,separator,"UTF-8");
	}
	
    public static String read(String path,String separator, String encoding){  
    	StringBuffer content = new StringBuffer();  
        try {
	        File file = new File(path);  
	        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));  
	        String line = null;  
	        while ((line = reader.readLine()) != null)  
	            content.append(line+separator);  
	        reader.close();
        }catch (Exception e) {
        	MessageUtil.error(logger, "读文件操作", e);
        }         
        return content.toString();  
    }  
  
    /**
     * 
         * 方法名:appendContent
         * 功能描述:添加至文件最后	 
         * 创建者:冯子文
         * 创建时间: 2016年4月21日 下午2:13:18 
    	 * 更新者:冯子文   
    	 * 更新时间: 2016年4月21日 下午2:13:18
     */
    public static void appendContent(String fileName, String content) {
    	try {
	    	// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
	    	FileWriter writer = new FileWriter(fileName, true);
	    	writer.write(content);
	    	writer.flush();
	    	writer.close();
    	} catch (IOException e) {
    		MessageUtil.error(logger, "追加文件操作", e);
    	}
    }
    
    public static void appendContentByStream(String fileName, String content) {
    	try {
    	    // 打开一个随机访问文件流，按读写方式
    	    RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
    	    // 文件长度，字节数
    	    long fileLength = randomFile.length();
    	    // 将写文件指针移到文件尾。
    	    randomFile.seek(fileLength);
    	    randomFile.writeBytes(content);
    	    randomFile.close();
    	} catch (IOException e) {
    		MessageUtil.error(logger, "通过流的方式追加文件操作", e);
    	}
     } 

	public static void getFiles(File root,List<File> list){
		if(list==null || root==null)
			return;
		File[] subFiles = root.listFiles();
		if(subFiles!=null)
		for(File tmp : subFiles)
			if(tmp.isDirectory()){//文档
				getFiles(tmp,list);
			}else{
				list.add(tmp);
			}
	}
	public static List<File> getFileList(String path){
	    File root = new File(path);
	    List<File> files = new ArrayList<File>();
	    if(!root.isDirectory()){
	        files.add(root);
	    }else{
	        File[] subFiles = root.listFiles();
	        if(subFiles!=null)
	        for(File f : subFiles)
	            files.addAll(getFileList(f.getAbsolutePath()));    
	    }
	    return files;
	}
    
    
    public static void main(String[] args) throws IOException {  
     
    }  
}
