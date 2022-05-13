package com.fly;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
//	public static void main(String [] args) {
//		System.out.println(path);
//	}
	//����
	public static BufferedImage bg = null;
	public static BufferedImage bg2 = null;
	//����
	public static BufferedImage jump_L = null;
	//����
	public static BufferedImage jump_r = null;
	//��վ��
	public static BufferedImage stand_l = null;
	//��վ��
	public static BufferedImage stand_r = null;
	//�Ǳ�
	public static BufferedImage tower = null;
	//���
	public static BufferedImage gan = null;
	//�ϰ���
	public static List<BufferedImage> obstacle= new ArrayList<>();
	//������
	public static List<BufferedImage> run_l= new ArrayList<>();
	//������
	public static List<BufferedImage> run_r= new ArrayList<>();
	//Ģ��
	public static List<BufferedImage> mogu= new ArrayList<>();
	//ʳ�˻�
	public static List<BufferedImage> flower= new ArrayList<>();
	//·��ǰ׺�������������
	public static String path= "C:\\Users\\��\\eclipse-workspace\\game\\src\\images\\";
	//��ʼ������
	public static void init() {
		try {
			//���ر���ͼƬ
			bg = ImageIO.read(new File(path + "bg.png"));
			bg2 = ImageIO.read(new File(path + "bg2.png"));
			//stand_l
			stand_l = ImageIO.read(new File(path + "s_mario_stand_L.png"));
			//stand_r
			stand_r = ImageIO.read(new File(path + "s_mario_stand_R.png"));
			//tower
			tower = ImageIO.read(new File(path + "tower.png"));
			//gan
			gan = ImageIO.read(new File(path + "gan.png"));
			//jump_l
			jump_L = ImageIO.read(new File(path + "s_mario_jump1_L.png"));
			//jump_r
			jump_r = ImageIO.read(new File(path + "s_mario_jump1_R.png"));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//run_l
		for (int i = 1; i <= 2; i++) {
			try {
				run_l.add(ImageIO.read(new File(path + "s_mario_run"+ i +"_L.png")));
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//run_r
		for (int i = 1; i <= 2; i++) {
			try {
				run_r.add(ImageIO.read(new File(path + "s_mario_run"+ i +"_R.png")));
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//obstacle
		try {
			obstacle.add(ImageIO.read(new File(path + "brick.png")));
			obstacle.add(ImageIO.read(new File(path + "soil_up.png")));
			obstacle.add(ImageIO.read(new File(path + "soil_base.png")));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//����ˮ��
		for(int i = 1; i <= 4; i++) {
			try{
				obstacle.add(ImageIO.read(new File(path + "pipe"+ i +".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		//�����ƻ����������
		try {
			obstacle.add(ImageIO.read(new File(path + "brick2.png")));
			obstacle.add(ImageIO.read(new File(path + "flag.png")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//����Ģ������
		for(int i = 1; i <= 3; i++) {
			try{
				mogu.add(ImageIO.read(new File(path + "fungus"+ i +".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		//����ʳ�˻�����
		for(int i = 1; i <= 2; i++) {
			try{
				flower.add(ImageIO.read(new File(path + "flower1."+ i +".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
