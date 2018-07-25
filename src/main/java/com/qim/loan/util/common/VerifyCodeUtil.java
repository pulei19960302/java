package com.qim.loan.util.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OK
     * 类名:VerifyCodeUtils
     * 描述:验证码工具	 
     * 创建者:冯子文
     * 创建时间: 2017年6月28日 下午1:47:52 
	 * 更新者:冯子文   
	 * 更新时间: 2017年6月28日 下午1:47:52
 */
public class VerifyCodeUtil {
	
	private static Logger logger = LoggerFactory.getLogger(VerifyCodeUtil.class);
	
	private static void createGraphy(BufferedImage image,int w, int h,String code){
		code=code.trim();
		Color[] colorArray = new Color[] {Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
				Color.PINK, Color.YELLOW ,Color.RED,Color.PINK,Color.GREEN,Color.DARK_GRAY,Color.BLUE,Color.BLACK};
		Random random = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		int fontSize = h - 4;
		Font font = new Font("Algerian", Font.ITALIC, fontSize);
		g2.setColor(Color.WHITE);// 设置边框色
		g2.setFont(font);
		g2.fillRect(0, 0, w, h);//设置矩形
		int verifySize = code.length();
		for (int i = 0; i < verifySize; i++){
			g2.setColor(colorArray[random.nextInt(colorArray.length)]);
			g2.drawString(String.valueOf(code.charAt(i)),((w - 10) / verifySize) * i + 5, h / 2+ fontSize / 2-5);
		}
		//设置边框
		g2.setColor(new Color(255, 255, 255));     
		g2.drawRect(0, 0, w - 1, h - 1);  
		g2.drawRect(1, 1, w - 1, h - 1);  
		g2.drawRect(0, 0, w - 2, h - 2); 
		g2.dispose();
	}
	
	public static void outputImage(int w, int h, OutputStream os, String code){
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
		createGraphy(image, w,h,code);
		try {
			ImageIO.write(image, "jpg", os);
		} catch (IOException e) {
			MessageUtil.error(logger, "输出图片", e);			
			e.printStackTrace();
		}
	}


	 public static void inputStreamToFile(InputStream ins,File file){  
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;  
			byte[] buffer = new byte[8192];  
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {  
		      os.write(buffer, 0, bytesRead);  
			}			
		} catch (IOException e) {
			MessageUtil.error(logger, "输出图片", e);	
			e.printStackTrace();
		}finally{
			if(os!=null)
			try {
				os.close();
			} catch (IOException e) {
				MessageUtil.error(logger, "关闭输出流", e);
				e.printStackTrace();
			}
			try {
				ins.close();
			} catch (IOException e) {
				MessageUtil.error(logger, "关闭输入流", e);
				e.printStackTrace();
			}
		}    
	} 
	public static void main(String[] args) throws IOException {
 
	}

}
