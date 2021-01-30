package com.mse.oop.downloader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

public class FileDownloader {

	public static String download(String url, String path) {
		InputStream openStream = null;
		BufferedReader br = null;
		BufferedWriter bwr = null;
		try {
			URL urlToDownload = new URL(url);

			openStream = urlToDownload.openStream();

			br = new BufferedReader(new InputStreamReader(openStream));

			bwr = new BufferedWriter(new FileWriter(new File(path)));
			String line = "";
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				bwr.write(line);
			}

		} catch (MalformedInputException e) {

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				openStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bwr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
