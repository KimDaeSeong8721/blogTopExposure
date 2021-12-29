package crawl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JProgressBar;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



class global{
   static int num;
   static String temp0[][] = new String[15][7];
   static String temp1[][] = new String[15][9];
    
   static int num5=1;
   static int numview=0;
   static int num7=1;  //이충형 수정
   static int complete=1;
   static int num2_view=0;
   static int num2_1=1;
   static int num2_2=1;
   static int num2_3=1;
   static int num2_4=1;
   static int num2_5=1;
}

public class Crawling {
  // 멤버변수 선
          JTextField text_array[][][],text_array1[][];
       JPanel p,p1,p2,p_load;
       JFrame frm;
       JTextField tfId,tfId1 ;
       JLabel search1, loading;
       JButton button_analysis ;
       JButton search,search_blog,search_num;
       JButton button_view,button_URL,button_name;
       JButton button_4,button_5,button_6,button_blog;
       JButton button2_view,button2_blog,button2_URL;
       JButton button2_4,button2_5,button2_6,button2_7,button2__1,button2__2;
       Crawling2 cr ;
       int minimum = 0;
       int maximum = 100;
       JProgressBar progress ;
   
    public static void main(String[] args) {
       
        // 인스턴스
        Crawling cra = new Crawling();
        cra.initializedUI();
        cra.buttonEvent();
    
    } 
    public void initializedUI() {
       progress = new JProgressBar(minimum, maximum);
      text_array = new JTextField[2][15][7];
      text_array1 = new JTextField[15][9];
       // 프레임 생성
           p1=new JPanel();
           p=new JPanel();
           p2=new JPanel();
           p_load = new JPanel();
          frm = new JFrame("블로그 키워드 탐지");
          
          // 프레임 크기 설정
           frm.setSize(1300, 685);
    
           // 프레임을 화면 가운데에 배치
           frm.setLocationRelativeTo(null);
    
           // 프레임을 닫았을 때 메모리에서 제거되도록 설정
           frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
           // 레이아웃 설정
           frm.getContentPane().setLayout(null);
    
           // 분석 버튼
           
           button_analysis = new RoundedButton("분석하기");
           button_analysis.setBounds(400,10,100,35);
           button_analysis.setBackground(new Color(255,255,255));
           p2.add(button_analysis);

           // 버튼 생성
           tfId = new JTextField(10);
           tfId.setBounds(10,10,300,35);

           tfId.setBorder(null);
           p2.add(tfId);
           
            search = new RoundedButton("검색");
           search.setBounds(320,10,70,35);
           search.setBackground(new Color(255,255,255));
           p2.add(search);
           
            search1 = new JLabel("검색 수: ");
           search1.setBounds(1100,10,50,35);
           p2.add(search1);
           
            tfId1 = new JTextField(10);
           tfId1.setBounds(1150,10,100,35);

           tfId1.setBorder(null);
           p2.add(tfId1);
           tfId1.setText("5");
           
            search_blog = new RoundedButton("블로그 정보");
           search_blog.setBounds(10,60,100,30);
           search_blog.setBackground(new Color(255,255,255));
           p2.add(search_blog);
           
            search_num = new RoundedButton("추가정보");           
            search_num.setBounds(120,60,100,30);

              search_num.setBackground(new Color(255,255,255));
            p2.add(search_num);
           
            button_view = new RoundedButton("순위");      
            button_view.setBounds(10,0,70,30);
              button_view.setBackground(new Color(255,255,255));
            p.add(button_view);
            button_blog = new RoundedButton("블로그");
           button_blog.setBounds(85,0,300,30);
           button_blog.setBackground(new Color(255,255,255));
           p.add(button_blog);
            button_name = new RoundedButton("제목");
              button_name.setBackground(new Color(255,255,255));
              button_name.setBounds(390,0,300,30);
           p.add(button_name);
            button_4 = new RoundedButton("방문자");
           button_4.setBounds(695,0,85,30);
           button_4.setBackground(new Color(255,255,255));
           p.add(button_4);
            button_5 = new RoundedButton("이미지");
           button_5.setBounds(785,0,95,30);
           button_5.setBackground(new Color(255,255,255));
           p.add(button_5);
            button_6 = new RoundedButton("날짜");
           button_6.setBounds(885,0,110,30);
           button_6.setBackground(new Color(255,255,255));
           p.add(button_6);
           button_URL = new RoundedButton("URL");
           button_URL.setBounds(1000,0,270,30);
           button_URL.setBackground(new Color(255,255,255));
           p.add(button_URL);
           
            button2_view = new RoundedButton("순위");
           button2_view.setBounds(10,0,70,30);
           button2_view.setBackground(new Color(255,255,255));
           p1.add(button2_view);
            button2_blog = new RoundedButton("제목");
           button2_blog.setBounds(85,0,300,30);
           button2_blog.setBackground(new Color(255,255,255));
           p1.add(button2_blog);

           button2_6 = new RoundedButton("타이틀 중복");
          button2_6.setBounds(390,0,100,30);
          button2_6.setBackground(new Color(255,255,255));
          p1.add(button2_6);
          button2__1 = new RoundedButton("본문 중복");
         button2__1.setBounds(495,0,100,30);
         button2__1.setBackground(new Color(255,255,255));
         p1.add(button2__1);
         button2__2 = new RoundedButton("글자수");
         button2__2.setBounds(600,0,90,30);
         button2__2.setBackground(new Color(255,255,255));
         p1.add(button2__2);
          button2_5 = new RoundedButton("방문자");
         button2_5.setBounds(695,0,85,30);
         button2_5.setBackground(new Color(255,255,255));
         p1.add(button2_5);
         button2_4 = new RoundedButton("이미지");
         button2_4.setBounds(785,0,95,30);
         button2_4.setBackground(new Color(255,255,255));
         p1.add(button2_4);
         button2_7 = new RoundedButton("날짜");
        button2_7.setBounds(885,0,110,30);
        button2_7.setBackground(new Color(255,255,255));
          p1.add(button2_7);
          
            button2_URL = new RoundedButton("URL");
           button2_URL.setBounds(1000,0,270,30);
           button2_URL.setBackground(new Color(255,255,255));
           p1.add(button2_URL);
           
           for(int i =0; i<15;i++) {
               for(int j=0; j<7;j++) {
                  text_array[0][i][j] = new JTextField(5);
                  text_array[0][i][j].setBorder(null);
                  text_array[0][i][j].setHorizontalAlignment(JTextField.CENTER);
               }
                    text_array[0][i][0].setBounds(10,35+35*i,70,30);
                    p.add(text_array[0][i][0]);
                    text_array[0][i][1].setBounds(85,35+35*i,300,30);
                    p.add(text_array[0][i][1]);
                    text_array[0][i][2].setBounds(390,35+35*i,300,30);
                    p.add(text_array[0][i][2]);
                    text_array[0][i][3].setBounds(695,35+35*i,85,30);
                    p.add(text_array[0][i][3]);
                    text_array[0][i][4].setBounds(785,35+35*i,95,30);
                    p.add(text_array[0][i][4]);
                    text_array[0][i][5].setBounds(885,35+35*i,110,30);
                    p.add(text_array[0][i][5]);
                    text_array[0][i][6].setBounds(1000,35+35*i,270,30);
                    p.add(text_array[0][i][6]);
                    
               }
            for(int i =0; i<15;i++) {
               for(int j=0; j<9;j++) {
                  text_array1[i][j] = new JTextField(5); 
                  text_array1[i][j].setBorder(null);
                  text_array1[i][j].setHorizontalAlignment(JTextField.CENTER);
               }   
            text_array1[i][0].setBounds(10,35+35*i,70,30);
            p1.add(text_array1[i][0]);
            text_array1[i][1].setBounds(85,35+35*i,300,30);
            p1.add(text_array1[i][1]);
            text_array1[i][2].setBounds(390,35+35*i,100,30);
            p1.add(text_array1[i][2]);
            text_array1[i][3].setBounds(495,35+35*i,100,30);
            p1.add(text_array1[i][3]);
            text_array1[i][4].setBounds(600,35+35*i,90,30);
            p1.add(text_array1[i][4]);
            text_array1[i][5].setBounds(695,35+35*i,85,30);
            p1.add(text_array1[i][5]);
            text_array1[i][6].setBounds(785,35+35*i,95,30);
            p1.add(text_array1[i][6]);
            text_array1[i][7].setBounds(885,35+35*i,110,30);
            p1.add(text_array1[i][7]);
            text_array1[i][8].setBounds(1000,35+35*i,270,30);
            p1.add(text_array1[i][8]);
               }        
    
            p.setBounds(0,100,1300,585);
            p.setLayout(null);
            
            
            p1.setBounds(0,100,1300,585);
            p1.setLayout(null);
            p2.setBounds(0,0,1300,100);
            p2.setLayout(null);
            

            loading = new JLabel("Loading..",SwingConstants.CENTER);
              loading.setBounds(430,300,400,65);
              Font font = new Font("궁서 보통",Font.BOLD,50);
              loading.setFont(font);
              p_load.add(loading);
              
            p_load.setBounds(0,0,1300,685);
            p_load.setLayout(null);
            p_load.setBackground(new Color(240,240,240));
              
            frm.add(p);
            frm.add(p1);
            frm.add(p2);
            frm.add(p_load);
            
            // 프레임이 보이도록 설정
            frm.setVisible(true);
            p.setVisible(false);
            p1.setVisible(false);
            p2.setVisible(true);
            p_load.setVisible(false);
   }
   
