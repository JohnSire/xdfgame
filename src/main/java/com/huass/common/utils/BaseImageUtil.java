package com.huass.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

public class BaseImageUtil 
{
	public boolean GenerateImage(String fName, String imgPath, String uuId) {
		BASE64Decoder decoder = new BASE64Decoder();
		fName = fName.substring(fName.indexOf(";base64,") + 8);
		byte[] b;
		try {
			b = decoder.decodeBuffer(fName);
			OutputStream imgout = new FileOutputStream(imgPath + "/"+uuId + ".jpg");
			imgout.write(b);
			imgout.flush();
			imgout.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
