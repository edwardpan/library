/* DwindlePic.java
 * project: EnvirLims
 */
package net.firecoder.utils;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 缩略图类，jpg图片文件，进行等比或非等比的大小转换。
 * s_pic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
 * @author 潘超（源自互联网）
 * create: 2011-9-19
 */
public class DwindlePicture {

	private String InputDir; //输入图路径
	private String OutputDir; //输出图路径
	private String InputFileName; //输入图文件名
	private String OutputFileName; //输出图文件名
	private int OutputWidth = 80; //默认输出图片宽
	private int OutputHeight = 80; //默认输出图片高
	private int rate = 0;
	private boolean proportion = true; //是否等比缩放标记(默认为等比缩放)

	public DwindlePicture() {
		//初始化变量
		InputDir = "";
		OutputDir = "";
		InputFileName = "";
		OutputFileName = "";
		OutputWidth = 80;
		OutputHeight = 80;
		rate = 0;
	}

	public boolean dwindle() {
		if (!OutputDir.endsWith("/") && !OutputDir.endsWith("\\")) {
			OutputDir += "/";
		}
		if (!InputDir.endsWith("/") && !InputDir.endsWith("\\")) {
			InputDir += "/";
		}
		
		//        BufferedImage image;
		//        String NewFileName;
		//建立输出文件对象
		File file = new File(OutputDir + OutputFileName);
		FileOutputStream tempout = null;
		try {
			tempout = new FileOutputStream(file);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		Image img = null;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Applet app = new Applet();
		MediaTracker mt = new MediaTracker(app);
		try {
			img = tk.getImage(InputDir + InputFileName);
			mt.addImage(img, 0);
			mt.waitForID(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (img.getWidth(null) == -1) {
			//            System.out.println(" can't read,retry!" + "<BR>");
			return false;
		} else {
			int new_w;
			int new_h;
			if (this.proportion == true) { //判断是否是等比缩放.
				//为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) img.getWidth(null)) / 
					(double) OutputWidth + 0.1;
				double rate2 = ((double) img.getHeight(null)) / 
					(double) OutputHeight + 0.1;
				double rate = rate1 > rate2 ? rate1 : rate2;
				new_w = (int) (((double) img.getWidth(null)) / rate);
				new_h = (int) (((double) img.getHeight(null)) / rate);
			} else {
				new_w = OutputWidth; //输出的图片宽度
				new_h = OutputHeight; //输出的图片高度
			}
			BufferedImage buffImg = new BufferedImage(new_w, new_h,
					BufferedImage.TYPE_INT_RGB);

			Graphics g = buffImg.createGraphics();

			g.setColor(Color.white);
			g.fillRect(0, 0, new_w, new_h);

			g.drawImage(img, 0, 0, new_w, new_h, null);
			g.dispose();

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tempout);
			try {
				encoder.encode(buffImg);
				tempout.close();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}
		}
		return true;
	}

	public boolean dwindle(String InputDir, String OutputDir, String InputFileName,
			String OutputFileName) {
		//输入图路径
		this.InputDir = InputDir;
		//输出图路径
		this.OutputDir = OutputDir;
		//输入图文件名
		this.InputFileName = InputFileName;
		//输出图文件名
		this.OutputFileName = OutputFileName;
		return dwindle();
	}

	public boolean dwindle(String InputDir, String OutputDir, String InputFileName,
			String OutputFileName, int width, int height, boolean gp) {
		//输入图路径
		this.InputDir = InputDir;
		//输出图路径
		this.OutputDir = OutputDir;
		//输入图文件名
		this.InputFileName = InputFileName;
		//输出图文件名
		this.OutputFileName = OutputFileName;
		//设置图片长宽
		setW_H(width, height);
		//是否是等比缩放 标记
		this.proportion = gp;
		return dwindle();
	}

	public void setInputDir(String InputDir) {
		this.InputDir = InputDir;
	}
	public void setOutputDir(String OutputDir) {
		this.OutputDir = OutputDir;
	}
	public void setInputFileName(String InputFileName) {
		this.InputFileName = InputFileName;
	}
	public void setOutputFileName(String OutputFileName) {
		this.OutputFileName = OutputFileName;
	}
	public void setOutputWidth(int OutputWidth) {
		this.OutputWidth = OutputWidth;
	}
	public void setOutputHeight(int OutputHeight) {
		this.OutputHeight = OutputHeight;
	}
	public void setW_H(int width, int height) {
		this.OutputWidth = width;
		this.OutputHeight = height;
	}
}