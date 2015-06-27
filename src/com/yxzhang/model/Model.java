package com.yxzhang.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author acer
 *水量计算	
 */
public class Model {
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException{
		//水池面积常量
		final double SC01_AREA=35.76;
		final double SC02_AREA=35.76;
		final double SC03_AREA=71.52;
		final double SC04_AREA=32.625;
		final double SC05_AREA=32.625;
		//管道管径常量
		final double GD01_DIAM=1;
		final double GD02_DIAM=1;
		final double GD03_DIAM=1;
		final double GD04_DIAM=0.8;
		final double GD04a_DIAM=0.8;
		final double GD05_DIAM=0.8;
		final double GD05a_DIAM=0.8;
		final double GD06_DIAM=0.8;
		final double GD07_DIAM=0.8;
		final double GD08_DIAM=0.8;
		final double GD09_DIAM=0.8;
		final double GD010_DIAM=0.8;
		final double GD011_DIAM=0.8;
		
		final double  g=9.8;
		int n=0;
		double dt=1;
		/*
		 * 数据写入文件设置
		 */
		PrintWriter GDout = new PrintWriter("gd_Data.dat", "UTF-8");
		PrintWriter SCout = new PrintWriter("sc_Data.dat", "UTF-8");
		/*
		 * 初始化水池对象
		 */
		SC MTG_QS_SC01 = new SC("MTG_QS_SC01",SC01_AREA,0);
		SC MTG_QS_SC02 = new SC("MTG_QS_SC02",SC02_AREA,0);
		SC MTG_QS_SC03 = new SC("MTG_QS_SC03",SC03_AREA,0);
		SC MTG_QS_SC04 = new SC("MTG_QS_SC04",SC04_AREA,0);
		SC MTG_QS_SC05 = new SC("MTG_QS_SC05",SC05_AREA,0);
		/*
		 * 初始化管道对象
		 */
		GD MTG_QS_GD01 = new GD("MTG_QS_GD01", GD01_DIAM, 6);
		GD MTG_QS_GD02 = new GD("MTG_QS_GD02", GD02_DIAM, 6);
		GD MTG_QS_GD03 = new GD("MTG_QS_GD03", GD03_DIAM, 0);
		GD MTG_QS_GD04 = new GD("MTG_QS_GD04", GD04_DIAM, 0);
		GD MTG_QS_GD04a = new GD("MTG_QS_GD04a",GD04a_DIAM, 0);
		GD MTG_QS_GD05 = new GD("MTG_QS_GD05", GD05_DIAM, 0);
		GD MTG_QS_GD05a = new GD("MTG_QS_GD05a", GD05a_DIAM, 0);
		GD MTG_QS_GD06 = new GD("MTG_QS_GD06",GD06_DIAM, 0);
		GD MTG_QS_GD07 = new GD("MTG_QS_GD07", GD07_DIAM, 0);
		GD MTG_QS_GD08 = new GD("MTG_QS_GD08", GD08_DIAM, 0);
		GD MTG_QS_GD09 = new GD("MTG_QS_GD09",GD09_DIAM, 0);
		GD MTG_QS_GD10 = new GD("MTG_QS_GD010",GD010_DIAM, 0);
		GD MTG_QS_GD11 = new GD("MTG_QS_GD011", GD011_DIAM, 0);
		/*
		 * 初始化阀门对象
		 */
		FM MTG_QS_FM01 = new FM("MTG_QS_FM01",MTG_QS_GD04,1);
		FM MTG_QS_FM02 = new FM("MTG_QS_FM02",MTG_QS_GD05,1);
		FM MTG_QS_FM03 = new FM("MTG_QS_FM03",MTG_QS_GD06,1);
		FM MTG_QS_FM04 = new FM("MTG_QS_FM04",MTG_QS_GD09,1);
		/**
		 * 开始计算
		 */
		for(n=0;n<=1000;n++){
			GDout.println("时间："+n*dt+"s;	"+"dt="+dt+"s;");
			SCout.println("时间："+n*dt+"s;	"+"dt="+dt+"s;");
			/**
			 * 管道03
			 */
			MTG_QS_GD03.setFlow(MTG_QS_GD01.getFlow()+MTG_QS_GD02.getFlow());//流量 
			/**
			 * 管道04
			 */
			MTG_QS_GD04.setFlow(MTG_QS_GD03.getFlow()*0.5*MTG_QS_FM01.getOnoff());//流量
			/**
			 * 管道05
			 */
			MTG_QS_GD05.setFlow(MTG_QS_GD03.getFlow()*0.5*MTG_QS_FM02.getOnoff());//流量
			/**
			 * 水池01
			 */
			MTG_QS_SC01.setIn(MTG_QS_GD04.getFlow()*dt);//dt时间内进水量
			MTG_QS_SC01.setOut(Math.sqrt(2*g*MTG_QS_SC01.getH()
					)*MTG_QS_GD04a.getS()*dt);//dt时间内出水量
			MTG_QS_SC01.computeVnow();//计算水池实时水量
			/**
			 * 水池02
			 */
			MTG_QS_SC02.setIn(MTG_QS_GD05.getFlow()*dt);//dt时间内进水量
			MTG_QS_SC02.setOut(Math.sqrt(2*g*MTG_QS_SC02.getH()
					)*MTG_QS_GD05a.getS()*dt);//dt时间内出水量
			MTG_QS_SC02.computeVnow();//计算水池实时水量
			//n=1
			/**
			 * 管道04a
			 */
			MTG_QS_GD04a.setFlow(MTG_QS_SC01.getOut());//流量
			/**
			 * 管道05a
			 */
			MTG_QS_GD05a.setFlow(MTG_QS_SC02.getOut());//流量
			//n=2
			/**
			 * 管道07
			 */
			MTG_QS_GD07.setSpeed(Math.sqrt(2*g*MTG_QS_SC03.getH()));//流速
			/**
			 * 管道08
			 */
			MTG_QS_GD08.setSpeed(MTG_QS_GD07.getSpeed());//流速
			/**
			 * 管道06
			 */
			MTG_QS_GD06.setSpeed(MTG_QS_GD07.getSpeed()*MTG_QS_FM03.getOnoff());//流速
			/**
			 * 管道09
			 */
			MTG_QS_GD09.setSpeed(MTG_QS_GD07.getSpeed()*MTG_QS_FM04.getOnoff());//流速
			//n=1
			/**
			 * 水池03
			 */
			MTG_QS_SC03.setIn(MTG_QS_GD04a.getFlow()*dt+MTG_QS_GD05a.getFlow()*dt);//dt时间内进水量
			MTG_QS_SC03.setOut((MTG_QS_GD06.getFlow()+MTG_QS_GD07.getFlow()+MTG_QS_GD08.getFlow()+MTG_QS_GD09.getFlow())*dt);//dt时间内出水量    //n=2
			MTG_QS_SC03.computeVnow();//计算水池实时水量
			//n=2
			/**
			 * 管道10
			 */
			MTG_QS_GD10.setFlow(0);//流量
			/**
			 * 管道11
			 */
			MTG_QS_GD11.setFlow(0);//流量
			//n=2
			/**
			 * 水池04
			 */
			MTG_QS_SC04.setIn(MTG_QS_GD06.getFlow()*dt+MTG_QS_GD07.getFlow()*dt);//dt时间内进水量
			MTG_QS_SC04.setOut(MTG_QS_GD10.getFlow()*dt);//dt时间内出水量  //n=3
			MTG_QS_SC04.computeVnow();//计算水池实时水量
			//n=2
			/**
			 * 水池05
			 */
			MTG_QS_SC05.setIn(MTG_QS_GD08.getFlow()*dt+MTG_QS_GD09.getFlow()*dt);//dt时间内进水量
			MTG_QS_SC05.setOut(MTG_QS_GD11.getFlow()*dt);//dt时间内出水量  //n=3
			MTG_QS_SC05.computeVnow();//计算水池实时水量
			/**
			 * 将t=n秒时管道数据的数据写入gd_data.dat文件
			 */
			MTG_QS_GD01.writeData(GDout);//写入数据
			MTG_QS_GD02.writeData(GDout);//写入数据
			MTG_QS_GD03.writeData(GDout);//写入数据
			MTG_QS_GD04.writeData(GDout);//写入数据
			MTG_QS_GD05.writeData(GDout);//写入数据
			MTG_QS_GD04a.writeData(GDout);//写入数据
			MTG_QS_GD05a.writeData(GDout);//写入数据
			MTG_QS_GD06.writeData(GDout);//写入数据
			MTG_QS_GD07.writeData(GDout);//写入数据
			MTG_QS_GD08.writeData(GDout);//写入数据
			MTG_QS_GD09.writeData(GDout);//写入数据
			MTG_QS_GD10.writeData(GDout);//写入数据
			MTG_QS_GD11.writeData(GDout);//写入数据
			/**
			 * 将t=n秒时水池数据的数据写入sc_data.dat文件
			 */
			MTG_QS_SC01.writeData(SCout);//写入数据
			MTG_QS_SC02.writeData(SCout);//写入数据
			MTG_QS_SC03.writeData(SCout);//写入数据
			MTG_QS_SC04.writeData(SCout);//写入数据
			MTG_QS_SC05.writeData(SCout);//写入数据
		}
	}
}
