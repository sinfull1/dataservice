package com.abc.newsserversec.controller.info;

import com.abc.newsserversec.common.StaticVariable;
import com.abc.newsserversec.model.info.ESCount;
import com.abc.newsserversec.model.info.ESResultRoot;
import com.abc.newsserversec.model.info.Hit;
import com.abc.newsserversec.common.HttpHandler;
import com.abc.newsserversec.model.info.SourceSet;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 医院数据查询控制器
 */
@RestController
public class HospitalController {

    /**
     * 根据医院名称查找医院信息
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping("/method/search_hospital_name")
    public String searchHospitalByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");

        String num = request.getParameter("num");
        String keyword = request.getParameter("keyword");
        if(num == null || keyword == null) return "fail";
        if(num.equals("") || keyword.equals("")) return "fail";

        String esRequest = StaticVariable.esRequest;
        int from = Integer.valueOf(num);
        from = from*10;

        String condition = "hospital_name:\\\\\""+keyword+"\\\\\"";
        esRequest = esRequest.replaceFirst("\"#from\"",String.valueOf(from));
        esRequest = esRequest.replaceFirst("\"#includes\"","");
        esRequest = esRequest.replaceFirst("\"#excludes\"",StaticVariable.ExcludeFields);
        String postbody = esRequest.replaceFirst("#query",condition);
        postbody = postbody.replaceFirst("\"#aggs\"","{}");

        String ret = HttpHandler.httpPostCall("http://localhost:9200/new_hospital/_search", postbody);
        ESResultRoot retObj = new GsonBuilder().create().fromJson(ret, ESResultRoot.class);
        SourceSet productSet = new SourceSet();
        for(Hit hit:retObj.hits.hits){
            productSet.add(hit._source);
        }
        if(from == 0) {
            //计数
            String esCount = StaticVariable.esCount;
            esCount = esCount.replaceFirst("#query", condition);
            String countRet = HttpHandler.httpPostCall("http://localhost:9200/new_hospital/_count", esCount);
            ESCount esCt = new GsonBuilder().create().fromJson(countRet, ESCount.class);
            productSet.setMatchCount(esCt.count);
        }
        return new GsonBuilder().create().toJson(productSet);
    }

    /**
     * 根据id查找医院信息
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping("/method/search_hospital_id")
    public String searchHospitalById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");

        String id = request.getParameter("id");
        if(id == null) return "fail";
        if(id.equals("")) return "fail";

        String esRequest = StaticVariable.esRequest;
        String condition = "id:\\\\\""+id+"\\\\\"";
        esRequest = esRequest.replaceFirst("\"#from\"",String.valueOf(0));
        esRequest = esRequest.replaceFirst("\"#includes\"","");
        esRequest = esRequest.replaceFirst("\"#excludes\"",StaticVariable.ExcludeFields);
        String postbody = esRequest.replaceFirst("#query",condition);
        postbody = postbody.replaceFirst("\"#aggs\"","{}");

        String ret = HttpHandler.httpPostCall("http://localhost:9200/new_hospital/_search", postbody);
        ESResultRoot retObj = new GsonBuilder().create().fromJson(ret, ESResultRoot.class);
        SourceSet productSet = new SourceSet();
        for(Hit hit:retObj.hits.hits){
            productSet.add(hit._source);
        }
        return new GsonBuilder().create().toJson(productSet);
    }
}