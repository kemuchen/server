package cn.framework.util.sys;


import cn.framework.cache.CacheMangerImplements;
import cn.framework.common.entity.BaseEntity;
import cn.framework.system.dao.auto.entity.SysConfigEntity;
import cn.framework.system.dao.auto.entity.SysDictEntity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName Util
 * @Desc
 * @Author kelei
 * @Date 2019/7/8 11:05
 * @Version 1.0
 */
public class SysUtil {

    /**
     * 数据库字符集
     */
    private static final String DB_CHARSET = "GBK";

    /**
     * 日志打印对象
     */
    static Logger logger = LoggerFactory.getLogger(SysUtil.class);

    /**
     * 驼峰转下划线
     */
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     */
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 判断对象是否为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        // 对象为空，返回false
        if (object == null) {
            return true;
        }

        // 如果对象是字符串，则判断对象是否为空串
        if (object instanceof String && "".equals(object)) {
            return true;
        }

        // 对象是map，则判断map是否为空
        if (object instanceof Map && ((Map<?, ?>) object).isEmpty()) {
            return true;
        }

        // 对象是list，则判断list.size是否为0
        if (object instanceof List && ((List<?>) object).size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取字符串的数据库长度
     *
     * @param str
     * @return
     */
    public static int getDbLength(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return str.getBytes(DB_CHARSET).length;
        } catch (UnsupportedEncodingException e) {
            return str.getBytes().length;
        }
    }

