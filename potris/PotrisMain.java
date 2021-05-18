package sist.com.potris;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class PotrisMain extends JFrame {
   private int speed = 10; //1p 
   private int speed2 = -10; //1p

   private int speed9 = 10; //2p
   private int speed99 = -10; //2p

   private int xx = 1100; //1p
   private int yy = 540; //1p

   private int xx2 = 200; //2p
   private int yy2 = 540; //2p

   private int x = -100;
   private int y = -100;

   private int x2 = -100;
   private int y2 = -100;

   private int weak; //공의 세기를 담아서 쓰는것
   private int weak2;
   
   // private int middle;
   // private int strong;
   
   private int cnt; //1p 공의 세기
   private int cnt2; //2p 공의 세기

   private int boom; 
   private int boom2;
   
   private int stop = 1; //1p 대포가 날라갈때 멈춰주는 기능. 1이면 움직이고 2면 멈춘다.
   private int stop2 = 1; //2p 대포가 날라갈때 멈춰주는 기능. 1이면 움직이고 2면 멈춘다.
   
   private int cannonstop = 1;
   private int cannonstop2 = 1;
    
   private int turn = 1;
   
   private int count = -1;
   
   private int hp=100; //처음 체력을 100으로 둔다.
   private int hp2=100;
   
   
   // private int xxx = 0;
   // private int yyy = 930;
   // private ImageIcon img=new ImageIcon("e:\\2.png");
   private ImageIcon img2 = new ImageIcon("e:\\010.png");
   private ImageIcon img4 = new ImageIcon("e:\\0000.png");
   private ImageIcon img3 = new ImageIcon("e:\\7777nobook.png"); //배경 이미지

   private ImageIcon img5 = new ImageIcon("e:\\dah.png");
   private ImageIcon img6 = new ImageIcon("e:\\daah.png");

   private ImageIcon img7 = new ImageIcon("e:\\sun111.png"); //1p 대포 이미지
   private ImageIcon img8 = new ImageIcon("e:\\eun1.png"); //2p 대포 이미지
   
   private JProgressBar bar = new JProgressBar();
   private JProgressBar bar2 = new JProgressBar();

   private ImageIcon[] img9 = new ImageIcon[26];

   private ImageIcon img10 = new ImageIcon("e:\\brick1.jpg");
   
   private int change = 1; //1p 왼쪽 오른쪽 이미지 바꿔주기

   private int change2 = 4; //2p 왼쪽 오른쪽 이미지 바꿔주기

   public PotrisMain() {
	   
	  

      for (int i = 0; i < 26; i++) {
         img9[i] = new ImageIcon("e:\\boom" + i + ".png");
      }

      this.setBounds(0, 0, 2000, 1010);
      this.setContentPane(new Potris());
      
     // bar.setValue(hp);
     // this.add(bar);
      
      //setHp(100);
      
      this.setVisible(true);

   }
   
   public void setHp(int hp) { //1p HP의 값을 넣어주는 메소드
	   bar.setValue(hp);
	   bar.setBounds(xx2-17, yy2+50, 100, 20);
	   this.add(bar);	   
   }
   
   public void setHp2(int hp2) { //1p HP의 값을 넣어주는 메소드
	   bar2.setValue(hp2);
	   bar2.setBounds(xx-17, yy+50, 100, 20);
	   this.add(bar2);	   
   }

   class Potris extends JPanel implements KeyListener{
      
      class TurnThread extends Thread{  //카운트를 세주고 턴을 바꿔주는 쓰레드.
    	 
         @Override
         public void run() {
            // TODO Auto-generated method stub
        	       		
            while(true) {
            	//setHp(hp);
            	
               //System.out.println(count);
               count=count+1; //시간초
               repaint();
               if(count > 20) { //20초가 넘으면 다시 0초로 바꿔주고 턴을 바꾼다.
                  count = 0; //count를 0으로 초기화
                  turn = turn * -1; //1p 2p 턴제 방식 1이면 1p가 움직임 , -1이면 2p가 움직임
                  //System.out.println("turn:"+turn);
               }
               try {
                  Thread.sleep(1000);
               } catch (Exception e) {
                  // TODO: handle exception
               }   
            }         
         }      
      }
      
      public Potris() {
    	     	  
         PotrisMain.this.addKeyListener(this);
                 
         TurnThread tt=new TurnThread();
         tt.start();
               
      }

      public void paintComponent(Graphics g) {
         super.paintComponent(g);        

         g.drawImage(img3.getImage(), 0, 0, this); //배경 이미지

         g.drawImage(img10.getImage(), 640, 300, this); //벽        
         
         g.drawImage(img7.getImage(), x, y, this); //1p 대포이미지

         g.drawImage(img8.getImage(), x2, y2, this); //2p 대포이미지
         
         String s=String.valueOf(count);
         g.setColor(Color.RED);
         g.setFont(new Font("", Font.BOLD, 80));
         g.drawString(s, 650, 170);
         
         g.setColor(Color.white); //각도 drawLine색 화이트
         
         setHp(hp);//이게 엄청 중요하다. 여기 위치에서 해줘야 바로 그려진다. 아니면 처음에 다른곳에 그려지고 그 이후에 그려진다. 쓰레드와 리페인트가 되어야 그려진다.
         setHp2(hp2);           

         if (change == 1) { // 1p 왼쪽 방향
            g.drawImage(img4.getImage(), xx, yy, this); // 왼쪽 얼굴 방향         
            g.drawLine(xx - 40 - speed2, yy + speed2, xx + 10, yy + 10); // 왼쪽 각도 이미지
         } else if (change == 2) { // 1p 오른쪽 방향
            g.drawImage(img2.getImage(), xx, yy, this); // 오른쪽 얼굴 방향
            g.drawLine(xx + 103 + speed2, yy + speed2, xx + 54, yy + 10); // 오른쪽 각도 이미지
         }

         if (change2 == 3) { // 2p 왼쪽 방향
            g.drawImage(img6.getImage(), xx2, yy2, this);
            g.drawLine(xx2 - 40 - speed99, yy2 + speed99, xx2 + 10, yy2 + 10); // 왼쪽 각도 이미지
         } else if (change2 == 4) { // 2p 오른쪽 방향
            g.drawImage(img5.getImage(), xx2, yy2, this);
            g.drawLine(xx2 + 103 + speed99, yy2 + speed99, xx2 + 54, yy2 + 10); // 오른쪽 각도 이미지
         }

         if (y > 550) { // 1p 폭탄 이미지
            int boomx = x;
            g.drawImage(img9[boom].getImage(), boomx - 100, 430, this);
         }

         if (y2 > 550) { // 2p 폭탄 이미지
            int boomx2 = x2;
            g.drawImage(img9[boom2].getImage(), boomx2 - 100, 430, this);
         }
         
      }

      @Override
      public void keyTyped(KeyEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void keyPressed(KeyEvent e) {

         if ((e.getKeyCode() == KeyEvent.VK_SPACE)&&(stop==1)&&(turn==1)) { //stop이 1일때 움직이고 상대방 대포가 발사하고 있을땐 2로 바뀌어서 못 움직인다.
            weak = cnt++;
            // System.out.println(cnt);
         }

         if ((e.getKeyCode() == KeyEvent.VK_LEFT)&&(stop==1)&&(turn==1)) {
        	 if(xx>730) { //벽 넘어로 못가게 막아둠
            change = 1;
            xx = xx - speed;
        	 }

            repaint();

         }
         if ((e.getKeyCode() == KeyEvent.VK_RIGHT)&&(stop==1)&&(turn==1)) {
        	 if(xx<1290) { //왼쪽 화면 밖으로 못 가게 막아둠
            change = 2;
            xx = xx + speed;
        	 }
            repaint();
         }

         if ((e.getKeyCode() == KeyEvent.VK_UP)&&(turn==1)) { // 1p각도 올려주기
            if (speed2 > -40) { // 위 각도 제한 걸어두기
               speed2 = speed2 - 1;
               repaint();
            }

         }
         if ((e.getKeyCode() == KeyEvent.VK_DOWN)&&(turn==1)) { // 1p각도 내려주기
            if (speed2 < -3) { // 아래 각도 제한 걸어두기
               speed2 = speed2 + 1;
               repaint();
            }

         }
        
         //////////////////////////////////////////////////////////////////////////////////////////////////////////////
         
         if ((e.getKeyCode() == KeyEvent.VK_Q)&&(stop2==1)&&(turn==-1)) { //stop2가 1일때 움직이고 상대방 대포가 발사하고 있을땐 2로 바뀌어서 못 움직인다.
            weak2 = cnt2++;
            // System.out.println(cnt);
         }

         if ((e.getKeyCode() == KeyEvent.VK_A)&&(stop2==1)&&(turn==-1)) {
        	 if(xx2>0) {
            change2 = 3;
            xx2 = xx2 - speed9;

            repaint();
        	 }

         }
         if ((e.getKeyCode() == KeyEvent.VK_D)&&(stop2==1)&&(turn==-1)) {
        	 if(xx2<580) {
            change2 = 4;
            xx2 = xx2 + speed9;

            repaint();
        	 }
         }

         if ((e.getKeyCode() == KeyEvent.VK_W)&&(turn==-1)) {
            if (speed99 > -40) {
               speed99 = speed99 - 1;
               repaint();
            }
         }
         if ((e.getKeyCode() == KeyEvent.VK_S)&&(turn==-1)) {
            if (speed99 < -3) {
               speed99 = speed99 + 1;
               repaint();
            }
         }

      }

      @Override
      public void keyReleased(KeyEvent e) {
         // TODO Auto-generated method stub
         // x = xx + 13;
         // y = yy + 10;
         ///////////////////////////////////////////////////////////////////////// 여기
         if ((e.getKeyCode() == KeyEvent.VK_SPACE)&&(cannonstop==1)) {

            class PotrisThread extends Thread {

               @Override
               public void run() {
                  // TODO Auto-generated method stub
                  /*
                   * for (int i = 0; i < 25; i++) { boom=i; repaint(); try { Thread.sleep(25); }
                   * catch (Exception e) { // TODO: handle exception } }
                   */

                  if ((change == 2)&&(turn==1)) { // 1p 오른쪽 이미지,누르고 뗄때 turn의 조건을 달아 주지 않으면 증가하는 발사는 하지 않지만 기본 발사는 가능하다.
                     x = xx + 40; // 발사 하기 전의 오른쪽 대포의 처음위치, xx는 탱크의 위치에서 오른쪽 +40
                     y = yy + 10; // yy는 탱크의 위치에서 밑으로 +10
                     while (true) {

                        if (weak < 2) { // 대포의 세기, 누를수록 cnt값이 증가, 더해주는 값이 오른쪽으로 가야하니까 x좌표로 증가
                           y = y + (speed2++);
                           x = x + speed - 5;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 4) {
                           y = y + (speed2++);
                           x = x + speed - 3;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 6) {
                           y = y + (speed2++);
                           x = x + speed - 1;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 8) {
                           y = y + (speed2++);
                           x = x + speed + 1;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 10) {
                           y = y + (speed2++);
                           x = x + speed + 3;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 12) {
                           y = y + (speed2++);
                           x = x + speed + 5;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 14) {
                           y = y + (speed2++);
                           x = x + speed + 7;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 16) {
                           y = y + (speed2++);
                           x = x + speed + 9;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 18) {
                           y = y + (speed2++);
                           x = x + speed + 11;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 20) {
                           y = y + (speed2++);
                           x = x + speed + 13;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 22) {
                           y = y + (speed2++);
                           x = x + speed + 15;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 24) {
                           y = y + (speed2++);
                           x = x + speed + 17;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak >= 26) {
                           y = y + (speed2++);
                           x = x + speed + 20;
                           stop2=2;
                           cannonstop=2;
                        }

                        repaint();
                        if ( y > 550 || x > 1360) { // y > 920 || x > 1890 노트북 조건 , 대포가 멈춰야 하는 위치
                           // speed2 = -40;
                           speed2 = -10; // 대포가 멈추고 speed2의 값을 -10으로 되돌려둔다.
                           cnt = 0; // 대포의 세기를 증가시키는 cnt값을 0으로 돌린다.
                           y = 1000; // 대포의 위치를 y=1000으로 둬서 화면 밖에서 대기시킨다.
                           stop2=1;
                           
                           cannonstop=1;
                                                            
                           count = 0; //대포가 터졌을때 시간초를 다시 0초로 바꿔주고
                           turn = turn * -1; //턴을 넘겨준다.
                                
                           if((xx2-20<x)&&(xx2+60>x)) { //xx2 yy2는 상대방 위치 x y는 내 미사일 위치
                        	   hp=hp-20;
                        	   setHp(hp);
                           }
                           ////////////////////////////////////////////////

                           for (int i = 0; i < 26; i++) { // 폭발 이미지
                              boom = i; // boom에 담아줘서 사용한다
                              repaint();

                              try {
                                 Thread.sleep(42);
                              } catch (Exception e) {
                                 // TODO: handle exception
                              }
                           }

                           /////////////////////////////////////////////

                           break; // 대포가 멈춰야 할때 break으로 멈춰준다.
                        }

                        try {
                           Thread.sleep(25);
                        } catch (Exception e) {
                           // TODO: handle exception
                        }
                     }

                  } else if ((change == 1)&&(turn==1)) { //1p 왼쪽방향
                     x = xx - 10;
                     y = yy + 10;

                     while (true) {

                        if (weak < 2) {
                           y = y + (speed2++);
                           x = x - speed + 5;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 4) {
                           y = y + (speed2++);
                           x = x - speed + 3;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 6) {
                           y = y + (speed2++);
                           x = x - speed + 1;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 8) {
                           y = y + (speed2++);
                           x = x - speed - 1;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 10) {
                           y = y + (speed2++);
                           x = x - speed - 3;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 12) {
                           y = y + (speed2++);
                           x = x - speed - 5;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 14) {
                           y = y + (speed2++);
                           x = x - speed - 7;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 16) {
                           y = y + (speed2++);
                           x = x - speed - 9;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 18) {
                           y = y + (speed2++);
                           x = x - speed - 11;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 20) {
                           y = y + (speed2++);
                           x = x - speed - 13;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 22) {
                           y = y + (speed2++);
                           x = x - speed - 15;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak < 24) {
                           y = y + (speed2++);
                           x = x - speed - 17;
                           stop2=2;
                           cannonstop=2;
                        } else if (weak >= 26) {
                           y = y + (speed2++);
                           x = x - speed - 20;
                           stop2=2;
                           cannonstop=2;
                        }

                        repaint();
                        if (x < 0 ||y > 550 || x > 1360 || (x>630&&x<710&&y>300)) { // y > 920 || x > 1890 노트북 조건 //()안의 조건은 벽에 부딪히면 터지는 조건
                           // speed2 = -40;
                           speed2 = -10;
                           cnt = 0;
                           y = 1000;
                           stop2=1;
                           
                           cannonstop=1;
                           
                           count = 0; //대포가 터졌을때 시간초를 다시 0초로 바꿔주고
                           turn = turn * -1; //턴을 넘겨준다.
                           
                           if((xx2-20<x)&&(xx2+60>x)) { //xx2 yy2는 상대방 위치 x y는 내 미사일 위치
                        	   hp=hp-20;
                        	   setHp(hp);
                           }
                           
                           //////////////////////////////////////////////////////

                           for (int i = 0; i < 26; i++) {
                              boom = i;
                              repaint();
                              try {
                                 Thread.sleep(42);
                              } catch (Exception e) {
                                 // TODO: handle exception
                              }
                           }

                           //////////////////////////////////////////////////////
                           break;
                        }

                        try {
                           Thread.sleep(25);
                        } catch (Exception e) {
                           // TODO: handle exception
                        }
                     }
                  }
               }
            }
            PotrisThread pt = new PotrisThread();
            pt.start();
         }
         ///////////////////////////////////////////////////////////////////////////////////////////////////// 여기

         if ((e.getKeyCode() == KeyEvent.VK_Q)&&(cannonstop2==1)) {

            class PotrisThread extends Thread {

               @Override
               public void run() {
                  // TODO Auto-generated method stub

                  if ((change2 == 4)&&(turn==-1)) { //2p 오른쪽 방향
                     x2 = xx2 + 40; //대포가 발사하기 전에 멀리가 있는 대포를 데려온다
                     y2 = yy2 + 10; 
                     while (true) {

                        if (weak2 < 2) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 - 5;
                           stop=2; //대포가 날가갈땐 stop이 2로 바뀌어서 1p의 방향키는 움직이지 않는다. 1p의 방향키는 stop이 1이 되어야 움직인다.
                           cannonstop2=2;
                        } else if (weak2 < 4) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 - 3;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 6) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 - 1;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 8) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 1;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 10) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 3;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 12) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 5;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 14) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 7;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 16) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 9;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 18) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 11;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 20) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 13;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 22) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 15;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 24) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 17;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 >= 26) {
                           y2 = y2 + (speed99++);
                           x2 = x2 + speed9 + 20;
                           stop=2;
                           cannonstop2=2;
                        }

                        repaint();
                        if (  y2 > 550 || x2 > 1360|| (x2>630&&x2<710&&y2>300)) { // y > 920 || x > 1890 노트북 조건
                           // speed2 = -40;
                           speed99 = -10;
                           cnt2 = 0;
                           y2 = 1000;
                           stop=1; //1p가 다시 움직일 수 있게 대포가 멈추면 다시 stop을 1로 바꿔준다.
                           
                           cannonstop2=1;
                           
                           count = 0; //대포가 터졌을때 시간초를 다시 0초로 바꿔주고
                           turn = turn * -1; //턴을 넘겨준다.
                           
                           if((xx-20<x2)&&(xx+60>x2)) { //xx2 yy2는 상대방 위치 x y는 내 미사일 위치
                        	   hp2=hp2-20;
                        	   setHp2(hp2);
                           }
                           ////////////////////////////////////////////////

                           for (int i = 0; i < 26; i++) {
                              boom2 = i;
                              repaint();

                              try {
                                 Thread.sleep(42);
                              } catch (Exception e) {
                                 // TODO: handle exception
                              }
                           }

                           /////////////////////////////////////////////

                           break;
                        }

                        try {
                           Thread.sleep(25);
                        } catch (Exception e) {
                           // TODO: handle exception
                        }
                     }

                  } else if ((change2 == 3)&&(turn==-1)) { //2p 왼쪽방향
                     x2 = xx2 - 10; //대포가 발사하기 전에 멀리가 있는 대포를 데려온다
                     y2 = yy2 + 10; 

                     while (true) {

                        if (weak2 < 2) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 + 5;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 4) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 + 3;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 6) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 + 1;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 8) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 1;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 10) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 3;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 12) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 5;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 14) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 7;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 16) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 9;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 18) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 11;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 20) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 13;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 22) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 15;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 < 24) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 17;
                           stop=2;
                           cannonstop2=2;
                        } else if (weak2 >= 26) {
                           y2 = y2 + (speed99++);
                           x2 = x2 - speed9 - 20;
                           stop=2;
                           cannonstop2=2;
                        }

                        repaint();
                        if (y2 > 550 || x2 > 1360 || x2 < 0 ) { // y > 920 || x > 1890 노트북 조건
                           // speed2 = -40;
                           speed99 = -10;
                           cnt2 = 0;
                           y2 = 1000;
                           stop=1;
                           
                           cannonstop2=1;
                           
                           count = 0; //대포가 터졌을때 시간초를 다시 0초로 바꿔주고
                           turn = turn * -1; //턴을 넘겨준다.
                           
                           if((xx-20<x2)&&(xx+60>x2)) { //xx2 yy2는 상대방 위치 x y는 내 미사일 위치
                        	   hp2=hp2-20;
                        	   setHp2(hp2);
                           }
                           ////////////////////////////////////////////////

                           for (int i = 0; i < 26; i++) {
                              boom2 = i;
                              repaint();

                              try {
                                 Thread.sleep(42);
                              } catch (Exception e) {
                                 // TODO: handle exception
                              }
                           }

                           /////////////////////////////////////////////
                           break;
                        }

                        try {
                           Thread.sleep(25);
                        } catch (Exception e) {
                           // TODO: handle exception
                        }
                     }
                  }

               }
            }
            PotrisThread pt = new PotrisThread();
            pt.start();
         }
      }
   }
//////////////////////////////////////////////////////////////////////////////

   public static void main(String[] args) {
      new PotrisMain();
   }
}
