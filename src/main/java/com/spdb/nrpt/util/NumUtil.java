package com.spdb.nrpt.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

//数字工具类
public class NumUtil {

	//测试方法
	/*
	 * public static void main(String[] strs) {
	 * System.out.println(dealNum("15",4,4)); }
	 */
	
    //获得最小值
    public static String getMin(List<String> dataList){
        String num = "";
        for (int i=0; i<dataList.size()-1; i++){
            BigDecimal bigDecimal1 = new BigDecimal(i);
            BigDecimal bigDecimal2 = new BigDecimal(i+1);
            BigDecimal min = bigDecimal1.subtract(bigDecimal2);
            Double res = new Double(min.toString());
            if (res<0){
                num = bigDecimal1.toString();
            }
        }
        return num;
    }

    //处理科学计数法,并除以x,取y位小数
    public static String dealNum(String num,int x,int y) {
        BigDecimal bigDecimal = new BigDecimal(num);
        String numString =  bigDecimal.toPlainString();
        BigDecimal NumBigDecimal = new BigDecimal(numString);
        BigDecimal divisionBigDecimal = new BigDecimal(x);
        BigDecimal bigDecimalRes =  NumBigDecimal.divide(divisionBigDecimal);
        bigDecimalRes = bigDecimalRes.setScale(y, RoundingMode.HALF_UP);
        return bigDecimalRes.toString();
    }


}
