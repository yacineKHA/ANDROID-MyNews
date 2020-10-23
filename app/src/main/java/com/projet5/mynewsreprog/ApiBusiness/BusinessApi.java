package com.projet5.mynewsreprog.ApiBusiness;


import java.util.ArrayList;
import java.util.List;

public class BusinessApi {
    private String copyright;

    private String last_updated;

    private String section;

    private List<ResultBusiness> results = new ArrayList<>();

    private String num_results;

    private String status;

    public BusinessApi() {

    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<ResultBusiness> getResults() {
        return results;
    }

    public List<ResultBusiness> setResults() {
        return results;
    }

    public String getNum_results() {
        return num_results;
    }

    public void setNum_results(String num_results) {
        this.num_results = num_results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
