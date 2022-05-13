package com.fly;

import java.awt.image.BufferedImage;

public class Mario implements Runnable {
	// ���ڱ�ʾ��������
	private int x;
	private int y;

	// ���ڱ�ʾ��ǰ״̬
	private String status;

	// ������ʾ��ǰ״̬��Ӧ��ͼ��
	private BufferedImage show = null;

	// ����һ��BackGround����������ȡ�ϰ�����Ϣ
	private BackGround backGrund = new BackGround();

	// ����ʵ������¶���
	private Thread thread = null;

	// ����µ��ƶ��ٶ�
	private int xSpeed;

	// ����µ���Ծ�ٶ�
	private int ySpeed;

	// ����һ������
	private int index;

	// ���������ʱ��
	private int upTime = 0;
	
	//�����ж�������Ƿ��ߵ��Ǳ��ſ�
	private boolean isOK;
	
	//������Ƿ�����
	private boolean isDeath = false;
	
	//��ʾ����
	private int score = 0;

	public Mario() {

	}

	public Mario(int x, int y) {
		this.x = x;
		this.y = y;
		show = StaticValue.stand_r;
		this.status = "stand--right";
		thread = new Thread(this);
		thread.start();
	}
	
	//�������������
	public void death() {
		isDeath = true;
	}

	//����������ƶ�
	public void leftMove() {
		//�ж�������Ƿ���������
		if (backGrund.isReach()) {
			xSpeed = 0;
		}
		// �ı��ٶ�
		xSpeed = -5;
		// �ж�������Ƿ��ڿ���
		if (status.indexOf("jump") != -1) {
			status = "jump--left";
		} else {
			status = "move--left";
		}
	}

	// ����������ƶ�
	public void rightMove() {
		// �ж�������Ƿ���������
		if (backGrund.isReach()) {
			xSpeed = 0;
		}
		// �ı��ٶ�
		xSpeed = 5;
		if (status.indexOf("jump") != -1) {
			status = "jump--right";
		} else {
			status = "move--right";
		}
	}

	// ���������ֹͣ
	public void leftStop() {
		xSpeed = 0;
		if (status.indexOf("jump") != -1) {
			status = "jump--left";
		} else {
			status = "stop--left";
		}
	}

	// ���������ֹͣ
	public void rightStop() {
		xSpeed = 0;
		if (status.indexOf("jump") != -1) {
			status = "jump--right";
		} else {
			status = "stop--right";
		}
	}

	// �������Ծ
	public void jump() {
		if (status.indexOf("jump") == -1) {
			if (status.indexOf("left") != -1) {
				status = "jump--left";
			} else {
				status = "jump--right";
			}
			ySpeed = -10;
			upTime = 7;
		}
		//�ж�������Ƿ���������
		if (backGrund.isReach()) {
			ySpeed = 0;
		}
	}

	// ��������䷽��
	public void fall() {
		if (status.indexOf("left") != -1) {
			status = "jump--left";
		} else {
			status = "jump--right";
		}
		ySpeed = 10;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// �Ƿ�λ���ϰ�������
			boolean onObstacle = false;
			//�Ƿ����������
			boolean canRight = true;
			//�Ƿ����������
			boolean canLeft = true;
			//�ж�������Ƿ񵽴����λ��
			if(backGrund.isFlag() && this.x >= 500) {
				this.backGrund.setReach(true);
				//�ж������Ƿ��������
				if (this.backGrund.isBase()) {
					status = "move--right";
					if (x < 690) {
						x += 5;
					}else {
						isOK = true;
					}
				}else {	
					if(y < 395) {
						xSpeed = 0;
						this.y += 5;
						status = "jump--right";
					}
					if (y > 395) {
						this.y = 395;
						status = "stop--right"; 
					}
				}
			}else {
			// ������ǰ�������е��ϰ���
			for (int i = 0; i < backGrund.getObstacleList().size(); i++) {
				Obstacle ob = backGrund.getObstacleList().get(i);
				// �ж�������Ƿ�λ���ϰ�����
				if (ob.getY() == this.y + 25 && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
					onObstacle = true;
				}
				//�ж�������Ƿ�����������ש��
				if ((ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20) && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
					if (ob.getType() == 0) {
						backGrund.getObstacleList().remove(ob);
						score++;
					}
					upTime = 0;
				}
				//�ж��Ƿ����������
				if (ob.getX() == this.x + 25 && (ob.getY() > this.y - 30 &&	ob.getY() < this.y + 25)) {
					canRight = false;
				}
				//�ж��Ƿ����������
				if (ob.getX() == this.x - 30 && (ob.getY() > this.y - 30 &&	ob.getY() < this.y + 25)) {
					canLeft = false;
				}
			}
			
			//�ж�������Ƿ����������������߲���Ģ������
			for(int i = 0; i < backGrund.getEnemies().size(); i++) {
				Enemy e = backGrund.getEnemies().get(i);
				
				if (e.getY() == this.y + 20 && (e.getX() - 25 <= this.x && e.getX() + 35 >= this.x)) {
					if (e.getType() == 1) {
						e.death();
						score += 2;
						upTime = 3;
						ySpeed = -10;
					}else if (e.getType() == 2) {
						//���������
						death();
					}
				}
				if ((e.getX() + 35 > this.x && e.getX() - 25 < this.x) && (e.getY() + 35 > this.y && e.getY() - 20 < this.y)) {
					//���������
					death();
				}
			}

			// �����������Ծ����
			if (onObstacle && upTime == 0) {
				if (status.indexOf("left") != -1) {
					if (xSpeed != 0) {
						status = "move--left";
					} else {
						status = "stop--left";
					}
				} else {
					if (xSpeed != 0) {
						status = "move--right";
					} else {
						status = "stop--right";
					}
				}
			} else {
				if (upTime != 0) {
					upTime--;
				} else {
					fall();
				}
				y += ySpeed;
			}
			}
			
			if ((canLeft && xSpeed < 0) || (canRight && xSpeed > 0)) {
				x += xSpeed;
				// �Ƿ������
				if (x < 0) {
					x = 0;
				}
			}
			// �Ƿ��ƶ�״̬
			if (status.contains("move")) {
				index = index == 0 ? 1 : 0;
			}
			// �ж��Ƿ������ƶ�
			if ("move--left".equals(status)) {
				show = StaticValue.run_l.get(index);
			}
			// �Ƿ������ƶ�
			if ("move--right".equals(status)) {
				show = StaticValue.run_r.get(index);
			}
			// �Ƿ�����ֹͣ
			if ("stop--left".equals(status)) {
				show = StaticValue.stand_l;
			}
			// �Ƿ�����ֹͣ
			if ("stop--right".equals(status)) {
				show = StaticValue.stand_r;
			}
			// �Ƿ�������Ծ
			if ("jump--left".equals(status)) {
				show = StaticValue.jump_L;
			}
			// �Ƿ�������Ծ
			if ("jump--right".equals(status)) {
				show = StaticValue.jump_r;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public int getScore() {
		return score;
	}

	public boolean isDeath() {
		return isDeath;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getShow() {
		return show;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setShow(BufferedImage show) {
		this.show = show;
	}

	public void setBackGrund(BackGround backGrund) {
		this.backGrund = backGrund;
	}

	public boolean isOK() {
		return isOK;
	}

}
