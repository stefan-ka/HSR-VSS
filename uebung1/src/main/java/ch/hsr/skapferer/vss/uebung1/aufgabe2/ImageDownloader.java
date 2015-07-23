package ch.hsr.skapferer.vss.uebung1.aufgabe2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ImageDownloader {

	public static void downloadImage(String url, String filename) throws Exception {
		try (InputStream is = new URL(url).openStream(); FileOutputStream fos = new FileOutputStream(new File(filename))) {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer, 0, 1024)) > -1) {
				fos.write(buffer, 0, len);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		downloadImage("http://kapferer.ch/img/home-bg.jpg", "/tmp/downloadImage.jpg");
	}

}
