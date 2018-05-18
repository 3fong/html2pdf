package com.huishi;

import java.io.File;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 * 测试
 * 描述：含表格、二维码、水印的测试
 * zkh
 * 2018年2月12日 下午2:08:26
 */
public class ZTest {

	private final static String DEST = "./target/desc/test.pdf";
	
	public static void main(String[] args) throws Exception {
		/**1、检查pdf存放路径（如果所给路径中的部分文件夹不存在，则自动创建）*/
		new File(DEST).getParentFile().mkdirs();
		/**2、创建PDF文档文件*/
		// 2.1、创建加密的Pdf文档
		PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
		// 2.2、通过Pdf文档创建Document文档
		Document document = new Document(pdf, PageSize.A4);
		/**3、添加水印*/
		pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new Watermark("这 就 是 一 行 水 印 文 字", 175, 705));
		pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new Watermark("这 是 一 行 水 印 文 字", 440,715));
		pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new Watermark("这 就 是 一 行 水 印 文 字", 175, 425));
		pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new Watermark("这 是 一 行 水 印 文 字", 440,435));
		pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new Watermark("这 就 是 一 行 水 印 文 字", 175, 145));
		pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new Watermark("这 是 一 行 水 印 文 字", 440,155));
		/**4、获得中文字体*/
		PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
		/**5、生成二维码图片（下面的表格中使用）*/
        Image image = QRCode.getQRCodeImage(pdf, "{\"id\":\"467046859098fh8try0h8t\",\"name\":\"tom\",\"url\":\"http://edu.sstrc.gov.cn/cepsp\"}", 80, 80);
        /**6、创建表格（一行七列）*/  
        Table table = new Table(new float[] { 10, 10, 10, 20, 10, 20, 20 }) // 每列宽度（按百分比）
        		.setWidth(UnitValue.createPercentValue(100))     // 宽度（百分比，也可以设置数值）
        		.setHeight(UnitValue.createPercentValue(100))    // 高度（百分比，也可以设置数值） 
        		.setFont(font)                                   // 设置字体
        		.setFontSize(10)                                 // 文字大小
        		.setTextAlignment(TextAlignment.CENTER)          // 水平居中
        		.setVerticalAlignment(VerticalAlignment.MIDDLE); // 垂直居中
        /**7、写入数据到表格*/
        Cell cell = null;
        // 7.1、第一行
        cell = new Cell()
        		.add(new Paragraph("姓      名"))
        		.setWidth(UnitValue.createPercentValue(10))
        		.setBold()
        		.setHeight(24)
		        .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("小明"))
        		.setWidth(UnitValue.createPercentValue(10))
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("身份证号"))
        		.setWidth(UnitValue.createPercentValue(10))
        		.setBold()
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("6103526198106160889"))
        		.setWidth(UnitValue.createPercentValue(20))
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("这是证书编号").setFixedLeading(10)) // 设置行间距
        		.setWidth(UnitValue.createPercentValue(10))
        		.setBold()
		        .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("6100004002238"))
        		.setWidth(UnitValue.createPercentValue(20))
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        //表格列合并"4"代表合并4列  
        cell = new Cell(3, 1)
        		.add(image)
        		.setWidth(UnitValue.createPercentValue(20))
        		.setPaddingLeft(12)
        		.setBorderBottom(Border.NO_BORDER)
		        .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);  
        
        // 7.2、第二行
        cell = new Cell()
        		.add(new Paragraph("工作单位"))
        		.setBold()
        		.setHeight(24)
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell(1, 3)
        		.add(new Paragraph("陕西西安"))
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("职      称"))
        		.setBold()
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("研究实习生"))
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        // 7.3、第三行
        cell = new Cell()
        		.add(new Paragraph("科目名称"))
        		.setBold()
        		.setHeight(24)
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell(1, 3)
        		.add(new Paragraph("计算机软件"))
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        cell = new Cell()
        		.add(new Paragraph("培训基地"))
        		.setBold()
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("高新技术产业园").setFixedLeading(10)) // 设置行间距
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        // 7.4、第四行
        cell = new Cell()
        		.add(new Paragraph("学习时长"))
        		.setBold()
        		.setHeight(24)
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("24小时"))
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("考核结果"))
        		.setBold()
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("合格(85分)"))
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("科目属性"))
        		.setBold()
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("面授公需"))
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        cell = new Cell()
        		.add(new Paragraph("2017/05/04\n扫码查询成绩").setFontSize(9).setFixedLeading(10))
        		.setBorderTop(Border.NO_BORDER)
        		.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        
        document.add(table);
        
		// 关闭Document、PdfDocument
		pdf.close();
		document.close();
	}
	
}
