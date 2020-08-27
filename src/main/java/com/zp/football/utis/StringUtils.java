package com.zp.football.utis;


import org.apache.oro.text.perl.Perl5Util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.web.util.HtmlUtils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/21 11:08
 */
public class StringUtils {

    /**
     * 加权因子
     */
    @SuppressWarnings("unused")
    private static final int[] weight = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6,
            3, 7, 9, 10, 5, 8, 4, 2, 1};

    /**
     * 用指定的字符集编码URL
     *
     * @param url     要编码的URL
     * @param charset 字符集
     * @return 编码后的URL
     */
    public static String encodeURL(String url, String charset) {
        if (url != null && url.length() > 0) {
            try {
                return URLEncoder.encode(url, charset);
            } catch (UnsupportedEncodingException ex) {
                return url;
            }
        }
        return url;
    }

    /**
     * 以指定的字符编码解析字符串的长度
     *
     * @param txt     要解析的字符串
     * @param charset 编码
     * @return 字符串的长度
     */
    public static int getStrLength(String txt, String charset) {
        try {
            return txt.getBytes(charset).length;
        } catch (UnsupportedEncodingException ex) {
            return txt.length();
        }
    }

    /**
     * 去掉指定字符串的首尾特殊字符
     *
     * @param source 指定字符串
     * @param beTrim 要去除的特殊字符
     * @return 去掉特殊字符后的字符串
     */
    public static String trimStringWithAppointedChar(String source,
                                                     String beTrim) {
        if (!source.equalsIgnoreCase("")) {
            // 循环去掉字符串首的beTrim字符
            String beginChar = source.substring(0, 1);
            while (beginChar.equalsIgnoreCase(beTrim)) {
                source = source.substring(1, source.length());
                beginChar = source.substring(0, 1);
            }

            // 循环去掉字符串尾的beTrim字符
            String endChar = source.substring(source.length() - 1, source
                    .length());
            while (endChar.equalsIgnoreCase(beTrim)) {
                source = source.substring(0, source.length() - 1);
                endChar = source
                        .substring(source.length() - 1, source.length());
            }
        }
        return source;
    }

    /**
     * 去掉指定字符串的首尾特殊字符
     *
     * @param source  指定字符串
     * @param beTrim  要去除的特殊字符
     * @param endTrim 要去的特殊字符
     * @return 去掉特殊字符后的字符串
     */
    public static String trimStringWithAppointedChar(String source,
                                                     String beTrim, String endTrim) {
        if (!source.equalsIgnoreCase("")) {
            // 循环去掉字符串首的beTrim字符
            String beginChar = source.substring(0, 2);
            while (beginChar.equalsIgnoreCase(beTrim)) {
                source = source.substring(2, source.length());
                beginChar = source.substring(0, 2);
            }

            // 循环去掉字符串尾的beTrim字符
            String endChar = source.substring(source.length() - 1, source
                    .length());
            while (endChar.equalsIgnoreCase(endTrim)) {
                source = source.substring(0, source.length() - 1);
                endChar = source
                        .substring(source.length() - 1, source.length());
            }
        }
        return source;
    }

    /**
     * 用sign分隔字符串data(不判断data是否带有双引号的)
     *
     * @param data 要拆分的字符串
     * @param sign 分隔符
     * @return list 分隔后的List
     */
    public static List<String> spit(String data, String sign) {
        StringTokenizer stkzer = new StringTokenizer(data, sign);
        String temp = null;
        List<String> list = new ArrayList<String>();
        while (stkzer.hasMoreTokens()) {
            temp = stkzer.nextToken();
            list.add(temp);
        }
        return list;
    }

    /**
     * 用sign分隔字符串data(data是带有双引号的) 如:"system_id","type","command_line",
     *
     * @param data 要拆分的字符串
     * @param sign 分隔符
     * @return list 分隔后的List
     */
    public static List<String> spitWithQuotationMark(String data, String sign) {

        List<String> keysWithQuotationMark = new ArrayList<String>();

        String[] tempData = data.split(sign);
        for (int i = 0; i < tempData.length; i++) {
            keysWithQuotationMark.add(tempData[i]);
        }

        List<String> keys = new ArrayList<String>();
        // 此时得到的key值列表还是带有双引号的，下边的循环把双引号去掉
        for (int i = 0; i < keysWithQuotationMark.size(); i++) {
            String eachKey = (String) keysWithQuotationMark.get(i);
            String key = null;
            if (eachKey.length() != 0
                    && eachKey.substring(0, 1).equalsIgnoreCase("\"")) {
                eachKey = eachKey.substring(1, eachKey.length());
            }
            if (eachKey.length() != 0
                    && eachKey.substring(eachKey.length() - 1)
                    .equalsIgnoreCase("\"")) {
                eachKey = eachKey.substring(0, eachKey.length() - 1);
            }
            key = eachKey;

            keys.add(key);
        }
        return keys;
    }

    /**
     * 判断字符串为null或者为""
     *
     * @param value 要判断的字符串
     * @return 是否为null或者为""
     */
    public static boolean isNullorBlank(String value) {
        return null == value || "".equals(value);
    }

    /**
     * 去掉指定字符串两端的空格
     *
     * @param value 指定的字符串
     * @return 去掉两端空格后的字符串。如果传入的指定字符串是null，返回""。
     */
    public static String trim(String value) {
        if (value == null) {
            return "";
        } else {
            return value.trim();
        }
    }

    /**
     * 将指定字符串的两端加上单引号"'"
     *
     * @param value 指定的字符串
     * @return 加过单引号的字符串，如果传入的字符串是null，返回null。
     */
    public static String sem(String value) {
        if (value == null) {
            return null;
        } else {
            return "'" + value + "'";

        }
    }

    /**
     * 将指定的数字转化为指定长度的字符串，多余部分用"#"填充。例如：intToStrWithSharp(1000, 6)->"##1000"
     *
     * @param value  要转换的整数
     * @param length 转换后的字符串长度
     * @return 转换后的字符串，如果指定的长度小于整数的位数，则只返回数字。例如：intToStrWithSharp(1000,
     * 2)->"1000"
     */
    public static String intToStrWithSharp(Integer value, int length) {
        int valueLength = value.toString().length();
        int diff = length - valueLength;

        if (value.intValue() < Integer.MAX_VALUE) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < diff; i++) {
                sb.append('#');
            }
            sb.append(value.intValue());
            return sb.toString();
        } else {
            return "-1";
        }
    }

    /**
     * 判断一个String对象是否为null，为null返回""，否则返回str自身。
     *
     * @param str 要判断的String对象
     * @return str自身或""
     */
    public static String getEmptyStringIfNull(String str) {
        if (str == null)
            return "";
        return str;
    }

    /**
     * 将一个byte数组转换为字符串
     *
     * @param arr 要转换的byte数组
     * @return 转换好的字符串，如果数组的length=0，则返回""。
     */
    public static String bytetoString(byte[] arr) {
        String str = "";
        String tempStr = "";
        for (int i = 1; i < arr.length; i++) {
            tempStr = (Integer.toHexString(arr[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            } else {
                str = str + tempStr;
            }
        }
        return str;
    }

    /**
     * 分析字符串得到Integer.
     *
     * @param str1 String
     * @return Integer
     */
    public static Integer myparseIntObj(String str1) {
        try {
            if (isBlank(str1)) {
                return null;
            } else {
                // 16进制
                if (str1.startsWith("0x")) {
                    String sLast = str1.substring(2);
                    return Integer.valueOf(sLast, 16);
                } else {
                    return Integer.valueOf(str1);
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 分析一个字符串,得到一个整数,如果错误,设置为缺省值-1.
     *
     * @param str1 String
     * @return int
     */
    public static int myparseInt(String str1) {
        return parseStringToIntOrSetDefaultValue(str1, -1);
    }

    /**
     * 分析一个字符串,得到一个整数,如果错误,设置为缺省值. 如果一个字符串以0x开头,则认为是16进制的.
     *
     * @param str1     字符串
     * @param nDefault 缺省值
     * @return int
     */
    public static int parseStringToIntOrSetDefaultValue(String str1, int nDefault) {
        int result;
        try {
            if (isBlank(str1)) {
                result = nDefault;
            } else {
                // 16进制
                if (str1.startsWith("0x")) {
                    String sLast = str1.substring(2);
                    result = Integer.parseInt(sLast, 16);
                } else {
                    result = Integer.parseInt(str1);
                }
            }
        } catch (NumberFormatException e) {
            result = nDefault;
        }
        return result;
    }

    /**
     * 分析一个字符串得到float,如果错误,设置一个缺省值-1.
     *
     * @param str1 String
     * @return float
     */
    public static float myparseFloat(String str1) {
        return myparseFloat(str1, -1);
    }

    /**
     * 分析一个字符串得到float,如果错误,设置一个缺省值.
     *
     * @param str1     String
     * @param nDefault 缺省值
     * @return float
     */
    public static float myparseFloat(String str1, float nDefault) {
        float result;
        try {
            result = isBlank(str1) ? nDefault : Float.parseFloat(str1);
        } catch (NumberFormatException e) {
            result = nDefault;
        }
        return result;
    }

    /**
     * 分析一个字符串得到Float,如果错误,返回null.
     *
     * @param str1 String
     * @return Float(may be null)
     */
    public static Float myparseFloatObj(String str1) {
        try {
            if (isBlank(str1)) {
                return null;
            } else {
                return Float.valueOf(str1);
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 分析一个字符串得到double,如果错误,设置一个缺省值.
     *
     * @param str1     String
     * @param nDefault 缺省值
     * @return double
     */
    public static double parseStringToDouble(String str1, double nDefault) {
        double result;
        try {
            result = isBlank(str1) ? nDefault : Double.parseDouble(str1);
        } catch (NumberFormatException e) {
            result = nDefault;
        }
        return result;
    }

    /**
     * 分析一个字符串得到long,如果错误,设置一个缺省值 -1.
     *
     * @param str1 String
     * @return long
     */
    public static long myparseLong(String str1) {
        return myparseLong(str1, -1);
    }

    /**
     * 分析一个字符串得到long,如果错误,设置一个缺省值 .
     *
     * @param str1     字符串
     * @param nDefault 缺省值
     * @return long
     */
    public static long myparseLong(String str1, long nDefault) {
        long result;
        try {
            result = isBlank(str1) ? nDefault : Long.parseLong(str1);
        } catch (NumberFormatException e) {
            result = nDefault;
        }
        return result;
    }

    /**
     * 分析一个字符串得到Long,如果错误,返回null .
     *
     * @param str1 字符串
     * @return Long
     */
    public static Long myparseLongObj(String str1) {
        try {
            if (isBlank(str1)) {
                return null;
            } else {
                // 16进制
                if (str1.startsWith("0x")) {
                    String sLast = str1.substring(2);
                    return Long.valueOf(sLast, 16);
                } else {
                    return Long.valueOf(str1);
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 为显示/编辑而转换串值，将空对象转换为空串.
     *
     * @param astr 字符串的值
     * @return 如果字符串为空, 则返回空串(不是null), 如果不空, 原样返回
     */
    public static String getShowStr(String astr) {
        return (null == astr) ? "" : astr;
    }

    /**
     * 返回一个字符串的n次组合后的字符串.
     *
     * @param sStr  原字符串
     * @param nRate 次数
     * @return 组合好的字符串
     */
    public static String getManyStr(String sStr, int nRate) {
        StringBuffer strBF = new StringBuffer();
        for (int i = 0; i < nRate; i++) {
            strBF.append(sStr);
        }
        return strBF.toString();
    }

    /**
     * 格式化数字:返回定长的字符串.
     *
     * @param aNum    格式化的数字
     * @param aLength 长度
     * @return 格式化好的字符串.
     */
    public static String formatNumber(int aNum, int aLength) {
        String sNum = Integer.toString(aNum);

        int nLength = aLength - sNum.length();
        if (nLength < 1) {
            return sNum;
        }

        for (int i = 1; i <= nLength; i++) {
            sNum = "0" + sNum;
        }
        return sNum;
    }

    /**
     * 根据格式输出浮点数的字符串.
     *
     * @param aFloat 浮点数
     * @param nSyo   字符串格式,参考NumberFormat的说明.
     * @return String
     */
    public static String getShowFloat(float aFloat, String nSyo) {
        NumberFormat astr = NumberFormat.getInstance();
        ((DecimalFormat) astr).applyPattern(nSyo);

        return astr.format(aFloat);
    }

    /**
     * 从属性里面读取一个字符串出来,如果空,返回缺省值.
     *
     * @param aPROP    属性句柄
     * @param itemName 属性名称
     * @param sDefault 缺省值
     * @return String
     */
    public static String getPROPString(PropertyResourceBundle aPROP,
                                       String itemName, String sDefault) {
        String aValue = "";
        try {
            if (null != aPROP) {
                aValue = aPROP.getString(itemName);
            }
        } catch (MissingResourceException e) {
            // donothing
        } catch (ClassCastException e) {
            // donothing
        }

        if (isTrimEmpty(aValue)) {
            aValue = sDefault;
        }
        return aValue;
    }

    /**
     * 从属性里面读取一个字符串出来,如果空,返回"".
     *
     * @param aPROP    属性句柄
     * @param itemName 属性名称
     * @return String
     */
    public static String getPROPString(PropertyResourceBundle aPROP,
                                       String itemName) {
        return getPROPString(aPROP, itemName, "");
    }

    /**
     * 翻译一个字符串到目标编码.
     * <p>
     * 如果缺省编码为空,则设置缺省编码为源编码.
     *
     * @param aStr           源字符串
     * @param sDefaultEncode 缺省编码
     * @param srcCharSet     源编码
     * @param destCharSet    目标编码
     * @return 编码后的字符串
     */
    public static String getEXTCHARSETString(String aStr,
                                             String sDefaultEncode, String srcCharSet, String destCharSet) {
        String lastDefaultEncode = sDefaultEncode;
        String strTemp = null;

        try {
            strTemp = aStr;

            if (isBlank(lastDefaultEncode)) {
                lastDefaultEncode = srcCharSet;
            }
            // 如果源字符集不等于目标字符集
            if (!(sDefaultEncode.equalsIgnoreCase(destCharSet))) {
                if (strTemp != null) {
                    if (isTrimEmpty(lastDefaultEncode)) {
                        strTemp = new String(strTemp.getBytes(), destCharSet);
                    } else {
                        strTemp = new String(strTemp
                                .getBytes(lastDefaultEncode), destCharSet);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return (strTemp == null) ? "" : strTemp;
    }


    /**
     * 字符串是否为空:null或者长度为0.
     *
     * @param astr 源字符串.
     * @return boolean
     */
    public static boolean isBlank(String astr) {
        return ((null == astr) || (astr.length() == 0));
    }

    /**
     * 去掉左右空格后字符串是否为空.
     *
     * @param astr String
     * @return boolean
     */
    public static boolean isTrimEmpty(String astr) {
        if ((null == astr) || (astr.length() == 0)) {
            return true;
        }
        if (isBlank(trim(astr))) {
            return true;
        }
        return false;
    }

    /**
     * 按照规则拆分字符串到字符串数组中.
     *
     * @param pattern 拆分规则,格式为: /,/
     * @param aStr    要拆分的字符串
     * @return 拆分后的字符串数组
     */
    public static String[] splitString(String pattern, String aStr) {
        List<Object> alist = new ArrayList<Object>();
        Perl5Util util = new Perl5Util();
        util.split(alist, pattern, aStr);
        String[] astrs = new String[alist.size()];
        alist.toArray(astrs);
        return astrs;
    }

    /**
     * 按照规则拆分字符串到字符串数组中.
     *
     * @param pattern 拆分规则,格式为: /,/
     * @param aStr    要拆分的字符串
     * @param nLimit  拆分次数
     * @return 拆分后的字符串数组
     */
    public static String[] splitString(String pattern, String aStr, int nLimit) {
        List<Object> alist = new ArrayList<Object>();
        Perl5Util util = new Perl5Util();
        util.split(alist, pattern, aStr, nLimit);
        String[] astrs = new String[alist.size()];
        alist.toArray(astrs);
        return astrs;
    }

    /**
     * 拆分字符串到数组列表中.
     *
     * @param pattern 拆分规则
     * @param aStr    要拆分的字符串
     * @return 拆分后的数组列表
     */
    @SuppressWarnings("unchecked")
    public static List splitStrToList(String pattern, String aStr) {
        List alist = new ArrayList();
        Perl5Util util = new Perl5Util();
        util.split(alist, pattern, aStr);
        return alist;
    }

    /**
     * 拆分字符串为一维字符串数组
     *
     * @param str  要拆分的字符串
     * @param sign 字符串拆分的标识符
     * @return strData 拆分后的一维字符串数组
     */
    public static String[] splitStrToArray(String str, String sign) {
        String[] strData = null;
        StringTokenizer st1 = new StringTokenizer(str, sign);
        strData = new String[st1.countTokens()];
        int i = 0;
        while (st1.hasMoreTokens()) {
            strData[i] = st1.nextToken().trim();
            i++;
        }
        return strData;
    }

    /**
     * 截取一定长度的字符串,根据指定的编码来判断长度. 例如指定编码为GBK,则一个汉字为2个字符长度
     *
     * @param astr       String
     * @param nlength    int
     * @param destEncode String
     * @return String
     */
    public static String msubstr(String astr, int nlength, String destEncode) {
        byte[] mybytes;
        try {
            mybytes = astr.getBytes(destEncode);

            if (mybytes.length <= nlength) {
                return astr;
            }
            if (nlength < 1) {
                return "";
            }

            for (int i = 0; i < astr.length(); i++) {
                String aTestStr = astr.substring(0, i + 1);
                int nDestLength = aTestStr.getBytes(destEncode).length;
                if (nDestLength > nlength) {
                    if (i == 0) {
                        return "";
                    } else {
                        return astr.substring(0, i);
                    }
                }
            }

            return astr;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 返回带省略标记的截断的字符串.
     *
     * @param astr     源字符串
     * @param nlength  截断的长度
     * @param aDot     后缀
     * @param encoding 编码
     * @return String
     */
    public static String getDotMsubstr(String astr, int nlength, String aDot,
                                       String encoding) {
        byte[] mybytes = astr.getBytes();

        // if not long enough,return old string
        if (mybytes.length <= nlength) {
            return astr;
        }

        int nLastLen = nlength - aDot.length();

        if (nLastLen < 1) {
            nLastLen = 1;

        }
        return msubstr(astr, nLastLen, encoding) + aDot;

    }

    /**
     * 得到字符串的字符长度 按照指定编码测定.
     *
     * @param astr        String
     * @param sDestEncode String
     * @return int 返回字符长度
     */
    public static int mlength(String astr, String sDestEncode) {
        try {
            return astr.getBytes(sDestEncode).length;
        } catch (UnsupportedEncodingException e) {
            return astr.getBytes().length;
        }
    }

    /**
     * 连接2个字符串.
     *
     * @param aOriStr   源字符串
     * @param aLinkSign 连接标记
     * @param aLinkStr  要连接的字符串
     * @return String
     */
    public static String link2Str(String aOriStr, String aLinkSign,
                                  String aLinkStr) {
        if (isBlank(aOriStr)) {
            return aLinkStr;
        } else {
            return aOriStr + aLinkSign + aLinkStr;
        }
    }

    /**
     * 连接字符串数组.
     *
     * @param astrBf StringBuffer
     * @param aryStr String[]
     * @return StringBuffer
     */
    public static StringBuffer linkAryStr(StringBuffer astrBf, String[] aryStr) {
        for (int i = 0; i < aryStr.length; i++) {
            astrBf.append(aryStr[i]);
        }
        return astrBf;
    }

    /**
     * 连接字符串数组.
     *
     * @param aryStr String[]
     * @param sSign  String
     * @return String
     */
    public static String linkAryStr(String[] aryStr, String sSign) {
        StringBuffer asbf = new StringBuffer();
        if (null == aryStr) {
            return asbf.toString();
        }

        for (int i = 0; i < aryStr.length; i++) {
            if (i > 0) {
                asbf.append(sSign);
            }
            asbf.append(aryStr[i]);
        }
        return asbf.toString();
    }

    /**
     * 字符串替换,用dest替换astr里面的Sign.
     *
     * @param str1 源字符串
     * @param sign 要被替换的标记
     * @param dest 替换后的标记
     * @return String
     */
    public static String replace(String str1, String sign, String dest) {
        String astr = str1;
        if (isBlank(astr)) {
            return "";
        }

        Perl5Util util = new Perl5Util();
        String lastSign = sign.replace('`', ' ');
        // astr = util.substitute("s",astr)
        String lastDest = dest.replace('`', ' ');
        astr = util.substitute("s`" + lastSign + "`" + lastDest + "`g", astr);

        return astr;
    }

    /**
     * 把用户输入的内容原样显示,但是html按照html格式显示. 对应原版的toTextSee. 其中包含处理回车,空格,Tab等,不翻译
     * <为&lt;
     *
     * @param str1 String
     * @return String
     */
    public static String str2TextShow(String str1) {
        String astr = str1;
        if (isBlank(astr)) {
            return "";
        }

        Perl5Util util = new Perl5Util();
        astr = util.substitute("s#\t#&nbsp;&nbsp;#g", astr);
        astr = util.substitute("s#\r##g", astr);

        astr = util.substitute("s#\n{3,}#\n\n#g", astr);
        astr = util.substitute("s#^\n+#\n#g", astr);
        // astr = util.substitute("s/\\\n/\\n/g", astr);

        astr = util.substitute("s#\n#<br>#g", astr);

        // astr = util.substitute("s#\\s\\s\\s#&nbsp;&nbsp;&nbsp;#g", astr);
        astr = util.substitute("s#\\s\\s\\s#&nbsp; &nbsp;#g", astr);
        astr = util.substitute("s#\\s\\s#&nbsp;&nbsp;#g", astr);

        // $str=nl2br($str);
        return (astr);

    }

    /**
     * 把字符串处理,完全按照输入时的原样显示. html格式的部分也处理为文本.
     *
     * @param aStr String
     * @return String
     */
    public static String str2PureTextShow(String aStr) {
        return str2TextShow(str2TextHtml(aStr));
    }

    /**
     * 把字符串处理,方便编辑.
     *
     * @param aStr String
     * @return String
     */
    public static String str2InputTextEdit(String aStr) {
        return getShowStr(str2InputText(aStr));
    }

    /**
     * 返回字符串,编码处理,主要用于html链接的值.
     *
     * @param str1 String
     * @return String
     */
    public static String str2URLValue(String str1) {
        String astr = str1;
        if (isBlank(astr)) {
            return "";
        }
        // 上面注释掉的是原有的程序
        // Perl5Util util = new Perl5Util();
        // astr = util.substitute("s#\"#\\%22#g", astr);
        // 直接编码
        // astr = urlEncode(astr);

        astr = str2TextHtmlRidSpace(astr);

        return astr;
    }

    /**
     * 返回字符串,编码处理,主要用于html链接内部函数的值. 例如javascript的参数
     * <p>
     * %22 => " %3c => < %3e => > &amp; =>& %20 =>空格
     *
     * @param str1 String
     * @return String
     */
    public static String str2JSUrlFuncValue(String str1) {
        String astr = str1;
        if (isBlank(astr)) {
            return "";
        }
        Perl5Util util = new Perl5Util();
        astr = util.substitute("s#\"#%22#g", astr);
        astr = util.substitute("s/</%3c/g", astr);
        astr = util.substitute("s/>/%3e/g", astr);
        astr = util.substitute("s/&/&amp;/g", astr);
        astr = util.substitute("s/ /%20/g", astr);
        return astr;
    }

    /**
     * 返回字符串,编码处理,主要用于onclick,onchange等javascript的值.
     * <p>
     * 引号被替换为 \&quot;
     *
     * @param str1 String
     * @return String
     */
    public static String str2JSCommValue(String str1) {
        String astr = str1;
        if (isBlank(astr)) {
            return "";
        }

        Perl5Util util = new Perl5Util();
        astr = util.substitute("s/&/&amp;/g", astr);
        astr = util.substitute("s#<#&lt;#g", astr);
        astr = util.substitute("s/>/&gt;/g", astr);
        // astr = util.substitute("s/ /&nbsp;/g", astr);
        // 引号被替换为 \&quot;
        astr = util.substitute("s/\"/\\\\&quot;/g", astr);
        return astr;
    }

    /**
     * 转换字符串, 用于普通xml赋值. < replaced with &lt; > replaced with &gt; & replaced
     * with &amp; " replaced with &quot; ' replaced with &apos;
     *
     * @param str1 String
     * @return String
     */
    public static String str2TextXML(String str1) {
        String astr = str1;
        if (isBlank(astr)) {
            return "";
        }

        Perl5Util util = new Perl5Util();
        astr = util.substitute("s/&/&amp;/", astr);
        astr = util.substitute("s#<#&lt;#g", astr);
        astr = util.substitute("s/>/&gt;/g", astr);
        astr = util.substitute("s/\"/&quot;/", astr);
        astr = util.substitute("s/'/&apos;/", astr);
        return astr;
    }


    /**
     * 返回字符串:用于input表单元素的value:对应toHtml_in.
     *
     * @param str1 String
     * @return String
     */
    public static String str2InputText(String str1) {
        String astr = str1;
        if (isBlank(astr)) {
            return "";
        }
        Perl5Util util = new Perl5Util();
        astr = util.substitute("s/&/&amp;/", astr);
        astr = util.substitute("s#<#&lt;#g", astr);
        astr = util.substitute("s/>/&gt;/g", astr);
        // astr = util.substitute("s/ /&nbsp;/", astr);
        astr = util.substitute("s/\"/&quot;/g", astr);
        return astr;
    }

    /**
     * 把html中的特殊字符翻译为和显示和输入的一样:原版对应toHtml_all.
     *
     * @param astr String
     * @return String
     */
    public static String str2TextHtml(String astr) {
        if (isBlank(astr)) {
            return "";
        }
        String result = astr;
        Perl5Util util = new Perl5Util();
        result = util.substitute("s/&/&amp;/g", result);
        result = util.substitute("s#<#&lt;#g", result);
        result = util.substitute("s/>/&gt;/g", result);
        result = util.substitute("s/ /&nbsp;/g", result);
        result = util.substitute("s/\"/&quot;/g", result);
        return result;
    }

    /**
     * 把html中的特殊字符翻译为和显示和输入的一样:但不翻译空格 .
     *
     * @param astr String
     * @return String
     */
    public static String str2TextHtmlRidSpace(String astr) {
        if (isBlank(astr)) {
            return "";
        }

        String result = astr;

        Perl5Util util = new Perl5Util();
        result = util.substitute("s/&/&amp;/g", result);
        result = util.substitute("s#<#&lt;#g", result);
        result = util.substitute("s/>/&gt;/g", result);
        // result = util.substitute("s/ /&nbsp;/g", result);
        result = util.substitute("s/\"/&quot;/g", result);
        return result;
    }

    /**
     * 把字符串中的双引号替换为\",用作html元素的赋值,例如把这个值放在""中间时.
     *
     * @param str1 String
     * @return String
     */
    public static String str2HtmlValue(String str1) {
        String sStr = str1;
        if (isBlank(sStr)) {
            return "";
        }

        Perl5Util util = new Perl5Util();
        sStr = util.substitute("s/\"/\\\\\"/g", sStr);
        return sStr;
    }

    /**
     * 在用作sql的字符串替换字符串中的单引号为两个单引号.
     * <p>
     * 如果是查询组合Sql时需要,请参考CommSQLFunc类
     *
     * @param str1 String
     * @return String
     */
    public static String str2TextSql(String str1) {
        String astr = str1;
        if (isBlank(astr)) {
            return "";
        }

        Perl5Util util = new Perl5Util();
        astr = util.substitute("s/\'/\'\'/g", astr);
        return astr;
    }

    /**
     * if a String identify "true".
     *
     * @param aPropString 字符串
     * @return true if y,yes,true,1
     */
    public static boolean isTrueString(String aPropString) {
        String strTemp = aPropString.toLowerCase(Locale.US);
        return (strTemp.startsWith("true") || strTemp.startsWith("yes")
                || strTemp.startsWith("1") || strTemp.startsWith("y"));
    }

    /**
     * 将字符串里指定的字符串被代替字符串所替换 例如 指定的字符串 "sdupipo" 将里面出现p的字符穿都变成g
     *
     * @param source  指定字符串
     * @param target  要被代替的字符串
     * @param replace 代替字符串
     * @return 替换后的字符串
     */
    public static String stringReplace(String source, String target,
                                       String replace) {
        if (source != null && target != null && replace != null) {
            StringBuffer stringbuffer = new StringBuffer(source.length() + 256);

            int i = -1;
            do {
                i++;

                i = source.indexOf(target);
                if (i > -1) {
                    stringbuffer.append(source.substring(0, i));

                    stringbuffer.append(replace);

                    source = source.substring(i + target.length());
                }
            } while (i != -1);

            stringbuffer.append(source);

            return stringbuffer.toString();
        } else {
            return source;
        }
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isNullString(String str) {
        if (str == null || str.trim().length() < 1)
            return true;
        else
            return false;
    }

    /**
     * 把源字符串的第一字符串去掉然后添加要替换的字符串
     * <p>
     * 例如："a,b,c"要把a去掉然后往后面添加d变成"b,c,d" source的大小必须和count相等
     *
     * @param source  源字符串
     * @param replace 要替换的字符串
     * @param count   要替换的大小
     * @return 返回替换后的字符串
     */
    public static String convertString(String source, String replace, int count) {
        String[] listSource = source.split(",");

        String resultStr = "";

        if (null != source && source.equals("")) {
            resultStr = replace;

            return resultStr;
        }

        if (listSource.length == count) {
            for (int i = 0; i < listSource.length; i++) {
                if (i == count - 1) {
                    listSource[i] = replace;

                    resultStr += listSource[i];
                } else {
                    listSource[i] = listSource[i + 1];

                    resultStr += listSource[i] + ",";
                }
            }
        } else if (listSource.length < count) {
            resultStr = source + "," + replace;
        }

        return resultStr;
    }

    /**
     * 把html中的特殊字符翻译为和显示和输入的一样:原版对应toHtml_all.
     *
     * @param astr String
     * @return String
     */
    public static String str2DefaultTextHtml(String astr) {
        if (isBlank(astr)) {
            return "";
        }
        // String result = astr;
        // Perl5Util util = new Perl5Util();
        // result = util.substitute("s/&/&amp;/g", result);
        // result = util.substitute("s/</&lt;#g", result);
        // result = util.substitute("s/>/&gt;/g", result);
        // result = util.substitute("s/ /&nbsp;/g", result);
        // result = util.substitute("s/\"/&quot;/g", result);
        // astr = str2TextHtml(astr);
        String r = HtmlUtils.htmlEscape(astr);
        Perl5Util util = new Perl5Util();

        r = util.substitute("s/ /&nbsp;/g", r);
        util = null;
        return r;
    }

    /**
     * 将字节进行 KB MB GB换算
     *
     * @param size 字节数
     * @return 换算后的字符串
     */
    public static String changeCapacities(long size) {
        long returns = 0;
        String den = "B";
        if (size < 1024) {
            returns = size;
        } else if (size >= 1024 && size < 1048576) {
            returns = size / 1024;
            den = "KB";
        } else if (size >= 1048576 && size < 1073741824) {
            returns = size / 1024 / 1024;
            den = "MB";
        } else {
            returns = size / 1024 / 1024 / 1024;
            den = "GB";
        }
        return new String(returns + den);
    }

    public static Document getDocument(String message) {
        Document doc = null;
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(new ByteArrayInputStream(message
                    .getBytes("utf-8")));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doc;
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isLetterOrInteger(String str) {
        Pattern pattern = Pattern.compile("[0-9A-Za-z]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isEmail(String str) {
        Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 此方法已经不建议使用，请使用checkMobileNumber(String mobile)<br>
     * 判断是否是移动手机号码 如果输入的是11位号码 正则验证 验证通过 return "" 验证未通过 继续判断是14701开头 如果是
     * 继续判断14701之后的号码是否是数字 如是 返回""; 否 返回1 表示 移动号码开头 2表示非移动号码 3 错误
     *
     * @param str
     * @return ""是移动号码
     */
    @Deprecated
    public static String checkMobile(String str) {
        if (str.length() == 11) {
            Pattern pattern = Pattern
                    .compile("^(((134[0-9]{1})|(135[0-9]{1})|(136[0-9]{1})|(137[0-9]{1})|(138[0-9]{1})|(139[0-9]{1})|(150[0-9]{1})|(151[0-9]{1})|(152[0-9]{1})|(157[0-9]{1})|(158[0-9]{1})|(159[0-9]{1})|(188[0-9]{1}))+\\d{7})$");
            Matcher isNum = pattern.matcher(str);
            if (isNum.matches()) {
                return "";
            }
            if (str.substring(0, 5).equals("14701")) {
                if (isInteger(str.substring(5, str.length()))) {
                    return "";
                } else {
                    return "1";
                }
            }
        } else {
            if (str.length() >= 3) {
                String tmps = str.substring(0, 3);
                if (tmps.equals("134") || tmps.equals("135")
                        || tmps.equals("136") || tmps.equals("137")
                        || tmps.equals("138") || tmps.equals("139")
                        || tmps.equals("150") || tmps.equals("151")
                        || tmps.equals("152") || tmps.equals("157")
                        || tmps.equals("158") || tmps.equals("159")) {
                    return "1";
                } else {
                    if (str.length() >= 5) {
                        if (str.substring(0, 5).equals("14701")) {
                            return "1";
                        }
                    }
                    return "2";
                }
            } else {
                return "2";
            }
        }
        return "3";
    }

    /**
     * 验证移动手机号
     *
     * @param mobile
     * @return 0：非移动手机号；1:是移动号码；2：手机号不完整；
     * @author zhoushijun
     * @since 2009-12-2
     */
    public static int checkMobileNumber(String mobile) {
        if (mobile.length() == 11) {
            Pattern pattern = Pattern
                    .compile("^((134|135|136|137|138|139|150|151|152|157|158|159|188|147|187)+\\d{8})$");
            Matcher isNum = pattern.matcher(mobile);
            if (isNum.matches()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 2;
        }
    }

    public static boolean isChineaseLetter(String name) {
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]*$");
        Matcher isNum = pattern.matcher(name);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 返回url参数的最后一个参数
     *
     * @param url
     * @return
     */

    public static String getEndOfUrlParams(String url) {
        String[] split = url.split("=");
        return split[split.length - 1];
    }

    public static String getEndOfNumParams(String url) {
        String[] split = url.split("-");
        String s = split[split.length - 1];
        return s.split("\\.")[0];
    }

    public static String getPrefixNumberText(String str) {
        String num = "";
        try {
            Pattern pattern = Pattern.compile("[^\\d]+([\\d]+)[^\\d]+.*");
            Matcher isNum = pattern.matcher(str);
            boolean b = isNum.find();
            if (b) {
                num = isNum.group(1);
            } else {
                num = "-1";
            }
        } catch (Exception e) {
            num = "-1";
        }
        return num;
    }

    public static String getNumberText(String str) {
        if (StringUtils.isBlank(str)) {
            throw new RuntimeException("参数str不能为空");
        }
        StringBuffer number = new StringBuffer("");

        String[] strArray = str.split("");
        for (String string : strArray) {
            if (!StringUtils.isBlank(string) && RegUtils.isNumberText(string)) {
                number.append(string);
            }
        }
        return number.toString();
    }

    public static int getIntValue(String str) {
        int r = -1;
        if (str != null && str.length() != 0) {
            StringBuffer bf = new StringBuffer();
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c >= '0' && c <= '9') {
                    bf.append(c);
                } else if (c == ',') {
                    continue;
                } else {
                    if (bf.length() != 0) {
                        break;
                    }
                }
            }
            try {
                r = Integer.parseInt(bf.toString());
            } catch (Exception e) {
                r = -1;
            }
        }
        return r;
    }

    public static double getDoubleValue(String str) {
        double d = -1;
        if (str != null && str.length() != 0) {
            StringBuffer bf = new StringBuffer();
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c >= '0' && c <= '9') {
                    bf.append(c);
                } else if (c == '.') {
                    if (bf.length() == 0) {
                        continue;
                    } else if (bf.indexOf(".") != -1) {
                        break;
                    } else {
                        bf.append(c);
                    }
                } else {
                    if (bf.length() != 0) {
                        break;
                    }
                }
            }
            try {
                d = Double.parseDouble(bf.toString());
            } catch (Exception e) {
                d = -1;
            }
        }

        return d;
    }


    public static void main(String[] args) {

        String url = "https://liansai.500.com/team/4285/teamlineup";
        String[] split1 = url.split("/");
        String s2 = split1[split1.length - 2];
        System.out.println(s2);

        String sheng = "";
        int prefixNumberText = getIntValue(sheng);
        System.out.println(prefixNumberText);
        String doubleString = "se";
        String doubleString2 = "";
        double doubleValue = getDoubleValue(doubleString);
        double doubleValue1 = getDoubleValue(doubleString2);

        /*String s1 = getPrefixNumberText("双方近次交战，横滨，进10球，失6球，大球3次，小球3次");
        String s = "双方近次交战，横滨，进10球，失6球，大球3次，小球3次";
        String[] split = s.split("，");
        Pattern pattern = Pattern.compile("[^\\d]+([\\d]+)[^\\d]+.*");
        Matcher isNum = pattern.matcher(split[split.length-1]);
        boolean b = isNum.find();
        if(b) {
            String group = isNum.group(1);
            System.out.println(group);
        }
        String content = "双方近次交战740981双方近次交战30双方近次交战";
        Pattern p = Pattern.compile("[^\\d]+([\\d]+)[^\\d]+.*");
        Matcher m = p.matcher(content);
        boolean result = m.find();
        String find_result = null;
        if (result) {
            find_result = m.group(1);
        }
        System.out.println(find_result);*/
        String start = "2020-01-02";
        String end = "2020-01-09";
        double v = -1 / 100.0;
        Date startDate = DateUtils.strToDate(start, "yyyy-MM-dd");
        Date endDate = DateUtils.strToDate(end, "yyyy-MM-dd");
        Integer intevalDays = DateUtils.getIntevalDays(startDate, endDate);
        System.out.println(intevalDays);
    }
}