   public void buttonEvent() {
      button2_4.addActionListener(event ->{
         global.num2_view=0;
         global.num2_1=1;
         global.num2_2=1;
         global.num2_3=1;
         global.num2_4=1;
         //global.num2_5=1;
           button2_view.setText("순위");
           button2_6.setText("타이틀 중복");
           button2__1.setText("본문 중복");
           button2__2.setText("글자 수");
           button2_5.setText("방문자");
           button2_4.setText("이미지");
      int text[] = new int[global.num];
           for (int i=0;i<global.num;i++)
                   text[i]=Integer.parseInt(global.temp1[i][6]);
           
           if(global.num2_5%2==1) {
               text = sort(text,global.num);
               button2_4.setText("이미지▲"); 
               }
               else {
                  text = desort(text,global.num);
                  button2_4.setText("이미지▼");
               }
           
              for (int i=0;i<global.num;i++)
              for (int j=0;j<9;j++)
                 text_array1[i][j].setText(global.temp1[text[i]][j]);
              
           global.num2_5++; 
               
    });
      
      button2_5.addActionListener(event ->{
         global.num2_view=0;
         global.num2_1=1;
         global.num2_2=1;
         global.num2_3=1;
         //global.num2_4=1;
         global.num2_5=1;
           button2_view.setText("순위");
           button2_6.setText("타이틀 중복");
           button2__1.setText("본문 중복");
           button2__2.setText("글자 수");
           button2_5.setText("방문자");
           button2_4.setText("이미지");
      int text[] = new int[global.num];
           for (int i=0;i<global.num;i++)
                   text[i]=Integer.parseInt(global.temp1[i][5]);
           
           if(global.num2_4%2==1) {
               text = sort(text,global.num);
               button2_5.setText("방문자▲"); 
               }
               else {
                  text = desort(text,global.num);
                  button2_5.setText("방문자▼");
               }
           
              for (int i=0;i<global.num;i++)
              for (int j=0;j<9;j++)
                 text_array1[i][j].setText(global.temp1[text[i]][j]);
              
           global.num2_4++; 
               
    });
      
      button2__2.addActionListener(event ->{
         global.num2_view=0;
         global.num2_1=1;
         global.num2_2=1;
         //global.num2_3=1;
         global.num2_4=1;
         global.num2_5=1;
           button2_view.setText("순위");
           button2_6.setText("타이틀 중복");
           button2__1.setText("본문 중복");
           button2__2.setText("글자 수");
           button2_5.setText("방문자");
           button2_4.setText("이미지");
      int text[] = new int[global.num];
           for (int i=0;i<global.num;i++)
                   text[i]=Integer.parseInt(global.temp1[i][4]);
           
           if(global.num2_3%2==1) {
               text = sort(text,global.num);
               button2__2.setText("글자 수▲"); 
               }
               else {
                  text = desort(text,global.num);
                  button2__2.setText("글자 수▼");
               }
           
              for (int i=0;i<global.num;i++)
              for (int j=0;j<9;j++)
                 text_array1[i][j].setText(global.temp1[text[i]][j]);
              
           global.num2_3++; 
               
    });
      
      button2__1.addActionListener(event ->{
         global.num2_view=0;
         global.num2_1=1;
         //global.num2_2=1;
         global.num2_3=1;
         global.num2_4=1;
         global.num2_5=1;
           button2_view.setText("순위");
           button2_6.setText("타이틀 중복");
           button2__1.setText("본문 중복");
           button2__2.setText("글자 수");
           button2_5.setText("방문자");
           button2_4.setText("이미지");
      int text[] = new int[global.num];
           for (int i=0;i<global.num;i++)
                   text[i]=Integer.parseInt(global.temp1[i][3]);
           
           if(global.num2_2%2==1) {
               text = sort(text,global.num);
               button2__1.setText("본문 중복▲"); 
               }
               else {
                  text = desort(text,global.num);
                  button2__1.setText("본문 중복▼");
               }
           
              for (int i=0;i<global.num;i++)
              for (int j=0;j<9;j++)
                 text_array1[i][j].setText(global.temp1[text[i]][j]);
              
           global.num2_2++; 
               
    });
      button2_6.addActionListener(event ->{
         global.num2_view=0;
         //global.num2_1=1;
         global.num2_2=1;
         global.num2_3=1;
         global.num2_4=1;
         global.num2_5=1;
           button2_view.setText("순위");
           button2_6.setText("타이틀 중복");
           button2__1.setText("본문 중복");
           button2__2.setText("글자 수");
           button2_5.setText("방문자");
           button2_4.setText("이미지");
      int text[] = new int[global.num];
           for (int i=0;i<global.num;i++)
                   text[i]=Integer.parseInt(global.temp1[i][2]);
           
           if(global.num2_1%2==1) {
               text = sort(text,global.num);
               button2_6.setText("타이틀 중복▲"); 
               }
               else {
                  text = desort(text,global.num);
                  button2_6.setText("타이틀 중복▼");
               }
           
              for (int i=0;i<global.num;i++)
              for (int j=0;j<9;j++)
                 text_array1[i][j].setText(global.temp1[text[i]][j]);
              
           global.num2_1++; 
               
    });
      
      
      button2_view.addActionListener(event ->{
         //global.num2_view=0;
         global.num2_1=1;
         global.num2_2=1;
         global.num2_3=1;
         global.num2_4=1;
         global.num2_5=1;
           button2_view.setText("순위");
           button2_6.setText("타이틀 중복");
           button2__1.setText("본문 중복");
           button2__2.setText("글자 수");
           button2_5.setText("방문자");
           button2_4.setText("이미지");
      int text[] = new int[global.num];
           for (int i=0;i<global.num;i++)
                   text[i]=Integer.parseInt(global.temp1[i][0]);
           
           if(global.num2_view%2==1) {
               text = sort(text,global.num);
               button2_view.setText("순위▼"); 
               }
               else {
                  text = desort(text,global.num);
                  button2_view.setText("순위▲");
               }
           
              for (int i=0;i<global.num;i++)
              for (int j=0;j<9;j++)
                 text_array1[i][j].setText(global.temp1[text[i]][j]);
              
           global.num2_view++; 
               
    });
      button_4.addActionListener(event ->{
        global.numview=0;//
        global.num7=1;
        button_view.setText("순위");
        button_4.setText("방문자");
        button_5.setText("이미지");
   int text[] = new int[global.num];
        for (int i=0;i<global.num;i++)
                text[i]=Integer.parseInt(global.temp0[i][3]);
        
        if(global.num5%2==1) {
            text = sort(text,global.num);
            button_4.setText("방문자▲"); 
            }
            else {
               text = desort(text,global.num);
               button_4.setText("방문자▼");
            }
        
           for (int i=0;i<global.num;i++)
           for (int j=0;j<7;j++)
              text_array[0][i][j].setText(global.temp0[text[i]][j]);
           
        global.num5++; 
            
 });//이부분 추가
       
       button_view.addActionListener(event ->{
          global.num5=1;
              global.num7=1;

              button_view.setText("순위");
              button_4.setText("방문자");
              button_5.setText("이미지");
          int text[] = new int[global.num];
           for (int i=0;i<global.num;i++)
                   text[i]=Integer.parseInt(global.temp0[i][0]);
           
           text = sort(text,global.num);
             
           
           if(global.numview%2==1) {
              for (int i=0;i<global.num;i++)
              for (int j=0;j<7;j++)
                 text_array[0][i][j].setText(global.temp0[text[i]][j]);
              button_view.setText("순위▼");
           }else {
              for (int i=0;i<global.num;i++)
                  for (int j=0;j<7;j++)
                     text_array[0][global.num-1-i][j].setText(global.temp0[text[i]][j]);
              button_view.setText("순위▲");
           }
           global.numview++;
               
    });  //이 부분추가
       button_5.addActionListener(event ->{
          global.num5=1;
              global.numview=0;//
              button_view.setText("순위");
              button_4.setText("방문자");
              button_5.setText("이미지");
          int text[] = new int[global.num];
           for (int i=0;i<global.num;i++)
                   text[i]=Integer.parseInt(global.temp0[i][4]);
           if(global.num7%2==1) {
           text = sort(text,global.num);
           button_5.setText("이미지▲"); 
           }
           else {
              text = desort(text,global.num);
              button_5.setText("이미지▼");
           }
           
              for (int i=0;i<global.num;i++)
              for (int j=0;j<7;j++)
                 text_array[0][i][j].setText(global.temp0[text[i]][j]);
              
           
           global.num7++;
               
    }); //이부분추가
       
       search.addActionListener(event -> { //버튼 클릭
      
            global.complete=1;
            Thread t1=new Thread(new threadload());
            Thread t2=new Thread(new threadsearch());
            t1.start();
            t2.start();
              global.num5=1;
              global.numview=0;//
              global.num7=1;  //이충형 수정

              button_view.setText("순위");
              button_4.setText("방문자");
              button_5.setText("이미지");
              // 인스턴스생성 
              
                 


           p.setVisible(true);
           p2.setVisible(true);
            p_load.setVisible(false);
          
       });
       
        
       Action ok = new AbstractAction() {

          public void actionPerformed(ActionEvent arg0) {
           
                search.doClick();
           
          }
          };
           
          KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
     
          tfId.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "ENTER");
          tfId.getActionMap().put("ENTER", ok);  //이충형 추가


       
       
