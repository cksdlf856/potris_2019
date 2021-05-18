package sist.com.potris;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PotrisPaintComponent extends JFrame {
	private int speed = 10;
	// private int speed2 = -40;
	private int speed2 = -10;

	private int speed9 = 10;
	// private int speed2 = -40;
	private int speed99 = -10;

	// private int xx = 0;
	// private int yy = 0;

	private int xx = 1100;
	private int yy = 540; // ���� ȭ��

	private int xx2 = 200;
	private int yy2 = 540;

	// private int yy = 650;//��Ʈ�� ȭ��

	private int x = -100;
	private int y = -100;

	private int x2 = -100;
	private int y2 = -100;

	private int weak; //���� ���⸦ ��Ƽ� ���°�

	private int weak2;
	// private int middle;
	// private int strong;
	private int cnt; //���� ����

	private int cnt2;

	private int boom;
	private int boom2;
	
	private int stop = 1; //������ ���󰥶� �����ִ� ���. 1�̸� �����̰� 2�� �����.
	private int stop2 = 1;
	
	private int turn = 1;
	private int count;

	// private int xxx = 0;
	// private int yyy = 930;
	// private ImageIcon img=new ImageIcon("e:\\2.png");
	private ImageIcon img2 = new ImageIcon("e:\\010.png");
	private ImageIcon img4 = new ImageIcon("e:\\0000.png");
	private ImageIcon img3 = new ImageIcon("e:\\7777nobook.png"); //��� �̹���

	private ImageIcon img5 = new ImageIcon("e:\\dah.png");
	private ImageIcon img6 = new ImageIcon("e:\\daah.png");

	private ImageIcon img7 = new ImageIcon("e:\\sun111.png"); //1p ���� �̹���
	private ImageIcon img8 = new ImageIcon("e:\\eun1.png"); //2p ���� �̹���

	private ImageIcon[] img9 = new ImageIcon[26];

	private int change = 1; //1p ���� ������ �ٲ��ֱ�

	private int change2 = 4; //2p ���� ������ �ٲ��ֱ�

	public PotrisPaintComponent() {

		for (int i = 0; i < 26; i++) {
			img9[i] = new ImageIcon("e:\\boom" + i + ".png");
		}

		this.setBounds(0, 0, 2000, 1010);
		this.setContentPane(new Potris());
		this.setVisible(true);

	}

	class Potris extends JPanel implements KeyListener {
		
		class TurnThread extends Thread{  //ī��Ʈ�� ���ְ� ���� �ٲ��ִ� ������.

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true) {
					
					//System.out.println(count);
					count=count+1; //�ð���
					repaint();
					if(count > 20) { //20�ʰ� ������ �ٽ� 0�ʷ� �ٲ��ְ� ���� �ٲ۴�.
						count = 0; //count�� 0���� �ʱ�ȭ
						turn = turn * -1; //1p 2p ���� ��� 1�̸� 1p�� ������ , -1�̸� 2p�� ������
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
			PotrisPaintComponent.this.addKeyListener(this);
		    
			TurnThread tt=new TurnThread();
			tt.start();
			/*
			 * class PotrisThread extends Thread{
			 * 
			 * @Override public void run() { // TODO Auto-generated method stub while(true)
			 * { x= x+speed; y= y+speed; repaint();
			 * 
			 * try { Thread.sleep(10); } catch (Exception e) { // TODO: handle exception } }
			 * } }
			 * 
			 * PotrisThread pt=new PotrisThread(); pt.start();
			 */
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// g.setColor(Color.WHITE);
			// g.fillRect(0, 0, 2000, 1010);

			g.drawImage(img3.getImage(), 0, 0, this); //��� �̹���

			// g.setColor(new Color(251, 199, 0));
			// g.fillOval(x, y, 20, 20);

			g.drawImage(img7.getImage(), x, y, this); //1p �����̹���

			// g.setColor(new Color(251, 199, 0));
			// g.fillOval(x2, y2, 20, 20);

			g.drawImage(img8.getImage(), x2, y2, this); //2p �����̹���
			
			String s=String.valueOf(count);
			g.setColor(Color.white);
			g.setFont(new Font("", Font.BOLD, 40));
			g.drawString(s, 680, 170);

			// g.drawImage(img.getImage(), x, y, this);

			// g.setColor(Color.yellow);
			// g.fillRect(xx, yy, 40, 40);

			// g.drawImage(img2.getImage(), xx, yy, this);

			if (change == 1) { // 1p ���� ����
				g.drawImage(img4.getImage(), xx, yy, this); // ���� �� ����
				g.drawLine(xx - 40 - speed2, yy + speed2, xx + 10, yy + 10); // ���� ���� �̹���
			} else if (change == 2) { // ������ ����
				g.drawImage(img2.getImage(), xx, yy, this); // ������ �� ����
				g.drawLine(xx + 103 + speed2, yy + speed2, xx + 54, yy + 10); // ������ ���� �̹���
			}

			if (change2 == 3) { // 2p ���� ����
				g.drawImage(img6.getImage(), xx2, yy2, this);
				g.drawLine(xx2 - 40 - speed99, yy2 + speed99, xx2 + 10, yy2 + 10); // ���� ���� �̹���
			} else if (change2 == 4) { // ������ ����
				g.drawImage(img5.getImage(), xx2, yy2, this);
				g.drawLine(xx2 + 103 + speed99, yy2 + speed99, xx2 + 54, yy2 + 10); // ������ ���� �̹���
			}

			if (y > 550) { // 1p ��ź �̹���
				int boomx = x;
				g.drawImage(img9[boom].getImage(), boomx - 100, 430, this);
			}

			if (y2 > 550) { // 2p ��ź �̹���
				int boomx2 = x2;
				g.drawImage(img9[boom2].getImage(), boomx2 - 100, 430, this);
			}

			/*
			 * g.setColor(Color.BLACK); g.drawOval(1335, 500, 20, 20);
			 */

			/*
			 * g.setColor(Color.PINK); g.fillOval(xxx, yyy, 17, 17);
			 */
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {

			/*
			 * if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			 * 
			 * class PotrisThread extends Thread {
			 * 
			 * @Override public void run() { // TODO Auto-generated method stub while (true)
			 * {
			 * 
			 * y = y + (speed2++); x = x + speed +10;
			 * 
			 * repaint(); if (y > 920 || x > 1890) { //speed2 = -40; speed2 = -30; break; }
			 * 
			 * try { Thread.sleep(20); } catch (Exception e) { // TODO: handle exception } }
			 * } } PotrisThread pt = new PotrisThread(); pt.start(); }
			 */
			if ((e.getKeyCode() == KeyEvent.VK_SPACE)&&(stop==1)&&(turn==1)) {
				weak = cnt++;
				// System.out.println(cnt);
			}

			if ((e.getKeyCode() == KeyEvent.VK_LEFT)&&(stop==1)&&(turn==1)) {
				change = 1;
				xx = xx - speed;

				repaint();

			}
			if ((e.getKeyCode() == KeyEvent.VK_RIGHT)&&(stop==1)&&(turn==1)) {
				change = 2;
				xx = xx + speed;

				repaint();
			}

			if ((e.getKeyCode() == KeyEvent.VK_UP)&&(turn==1)) { // 1p���� �÷��ֱ�
				if (speed2 > -40) { // �� ���� ���� �ɾ�α�
					speed2 = speed2 - 1;
					repaint();
				}

			}
			if ((e.getKeyCode() == KeyEvent.VK_DOWN)&&(turn==1)) { // 1p���� �����ֱ�
				if (speed2 < -3) { // �Ʒ� ���� ���� �ɾ�α�
					speed2 = speed2 + 1;
					repaint();
				}

			}

			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if ((e.getKeyCode() == KeyEvent.VK_Q)&&(stop2==1)&&(turn==-1)) {
				weak2 = cnt2++;
				// System.out.println(cnt);
			}

			if ((e.getKeyCode() == KeyEvent.VK_A)&&(stop2==1)&&(turn==-1)) {
				change2 = 3;
				xx2 = xx2 - speed9;

				repaint();

			}
			if ((e.getKeyCode() == KeyEvent.VK_D)&&(stop2==1)&&(turn==-1)) {
				change2 = 4;
				xx2 = xx2 + speed9;

				repaint();
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

			/*
			 * if (e.getKeyCode() == KeyEvent.VK_A) { xxx = xxx - speed;
			 * 
			 * repaint();
			 * 
			 * } if (e.getKeyCode() == KeyEvent.VK_D) {
			 * 
			 * xxx = xxx + speed;
			 * 
			 * repaint(); }
			 */

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			// x = xx + 13;
			// y = yy + 10;
			///////////////////////////////////////////////////////////////////////// ����
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {

				class PotrisThread extends Thread {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						/*
						 * for (int i = 0; i < 25; i++) { boom=i; repaint(); try { Thread.sleep(25); }
						 * catch (Exception e) { // TODO: handle exception } }
						 */

						if ((change == 2)&&(turn==1)) { // 1p ������ �̹���,������ ���� turn�� ������ �޾� ���� ������ �����ϴ� �߻�� ���� ������ �⺻ �߻�� �����ϴ�.
							x = xx + 40; // �߻� �ϱ� ���� ������ ������ ó����ġ, xx�� ��ũ�� ��ġ���� ������ +40
							y = yy + 10; // yy�� ��ũ�� ��ġ���� ������ +10
							while (true) {

								if (cnt < 2) { // ������ ����, �������� cnt���� ����, �����ִ� ���� ���������� �����ϴϱ� x��ǥ�� ����
									y = y + (speed2++);
									x = x + speed - 5;
									stop2=2;
								} else if (cnt < 4) {
									y = y + (speed2++);
									x = x + speed - 3;
									stop2=2;
								} else if (cnt < 6) {
									y = y + (speed2++);
									x = x + speed - 1;
									stop2=2;
								} else if (cnt < 8) {
									y = y + (speed2++);
									x = x + speed + 1;
									stop2=2;
								} else if (cnt < 10) {
									y = y + (speed2++);
									x = x + speed + 3;
									stop2=2;
								} else if (cnt < 12) {
									y = y + (speed2++);
									x = x + speed + 5;
									stop2=2;
								} else if (cnt < 14) {
									y = y + (speed2++);
									x = x + speed + 7;
									stop2=2;
								} else if (cnt < 16) {
									y = y + (speed2++);
									x = x + speed + 9;
									stop2=2;
								} else if (cnt < 18) {
									y = y + (speed2++);
									x = x + speed + 11;
									stop2=2;
								} else if (cnt < 20) {
									y = y + (speed2++);
									x = x + speed + 13;
									stop2=2;
								} else if (cnt < 22) {
									y = y + (speed2++);
									x = x + speed + 15;
									stop2=2;
								} else if (cnt < 24) {
									y = y + (speed2++);
									x = x + speed + 17;
									stop2=2;
								} else if (cnt >= 26) {
									y = y + (speed2++);
									x = x + speed + 20;
									stop2=2;
								}

								repaint();
								if (y > 550 || x > 1870) { // y > 920 || x > 1890 ��Ʈ�� ���� , ������ ����� �ϴ� ��ġ
									// speed2 = -40;
									speed2 = -10; // ������ ���߰� speed2�� ���� -10���� �ǵ����д�.
									cnt = 0; // ������ ���⸦ ������Ű�� cnt���� 0���� ������.
									y = 1000; // ������ ��ġ�� y=1000���� �ּ� ȭ�� �ۿ��� ����Ų��.
									stop2=1;
									                                 
									count = 0; //������ �������� �ð��ʸ� �ٽ� 0�ʷ� �ٲ��ְ�
									turn = turn * -1; //���� �Ѱ��ش�.
									////////////////////////////////////////////////

									for (int i = 0; i < 26; i++) { // ���� �̹���
										boom = i; // boom�� ����༭ ����Ѵ�
										repaint();

										try {
											Thread.sleep(42);
										} catch (Exception e) {
											// TODO: handle exception
										}
									}

									/////////////////////////////////////////////

									break; // ������ ����� �Ҷ� break���� �����ش�.
								}

								try {
									Thread.sleep(25);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

						} else if ((change == 1)&&(turn==1)) {
							x = xx - 10;
							y = yy + 10;

							while (true) {

								if (cnt < 2) {
									y = y + (speed2++);
									x = x - speed + 5;
									stop2=2;
								} else if (cnt < 4) {
									y = y + (speed2++);
									x = x - speed + 3;
									stop2=2;
								} else if (cnt < 6) {
									y = y + (speed2++);
									x = x - speed + 1;
									stop2=2;
								} else if (cnt < 8) {
									y = y + (speed2++);
									x = x - speed - 1;
									stop2=2;
								} else if (cnt < 10) {
									y = y + (speed2++);
									x = x - speed - 3;
									stop2=2;
								} else if (cnt < 12) {
									y = y + (speed2++);
									x = x - speed - 5;
									stop2=2;
								} else if (cnt < 14) {
									y = y + (speed2++);
									x = x - speed - 7;
									stop2=2;
								} else if (cnt < 16) {
									y = y + (speed2++);
									x = x - speed - 9;
									stop2=2;
								} else if (cnt < 18) {
									y = y + (speed2++);
									x = x - speed - 11;
									stop2=2;
								} else if (cnt < 20) {
									y = y + (speed2++);
									x = x - speed - 13;
									stop2=2;
								} else if (cnt < 22) {
									y = y + (speed2++);
									x = x - speed - 15;
									stop2=2;
								} else if (cnt < 24) {
									y = y + (speed2++);
									x = x - speed - 17;
									stop2=2;
								} else if (cnt >= 26) {
									y = y + (speed2++);
									x = x - speed - 20;
									stop2=2;
								}

								repaint();
								if (y > 550 || x > 1870) { // y > 920 || x > 1890 ��Ʈ�� ����
									// speed2 = -40;
									speed2 = -10;
									cnt = 0;
									y = 1000;
									stop2=1;
									
									count = 0; //������ �������� �ð��ʸ� �ٽ� 0�ʷ� �ٲ��ְ�
									turn = turn * -1; //���� �Ѱ��ش�.
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
			///////////////////////////////////////////////////////////////////////////////////////////////////// ����

			if (e.getKeyCode() == KeyEvent.VK_Q) {

				class PotrisThread extends Thread {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						if ((change2 == 4)&&(turn==-1)) { //2p ������ ����
							x2 = xx2 + 40;
							y2 = yy2 + 10;
							while (true) {

								if (cnt2 < 2) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 - 5;
									stop=2; //������ �������� stop�� 2�� �ٲ� 1p�� ����Ű�� �������� �ʴ´�. 1p�� ����Ű�� stop�� 1�� �Ǿ�� �����δ�.
								} else if (cnt2 < 4) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 - 3;
									stop=2;
								} else if (cnt2 < 6) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 - 1;
									stop=2;
								} else if (cnt2 < 8) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 1;
									stop=2;
								} else if (cnt2 < 10) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 3;
									stop=2;
								} else if (cnt2 < 12) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 5;
									stop=2;
								} else if (cnt2 < 14) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 7;
									stop=2;
								} else if (cnt2 < 16) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 9;
									stop=2;
								} else if (cnt2 < 18) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 11;
									stop=2;
								} else if (cnt2 < 20) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 13;
									stop=2;
								} else if (cnt2 < 22) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 15;
									stop=2;
								} else if (cnt2 < 24) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 17;
									stop=2;
								} else if (cnt2 >= 26) {
									y2 = y2 + (speed99++);
									x2 = x2 + speed9 + 20;
									stop=2;
								}

								repaint();
								if (y2 > 550 || x2 > 1870) { // y > 920 || x > 1890 ��Ʈ�� ����
									// speed2 = -40;
									speed99 = -10;
									cnt2 = 0;
									y2 = 1000;
									stop=1; //1p�� �ٽ� ������ �� �ְ� ������ ���߸� �ٽ� stop�� 1�� �ٲ��ش�.
									
									count = 0; //������ �������� �ð��ʸ� �ٽ� 0�ʷ� �ٲ��ְ�
									turn = turn * -1; //���� �Ѱ��ش�.
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

						} else if ((change2 == 3)&&(turn==-1)) { //2p ���ʹ���
							x2 = xx2 - 10;
							y2 = yy2 + 10;

							while (true) {

								if (cnt2 < 2) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 + 5;
									stop=2;
								} else if (cnt2 < 4) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 + 3;
									stop=2;
								} else if (cnt2 < 6) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 + 1;
									stop=2;
								} else if (cnt2 < 8) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 1;
									stop=2;
								} else if (cnt2 < 10) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 3;
									stop=2;
								} else if (cnt2 < 12) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 5;
									stop=2;
								} else if (cnt2 < 14) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 7;
									stop=2;
								} else if (cnt2 < 16) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 9;
									stop=2;
								} else if (cnt2 < 18) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 11;
									stop=2;
								} else if (cnt2 < 20) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 13;
									stop=2;
								} else if (cnt2 < 22) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 15;
									stop=2;
								} else if (cnt2 < 24) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 17;
									stop=2;
								} else if (cnt2 >= 26) {
									y2 = y2 + (speed99++);
									x2 = x2 - speed9 - 20;
									stop=2;
								}

								repaint();
								if (y2 > 550 || x2 > 1870) { // y > 920 || x > 1890 ��Ʈ�� ����
									// speed2 = -40;
									speed99 = -10;
									cnt2 = 0;
									y2 = 1000;
									stop=1;
									
									count = 0; //������ �������� �ð��ʸ� �ٽ� 0�ʷ� �ٲ��ְ�
									turn = turn * -1; //���� �Ѱ��ش�.
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
		new PotrisPaintComponent();
	}
}