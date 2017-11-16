package com.sidc.tester.api;

import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

import org.apache.http.entity.StringEntity;
import org.junit.Test;

public class printerTest {

	@Test
	public void test3() throws IOException {
		StringEntity entity = new StringEntity("中文", "UTF-8");
		
		
		System.out.println(entity);

	}

	@Test
	public void test() throws IOException {
		FileInputStream fiStream = null;
		try {
			fiStream = new FileInputStream("C:\\auth.txt");
		} catch (FileNotFoundException ffne) {
		}
		if (fiStream == null) {
			System.out.println("wer");
			return;
		}

		PrintService[] service = PrinterJob.lookupPrintServices();

		for (PrintService entity : service) {
			if (entity.getName().equals("SHARP MX-2010U")) {
				DocPrintJob docPrintJob = entity.createPrintJob();

				DocAttributeSet das = new HashDocAttributeSet();

				DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_8;
				Doc doc = new SimpleDoc(fiStream, flavor, das);

				PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
				aset.add(MediaSizeName.ISO_A4);
				// aset.add(new Copies(1));

				try {

					docPrintJob.print(doc, aset);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Test
	public void test2() throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("D:\\b.html"));
		String str = "";
		String line;
		while ((line = br.readLine()) != null) {
			str += line + "\n";
		}
		br.close();
		str = str.replace("${roomNo}", "608");
		// System.out.println(str);

		InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));

		br = new BufferedReader(new InputStreamReader(is));

		String line2 = "";
		while ((line = br.readLine()) != null) {
			line2 += line + "\n";
		}
		String test = line2.substring(line2.indexOf("#{each}"), line2.indexOf("#{/each}") + 8);

		line2 = line2.replace(test, "{qqqqqqqqqq}");

		System.out.println(line2);
	}

	// convert InputStream to String
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
