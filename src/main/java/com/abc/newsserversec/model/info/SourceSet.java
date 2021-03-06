package com.abc.newsserversec.model.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/20.
 */
public class SourceSet {
    private int matchCount = 0;
    private List<Object> selectedDatas = new ArrayList<>();
    private List<Object> selectedDatasName = new ArrayList<>();//存放产品名称
    private List<Object> datas = new ArrayList<>();
    private List<Object> aggList = new ArrayList<>();
    private List<Object> provinceList = new ArrayList<>();
    private List<Object> priceList = new ArrayList<>();

    public void add(Object prd){
        datas.add(prd);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    public List<Object> getAggList() {
        return aggList;
    }

    public void setAggList(List<Object> aggList) {
        this.aggList = aggList;
    }

    public List<Object> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<Object> provinceList) {
        this.provinceList = provinceList;
    }

    public List<Object> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Object> priceList) {
        this.priceList = priceList;
    }

    public List<Object> getSelectedDatas() {
        return selectedDatas;
    }

    public void setSelectedDatas(List<Object> selectedDatas) {
        this.selectedDatas = selectedDatas;
    }

    public List<Object> getSelectedDatasName() {
        return selectedDatasName;
    }

    public void setSelectedDatasName(List<Object> selectedDatasName) {
        this.selectedDatasName = selectedDatasName;
    }
}
