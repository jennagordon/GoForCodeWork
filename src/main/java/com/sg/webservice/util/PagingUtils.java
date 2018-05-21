package com.sg.webservice.util;

import java.util.ArrayList;
import java.util.List;

public class PagingUtils {

    public static final Integer DEFAULT_OFFSET =0;
    public static final Integer DEFAULT_LIMIT =5;
    public static final Integer DEFAULT_PAGENUMBERS =5;

    public static List<Integer> getPageNumbers(int startPage, Integer numberOfPages) {
        List<Integer> pages = new ArrayList<>();

        for(int i = startPage - (numberOfPages/2); i< (numberOfPages - (numberOfPages/2)) + startPage; i++) {
            if(i>0) {
                pages.add(i);
            }
        }
        if(pages.size()<numberOfPages){
            for(int i=0; i<=(numberOfPages-pages.size()); i++) {
                pages.add(pages.size() + 1);
            }
        }
        return pages;
    }

    public static Integer calculatePageNumber(int limit, int offset) {
        return offset/limit + 1;
    }
}
