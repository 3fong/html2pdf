package com.huishi;

import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Image;

/**
 * 二维码
 *
 * zkh
 * 2018年2月26日 下午3:55:14
 */
public class QRCode {

	/**
	 * 生成二维码图片
	 * 描述：黑色
	 * @param pdf
	 * @param content  例："{\"id\":\"467046859098fh8try0h8t\",\"name\":\"小明\"}"
	 * @param width
	 * @param height
	 * @return
	 */
	public static Image getQRCodeImage(PdfDocument pdf, String content, int width, int height) {
		// 生成二维码
        BarcodeQRCode qrcode = new BarcodeQRCode(content);
        // 生成二维码图片
        Image image = new Image(qrcode.createFormXObject(ColorConstants.BLACK, pdf));
        // 设置大小
        image.scaleToFit(width, height);
        return image;
	}
	
}
