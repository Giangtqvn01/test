package com.example.actvn.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExcelUtils {

    // Auto resize column width
    public static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    public static CellStyle createStyleForNgayThang(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        return cellStyle;
    }

    public static CellStyle createStyleForString(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);

        return cellStyle;
    }
    public static CellStyle createStyleForHeaderTable(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        return cellStyle;
    }

    public static void borderStyleMergedCell(Sheet sheet, int firstRow, int lastRow, int firstColumn, int lastColumn) {
        Row myRow1 = sheet.createRow(firstRow);
        Row myRow2 = sheet.createRow( lastRow);
        if (firstColumn == lastColumn) {
            Cell blankCell1 = myRow1.createCell(firstColumn);
            Cell blankCell2 = myRow2.createCell(firstColumn);

            CellStyle cellStyle1 = sheet.getWorkbook().createCellStyle();
            cellStyle1.setBorderTop(BorderStyle.THIN);
            cellStyle1.setBorderLeft(BorderStyle.THIN);
            cellStyle1.setBorderRight(BorderStyle.THIN);
            blankCell1.setCellStyle(cellStyle1);

            CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
            cellStyle2.setBorderLeft(BorderStyle.THIN);
            cellStyle2.setBorderBottom(BorderStyle.THIN);
            cellStyle2.setBorderRight(BorderStyle.THIN);
            blankCell2.setCellStyle(cellStyle2);
        } else {
            for (int i = firstColumn; i <= lastColumn; ++i) {
                Cell blankCell1 = myRow1.createCell(i);
                Cell blankCell2 = myRow2.createCell(i);

                if (i == firstColumn) {
                    CellStyle cellStyle1 = sheet.getWorkbook().createCellStyle();
                    cellStyle1.setBorderTop(BorderStyle.THIN);
                    cellStyle1.setBorderLeft(BorderStyle.THIN);
                    blankCell1.setCellStyle(cellStyle1);

                    CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
                    cellStyle2.setBorderLeft(BorderStyle.THIN);
                    cellStyle2.setBorderBottom(BorderStyle.THIN);
                    blankCell2.setCellStyle(cellStyle2);
                } else if (i == lastColumn) {
                    CellStyle cellStyle1 = sheet.getWorkbook().createCellStyle();
                    cellStyle1.setBorderTop(BorderStyle.THIN);
                    cellStyle1.setBorderRight(BorderStyle.THIN);
                    blankCell1.setCellStyle(cellStyle1);

                    CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
                    cellStyle2.setBorderRight(BorderStyle.THIN);
                    cellStyle2.setBorderBottom(BorderStyle.THIN);
                    blankCell2.setCellStyle(cellStyle2);
                } else {
                    CellStyle cellStyle1 = sheet.getWorkbook().createCellStyle();
                    cellStyle1.setBorderTop(BorderStyle.THIN);
                    blankCell1.setCellStyle(cellStyle1);

                    CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
                    cellStyle2.setBorderBottom(BorderStyle.THIN);
                    blankCell2.setCellStyle(cellStyle2);
                }
            }
        }
    }

    public static CellStyle createStyleForNumber(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);

        return cellStyle;
    }

    public static CellStyle createStyleForSTT(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        return cellStyle;
    }

    public static CellStyle createStyleForTile(Sheet sheet) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color
        font.setBold(true);

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }
    public static CellStyle createStyleTextBold(Sheet sheet){
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 12); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color
        font.setBold(true);


        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        return cellStyle;
    }
    public static CellStyle createStyleForTileGachChan(Sheet sheet) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color
        font.setBold(true);
        font.setUnderline(Font.U_SINGLE);

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    // Create workbook
    public static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    public static void writeTitle(Sheet sheet, int rowIndex, String title, int indexTitle) {
        int COLUMN_INDEX_TITLE = indexTitle;
        // create CellStyle
        CellStyle cellStyle = createStyleForTitle(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(title);
    }

    public static void writeTitleTuNgayDenNgay(Sheet sheet, int rowIndex, String title, int indexTitle) {
        int COLUMN_INDEX_TITLE = indexTitle;
        // create CellStyle
        CellStyle cellStyle = createStyleForTitleTuNgayDenNgay(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(title);
    }

    public static CellStyle createStyleForTitle(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color


        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    public static CellStyle createStyleForTitleTuNgayDenNgay(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setItalic(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color


        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    // Create CellStyle for header
    public static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        return cellStyle;
    }

    // Create output file
    public static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
