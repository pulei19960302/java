package com.qim.loan.util.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
     * 类名:BarCodeGenerateUtil
     * 描述:	生成二维码 
     * 创建者:冯子文
     * 创建时间: 2016年4月21日 下午2:56:50 
	 * 更新者:冯子文   
	 * 更新时间: 2016年4月21日 下午2:56:50
 */
public class BarCodeUtil {
	
	
	private static Logger logger = LoggerFactory.getLogger(BarCodeUtil.class);

	/**
	 * 
	     * 方法名:createBarCode
	     * 功能描述:创建二维码	 
	     * 创建者:冯子文
	     * 创建时间: 2016年4月21日 下午3:01:16 
		 * 更新者:冯子文   
		 * 更新时间: 2016年4月21日 下午3:01:16
	 */
	public static boolean createBarCode(String filePath,String fileName,String content,BarcodeFormat barFormat,String format,Integer width,Integer height){
		boolean flag=false;
		try {
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content,barFormat, width, height, hints);
			Path path = FileSystems.getDefault().getPath(filePath, fileName);
			MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像// 生成矩阵
			flag=true;
		} catch (Exception e) {
			logger.error("[error]"+"创建条码错误:"+e.getMessage());
			e.printStackTrace();
		}
        return flag;	
	}
	/**
	     * 方法名:genTwoDimensionCode
	     * 功能描述:生成二维码	 
	     * 创建者:冯子文
	     * 创建时间: 2016年4月21日 下午3:11:44 
		 * 更新者:冯子文   
		 * 更新时间: 2016年4月21日 下午3:11:44
	 */
	public static boolean genTwoDimensionCode(String filePath,String fileName,String content,BarcodeFormat barFormat,String format){
		return createBarCode(filePath,fileName,content,barFormat,format,200,200);
	}
	
	/**
	 * 
	     * 方法名:parseBarCode
	     * 功能描述:解析图像信息	 
	     * 创建者:冯子文
	     * 创建时间: 2016年4月21日 下午3:20:39 
		 * 更新者:冯子文   
		 * 更新时间: 2016年4月21日 下午3:20:39
	 */
	public static Result parseBarCode(String filePath){
		Result result=null;
		try {
			BufferedImage image = ImageIO.read(new File(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
		} catch (Exception e) {
			logger.error("[error]"+"解析条码错误:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public static Result parseBarCode(InputStream inputStream){
		Result result=null;
		try {
			BufferedImage image = ImageIO.read(inputStream);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
		} catch (Exception e) {
			logger.error("[error]"+"解析条码错误:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}	
	
	
	public static void main(String[] args) {
//		AccountType xx=new AccountType();
//		xx.setId("122");
//		xx.setRecordDate(1234);
//		System.out.println(GsonUtil.toJson(xx));
	}	
	
}
