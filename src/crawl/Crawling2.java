package crawl;
import org.jsoup.Connection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Crawling2 {
	
	//멤버변수선언 
   String blog_url,blog_title,post_date,post_title,post_contents;
   int num,img_count,contents_count,average_visitor_count ;
  
 //   int repKeywordTitleCount;
   String rank,keyword;
   ArrayList<Article> articles ;
   
   //생성자  
   public Crawling2(String keyword,int num) {
      this.keyword = keyword;
      this.num = num ;
      articles = new ArrayList<Article>();   
      img_count = 0;
   }
   
   //크롤링 및 파싱 메소드 
   public void search() {
      String url = "https://search.naver.com/search.naver?where=view&query="+keyword; 
       
      Document doc = null;
      Document doc1 = null ;
      Document doc2 = null ; //add
      String blogID = "";
      Document iframeContentDoc = null ; //add
      String iframeSrc = "";  //add
      int total_count = 0;
      
      // parsing for blog infomation
      try {
         doc = Jsoup.connect(url).get();
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
          blog_url = total_area.select("a.api_txt_lines").attr("href");
         Pattern pattern = Pattern.compile("(\\.com\\/)(.*(?=\\/))");
         Matcher areaCode = pattern.matcher(blog_url);
      
         try {  //add
             doc2 = Jsoup.connect(blog_url).get();
            
             Element iframe = doc2.select("iframe").first();
            if (iframe != null) {
              iframeSrc = iframe.attr("src");
            }
             if(iframeSrc != null) {
                 iframeContentDoc = Jsoup.connect("https://blog.naver.com"+iframeSrc).get();
                 img_count = iframeContentDoc.select("div.se-main-container").select("img.se-image-resource").size();
                 if (img_count == 0) {
                	 img_count = iframeContentDoc.select("img._photoImage").size();
                 }
                 post_contents =iframeContentDoc.select("div.se-main-container").text();
            
             }
             
         } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }          //add
         
         
         
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
         average_visitor_count = Math.round(total_count/4) ;
      
         
         if(blog_url.contains("blog")) {
         Article article = new Article();
         article.rank = temp.attr("data-cr-rank");
         article.post_title = total_area.select("a.api_txt_lines").text();
         article.post_date = total_area.select("span.sub_time").text();
         article.blog_title = total_area.select("a.sub_txt").text();
         article.blog_url = blog_url;
         article.average_visitor_count = average_visitor_count;
         article.img_count = img_count; //add
         article.repKeywordTitleCount = countRepetionOf(article.post_title);
         article.contentsCount = post_contents.length();
         article.repKeywordContentsCount = countRepetionOf(post_contents);
         
      
         articles.add(article);
         }
         count++;
         
      }
      global.complete=0;//이충형 수정!!
//      a.p.setVisible(true);
//        a.p_load.setVisible(false);
      
   }
   
   //반복키워드 개수 세는 메소드 
   public int countRepetionOf(String str) {
      
      String temp = str.replaceAll(keyword,"ㅍ");
      int count = 0;

      for(int i = 0 ; i < temp.length() ;i++) {
         if(temp.charAt(i) =='ㅍ') {
            
            count +=1 ;
         }

      }
      return count ; 
   }
  // 분석 메소드 
   public void analyzeArticles() {
      
   }
   
  //아티클의 속성의 값 최소값 구하기 메소드
   public int getMinVisitor_count() {
	   int min = articles.get(0).getAverage_visitor_count() ;
	   for(int i = 1 ; i< articles.size() ; i++) {
		   int temp = articles.get(i).getAverage_visitor_count();
		   if (min > temp) {
			   min = temp ;
		   }
	   }
	   return min;
   }
   public int getMinImg_count() {
	   int min = articles.get(0).getImg_count() ;
	   for(int i = 1 ; i< articles.size() ; i++) {
		   int temp = articles.get(i).getImg_count();
		   if (min > temp) {
			   min = temp ;
		   }
	   }
	   return min;
   }
   public int getMinRepKeywordTitleCount() {
	   int min = articles.get(0).getRepKeywordTitleCount() ;
	   for(int i = 1 ; i< articles.size() ; i++) {
		   int temp = articles.get(i).getRepKeywordTitleCount();
		   if (min > temp) {
			   min = temp ;
		   }
	   }
	   return min;
   }
   public int getRepKeywordContentsCount() {
	   int min = articles.get(0).getRepKeywordContentsCount() ;
	   for(int i = 1 ; i< articles.size() ; i++) {
		   int temp = articles.get(i).getRepKeywordContentsCount();
		   if (min > temp) {
			   min = temp ;
		   }
	   }
	   return min;
   }
   public int getMinContents_count() {
	   int min = articles.get(0).getContentsCount() ;
	   for(int i = 1 ; i< articles.size() ; i++) {
		   int temp = articles.get(i).getContentsCount();
		   if (min > temp) {
			   min = temp ;
		   }
	   }
	   return min;
   }
}