package com.hlc.carrent.utils.excle;

import com.hlc.carrent.domain.Customer;
import com.hlc.carrent.domain.Rent;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ExprotRentUtil {

    /**
     * 导出出租单数据
     */
    @SuppressWarnings("deprecation")
    public static ByteArrayOutputStream exportRent(Rent rent, Customer customer, String sheetName) {
        //组装excel文档
        //1.创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建样式
        HSSFCellStyle baseStyle = ExcelStyleUtils.createBaseStyle(workbook);
        HSSFCellStyle subTitleStyle = ExcelStyleUtils.createSubTitleStyle(workbook);
        HSSFCellStyle tableTitleStyle = ExcelStyleUtils.createTableTitleStyle(workbook);
        HSSFCellStyle titleStyle = ExcelStyleUtils.createTitleStyle(workbook);
        //3.在工作薄创建sheet
        HSSFSheet sheet = workbook.createSheet();
        //4.设置sheet(列宽) 默认设置
        sheet.setDefaultColumnWidth(30);
        sheet.setColumnWidth(1, 50 * 256);//设置第二列列宽
        //5.合并
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(region);
        //6.创建第一行
        int index = 0;
        HSSFRow row1 = sheet.createRow(index);
        //6.1在第一行里面创建一个单元格
        HSSFCell row1_cell1 = row1.createCell(0);
        row1_cell1.setCellStyle(titleStyle);    //设置标题样式
        row1_cell1.setCellValue(customer.getCustname() + "的出租单信息");  //设置内容

        //7.创建第二行
        index++;
        HSSFRow row2 = sheet.createRow(index);
        row2.setHeightInPoints(150);
        //7.1 第一个单元格
        HSSFCell row2_cell1 = row2.createCell(0);
        row2_cell1.setCellStyle(baseStyle);
        row2_cell1.setCellValue("出租单号:");
        //7.2 第二个单元格
        HSSFCell row2_cell2 = row2.createCell(1);
        row2_cell2.setCellStyle(baseStyle);
        row2_cell2.setCellValue(rent.getRentid());
        //7.3 第三个单元格
        HSSFCell row2_cell3 = row2.createCell(2);
        row2_cell3.setCellStyle(baseStyle);
        row2_cell3.setCellValue("二维码:");
        //7.4 第四个单元格
        HSSFCell row2_cell4 = row2.createCell(3);
        row2_cell4.setCellStyle(baseStyle);
        row2_cell4.setCellValue("");

        //画二维码
        InputStream logoStream = ExprotCustomerUtils.class.getClassLoader().getResourceAsStream("./timg.jpg");
        BufferedImage image = ZXingCodeEncodeUtils.createZXingCodeLogo(rent.getRentid(), 150, 150, logoStream);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image,"JPEG",bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //画图的顶级管理器,一个sheel只能获取一次
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        /**
         * 参数4: 设置图片的平铺程度,最大值是255   255(代表铺满当前)
         * 参数5: 列的开始坐标
         * 参数6: 行的开始坐标
         * 参数7: 列的结束坐标
         * 参数8: 行的结束坐标
         */
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 255, (short) 3, 1, (short) 4, 1);
        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
        patriarch.createPicture(anchor,workbook.addPicture(bos.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));

        //8.第三行
        index++;
        HSSFRow row3 = sheet.createRow(index);
        //8.1 第一个单元格
        HSSFCell row3_cell1 = row3.createCell(0);
        row3_cell1.setCellStyle(baseStyle);
        row3_cell1.setCellValue("客户姓名:");
        //8.2 第二个单元格
        HSSFCell row3_cell2 = row3.createCell(1);
        row3_cell2.setCellStyle(baseStyle);
        row3_cell2.setCellValue(customer.getCustname());
        //8.3 第三个单元格
        HSSFCell row3_cell3 = row3.createCell(2);
        row3_cell3.setCellStyle(baseStyle);
        row3_cell3.setCellValue("身份证号:");
        //8.4 第四个单元格
        HSSFCell row3_cell4 = row3.createCell(3);
        row3_cell4.setCellStyle(baseStyle);
        row3_cell4.setCellValue(customer.getIdentity());

        //9.第四行
        index++;
        HSSFRow row4 = sheet.createRow(index);
        //9.1 第一个单元格
        HSSFCell row4_cell1 = row4.createCell(0);
        row4_cell1.setCellStyle(baseStyle);
        row4_cell1.setCellValue("取出时间:");
        //9.2 第二个单元格
        HSSFCell row4_cell2 = row4.createCell(1);
        row4_cell2.setCellStyle(baseStyle);
        row4_cell2.setCellValue(rent.getCreatetime().toLocaleString());
        //9.3 第三个单元格
        HSSFCell row4_cell3 = row4.createCell(2);
        row4_cell3.setCellStyle(baseStyle);
        row4_cell3.setCellValue("还车时间:");
        //9.4 第四个单元格
        HSSFCell row4_cell4 = row4.createCell(3);
        row4_cell4.setCellStyle(baseStyle);
        row4_cell4.setCellValue(rent.getReturndate().toLocaleString());


        //10.第五行
        index++;
        HSSFRow row5 = sheet.createRow(index);
        //10.1 第一个单元格
        HSSFCell row5_cell1 = row5.createCell(0);
        row5_cell1.setCellStyle(baseStyle);
        row5_cell1.setCellValue("车牌号:");
        //10.2 第二个单元格
        HSSFCell row5_cell2 = row5.createCell(1);
        row5_cell2.setCellStyle(baseStyle);
        row5_cell2.setCellValue(rent.getCarnumber());
        //10.3 第三个单元格
        HSSFCell row5_cell3 = row5.createCell(2);
        row5_cell3.setCellStyle(baseStyle);
        row5_cell3.setCellValue("出租价格:");
        //10.4 第四个单元格
        HSSFCell row5_cell4 = row5.createCell(3);
        row5_cell4.setCellStyle(baseStyle);
        row5_cell4.setCellValue(rent.getPrice());

        //11.第六行(空一行)
        index++;

        //12.第七行
        index++;
        HSSFRow row7 = sheet.createRow(index);

        //12.3 第三个单元格
        HSSFCell row7_cell3 = row7.createCell(2);
        row7_cell3.setCellStyle(baseStyle);
        row7_cell3.setCellValue("打印时间:");
        //12.4 第四个单元格
        HSSFCell row7_cell4 = row7.createCell(3);
        row7_cell4.setCellStyle(baseStyle);
        row7_cell4.setCellValue(new Date().toLocaleString());


        //13.第八行
        index++;
        HSSFRow row8 = sheet.createRow(index);

        //12.3 第三个单元格
        HSSFCell row8_cell3 = row8.createCell(2);
        row8_cell3.setCellStyle(baseStyle);
        row8_cell3.setCellValue("操作员:");
        //12.4 第四个单元格
        HSSFCell row8_cell4 = row8.createCell(3);
        row8_cell4.setCellStyle(baseStyle);
        row8_cell4.setCellValue(rent.getOpername());


        //13.第九行
        index++;
        HSSFRow row9 = sheet.createRow(index);

        //13.3 第三个单元格
        HSSFCell row9_cell3 = row9.createCell(2);
        row9_cell3.setCellStyle(baseStyle);
        row9_cell3.setCellValue("客户签名:");


        //组装完成

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //把workbook里面的数据写到outputStream
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream;    //返回数据

    }


}
