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
	//背景
	public static BufferedImage bg = null;
	public static BufferedImage bg2 = null;
	//左跳
	public static BufferedImage jump_L = null;
	//右跳
	public static BufferedImage jump_r = null;
	//左站立
	public static BufferedImage stand_l = null;
	//右站立
	public static BufferedImage stand_r = null;
	//城堡
	public static BufferedImage tower = null;
	//旗杆
	public static BufferedImage gan = null;
	//障碍物
	public static List<BufferedImage> obstacle= new ArrayList<>();
	//向左跑
	public static List<BufferedImage> run_l= new ArrayList<>();
	//向右跑
	public static List<BufferedImage> run_r= new ArrayList<>();
	//蘑菇
	public static List<BufferedImage> mogu= new ArrayList<>();
	//食人花
	public static List<BufferedImage> flower= new ArrayList<>();
	//路径前缀，方便后续调用
	public static String path= "C:\\Users\\。\\eclipse-workspace\\game\\src\\images\\";
	//初始化方法
	public static void init() {
		try {
			//加载背景图片
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
		//加载水管
		for(int i = 1; i <= 4; i++) {
			try{
				obstacle.add(ImageIO.read(new File(path + "pipe"+ i +".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		//不可破坏方块和旗子
		try {
			obstacle.add(ImageIO.read(new File(path + "brick2.png")));
			obstacle.add(ImageIO.read(new File(path + "flag.png")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//加载蘑菇敌人
		for(int i = 1; i <= 3; i++) {
			try{
				mogu.add(ImageIO.read(new File(path + "fungus"+ i +".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		//加载食人花敌人
		for(int i = 1; i <= 2; i++) {
			try{
				flower.add(ImageIO.read(new File(path + "flower1."+ i +".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
