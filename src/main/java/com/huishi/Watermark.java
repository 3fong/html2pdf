package com.huishi;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 * 水印
 * 描述：文字水印
 * zkh
 * 2018年2月26日 下午12:17:04
 */
@SuppressWarnings({ "unused", "deprecation" })
public class Watermark implements IEventHandler{
	public static final String FONT = "./src/main/resources/font/NotoSansCJKsc-Regular.otf";
	private String title;
	private float x;
	private float y;

	public Watermark(String title, float x, float y) {
		this.title = title;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void handleEvent(Event event) {
		PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
		// 获取Pdf文档
		PdfDocument pdfDocument = documentEvent.getDocument();
		// 获取文档页面
		PdfPage page = documentEvent.getPage();
		// 水印文字的字体
		PdfFont font = null;
		try {
//			font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false); // iText自带中文字体
			font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H,true); // 华文行楷
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDocument);
		new Canvas(canvas, pdfDocument, page.getPageSize())
			.setFontColor(ColorConstants.LIGHT_GRAY)
			.setFontSize(36)
			.setFont(font)
			.showTextAligned(
					new Paragraph().add(title), // new Text(title).setItalic() : 斜体
					x, 
					y,
					pdfDocument.getPageNumber(page), // 当前页在Pdf文档中所在页码
					TextAlignment.CENTER,            // 水平居中
					VerticalAlignment.MIDDLE,        // 垂直居中
					0.48f                            // 角度（弧度角）
			);
		
		/**
		// 图片水印
		try {
			canvas.addImage(ImageDataFactory.create("G:\\文件分类整理计划\\图片\\19.jpg"), page.getPageSize(), false);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		*/
	}
	
}
