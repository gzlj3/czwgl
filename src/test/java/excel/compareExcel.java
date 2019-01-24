package excel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class compareExcel {
	public static void main(String[] args) throws Exception {
		String fname1 = "test1.xls";
		String fname2 = "test2.xls";
		String resultname1 = "result1.txt";
		String resultname2 = "result2.txt";
		String resultname3 = "result3.txt";
		int compCol1 = 10, compCol2 = 2;
		if (args.length > 1) {
			fname1 = args[0];
			fname2 = args[1];
		}
		if (args.length > 3) {
			compCol1 = Integer.parseInt(args[2]);
			compCol2 = Integer.parseInt(args[3]);
			compCol1 --;
			compCol2 --;
		}
		if (args.length > 6) {
			resultname1 = args[4];
			resultname2 = args[5];
			resultname3 = args[6];
		}

		File file1 = new File(fname1);
		File file2 = new File(fname2);
		if (!file1.exists() || !file2.exists()) {
			System.out.println("文件1(" + fname1 + ")或文件2(" + fname2 + ")不存在！");
			System.exit(1);
		}
		List<String[]> data1 = getData(file1, 1);
		List<String[]> data2 = getData(file2, 1);

		System.out.println("开始比对...");
		// 生成T1和T2的交集（result1)，T1 - T2的差集
		List<String[]> resultList1 = new ArrayList<String[]>();
		List<String[]> resultList2 = new ArrayList<String[]>();
		int i, j;
		int emptyCount1=0;
		for (i = 0; i < data1.size(); i++) {
			String[] arr1 = data1.get(i);
			String value1 = arr1[compCol1];
			// System.out.println(value1);
			if (empty(value1)){
				emptyCount1 ++;
				continue;
			}
			for (j = 0; j < data2.size(); j++) {
				String[] arr2 = data2.get(j);
				String value2 = arr2[compCol2];
				if (empty(value2))
					continue;
				// System.out.println(value2);
				if (value1.equals(value2)) {
					resultList1.add(arr1);
					break;
				}
			}
			if (j >= data2.size()) {
				// 在目标表中未查到
				resultList2.add(arr1);
			}
		}
		// 生成T2 - T1的差集
		List<String[]> resultList3 = new ArrayList<String[]>();
		int emptyCount2=0;
		for (i = 0; i < data2.size(); i++) {
			String[] arr2 = data2.get(i);
			String value2 = arr2[compCol2];
			// System.out.println(value1);
			if (empty(value2)){
				emptyCount2++;
				continue;
			}
			for (j = 0; j < data1.size(); j++) {
				String[] arr1 = data1.get(j);
				String value1 = arr1[compCol1];
				if (empty(value1))
					continue;
				// System.out.println(value2);
				if (value1.equals(value2)) {
					// resultList1.add(arr1);
					break;
				}
			}
			if (j >= data1.size()) {
				// 在目标表中未查到
				resultList3.add(arr2);
			}
		}

		// File result1 = new File(resultname1);
		saveData(resultname1, resultList1);
		saveData(resultname2, resultList2);
		saveData(resultname3, resultList3);
		System.out.println("比对结束！共生成3个文件：\r\n在表1和表2中都存在的记录(" + resultname1
				+ ")共：" + resultList1.size() + "条。\r\n在表2中不存在的表1记录(" + resultname2 + ")共："
				+ resultList2.size() + "条。\r\n在表1中不存在的表2记录(" + resultname3 + ")共："
				+ resultList3.size() + "条。\r\n");
		if(emptyCount1>0){
			System.out.println("表1比较列有空值数：" + emptyCount1);
		}
		if(emptyCount2>0){
			System.out.println("表2比较列有空值数：" + emptyCount2);
		}
	}
	
	

	public static void saveData(String fname, List<String[]> data)
			throws FileNotFoundException, IOException {
		File file = new File(fname);
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(file));
		for (int i = 0; i < data.size(); i++) {
			String[] arr = data.get(i);
			for (int j = 0; j < arr.length; j++) {
				out.write((" "+arr[j] + " \t").getBytes());
			}
			out.write(("\r\n").getBytes());
		}
		out.close();

//		String fname1 = "D:/Workspaces/czwgl/lib/result2.xls";
//		File file1 = new File(fname1);
//		BufferedOutputStream out1 = new BufferedOutputStream(
//				new FileOutputStream(file1));
//		POIFSFileSystem fs = new POIFSFileSystem(new BufferedInputStream(new FileInputStream(
//				new File("D:/Workspaces/czwgl/lib/test1.xls"))));
//		HSSFWorkbook wb = new HSSFWorkbook(fs);
//		wb.removeSheetAt(0);
//		HSSFSheet sheet = wb.createSheet();
//		HSSFRow row = sheet.createRow(0);
//		HSSFCell cell = row.createCell((short)0);
//		cell.setCellValue("test");
//		fs.writeFilesystem(out1);
//		out1.close();
		// 打开HSSFWorkbook
		// POIFSFileSystem fs = new POIFSFileSystem(out);
		// HSSFWorkbook wb = new HSSFWorkbook(fs);
		// HSSFCell cell = null;
		// for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets();
		// sheetIndex++) {
		// HSSFSheet st = wb.getSheetAt(sheetIndex);
		// // 第一行为标题，不取
		// for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum();
		// rowIndex++) {
		// }
		// }
	}

	/**
	 * 
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * 
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * 
	 * @return 读出的Excel中数据的内容
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 */

	public static List<String[]> getData(File file, int ignoreRows)
			throws FileNotFoundException, IOException {
		System.out.println("正在读取文件(" + file.getName() + ")...");
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			// 第一行为标题，不取
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
					String value = "";
					cell = row.getCell(columnIndex);
					if (cell != null) {
						// 注意：一定要设成这个，否则可能会出现乱码
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd")
											.format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell
										.getNumericCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							// 导入时如果为公式生成的数据则无值
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y"
									: "N");
							break;
						default:
							value = "";
						}
					}
					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}
				if (hasValue) {
					result.add(values);
				}else{
					System.out.println("=====第"+(rowIndex+1)+"行数据读入错误！");
				}
			}
		}
		
		in.close();
		System.out.println("读取文件(" + file.getName() + ")结束，共读取行数："
				+ result.size());
		return result;
		// String[][] returnArray = new String[result.size()][rowSize];
		// for (int i = 0; i < returnArray.length; i++) {
		// returnArray[i] = (String[]) result.get(i);
		// }
		// return returnArray;
	}

	/**
	 * 
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 处理后的字符串
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
//		int length = str.length();
//		for (int i = length - 1; i >= 0; i--) {
//			if (str.charAt(i) != 0x20) {
//				break;
//			}
//			length--;
//		}
//		return str.substring(0, length);
	}

	public static boolean empty(String s) {
		return s == null || "".equals(s);
	}
}
