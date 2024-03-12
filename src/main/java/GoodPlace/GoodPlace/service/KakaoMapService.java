package GoodPlace.GoodPlace.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KakaoMapService {

    private final Logger logger = LoggerFactory.getLogger(KakaoMapService.class);

    private final WebClient webClient;

    private final String kakaoMapApiKey = "1189328ba1ff08ff21b410a6970baff7";

    public KakaoMapService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoMapApiKey)
                .build();
    }

    public Mono<String> searchNearbyRestaurants(double x, double y) {
        String query = "카카오프렌즈";
        String url = "/v2/local/search/keyword.json?y=" + y + "&x=" + x + "&radius=20000";

        logger.debug("Query URL: {}", url);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}