    /**
     * 本地图片转换成base64字符串
     *
     * @param imgFile 图片本地路径
     * @return
     */
    public static String imageToBase64(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data);// 返回Base64编码过的字节数组字符串
        } catch (IOException e) {
            logger.error("【图片Base64转换】错误：" + e);
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("【图片Base64转换】错误：" + e);
                }
            }
        }
    }

    /**
     * base64字符串转换成图片
     *
     * @param imgStr      base64字符串
     * @param imgFilePath 图片存放路径
     * @return
     */
    public static boolean base64ToImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
        if (SysUtil.isEmpty(imgStr)) { // 图像数据为空
            return false;
        }
        OutputStream out = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            File file = new File(imgFilePath.substring(0, imgFilePath.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            return true;
        } catch (Exception e) {
            logger.error("【Base64图片转换】错误：" + e);
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("【Base64图片转换】错误：" + e);
                }
            }
        }
    }

    /**
     * @param ：@param  string
     * @param ：@param  defaultString
     * @param ：@return
     * @return ：String
     * @throws
     * @Title：nvl
     * @Description：相当于oracle的nvl函数
     */
    public static String nvl(String string, String defaultString) {
        if (isEmpty(string)) {
            return defaultString;
        } else {
            return string;
        }
    }

    /**
     * 删除文件夹里的数据（默认里面没有文件夹）
     *
     * @param dir
     */
    public static void deleteDir(File dir) {
        File[] files = dir.listFiles();
        int len = files.length;
        for (int i = 0; i < len; i++) {
            files[i].delete();
        }
    }

    /**
     * 获取字符串的编码格式
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    /**
     * @Description: 字符串过滤
     * @Params: [str]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2019/9/26 9:50
     */
    public static String decodeBy10(String str) {
        //删除所有的空格
        str = str.replaceAll("[\\pP|~|$|^|<|>|\\||\\+|=]*", "");
        return str;
    }

    /**
     * @Description: 生成二维码
     * @Params: [text, width, height, filePath]
     * @return: void
     * @Author: 柯雷
     * @Date: 2019/10/25 9:31
     */
    public static void generateQRCode(String text, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 256, 256);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    /**
     * @param a 被除数
     * @param b 除数
     * @return 商
     * @date 2018-4-17下午2:24:48
     */
    public static String division(int a, int b) {
        DecimalFormat df = new DecimalFormat("0.00");//设置保留位数
        return df.format((float) a / b);
    }

    /**
     * @Description:
     * @Params: [xml]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2019/8/7 12:57
     */
    public static String formatXML(String xml) {
        String requestXML = null;
        try {
            // 拿取解析器
            SAXReader reader = new SAXReader();
            Document document = reader.read(new StringReader(xml));
            if (null != document) {
                StringWriter stringWriter = new StringWriter();
                // 格式化,每一级前的空格
                OutputFormat format = new OutputFormat("    ", true);
                // xml声明与内容是否添加空行
                format.setNewLineAfterDeclaration(false);
                // 是否设置xml声明头部
                format.setSuppressDeclaration(false);
                // 是否分行
                format.setNewlines(true);
                XMLWriter writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                writer.close();
                requestXML = stringWriter.getBuffer().toString();
            }
            return requestXML;
        } catch (Exception e) {
            System.out.println("格式化xml，失败 --> {}" + e);
            return null;
        }
    }

    /**
     * @Description: map 转xml
     * @Params: [map]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2019/8/7 12:58
     */
    public static String parseMap(Map<?, ?> map, StringBuffer sb) {
        Set<?> set = map.keySet();
        for (Iterator<?> it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (value instanceof HashMap) {
                sb.append("<" + key + ">");
                parseMap((HashMap<?, ?>) value, sb);
                sb.append("</" + key + ">");
            } else if (value instanceof ArrayList) {
                List<?> list = (ArrayList<?>) map.get(key);
                for (int i = 0; i < list.size(); i++) {
                    sb.append("<" + key + ">");
                    Map<?, ?> hm = (HashMap<?, ?>) list.get(i);
                    parseMap(hm, sb);
                    sb.append("</" + key + ">");
                }
            } else {
                sb.append("<" + key + ">" + value + "</" + key + ">");
            }
        }
        return sb.toString();
    }


    /**
     * @Description: xml转为map, map中有list（节点相同时候)，list中有map
     * @Params: [xml]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: 柯雷
     * @Date: 2019/8/7 14:57
     */
    public static Map<String, Object> parseXml(String xml) throws DocumentException {
        Map map = new HashMap();
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));//xml串第一行不能有空格，否则报错
            Element root = document.getRootElement();//得到xml文档根节点元素，即最上层的"<xml>"
            elementTomap(root, map);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @Description:
     * @Params: [outele, outmap]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: 柯雷
     * @Date: 2019/8/7 14:56
     */
    public static Map<String, Object> elementTomap(Element outele, Map<String, Object> outmap) {
        List<Element> list = outele.elements();
        int size = list.size();
        if (size == 0) {
            outmap.put(outele.getName(), outele.getTextTrim());
        } else {
            Map<String, Object> innermap = new HashMap<String, Object>();
            int i = 1;

            for (Element ele1 : list) {
                String eleName = ele1.getName();

                String value = ele1.getText();
                Object obj = innermap.get(eleName);
                if (obj == null) {
                    elementTomap(ele1, innermap);
                } else {
                    if (obj instanceof java.util.Map) {
                        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        elementTomap(ele1, innermap);
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        innermap.put(eleName, list1);
                    } else if (obj instanceof String) {

                        innermap.put(eleName + i, value);
                        i++;
                    } else {
                        elementTomap(ele1, innermap);
                        Map<String, Object> listValue = (Map<String, Object>) innermap.get(eleName);
                        ((List<Map<String, Object>>) obj).add(listValue);
                        innermap.put(eleName, obj);
                    }

                }
            }
            outmap.put(outele.getName(), innermap);
        }
        return outmap;
    }

    /**
     * @return java.lang.String
     * @Description 获取异常字符串
     * @Date 17:12 2020/3/28
     * @Param [e]
     **/
    public static String getExceptionString(Exception e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        try {
            e.printStackTrace(printStream);
            String string = new String(byteArrayOutputStream.toByteArray());
            return string;
        } catch (Exception ex) {
            logger.error("【Util.getExceptionString】获取异常字符串失败：" + e);
        } finally {
            try {
                if (printStream != null) {
                    printStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (Exception exception) {
                logger.error("【Util.getExceptionString】获取异常字符串失败：" + e);
            }
        }
        return "";
    }

    /**
     * @return java.lang.String
     * @Description 获取异常字符串
     * @Date 17:20 2020/3/28
     * @Param [e]
     **/
    public static String getExceptinLocation(Exception e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stackTraceElement.getClassName()).
                append(".").append(stackTraceElement.getMethodName()).
                append("(").append(stackTraceElement.getFileName()).
                append(":").append(stackTraceElement.getLineNumber()).append(")");
        return stringBuilder.toString();
    }

    /**
     * @return java.lang.String
     * @Description 获取异常执行方法
     * @Date 17:27 2020/3/28
     * @Param [e]
     **/
    public static String getExceptionMethod(Exception e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        return stackTraceElement.getMethodName();
    }

    /**
     * 获取访问者真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                try {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        // "***.***.***.***".length()
        if (ipAddress != null && ipAddress.length() > 15) {
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * @Description: Entity转Map
     * @Params: [entity]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: 柯雷
     * @Date: 2020/3/5 9:45
     */
    public static Map<String, Object> entityToMap(BaseEntity entity) {
        logger.info("【EntityMapUtil.entityToMap】Entity转Map:" + entity);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            // 获取Entity所有method，遍历method得到field
            Method[] methods = entity.getClass().getMethods();
            for (Method method : methods) {
                // 方法名
                String methodName = method.getName();
                // Entity的get方法
                if (methodName.startsWith("get") && !"getClass".equals(methodName) && !"getField".equals(methodName)) {
                    // 方法名去掉get后转换大小写得到字段名
                    methodName = methodName.substring(3);
                    methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
                    resultMap.put(methodName.substring(0, 1).toLowerCase() + methodName.substring(1), method.invoke(entity, new Object[]{}));
                }
            }
        } catch (Exception e) {
            logger.error("【EntityMapUtil.entityToMap】Entity转Map出错：" + e);
        }
        return resultMap;
    }

    /**
     * @Description: Map转Entity
     * @Params: [map]
     * @return: cn.framework.common.entity.BaseEntity
     * @Author: 柯雷
     * @Date: 2020/3/5 9:46
     */
    public static BaseEntity mapToEntity(Map<String, Object> map, Class<? extends BaseEntity> clazz) {
        logger.info("【EntityMapUtil.mapToEntity】Map转Entity:" + map);
        BaseEntity baseEntity = null;
        try {
            baseEntity = clazz.newInstance();
            // 遍历map
            for (String field : map.keySet()) {
                Object fieldValue = map.get(field);
                if (fieldValue != null) {
                    // 根据field得到set方法
                    String methodName = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
                    Method method;
                    try {
                        // 入参类型为integer
                        method = baseEntity.getClass().getMethod(methodName, Integer.class);
                        fieldValue = Integer.parseInt(fieldValue.toString());
                    } catch (Exception e) {
                        try {
                            // 入参类型为double
                            method = baseEntity.getClass().getMethod(methodName, Double.class);
                            fieldValue = Double.parseDouble(fieldValue.toString());
                        } catch (Exception e1) {
                            try {
                                // 入参类型为BigDecimal
                                method = baseEntity.getClass().getMethod(methodName, BigDecimal.class);
                                fieldValue = new BigDecimal(fieldValue.toString());
                            } catch (Exception e2) {
                                try {
                                    // 入参类型为Date
                                    method = baseEntity.getClass().getMethod(methodName, Date.class);
                                    fieldValue = new Date(fieldValue.toString());
                                } catch (Exception e3) {
                                    try {
                                        // 入参类型为Date
                                        method = baseEntity.getClass().getMethod(methodName, fieldValue.getClass());
                                    } catch (Exception e4) {
                                        throw new Exception(e4);
                                    }
                                }
                            }
                        }
                    }
                    method.invoke(baseEntity, new Object[]{fieldValue});
                }
            }
        } catch (Exception e) {
            logger.error("【EntityMapUtil.mapToEntity】Map转Entity出错:" + e);
        }
        return baseEntity;
    }

    /**
     * @Description: 驼峰转下划线
     * @Params: [str]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 10:37
     */
    public static String humpToLine(String str, boolean... firstIsLowerCase) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(stringBuffer);
        String string = stringBuffer.toString();
        if (firstIsLowerCase.length != 0 && firstIsLowerCase[0]) {
            string = string.substring(0, 1).toLowerCase() + string.substring(1);
        }
        return string;
    }

    /**
     * @Description: 下划线转驼峰
     * @Params: [str]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020/3/5 10:38
     */
    public static String lineToHump(String str, boolean... firstIsUpperCase) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(stringBuffer);
        String string = stringBuffer.toString();
        if (firstIsUpperCase.length != 0 && firstIsUpperCase[0]) {
            string = string.substring(0, 1).toUpperCase() + string.substring(1);
        }
        return string;
    }

    // 验证码字符集
    private static final char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z'};
    // 字符数量
    private static final int SIZE = 4;
    // 干扰线数量
    private static final int LINES = 0;
    // 宽度
    private static final int WIDTH = 80;
    // 高度
    private static final int HEIGHT = 40;
    // 字体大小
    private static final int FONT_SIZE = 30;

    /**
     * 生成随机验证码及图片 Object[0]：验证码字符串； Object[1]：验证码图片。
     */
    public static Object[] createImage() {
        StringBuffer sb = new StringBuffer();
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 2.获取图片画笔
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色
        graphic.setColor(Color.LIGHT_GRAY);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        // 5.画随机字符
        Random ran = new Random();
        for (int i = 0; i < SIZE; i++) {
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 设置随机颜色
            graphic.setColor(Color.black);
            // 设置字体大小
            graphic.setFont(new Font(null, Font.ITALIC, FONT_SIZE));
            // 画字符
            graphic.drawString(chars[n] + "", i * WIDTH / SIZE, HEIGHT * 2 / 3);
            // 记录字符
            sb.append(chars[n]);
        }
        // 6.画干扰线
        for (int i = 0; i < LINES; i++) {
            // 设置随机颜色
            graphic.setColor(Color.black);
            // 随机画线
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        // 7.返回验证码和图片
        return new Object[]{sb.toString(), image};
    }

    /**
     * 随机取色
     */
    private static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
        return color;
    }

    /**
     * @Description: 获取字典项
     * @Params: [key]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-05-04 11:03
     */
    public static List<SysDictEntity> getCacheSysDict(String dicttype) {
        List<SysDictEntity> sysConfigEntities = (List<SysDictEntity>) CacheMangerImplements.getInstance().getCacheDataByKey("SysDict");
        List<SysDictEntity> dictEntities = new ArrayList<>();
        for (SysDictEntity sysDictEntity : sysConfigEntities) {
            if (sysDictEntity.getDicttype().equals(dicttype)) {
                dictEntities.add(sysDictEntity);
            }
        }
        return dictEntities;
    }

    /**
     * @Description:
     * @Params: [dicttype]
     * @return: java.util.List<cn.framework.system.dao.auto.entity.SysDictEntity>
     * @Author: 柯雷
     * @Date: 2020-05-09 10:06
     */
    public static SysDictEntity getCacheSysDict(String dicttype, String dictcode) {
        List<SysDictEntity> sysConfigEntities = getCacheSysDict(dicttype);
        for (SysDictEntity sysDictEntity : sysConfigEntities) {
            if (sysDictEntity.getDictcode().equals(dictcode)) {
                return sysDictEntity;
            }
        }
        return null;
    }


    /**
     * @Description: 获取系统配置
     * @Params: [key]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-05-04 11:03
     */
    public static SysConfigEntity getCacheSysConfig(String key) {
        List<SysConfigEntity> sysConfigEntities = (List<SysConfigEntity>) CacheMangerImplements.getInstance().getCacheDataByKey("SysConfig");
        for (SysConfigEntity sysConfigEntity : sysConfigEntities) {
            if (sysConfigEntity.getConfig_name().equals(key)) {
                return sysConfigEntity;
            }
        }
        return null;
    }

    /**
     * 使用Cipher解密信息
     *
     * @param data 待解密的信息
     * @return 解密后的信息
     * @throws Exception
     */
    public static byte[] decrypt(String data) throws Exception {
        return org.apache.commons.codec.binary.Base64.decodeBase64(data);

    }

    /**
     * 使用BASE64加密信息
     *
     * @param data 待加密的信息
     * @return 加密后的信息
     * @throws Exception
     */
    public static String encrypt(byte[] data) throws Exception {
        return Base64.encodeBase64URLSafeString(data);
    }
}
