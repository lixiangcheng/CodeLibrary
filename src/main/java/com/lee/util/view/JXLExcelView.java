package com.lee.util.view;

import com.lee.util.NumStrUtils;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by lixiangcheng on 16/4/27.
 */


public class JXLExcelView
        extends AbstractJExcelView {
    private String[] columnNames = null;
    private String[] dbColumnNames = null;
    private Integer[] columnWidths = null;
    private Integer columnWidth = Integer.valueOf(10);

    protected void buildExcelDocument(Map<String, Object> map, WritableWorkbook work, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        this.columnNames = ((String[]) map.get("columnNames"));
        this.dbColumnNames = ((String[]) map.get("dbColumnNames"));
        this.columnWidths = ((Integer[]) map.get("columnWidths"));
        Integer cw = (Integer) map.get("columnWidth");
        if (cw != null) {
            this.columnWidth = cw;
        }
        String sheetName = (String) map.get("excelName");
        if (sheetName == null) {
            sheetName = "data";
        }
        String excelName = sheetName + System.currentTimeMillis() + ".xls";

        response.setContentType("APPLICATION/OCTET-STREAM");
        String dfileName = null;
        if (request.getHeader("user-agent").indexOf("MSIE") != -1) {
            dfileName = URLEncoder.encode(excelName, "utf-8");
        } else {
            dfileName = new String(excelName.getBytes("utf-8"), "iso-8859-1");
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + dfileName);

        WritableSheet ws = work.createSheet(sheetName, 0);

        addColumNameToWsheet(ws);

        writeContext(ws, (List) map.get("listDate"));

        System.out.println(".............download............");
    }

    private <T> void writeContext(WritableSheet wsheet, List<T> list) {
        int rows = list.size();

        WritableCellFormat wcf = getFormat();

        LinkedHashMap<?, ?> lmp = null;
        HashMap<?, ?> hmp = null;
        String type = null;
        try {
            for (int i = 0; i < rows; i++) {
                T t = list.get(i);
                if (i == 0) {
                    if ((t instanceof LinkedHashMap)) {
                        type = "lm";
                    } else if ((t instanceof HashMap)) {
                        type = "hm";
                    } else {
                        type = "oo";
                    }
                }
                if ("lm".equals(type)) {
                    lmp = (LinkedHashMap) t;
                    Set<?> colStr = lmp.keySet();
                    int j = 0;
                    for (Object col : colStr) {
                        if (j >= this.columnNames.length) {
                            break;
                        }
                        Object value = lmp.get(col);
                        addCell(wsheet, i, j, value, wcf);
                        j++;
                    }
                } else {
                    if ("hm".equals(type)) {
                        hmp = (LinkedHashMap) t;
                    }
                    for (int j = 0; j < this.dbColumnNames.length; j++) {
                        Object value = null;
                        if ("hm".equals(type)) {
                            value = hmp.get(this.dbColumnNames[j]);
                        } else {
                            value = PropertyUtils.getProperty(t, this.dbColumnNames[j]);
                        }
                        addCell(wsheet, i, j, value, wcf);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCell(WritableSheet wsheet, int row, int col, Object value, WritableCellFormat wcf)
            throws RowsExceededException, WriteException {
        String strv = "";
        try {
            strv = String.valueOf(value).trim();
        } catch (Exception e) {
            strv = "";
        }
        if (NumStrUtils.isNum(strv)) {
            WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false);
            DisplayFormat displayFormat = NumberFormats.TEXT;
            WritableCellFormat format = new WritableCellFormat(wf, displayFormat);
            format.setWrap(true);
            format.setAlignment(Alignment.RIGHT);
            format.setVerticalAlignment(VerticalAlignment.CENTRE);
            format.setBorder(Border.ALL, BorderLineStyle.NONE);

            Number number = new Number(col, row + 1, Double.parseDouble(strv), format);
            wsheet.addCell(number);
        } else {
            Label wlabel = new Label(col, row + 1, strv, wcf);

            wsheet.addCell(wlabel);
        }
    }

    private void addColumNameToWsheet(WritableSheet wsheet)
            throws RowsExceededException, WriteException {
        WritableFont wfont = new WritableFont(WritableFont.ARIAL,
                10, WritableFont.BOLD);

        WritableCellFormat wcfFC = new WritableCellFormat(wfont);
        try {
            wcfFC.setWrap(true);
            wcfFC.setAlignment(Alignment.CENTRE);
            wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        Label wlabel1 = null;
        String[] columNames = this.columnNames;
        if (columNames == null) {
            return;
        }
        int colSize = columNames.length;
        Integer[] colsWidth = this.columnWidths;
        if (colsWidth == null) {
            colsWidth = new Integer[colSize];
            for (int i = 0; i < colSize; i++) {
                colsWidth[i] = this.columnWidth;
            }
        }
        int temp = 0;
        String colName = null;
        for (int i = 0; i < colSize; i++) {
            colName = columNames[i];
            if ((colName == null) || ("".equals(colName))) {
                colName = "";
            }
            wlabel1 = new Label(i, 0, colName, wcfFC);
            wsheet.addCell(wlabel1);
            temp = colsWidth[i].intValue();

            temp = temp == 0 ? this.columnWidth.intValue() : temp;
            wsheet.setColumnView(i, temp);
        }
    }

    private WritableCellFormat getFormat() {
        WritableFont wfont = getFont();
        WritableCellFormat wcfFC = new WritableCellFormat(wfont);
        try {
            wcfFC.setWrap(true);
            wcfFC.setAlignment(Alignment.LEFT);
            wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcfFC.setBorder(Border.ALL, BorderLineStyle.NONE);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return wcfFC;
    }

    private WritableFont getFont() {
        return new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false);
    }
}
