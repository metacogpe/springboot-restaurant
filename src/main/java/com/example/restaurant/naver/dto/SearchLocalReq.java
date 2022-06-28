// 네이버 검색시 사용 요청 변수 : query, display, start, sort
// https://developers.naver.com/docs/serviceapi/search/blog/blog.md#%EB%B8%94%EB%A1%9C%EA%B7%B8
package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalReq {

    // 요청 변수 : query, display, start, sort
    private String query ="";
    private int display = 1;
    private int start = 1;
    private String sort = "sim";  // 기본값이 "sim"
}
