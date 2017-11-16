package com.sidc.sits.logical.room;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

import org.junit.Test;

import com.sidc.blackcore.api.sits.type.PrintType;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.utils.encrypt.AesEncrypt;
import com.sidc.utils.exception.SiDCException;

public class printTest {

	@Test
	public void test() throws UnsupportedEncodingException, SiDCException, Exception {
		String[] printerName = { "EPSON TM-U220 Receipt" };
		String a = "寒沐";
		//
		HttpClientUtils.sendPostWithPrint("http://61.221.43.160/sits", printerName, new String(a.getBytes()),
				signature(printerName[0]));

	}

	public static String toUtf8(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("UTF-8"), "UTF-8");
	}

	@Test
	public void test2() throws SQLException, SiDCException, PrinterException {
		JEditorPane editorpane = new JEditorPane();
		String text = "Sidc printer test 中文測試 \n heheiehihoi";
		editorpane.setText(text);

		PrinterJob pntJob = PrinterJob.getPrinterJob();
		PrintService[] service = PrinterJob.lookupPrintServices();
		pntJob.setPrintService(service[0]);

		pntJob.setPrintable(new Print2Test(editorpane)); // 指定由PrintTest這個Class的Instance來做列印的事
		try {
			pntJob.print(); // 開始列印
		} catch (PrinterException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void test3() throws Exception {
		JEditorPane editorpane = new JEditorPane();

		String text = "Sidc printer test";

		// editorpane.setPage(file.toURI().toURL());
		editorpane.setText(text);

		// JEditorPane view = new JEditorPane("https://www.google.com.tw/");

		PrinterJob job = PrinterJob.getPrinterJob();

		PrintService[] service = PrinterJob.lookupPrintServices();
		job.setPrintService(service[0]);

		job.setPrintable(new ComponentPrinter(editorpane));
		System.out.println("預設印表機-開始列印程序....");

		// job.print();

		JTextPane jtp = new JTextPane();
		jtp.setBackground(Color.white);
		jtp.setText(
				"text to print 預設印表機-開始列印程序....text to print 預設印表機-開始列印程序....text t印表機-開始列印程序....text to print 預設印表機-開始列印程序....");

		jtp.setAlignmentX(50);
		jtp.setAlignmentY(150);
		boolean show = true;
		try {

			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			pras.add(Chromaticity.MONOCHROME);
			// pras.add(MediaSizeName.ISO_A4);
			// pras.add(OrientationRequested.LANDSCAPE);

			jtp.print(null, null, show, service[0], pras, show);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private String signature(final String data) throws Exception {
		final AesEncrypt encrypt = new AesEncrypt("sidc-sits");
		return encrypt.encrypt(data).trim();
	}

}

class Print2Test implements Printable {

	public Print2Test() {

	}

	private Component component;

	public Print2Test(Component component) {
		this.component = component;
	}

	// 這裡是列印的主體
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {

		if (pageIndex >= 1) {
			return Printable.NO_SUCH_PAGE; // 不再繼續印
		}

		Dimension prefSize = component.getPreferredSize();
		component.setSize(prefSize);
		component.validate();

		Graphics2D g2d = (Graphics2D) graphics;

		// 設定列印的原點在可列印區域的左上角
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		// 設定用黑色印，且用標楷體12點的字來印
		g2d.setPaint(Color.black);
		Font myFont = new Font(Font.DIALOG, Font.PLAIN, 18);
		g2d.setFont(myFont);

		// 在相對原點的(x,y)=(10,10)處印字串
		// g2d.drawString("這是Testing", 10, 10);
		component.printAll(g2d);
		return Printable.PAGE_EXISTS;
	}
}

class ComponentPrinter implements Printable {
	private Component component;

	public ComponentPrinter(Component component) {
		this.component = component;
	}

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex == 0) {
			Dimension prefSize = component.getPreferredSize();
			component.setSize(prefSize);
			component.validate();
			double ratio = pageFormat.getImageableWidth() / prefSize.width;

			Graphics2D g2d = (Graphics2D) graphics;
			// translate origin to imageable origin
			g2d.transform(AffineTransform.getTranslateInstance(pageFormat.getImageableX(), pageFormat.getImageableY()));
			// scale output
			g2d.transform(AffineTransform.getScaleInstance(ratio, ratio));

			component.printAll(g2d);
			return PAGE_EXISTS;
		}
		return NO_SUCH_PAGE;
	}
}
