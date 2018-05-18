package com.huishi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;

/**
 * 利用HTML代码片段生成PDF
 *
 * zkh 2018年3月13日 下午2:05:17
 */
public class HtmlToPdf {
	private final static String DEST = "./target/desc/备案申请书.pdf";
	private final static String SRC = "./src/main/resources/template/备案申请书.html";
	public static final String FONT = "./src/main/resources/font/NotoSansCJKsc-Regular.otf";

	public void tomPdf(String html, String DEST) throws FileNotFoundException, IOException {
		ConverterProperties props = new ConverterProperties();
		DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, false, false);
		defaultFontProvider.addFont(FONT);
		props.setFontProvider(defaultFontProvider);
		PdfWriter writer = new PdfWriter(DEST);
		PdfDocument pdf = new PdfDocument(writer);
		pdf.setDefaultPageSize(new PageSize(595, 14400));
		Document document = HtmlConverter.convertToDocument(new FileInputStream(html), pdf, props);
		// 将所有内容在一个页面显示
		EndPosition endPosition = new EndPosition();
		LineSeparator separator = new LineSeparator(endPosition);
		document.add(separator);
		document.getRenderer().close();
		PdfPage page = pdf.getPage(1);
		float y = endPosition.getY() - 36;
		page.setMediaBox(new Rectangle(0, y, 595, 14400 - y));
		document.close();
		pdf.close();
	}

	public static void main(String[] args) {
		try {
			HtmlToPdf html = new HtmlToPdf();
			html.tomPdf(SRC, DEST);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class EndPosition implements ILineDrawer {

		/** A Y-position. */
		protected float y;

		/**
		 * Gets the Y-position.
		 *
		 * @return the Y-position
		 */
		public float getY() {
			return y;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#draw(com.itextpdf.kernel.pdf.
		 * canvas.PdfCanvas, com.itextpdf.kernel.geom.Rectangle)
		 */
		@Override
		public void draw(PdfCanvas pdfCanvas, Rectangle rect) {
			this.y = rect.getY();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#getColor()
		 */
		@Override
		public Color getColor() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#getLineWidth()
		 */
		@Override
		public float getLineWidth() {
			return 0;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#setColor(com.itextpdf.kernel.
		 * color.Color)
		 */
		@Override
		public void setColor(Color color) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#setLineWidth(float)
		 */
		@Override
		public void setLineWidth(float lineWidth) {
		}

	}
}
