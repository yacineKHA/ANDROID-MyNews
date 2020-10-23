package com.projet5.mynewsreprog.ApiSearch;

import java.util.ArrayList;
import java.util.List;

public class Docs {

    private String web_url;

    private String snippet;

    private String lead_paragraph;

    private List<Multimedia> multimedia = new ArrayList<>();

    private List<Keywords> keywords = new ArrayList<>();

    private Headline headline = new Headline();

    private String pub_date;

    private String section_name;

    private String news_desk;

    private String abstract_;

    private String subsection_name;


    public Docs (){

    }


    public Headline getHeadline() {
        return headline;
    }

    public String getAbstract() {
        return abstract_;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLead_paragraph() {
        return lead_paragraph;
    }

    public void setLead_paragraph(String lead_paragraph) {
        this.lead_paragraph = lead_paragraph;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public List<Keywords> getKeywords() {
        return keywords;
    }

    public String getNews_desk() {
        return news_desk;
    }

    public void setNews_desk(String news_desk) {
        this.news_desk = news_desk;
    }

    public void setKeywords(List<Keywords> keywords) {
        this.keywords = keywords;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getSubsection_name() {
        return subsection_name;
    }

    public void setSubsection_name(String subsection_name) {
        this.subsection_name = subsection_name;
    }
}
