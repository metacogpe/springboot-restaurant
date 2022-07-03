//네이버api 의 결과(지역 검색, 이미지 검색)를 잘 활용할 수 있는 서비스 필요
package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository; // 데이터베이스 불러오기

    public WishListDto search(String query){

        // 지역 검색
        var searchLocalReq = new SearchLocalReq(); // 지역 검색위한 request 만들기
        searchLocalReq.setQuery(query);            // 검색 request에 query 지정

        var searchLocalRes = naverClient.searchLocal(searchLocalReq); // 결과 받아오기
        // 지역 검색 결과가 있는 지 여부 파악하여 로직 정의 : 없을 경우에는 빈 데이터로 처리
        if(searchLocalRes.getTotal() > 0 ){
                // 검색 결과 있을 때, 첫 번째 item 꺼내기
                var localItem = searchLocalRes.getItems().stream().findFirst().get();

                // 가져온 결과로 이미지 쿼리 만들기
                var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", ""); // 검색 용이성 위해 문자열 처리
                var searchImageReq = new SearchImageReq();
                searchImageReq.setQuery(imageQuery);

        // 이미지 검색
                var searchImageRes = naverClient.searchImage(searchImageReq);

                if(searchImageRes.getTotal() > 0){
                    // 가져온 아이템 하나만 가져오기
                    var imageeItem = searchImageRes.getItems().stream().findFirst().get();
                    // 결과물 리턴
                    var result = new WishListDto();
                    result.setTitle(localItem.getTitle());
                    result.setCategory(localItem.getCategory());
                    result.setRoadAddress(localItem.getRoadAddress());
                    result.setHomePageLink(localItem.getLink());
                    result.setImageLink(imageeItem.getLink());

                    return result;
                }
        }
        return new WishListDto();  // 없을 경우 비어 있는 Dto로 리턴


    }

    //dto를 Entity로 변환한 다음에 저장하기
    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);            // dto를 entity로 변환
        var saveEntity = wishListRepository.save(entity); // entity를 db에 저장
        return entityToDto(saveEntity);
    }

    // dto를 Entity로 변환
    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVistDate(wishListDto.getLastVistDate());
        return entity;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity){
        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVistDate(wishListEntity.getLastVistDate());
        return dto;
    }

    public List<WishListDto> findAll() {
        // entity stream에 대해 dto로 맵핑 후에 리스트로 collect
        return wishListRepository.listAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(int index) {
        wishListRepository.deleteById(index);
    }

    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);
        if(wishItem.isPresent()){
            var item = wishItem.get();

            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}
