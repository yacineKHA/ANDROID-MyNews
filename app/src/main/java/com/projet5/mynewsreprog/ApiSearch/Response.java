package com.projet5.mynewsreprog.ApiSearch;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<Docs> docs = new ArrayList<>();

    public Response(){

    }

    public List<Docs> getDocs() {
        return docs;
    }

    public List<Docs> setDocs() {
        return docs;
    }
}
