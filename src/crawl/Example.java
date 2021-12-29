package crawl;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Example {
		public static void main(String[] args) throws IOException {
			
			// user input
			Scanner scan = new Scanner(System.in);
			System.out.println("input keword :");
			String keyword = scan.next();
			System.out.println("the number of blog : ");
			int num = scan.nextInt();
			
			
//			String BASE_URL = "https://api.naver.com";
//			String API_KEY = "01000000009996b69fd6c540c9e5e71a2ff5bb7c8f2450940d85edda1f75ca7457c338c22d";
//			String CUSTIMER_ID = "1861985";
//			String SECRET_KEY = "AQAAAACZlraf1sVAyeXnGi/1u3yP7unryWtWsjNqmThdMguvwQ==";
//			String url1 = "/keywordstool";
//		    String method = "GET";
//			String url2 = BASE_URL+ url1 + "?hintKeywords=movie&showDetail=1";
//
//			Jsoup.connect(url2).headers(Map<method,url1,API_KEY,SECRET_KEY,CUSTIMER_ID>);
			String url = "https://search.naver.com/search.naver?where=view&query="+keyword; 
			Document doc = null;       
			Document doc1 = null ;
			String blogID = "";
			int total_count = 0;
			Document doc2 = null; 
			Document iframeContentDoc = null ;
			String iframeSrc = "";
			String blog_url = "";
			// parsing for blog infomation
			try {
				doc = Jsoup.connect(url).get();
				System.out.print(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Elements element = doc.select("div.main_pack").select("section").select("div._more_contents_event_base");    
			Iterator<Element> ie1 = element.select("li.bx").iterator();
			
			int count = 0 ;
			while(ie1.hasNext()) {
				if (count == num) {
					break;
				}
				total_count = 0;

				
				Element temp = ie1.next();
				Element total_area = temp.selectFirst("div.total_area");
				System.out.print("temp");
				if (total_area != null) {
					
					 blog_url = total_area.select("a.api_txt_lines").attr("href");
					  System.out.print("ㅇㅁㅇ");

				} else {
					
					Element iframe = doc.select("iframemainFrame").first();
					  iframeSrc = iframe.attr("src");
					  iframeContentDoc = Jsoup.connect("https://blog.naver.com"+iframeSrc).get();
					  total_area = iframeContentDoc;
					  blog_url = total_area.select("meta").attr("content");
					  System.out.print(blog_url);
				}

				Pattern pattern = Pattern.compile("(\\.com\\/)(.*(?=\\/))");
				Matcher areaCode = pattern.matcher(blog_url);
			
				try {
					 doc2 = Jsoup.connect(blog_url).get();
					
					 Element iframe = doc2.select("iframe").first();
					  iframeSrc = iframe.attr("src");

					 if(iframeSrc != null) {
					     iframeContentDoc = Jsoup.connect("https://blog.naver.com"+iframeSrc).get();
					 }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				if (areaCode.find()) {
					blogID = areaCode.group(2);
					
				}
				String average_count_url ="https://blog.naver.com/NVisitorgp4Ajax.nhn?blogId="+blogID;
				try {
					 doc1 = Jsoup.connect(average_count_url).get();
				} catch (IOException e) {
					e.printStackTrace();
				}
					List<String> el2 = doc1.select("visitorcnt").eachAttr("cnt");
					
			    
				for(int i = 0 ; i< 4 ; i++) {
					total_count += Integer.parseInt((el2.get(i)));
				}
				int average_count = Math.round(total_count/4) ;
			
				System.out.println(temp.attr("data-cr-rank")+"위  " + total_area.select("a.api_txt_lines").text());
				
				
				System.out.println("url "+blog_url);
				System.out.println("날짜 :" +total_area.select("span.sub_time").text());
				System.out.println("블로그제목 :"+total_area.select("a.sub_txt").text());
				System.out.println("블로그 4일평균방문자수 : "+average_count);
				System.out.println("이미지개수 :" +iframeContentDoc.select("div.se-main-container").select("img.se-image-resource").size());
				System.out.println(	iframeContentDoc.select("div.se-main-container").text());
				System.out.println();
				count++;
			}

			System.out.println("============================================================");
			System.out.println();
		}
	}

