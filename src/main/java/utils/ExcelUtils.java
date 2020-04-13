package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	private static Workbook wbk;
	private static Sheet sht;
	private static Row row;
	private static Cell cell;

	public static Sheet getExcelSheet(String path, String SheetName) {
		try {
			wbk = WorkbookFactory.create(new File(path));
			sht = wbk.getSheet(SheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sht;
	}

	public static int getColumnCount() {
		try {
			int columnCount = sht.getRow(0).getLastCellNum();
			return columnCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getRowCount() {
		try {
			int totalRowCount = sht.getLastRowNum();
			return totalRowCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getCellValue(int RowNum, int ColNum) {
		try {
			DataFormatter df = new DataFormatter();
			cell = sht.getRow(RowNum).getCell(ColNum);
			return df.formatCellValue(cell);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Cell findCellWithText(Sheet sheet, String text) {
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (text.equals(cell.getStringCellValue()))
					return cell;
			}
		}
		return null;
	}

	public static ArrayList<Cell> findCellsWithText(Sheet sheet, String text) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (text.equals(cell.getStringCellValue()))
					cells.add(cell);
			}
		}
		return cells;
	}

	public static int getColumnIndex(String columnName, int totalColumnCount) {
		int colNum = 0;
		for (int i = 0; i < totalColumnCount; i++) {
			cell = sht.getRow(0).getCell(i);
			if (cell != null) {
				String colName = cell.getStringCellValue();
				if (columnName.equals(colName)) {
					colNum = i;
					break;
				}
			}
		}
		return colNum;
	}

	public static void setCellValue(String path, String shtName, int rowNum, int colNum, String cellVal) {
		try {
			FileInputStream fis = new FileInputStream(path);
			wbk = WorkbookFactory.create(fis);
			sht = wbk.getSheet(shtName);
			row = sht.getRow(rowNum);
			if (row == null) {
				sht.createRow(rowNum);
				row = sht.getRow(rowNum);
			}
			Cell column = row.createCell(colNum);
			column.setCellValue(cellVal);
			fis.close();
			FileOutputStream out = new FileOutputStream(path);
			wbk.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void removeRow(String path, String shtName, int rowIndex) {
		try {
			FileInputStream fis = new FileInputStream(path);
			wbk = WorkbookFactory.create(fis);
			sht = wbk.getSheet(shtName);
			int lastRowNum = sht.getLastRowNum();
			if (rowIndex >= 0 && rowIndex < lastRowNum) {
				sht.shiftRows(rowIndex + 1, lastRowNum, -1);
			}
			if (rowIndex == lastRowNum) {
				Row removingRow = sht.getRow(rowIndex);
				if (removingRow != null) {
					sht.removeRow(removingRow);
				}
			}
			fis.close();
			FileOutputStream out = new FileOutputStream(path);
			wbk.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
