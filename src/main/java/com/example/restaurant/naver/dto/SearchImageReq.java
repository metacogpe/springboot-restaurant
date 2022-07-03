// 네이버 통한 이미지 검색시 사용 요청 변수 : query, display, start, sort, filter
// https://developers.naver.com/docs/serviceapi/search/blog/blog.md#%EB%B8%94%EB%A1%9C%EA%B7%B8
package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageReq {

    // 요청 변수 : query, display, start, sort
    private String query ="";
    private int display = 1;
    private int start = 1;
    private String sort = "sim";  // 기본값이 "sim"
    private String filter = "all";// default값이 "all"

    // Query 파라미터 전달 편의를 위해 MultiValueMap 사용
    public MultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("sort", sort);
        map.add("filter", filter);
        return map;

    }

}
