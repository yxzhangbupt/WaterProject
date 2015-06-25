package com.yxzhang.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author acer
 *ˮ������	
 */
public class Model {
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException{
		final double  g=9.8;
		int n=0;
		double dt=1;
		/*
		 * ����д���ļ�����
		 */
		PrintWriter GDout = new PrintWriter("gd_Data.dat", "UTF-8");
		PrintWriter SCout = new PrintWriter("sc_Data.dat", "UTF-8");
		/*
		 * ��ʼ��ˮ�ض���
		 */
		SC MTG_QS_SC01 = new SC("MTG_QS_SC01",35.76,0);
		SC MTG_QS_SC02 = new SC("MTG_QS_SC02",35.76,0);
		SC MTG_QS_SC03 = new SC("MTG_QS_SC03",71.52,0);
		SC MTG_QS_SC04 = new SC("MTG_QS_SC04",32.625,0);
		SC MTG_QS_SC05 = new SC("MTG_QS_SC05",32.625,0);
		/*
		 * ��ʼ���ܵ�����
		 */
		GD MTG_QS_GD01 = new GD("MTG_QS_GD01", 1, 6);
		GD MTG_QS_GD02 = new GD("MTG_QS_GD02", 1, 6);
		GD MTG_QS_GD03 = new GD("MTG_QS_GD03", 1, 0);
		GD MTG_QS_GD04 = new GD("MTG_QS_GD04", 0.8, 0);
		GD MTG_QS_GD04a = new GD("MTG_QS_GD04a", 0.8, 0);
		GD MTG_QS_GD05 = new GD("MTG_QS_GD05", 0.8, 0);
		GD MTG_QS_GD05a = new GD("MTG_QS_GD05a", 0.8, 0);
		GD MTG_QS_GD06 = new GD("MTG_QS_GD06", 0.8, 0);
		GD MTG_QS_GD07 = new GD("MTG_QS_GD07", 0.8, 0);
		GD MTG_QS_GD08 = new GD("MTG_QS_GD08", 0.8, 0);
		GD MTG_QS_GD09 = new GD("MTG_QS_GD09", 0.8, 0);
		GD MTG_QS_GD10 = new GD("MTG_QS_GD010", 0.8, 0);
		GD MTG_QS_GD11 = new GD("MTG_QS_GD011", 0.8, 0);
		/*
		 * ��ʼ�����Ŷ���
		 */
		FM MTG_QS_FM01 = new FM("MTG_QS_FM01",MTG_QS_GD04,1);
		FM MTG_QS_FM02 = new FM("MTG_QS_FM02",MTG_QS_GD05,1);
		FM MTG_QS_FM03 = new FM("MTG_QS_FM03",MTG_QS_GD06,1);
		FM MTG_QS_FM04 = new FM("MTG_QS_FM04",MTG_QS_GD09,1);
		/**
		 * ��ʼ����
		 */
		for(n=0;n<=1000;n++){
			GDout.println("ʱ�䣺"+n*dt+"s;	"+"dt="+dt+"s;");
			SCout.println("ʱ�䣺"+n*dt+"s;	"+"dt="+dt+"s;");
			/**
			 * �ܵ�03
			 */
			MTG_QS_GD03.setFlow(MTG_QS_GD01.getFlow()+MTG_QS_GD02.getFlow());//���� 
			/**
			 * �ܵ�04
			 */
			MTG_QS_GD04.setFlow(MTG_QS_GD03.getFlow()*0.5*MTG_QS_FM01.getOnoff());//����
			/**
			 * �ܵ�05
			 */
			MTG_QS_GD05.setFlow(MTG_QS_GD03.getFlow()*0.5*MTG_QS_FM02.getOnoff());//����
			/**
			 * ˮ��01
			 */
			MTG_QS_SC01.setIn(MTG_QS_GD04.getFlow()*dt);//dtʱ���ڽ�ˮ��
			MTG_QS_SC01.setOut(Math.sqrt(2*g*MTG_QS_SC01.getH()
					)*MTG_QS_GD04a.getS()*dt);//dtʱ���ڳ�ˮ��
			MTG_QS_SC01.computeVnow();//����ˮ��ʵʱˮ��
			/**
			 * ˮ��02
			 */
			MTG_QS_SC02.setIn(MTG_QS_GD05.getFlow()*dt);//dtʱ���ڽ�ˮ��
			MTG_QS_SC02.setOut(Math.sqrt(2*g*MTG_QS_SC02.getH()
					)*MTG_QS_GD05a.getS()*dt);//dtʱ���ڳ�ˮ��
			MTG_QS_SC02.computeVnow();//����ˮ��ʵʱˮ��
			//n=1
			/**
			 * �ܵ�04a
			 */
			MTG_QS_GD04a.setFlow(MTG_QS_SC01.getOut());//����
			/**
			 * �ܵ�05a
			 */
			MTG_QS_GD05a.setFlow(MTG_QS_SC02.getOut());//����
			//n=2
			/**
			 * �ܵ�07
			 */
			MTG_QS_GD07.setSpeed(Math.sqrt(2*g*MTG_QS_SC03.getH()));//����
			/**
			 * �ܵ�08
			 */
			MTG_QS_GD08.setSpeed(MTG_QS_GD07.getSpeed());//����
			/**
			 * �ܵ�06
			 */
			MTG_QS_GD06.setSpeed(MTG_QS_GD07.getSpeed()*MTG_QS_FM03.getOnoff());//����
			/**
			 * �ܵ�09
			 */
			MTG_QS_GD09.setSpeed(MTG_QS_GD09.getSpeed()*MTG_QS_FM04.getOnoff());//����
			//n=1
			/**
			 * ˮ��03
			 */
			MTG_QS_SC03.setIn(MTG_QS_GD04a.getFlow()*dt+MTG_QS_GD05a.getFlow()*dt);//dtʱ���ڽ�ˮ��
			MTG_QS_SC03.setOut((MTG_QS_GD06.getFlow()+MTG_QS_GD07.getFlow()+MTG_QS_GD08.getFlow()+MTG_QS_GD09.getFlow())*dt);//dtʱ���ڳ�ˮ��    //n=2
			MTG_QS_SC03.computeVnow();//����ˮ��ʵʱˮ��
			//n=2
			/**
			 * �ܵ�10
			 */
			MTG_QS_GD10.setFlow(0);//����
			/**
			 * �ܵ�11
			 */
			MTG_QS_GD11.setFlow(0);//����
			//n=2
			/**
			 * ˮ��04
			 */
			MTG_QS_SC04.setIn(MTG_QS_GD06.getFlow()*dt+MTG_QS_GD07.getFlow()*dt);//dtʱ���ڽ�ˮ��
			MTG_QS_SC04.setOut(MTG_QS_GD10.getFlow()*dt);//dtʱ���ڳ�ˮ��  //n=3
			MTG_QS_SC04.computeVnow();//����ˮ��ʵʱˮ��
			//n=2
			/**
			 * ˮ��05
			 */
			MTG_QS_SC05.setIn(MTG_QS_GD08.getFlow()*dt+MTG_QS_GD09.getFlow()*dt);//dtʱ���ڽ�ˮ��
			MTG_QS_SC05.setOut(MTG_QS_GD11.getFlow()*dt);//dtʱ���ڳ�ˮ��  //n=3
			MTG_QS_SC05.computeVnow();//����ˮ��ʵʱˮ��
			/**
			 * ��t=n��ʱ�ܵ����ݵ�����д��gd_data.dat�ļ�
			 */
			MTG_QS_GD01.writeData(GDout);//д������
			MTG_QS_GD02.writeData(GDout);//д������
			MTG_QS_GD03.writeData(GDout);//д������
			MTG_QS_GD04.writeData(GDout);//д������
			MTG_QS_GD05.writeData(GDout);//д������
			MTG_QS_GD04a.writeData(GDout);//д������
			MTG_QS_GD05a.writeData(GDout);//д������
			MTG_QS_GD06.writeData(GDout);//д������
			MTG_QS_GD07.writeData(GDout);//д������
			MTG_QS_GD08.writeData(GDout);//д������
			MTG_QS_GD09.writeData(GDout);//д������
			MTG_QS_GD10.writeData(GDout);//д������
			MTG_QS_GD11.writeData(GDout);//д������
			/**
			 * ��t=n��ʱˮ�����ݵ�����д��sc_data.dat�ļ�
			 */
			MTG_QS_SC01.writeData(SCout);//д������
			MTG_QS_SC02.writeData(SCout);//д������
			MTG_QS_SC03.writeData(SCout);//д������
			MTG_QS_SC04.writeData(SCout);//д������
			MTG_QS_SC05.writeData(SCout);//д������
		}
	}
}