           search_blog.addActionListener(event -> {
              p.setVisible(true);
               p1.setVisible(false);
           
           });
           search_num.addActionListener(event -> {
              p.setVisible(false);
               p1.setVisible(true);
           });
           button_analysis.addActionListener(event -> {
                JFrame jFrame = new JFrame();
                 String str = "";
                str +="최소 방문자수는 "+ cr.getMinVisitor_count() +"명이상 \n";
                str +="최소 이미수는 "+ cr.getMinImg_count() +"개이상 \n";
                str +="타이틀에 키워드가 최소 "+ cr.getMinRepKeywordTitleCount() +"개이상 \n";
                str +="최소 본문 글자수는 "+ cr.getMinContents_count() +"자이상 \n";
                str +="본문에 키워드가 최소 "+ cr.getRepKeywordContentsCount() +"개이상 \n";
                str +="되어야 당신의 블로그글이 상위노출될 가능성이 높습니다.";


                      
                       
                
                 JOptionPane.showMessageDialog(jFrame, str);
              
              });
   }
   public static int[] sort(int[] count, int num) {
         int temp;
         if(num<=1)
            return null;
         int a[] = new int[num];
         for(int i=0;i<num;i++)
            a[i]=i;
         for (int i=num-1; i>0; i--)
            for (int j=1; j<=i; j++) {
               if(count[j]>count[j-1]) {
                  temp = count[j];
                  count[j] = count[j-1];
                  count[j-1] = temp;
                  temp = a[j];
                  a[j] = a[j-1];
                  a[j-1] = temp;
               }
            }
         return a;
         } 
   public static int[] desort(int[] count, int num) {
            int temp;
            if(num<=1)
               return null;
            int a[] = new int[num];
            for(int i=0;i<num;i++)
               a[i]=i;
            for (int i=num-1; i>0; i--)
               for (int j=1; j<=i; j++) {
                  if(count[j]<count[j-1]) {
                     temp = count[j];
                     count[j] = count[j-1];
                     count[j-1] = temp;
                     temp = a[j];
                     a[j] = a[j-1];
                     a[j-1] = temp;
                  }
               }
            return a;
            }  //sort함수 이충형 수정
   public class threadload implements Runnable{
      public void run() {
         try {
            while (global.complete==1) {
                 p.setVisible(false);
                 p2.setVisible(false);
                 p1.setVisible(false);
                  p_load.setVisible(true);
                  Thread.sleep(1000);
            }

              p2.setVisible(true);
            p.setVisible(true);
            p1.setVisible(false);
               p_load.setVisible(false);
               
         }catch(Exception e) {
            
         }
      }
   }
   public class threadsearch implements Runnable{
      public void run() {
         try {
            for(int i=0;i<global.num;i++) {
               text_array[0][i][0].setText("");
                text_array[0][i][1].setText("");
                text_array[0][i][6].setText("");
                text_array[0][i][5].setText("");
                text_array[0][i][3].setText("");
                text_array[0][i][2].setText("");
                text_array[0][i][4].setText("");
                text_array1[i][0].setText("");
                text_array1[i][1].setText("");
                text_array1[i][6].setText("");
                text_array1[i][5].setText("");
                text_array1[i][3].setText("");
                text_array1[i][2].setText("");
                text_array1[i][8].setText("");
                text_array1[i][4].setText("");
                text_array1[i][7].setText("");
                
               
            }
            String Keyword= tfId.getText();
                 String temp1 = tfId1.getText();
                 int num = Integer.parseInt(temp1);
             cr = new Crawling2(Keyword,num);
               cr.search();

                 global.num=cr.articles.size();                  
            for (int i = 0; i < cr.articles.size(); i++) {
                Article art = cr.articles.get(i);
               text_array[0][i][0].setText(art.getRank());
               text_array[0][i][1].setText(art.getBlog_title());
               text_array[0][i][6].setText(art.getBlog_url());
               String date= art.getPost_date();
               if((date.charAt(0)=='2') &&(date.charAt(1)=='0'))
                  date=date.substring(2);
               text_array1[i][0].setText(art.getRank());
               text_array1[i][1].setText(art.getPost_title());
               text_array1[i][2].setText(Integer.toString(art.getRepKeywordTitleCount()));
               text_array1[i][3].setText(Integer.toString(art.getRepKeywordContentsCount()));
               text_array1[i][4].setText(Integer.toString(art.getContentsCount()));
               text_array1[i][5].setText(Integer.toString(art.getAverage_visitor_count()));
               text_array1[i][6].setText(Integer.toString(art.getImg_count()));
               text_array1[i][7].setText(date);
               text_array1[i][8].setText(art.getBlog_url());

               global.temp1[i][0]=art.getRank();
               global.temp1[i][1]=(art.getPost_title());
               global.temp1[i][2]=(Integer.toString(art.getRepKeywordTitleCount()));
               global.temp1[i][3]=(Integer.toString(art.getRepKeywordContentsCount()));
               global.temp1[i][4]=(Integer.toString(art.getContentsCount()));
               global.temp1[i][5]=(Integer.toString(art.getAverage_visitor_count()));
               global.temp1[i][6]=(Integer.toString(art.getImg_count()));
               global.temp1[i][7]=(date);
               global.temp1[i][8]=(art.getBlog_url());
               
               text_array[0][i][5].setText(date);
               text_array[0][i][3].setText(Integer.toString(art.getAverage_visitor_count()));
               text_array[0][i][2].setText(art.getPost_title());
               text_array[0][i][4].setText(Integer.toString(art.getImg_count()));
               global.temp0[i][0] = art.getRank();
               global.temp0[i][1] = (art.getBlog_title());
               global.temp0[i][6] = (art.getBlog_url());
               global.temp0[i][5] = (date);
               global.temp0[i][3] = (Integer.toString(art.getAverage_visitor_count()));
               global.temp0[i][2] = (art.getPost_title());
               global.temp0[i][4] = (Integer.toString(art.getImg_count()));   //이충형 수정 
               global.complete=0;
            }

         }catch(Exception e) {
            
         }
      }
   }
   public class RoundedButton extends JButton {
         public RoundedButton() { super(); decorate(); } 
         public RoundedButton(String text) { super(text); decorate(); } 
         public RoundedButton(Action action) { super(action); decorate(); } 
         public RoundedButton(Icon icon) { super(icon); decorate(); } 
         public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
         protected void decorate() { setBorderPainted(false); setOpaque(false); }
         @Override 
         protected void paintComponent(Graphics g) {
            Color c=new Color(255,250,240); //배경색 결정
            Color o=new Color(0,0,0); //글자색 결정
            int width = getWidth(); 
            int height = getHeight(); 
            Graphics2D graphics = (Graphics2D) g; 
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
            if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
            else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
            else { graphics.setColor(c); } 
            graphics.fillRoundRect(0, 0, width, height, 10, 10); 
            FontMetrics fontMetrics = graphics.getFontMetrics(); 
            Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
            int textX = (width - stringBounds.width) / 2; 
            int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
            graphics.setColor(o); 
            graphics.setFont(getFont()); 
            graphics.drawString(getText(), textX, textY); 
            graphics.dispose(); 
            super.paintComponent(g); 
            }
         }
}