package com.fly;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements KeyListener, Runnable{
	//�������б���
	private List<BackGround> allBg = new ArrayList<>();
	//���浱ǰ����
	private BackGround nowBg = new BackGround();
	//����˫����
	private Image offScreenImage = null;
	//����¶���
	private Mario mario = new Mario();
	//����һ���̶߳�������ʵ������µ��˶�
	private Thread thread = new Thread(this);
	public MyFrame() {
		this.setSize(800, 600);
		
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setResizable(true);
		
		this.addKeyListener(this);
		
		this.setTitle("FLY_superMario");
		//��ʼ��ͼƬ
		StaticValue.init();
		//��ʼ�������
		mario = new Mario(10, 355); 
		//����ȫ������
		for(int i = 1; i <= 3; i++) {
			allBg.add(new BackGround(i, i == 3 ? true : false));
		}
		//��һ���������ó���
		nowBg = allBg.get(0);
		mario.setBackGrund(nowBg);
		//����ͼ��
		repaint();
		thread.start();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if (offScreenImage == null) {
			offScreenImage = createImage(800, 600);		
		}
		Graphics graphics = offScreenImage.getGraphics();
		graphics.fillRect(0, 0, 800, 600);
		
		//���Ʊ���
		graphics.drawImage(nowBg.getBgImage(), 0, 0, this);
		
		//���Ƶ���
		for(Enemy e: nowBg.getEnemies()) {
			graphics.drawImage(e.getShow(), e.getX(), e.getY(), this);
		}
		//�����ϰ���
		for(Obstacle ob : nowBg.getObstacleList()) {
			graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);
		}
		
		//���ƳǱ�
		graphics.drawImage(nowBg.getTower(), 620, 270, this);
		
		//�������
		graphics.drawImage(nowBg.getGan(), 500, 220, this);
		
		//���������
		graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);
		
		//��ӷ���
		Color c = graphics.getColor();
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("����", Font.BOLD, 25));
		graphics.drawString("��ǰ�ķ���Ϊ" + mario.getScore(), 300, 100);
		graphics.setColor(c);
		
		//��ͼ����Ƶ�������
		g.drawImage(offScreenImage, 0, 0, this);
	}

	public static void main(String [] args) {
		MyFrame myFrame = new MyFrame();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//�����ƶ�
		if(e.getKeyCode() == 68) {
			mario.rightMove();
		}
		//�����ƶ�
		if (e.getKeyCode() == 65) {
			mario.leftMove();
		}
		//��Ծ
		if (e.getKeyCode() == 87) {
			mario.jump();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//����ֹͣ
	if (e.getKeyCode() == 65) {
		mario.leftStop();
	}
		//����ֹͣ
	if (e.getKeyCode() == 68) {
		mario.rightStop();
	}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			repaint();
			try {
				Thread.sleep(50);
				if(mario.getX() >= 775) {
					nowBg = allBg.get(nowBg.getSort());
					mario.setBackGrund(nowBg);
					mario.setX(10);
					mario.setY(355);
				}
				
				//������Ƿ�����
				if (mario.isDeath()) {
					JOptionPane.showMessageDialog(this, "��Ϸ����");
					System.exit(0);
				}
				
				//��Ϸ�Ƿ����
				if (mario.isOK()) {
					JOptionPane.showMessageDialog(this, "ʤ��");
					System.exit(0);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
