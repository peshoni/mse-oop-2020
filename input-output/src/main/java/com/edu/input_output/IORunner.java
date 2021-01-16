/**
 * 
 */
package com.edu.input_output;

import com.edu.downloader.FileDownloader;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class IORunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// InputStreamReader inpo = new InputStreamReader(System.in);
//BufferedInputStream isr = new BufferedInputStream(System.in);
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		try {
//			System.out.println(br.readLine());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		FileDownloader.download("https://translate.google.bg/", "F:\\exampledownloader.txt");

	}

}
