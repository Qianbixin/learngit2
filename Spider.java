/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {

    public static String sendGet(String url) {
        String result = ""; // 定义一个字符串用来存储网页内容         
        BufferedReader in = null;// 定义一个缓冲字符输入流
        try {
            URL realUrl = new URL(url);// 将string转成url对象
            URLConnection connection = realUrl.openConnection(); //初始化一个链接到那个url的连接
            connection.connect();// 开始实际的连接
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));//初始化 BufferedReader输入流来读取URL的响应
            String line;//用来临时存储抓取到的每一行的数据
            while ((line = in.readLine()) != null) {
                result += line + "\n"; //遍历抓取到的每一行并将其存储到result里面 
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {// 使用finally来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    static String RegexString(String targetStr, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);// 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容，相当于埋好了陷阱匹配的地方就会掉下去
        Matcher matcher = pattern.matcher(targetStr);// 定义一个matcher用来做匹配 

        if (matcher.find()) {
            return matcher.group(1);// 如果找到了,打印出结果 
        }
        return "Nothing";
    }

    public static void main(String[] args) {
        String url = "";
        String result = sendGet(url);
//        String imgSrc = RegexString(result, "src=\"(.+?)\"");// 使用正则匹配图片的src内容		
        System.out.println(result);// 打印结果
    }
}
