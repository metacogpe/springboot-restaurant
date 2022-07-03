// 결과에 해당하는 정보
package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchLocalRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    //private String category;   // 네이버 api 문서 오류
    private List<SearchLocalItem> items;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchLocalItem {
        private String category;
        private String title;
        private String link;
        private String description;
        private String telephone;
        private String address;
        private String roadAddress;
        private int mapx;
        private int mapy;

    }
}
