package com.sqe.shop.service.file;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.BaseService;
import com.sqe.shop.common.Constants;
import com.sqe.shop.util.DateUtil;

@Component
public class ExcelExportService extends BaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelExportService.class);

	public void exportExcel(HttpServletRequest request, HttpServletResponse response, List<Map<String, Object>> list) { 
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			String fileName = "shop_"+DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+".xls";
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			HSSFSheet sheet = wb.createSheet("shop");
			sheet.setDefaultRowHeight((short) (2 * 256));
			sheet.setColumnWidth(0, 50 * 160);
			HSSFFont font = wb.createFont();
			font.setFontHeightInPoints((short) 16);
			HSSFRow row = sheet.createRow((int) 0);
			sheet.createRow((int) 1);
			sheet.createRow((int) 2);
			sheet.createRow((int) 3);
			sheet.createRow((int) 4);
			sheet.createRow((int) 5);
			sheet.createRow((int) 6);
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("店名/shop name ");
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue("所属者/shop onwer");
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("店铺状态/shop status");
			
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("店铺星级/shop level");
			
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue("店铺排行/shop rank");
			
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("店铺描述/shop description");
			
			HSSFRow row1 = null;
			int i=0;
			String status = "0";
			for (Map<String, Object> map : list){
				row1 = sheet.createRow((int) ++i);
				row1.createCell(0).setCellValue(map.get("shopTitle")==null?"":map.get("shopTitle").toString());
				row1.createCell(1).setCellValue(map.get("shopOnwer")==null?"":map.get("shopOnwer").toString());
				
				status= map.get("shopStatus")==null?"0":map.get("shopStatus").toString();
				row1.createCell(2).setCellValue(Constants.getStoreStatus(Integer.valueOf(status)));
				
				row1.createCell(3).setCellValue(map.get("shopLevel")==null?"":map.get("shopLevel").toString());
				row1.createCell(4).setCellValue(map.get("shopRank")==null?"":map.get("shopRank").toString());
				row1.createCell(5).setCellValue(map.get("description")==null?"":map.get("description").toString());
			}
			
			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e) {
			logger.error("导出excel异常："+e.getMessage());
		}
		
		/*try
		{
			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.close();
		}
		catch (ServiceException e)
		{
			logger.info("=====导出excel异常====");
		}
		catch (Exception e1)
		{
			logger.info("=====导出excel异常====");
		}*/
	}

}
