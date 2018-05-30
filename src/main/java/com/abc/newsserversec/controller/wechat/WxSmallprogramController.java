package com.abc.newsserversec.controller.wechat;

import com.abc.newsserversec.model.wechat.WxaccessToken;
import com.abc.newsserversec.model.wechat.WxspUserInfo;
import com.abc.newsserversec.service.wechat.WxspInfoService;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信小程序请求的方法
 */
@RestController
public class WxSmallprogramController {

    @Autowired
    private WxspInfoService wxspInfoService;

    /*
      微信小程序登录
   */
    @RequestMapping("/method/wx_smallprogram_login")
    public static String wxSmallprogramLogin(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result="";//访问返回结果
        BufferedReader read=null;//读取访问结果
        WxaccessToken wxaccessToken = null;

        String appid = "wxb447b4ac481750b1";
        String secret = "0b9bb6070e561dd1e62ec3e067242cba";
        String code = request.getParameter("code");
        System.out.println(code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";

        try {
            //创建url
            URL realurl=new URL(url);
            //打开连接
            URLConnection connection=realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
            for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            wxaccessToken = new GsonBuilder().create().fromJson(result, WxaccessToken.class);
            if(wxaccessToken != null) {
                if (wxaccessToken.getAccess_token() != null && wxaccessToken.getOpenid() != null) {
                    System.out.println(wxaccessToken.getAccess_token() + "," + wxaccessToken.getOpenid());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new GsonBuilder().create().toJson(wxaccessToken);
    }

    /*
     微信小程序--新增用户信息
  */
    @RequestMapping("/method/wxsp_insert_user")
    public void wxspInsertUser(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String openid = request.getParameter("openid");
        String nickname = request.getParameter("nickname");
        String sex = request.getParameter("sex");

        if(sex.equals("0")) sex = "未知";
        else if(sex.equals("1")) sex = "男";
        else sex = "女";

        WxspUserInfo user = wxspInfoService.selectWxspUserByOpenid(openid);
        if(user == null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            Map<String,String> map = new HashMap<>();
            map.put("openid",openid);
            map.put("nickname",nickname);
            map.put("sex",sex);
            map.put("createperson",openid);
            map.put("createdate",date);
            wxspInfoService.insertWxspUserByMap(map);
        }

    }

    /*
     微信小程序--新增查询信息
  */
    @RequestMapping("/method/wxsp_insert_usersearch")
    public void wxspInsertUserSearch(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String openid = request.getParameter("openid");
        String classtype = request.getParameter("classtype");
        String keyword = request.getParameter("keyword");
        String resultcount = request.getParameter("resultcount");

        WxspUserInfo user = wxspInfoService.selectWxspUserByOpenid(openid);
        if(user != null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            Map<String,Object> map = new HashMap<>();
            map.put("userid",user.getId());
            map.put("classtype",classtype);
            map.put("keyword",keyword);
            map.put("resultcount",resultcount);
            map.put("createdate",date);
            wxspInfoService.insertWxspUserSearchByMap(map);
        }

    }

}
