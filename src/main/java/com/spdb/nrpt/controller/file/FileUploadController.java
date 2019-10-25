package com.spdb.nrpt.controller.file;

import com.alibaba.fastjson.JSONArray;
import com.spdb.nrpt.entity.report.custom.CustomNumberEntity;
import com.spdb.nrpt.mapper.reportDataMapper.NRPTReportDataMapper;
import lombok.extern.slf4j.Slf4j;
/*import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Controller
@Slf4j
public class FileUploadController {

    @Autowired
    private NRPTReportDataMapper nrptReportDataMapper;

    @RequestMapping("/getFileUploadPage")
    public String getFileUploadPage(){
        return "file/NRPTFileUpload";
    }

    //客户数Excel处理
    @RequestMapping("/NRPTFileUpload")
    @ResponseBody
    public String NRPTFileUpload(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        //String savePath = "/nas/update/"+fileName;
        String savePath = "D:\\test\\"+fileName;
        log.info("文件名："+fileName+"---路径："+savePath);

        InputStream in = null;
        OutputStream out = null;
        List<CustomNumberEntity> dataList = new ArrayList<>();
        try {
            //file.transferTo(targetFile);
            out = new FileOutputStream(new File(savePath));

            in = file.getInputStream() ;

            int length = 0 ;

            byte [] buf = new byte[1024] ;

            while( (length = in.read(buf) ) != -1){
                out.write(buf, 0, length);
            }

            in = new FileInputStream(new File(savePath));

           /* XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for(int rowNum=0; rowNum<sheet.getLastRowNum()+1; rowNum++){
                XSSFRow row = sheet.getRow(rowNum);
                if (row!=null){
                    CustomNumberEntity customNumberEntity = new CustomNumberEntity();

                    Cell cellStatis_Dt = row.getCell(0);
                    String Statis_Dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellStatis_Dt.getDateCellValue());
                    customNumberEntity.setStatis_Dt(Statis_Dt);

                    Cell cellCust_Type_Cd = row.getCell(1);
                    cellCust_Type_Cd.setCellType(Cell.CELL_TYPE_STRING);
                    String Cust_Type_Cd = cellCust_Type_Cd.getStringCellValue();
                    customNumberEntity.setCust_Type_Cd(Cust_Type_Cd);

                    Cell cellOrg_id_1_lev = row.getCell(2);
                    cellOrg_id_1_lev.setCellType(Cell.CELL_TYPE_STRING);
                    String Org_id_1_lev = cellOrg_id_1_lev.getStringCellValue();
                    customNumberEntity.setOrg_id_1_lev(Org_id_1_lev);

                    Cell cellOrg_name_1_lev = row.getCell(3);
                    cellOrg_name_1_lev.setCellType(Cell.CELL_TYPE_STRING);
                    String Org_name_1_lev = cellOrg_name_1_lev.getStringCellValue();
                    customNumberEntity.setOrg_name_1_lev(Org_name_1_lev);

                    Cell cellCust_Cnt = row.getCell(4);
                    cellCust_Cnt.setCellType(Cell.CELL_TYPE_STRING);
                    String Cust_Cnt = cellCust_Cnt.getStringCellValue();
                    customNumberEntity.setCust_Cnt(Cust_Cnt);

                    dataList.add(customNumberEntity);
                }
            }

            log.info("最终数据为"+ JSONArray.toJSONString(dataList));

            nrptReportDataMapper.insetBatchCustomData(dataList);*/
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }finally {
            try {
                if (in!=null||out!=null){
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
