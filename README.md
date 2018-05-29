# itext
itext7的一些简单操作（二维码、水印、表格、HTML代码生成PDF）
itext7解决中文显示问题有两种解决方式:
 ``` 
1 引入对应的语言包,火狐浏览器预览生成的pdf可能存在部分中文乱码问题,同时因为加载了语言包,生成的pdf更大
	使用NotoSansCJKsc-Regular.otf,同时在pom中引入com.itextpdf.font-asian包.
2 设置字体:通过默认字体生成,pdf文件和html大小几乎相同,不存在浏览器预览乱码问题 
	PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
 ```
问题:
	HtmlToPdf类由于将html页面全部在一个pdf中输出,造成水印无法使用,欢迎各位参与解决!

本文是在其他人的基础上整理而来,原始访问链接:https://github.com/zkhgit/itext
资料链接:
    官方文档:https://developers.itextpdf.com/tutorials
    他人官方文档翻译版:https://github.com/iTextCN/itext7-in-mandarin/tree/master/
