package cn.framework.util.excel;

import cn.framework.security.exception.AppException;
import cn.framework.security.exception.codes.SysErrorCode;
import cn.framework.util.sys.SysUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020-05-14 9:42
 * @Version 1.0
 */
public class ExcelUtil {

    /**
     * 日志打印对象
     */
    static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * @Description: 导出Excel
     * @Params: [sheetName, headTitle, title, values]
     * @return: org.apache.poi.hssf.usermodel.HSSFWorkbook
     * @Author: 柯雷
     * @Date: 2020-05-14 9:59
     */
    public static HSSFWorkbook getHSSFWorkbook(Object[][] values, HSSFWorkbook workbook) throws AppException {
        try {
            HSSFSheet sheet = workbook.getSheetAt(0); // 获取工作表
            Integer columnNum = sheet.getRow(0).getPhysicalNumberOfCells();

            HSSFCell cells[] = new HSSFCell[columnNum];
            // 获取所有列信息
            for (int i = 0; i < columnNum; i++) {
                cells[i] = sheet.getRow(0).getCell(i);
            }

            // 将查询出的数据设置到sheet对应的单元格中
            for (int i = 0; i < values.length; i++) {
                Object[] obj = values[i];// 遍历每个对象
                HSSFRow row = sheet.createRow(i + 1);// 创建所需的行数
                for (int j = 0; j < Math.min(obj.length, columnNum); j++) {
                    HSSFCell cell = row.createCell(j, cells[j].getCellType());
                    switch (cells[j].getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            cell.setCellValue(Integer.parseInt(SysUtil.isEmpty(obj[j]) ? null : obj[j].toString()));
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            cell.setCellValue(SysUtil.isEmpty(obj[j]) ? "" : obj[j].toString());
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            cell.setCellValue(cells[j].getCellFormula());
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            cell.setCellValue(Boolean.parseBoolean(SysUtil.isEmpty(obj[j]) ? null : obj[j].toString()));
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                        case HSSFCell.CELL_TYPE_BLANK:
                            cell.setCellValue("");
                            break;
                    }
                    cell.setCellStyle(cells[j].getCellStyle()); // 设置单元格样式
                }
            }
            // 让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    // 当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }
            return workbook;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【ExcelUtil.getHSSFWorkbook】生成工作表出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 导出Excel
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String headTitle, String[] title,
                                               String[][] values) throws AppException {
        try {
            // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = workbook.createSheet(sheetName); // 创建工作表

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);

            // sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面 - 可扩展】
            HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook); // 获取列头样式对象

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (title.length - 1)));
            cellTiltle.setCellStyle(columnTopStyle);
            cellTiltle.setCellValue(headTitle);

            // 定义所需列数
            int columnNum = title.length;
            HSSFRow rowRowName = sheet.createRow(2); // 在索引2的位置创建行(最顶端的行开始的第二行)

            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n); // 创建列头对应个数的单元格
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
                HSSFRichTextString text = new HSSFRichTextString(title[n]);
                cellRowName.setCellValue(text); // 设置列头单元格的值
                cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
            }
            return getHSSFWorkbook(values, workbook);
        } catch (Exception e) {
            logger.error("【ExcelUtil.getHSSFWorkbook】生成工作表出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }

    }

    /**
     * @Description: 列头单元格样式
     * @Params: [workbook]
     * @return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     * @Author: 柯雷
     * @Date: 2020-05-14 9:43
     */
    public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /**
     * @Description: 列数据信息单元格样式
     * @Params: [workbook]
     * @return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     * @Author: 柯雷
     * @Date: 2020-05-14 9:43
     */
    public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        // font.setFontHeightInPoints((short)10);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /**
     * @Description: 导出Excel
     * @Params: [sheetName, headTitle, title, values, filePath]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-05-14 10:15
     */
    public static void exportExcel(String sheetName, String headTitle,
                                   String[] title, String[][] values, String filePath) throws AppException {
        try {
            HSSFWorkbook hssfWorkbook = getHSSFWorkbook(sheetName, headTitle,
                    title, values);
            OutputStream outputStream = new FileOutputStream(new File(filePath));
            hssfWorkbook.write(outputStream);
        } catch (AppException e) {
            logger.error("【ExcelUtil.exportExcel】导出Excel出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ExcelUtil.exportExcel】导出Excel出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }

    }

    /**
     * @Description: 导出Excel
     * @Params: [values, filePath, template_file]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020-05-14 10:47
     */
    public static void exportExcel(String[][] values, String filePath, String template_file) throws AppException {
        try {
            HSSFWorkbook hssfWorkbook = getHSSFWorkbook(values, new HSSFWorkbook(new FileInputStream(template_file)));
            OutputStream outputStream = new FileOutputStream(new File(filePath));
            hssfWorkbook.write(outputStream);
        } catch (AppException e) {
            logger.error("【ExcelUtil.exportExcel】导出Excel出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ExcelUtil.exportExcel】导出Excel出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }

    }

    /**
     * @Description: 获取导入的Excel数据
     * @Params: [filePath]
     * @return: java.util.List<java.lang.Object [ ]>[]
     * @Author: 柯雷
     * @Date: 2020-05-14 11:38
     */
    public static List<Object[]>[] importExcel(String filePath) throws AppException {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));// 得到这个excel表格对象
            Integer sheetNum = workbook.getNumberOfSheets();

            List<Object[]> data[] = new ArrayList[sheetNum];
            // 遍历每一个sheet页
            for (int i = 0; i < sheetNum; i++) {
                data[i] = getSheetData(workbook.getSheetAt(i));
            }
            return data;
        } catch (AppException e) {
            logger.error("【ExcelUtil.importExcel】导入数据出错：" + e);
            throw e;
        } catch (Exception e) {
            logger.error("【ExcelUtil.importExcel】导入数据出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * @Description: 获取sheet页数据
     * @Params: [sheet]
     * @return: java.util.List<java.lang.Object [ ]>
     * @Author: 柯雷
     * @Date: 2020-05-14 11:26
     */
    public static List<Object[]> getSheetData(HSSFSheet sheet) throws AppException {
        try {
            List<Object[]> sheetData = new ArrayList<>();
            Object rowData[];
            int rowNo = sheet.getLastRowNum(); //得到行数
            for (int i = 0; i < rowNo; i++) {
                HSSFRow row = sheet.getRow(i + 1);
                rowData = new Object[row.getLastCellNum()];
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    HSSFCell hssfCell = row.getCell(j);
                    switch (hssfCell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            rowData[j] = hssfCell.getNumericCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            rowData[j] = hssfCell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            rowData[j] = hssfCell.getRichStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            rowData[j] = hssfCell.getBooleanCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            rowData[j] = hssfCell.getErrorCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            rowData[j] = null;
                            break;
                    }
                }
                sheetData.add(rowData);
            }
            return sheetData;
        } catch (Exception e) {
            logger.error("【ExcelUtil.getSheetData】获取sheet页数据出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }


    public static void main(String[] args) throws Exception {
        // exportExcel(new String[][]{{"12450", "124454", "015121.1"}, {"盛大开放聚划算", "asdfasd", "@#&!@*^*&%#"}}, "D:\\text.xls", "D:\\template.xls");
        List<Object[]>[] data = importExcel("D:\\text.xls");
        for (int i = 0; i < data.length; i++) {
            System.err.println("=======================sheet" + (i + 1) + "======================");
            List<Object[]> sheetData = data[i];
            for (int j = 0; j < sheetData.size(); j++) {
                Object row[] = sheetData.get(j);
                for (int k = 0; k < row.length; k++) {
                    System.err.print(row[k] + "\t");
                }
                System.err.println();
            }
        }
    }
}
