//package crawl;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.time.Duration;
//
//import org.apache.http.HttpResponse;
//
//
//
//public class HttpClientService {
//    private final String API_KEY = "01000000009996b69fd6c540c9e5e71a2ff5bb7c8f2450940d85edda1f75ca7457c338c22d";
//    private final String SECRET_KEY = "AQAAAACZlraf1sVAyeXnGi/1u3yP7unryWtWsjNqmThdMguvwQ==";
//    private final String BASE_URL = "https://api.naver.com/keywordstool?hintKeywords=김밥&includeHintKeywords=1&showDetail=1";
//    private final String CUSTOMER_ID = "1861985";
//
//    private final HttpClient httpClient = HttpClient.newHttpClient();
//
//    
//    public static void main(String ar[]) throws IOException, InterruptedException {
//    	HttpClientService ht = new HttpClientService();
//    	
//    		System.out.println(ht.getHttpRequestForCatImages());
//    }
//    public HttpResponse getCatImagesApiResponse() throws IOException, InterruptedException {
//        return (HttpResponse) httpClient.send(getHttpRequestForCatImages(),toString());
//    }
//
//    private HttpRequest getHttpRequestForCatImages() {
//        return HttpRequest.newBuilder()
//                .uri(URI.create(BASE_URL))
//                .timeout(Duration.ofMillis(1000))
//                .headers(API_KEY, SECRET_KEY)
//                .GET()
//                .build();
//    }
//    public void get(String requestURL) {
//		try {
//			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
//			HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성
//			getRequest.addHeader("x-api-key", RestTestCommon.API_KEY); //KEY 입력
//
//			HttpResponse response = client.execute(getRequest);
//
//			//Response 출력
//			if (response.getStatusLine().getStatusCode() == 200) {
//				ResponseHandler<String> handler = new BasicResponseHandler();
//				String body = handler.handleResponse(response);
//				System.out.println(body);
//			} else {
//				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
//			}
//
//		} catch (Exception e){
//			System.err.println(e.toString());
//		}
//	}
//
//
//}
