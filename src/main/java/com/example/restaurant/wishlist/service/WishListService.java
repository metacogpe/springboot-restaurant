//네이버api 의 결과(지역 검색, 이미지 검색)를 잘 활용할 수 있는 서비스 필요
package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

    public void search(String query){

        // 지역 검색
        var searchLocalReq = new SearchLocalReq(); // 지역 검색위한 request 만들기
        searchLocalReq.setQuery(query);            // 검색 request에 query 지정

        var searchLocalRes = naverClient.searchLocal(searchLocalReq); // 결과 받아오기
        // 지역 검색 결과가 있는 지 여부 파악하여 로직 정의 : 없을 경우에는 빈 데이터로 처리
        if(searchLocalRes.getTotal() > 0 ){
                // 검색 결과 있을 때, 첫 번째 item 꺼내기
                var item = searchLocalRes.getItems().stream().findFirst().get();

                // 가져온 결과로 이미지 쿼리 만들기
                var imageQuery = item.getTitle().replaceAll("<[^>]*>", ""); // 검색 용이성 위해 문자열 처리
                var searchImageReq = new SearchImageReq();
                searchImageReq.setQuery(imageQuery);

        // 이미지 검색
                var searchImageRes = naverClient.searchImage(searchImageReq);

                if(searchImageRes.getTotal() > 0){
                    // 결과물 리턴



                }
        }



    }
}
