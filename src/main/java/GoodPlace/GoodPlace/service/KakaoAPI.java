package GoodPlace.GoodPlace.service;

import GoodPlace.GoodPlace.dto.PlaceInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class KakaoAPI {

    @Value("${kakao.map.api.key}")
    private String kakao_apikey;

    public String getRestaurant() {
        List<PlaceInfoDTO> placeInfoList = new ArrayList<>();
        StringBuffer response = new StringBuffer();

        try {
            // 맛집 단어 UTF-8로 인코딩
            String query = URLEncoder.encode("맛집", "UTF-8");

            // 파라미터를 사용하여 요청 URL을 구성한다.
            String apiURL = "https://dapi.kakao.com/v2/local/search/keyword.JSON?" +
                    "query=" + query
                    + "&category_group_code=" + "FD6"
                    + "&x=" + "37.5606326"
                    + "&y=" + "126.9433486"
                    + "&radius=" + "100";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            // 요청 헤더를 setRequestProperty로 지정해준다. 헤더가 더 많을시 더 추가하면 됨.
            con.setRequestProperty("Authorization", "KakaoAK " + kakao_apikey);
            con.setRequestMethod("GET");

            // 응답 코드 확인
            int responseCode = con.getResponseCode();
            BufferedReader br;

            // 정상 응답이 200이므로(http 상태코드)
            if(responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            ObjectMapper objectMapper = new ObjectMapper();
            // JSON 데이터의 "documents" 배열을 역직렬화하여 배열로 저장
            JsonNode rootNode = objectMapper.readTree(response.toString());
            JsonNode documentsNode = rootNode.get("documents");

            // documents 배열의 각 요소를 PlaceInfoDTO 객체로 변환하여 리스트에 추가
            for (JsonNode documentNode : documentsNode) {
                PlaceInfoDTO placeInfo = objectMapper.treeToValue(documentNode, PlaceInfoDTO.class);
                placeInfoList.add(placeInfo);
                System.out.println(placeInfo.getAddressName());
                System.out.println(placeInfo.getPlaceUrl());
                System.out.println(placeInfo.getPlaceName());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(response.toString());
        return response.toString();
    }

}
