package com.magiccash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* ѵ����������
*/

public class TrainingDataManager 
{
	private String[] traningFileClassifications;//ѵ�����Ϸ��༯��
	private File traningTextDir;//ѵ�����ϴ��Ŀ¼
	private static String defaultPath = "TrainningSet/Sample";
	
	public TrainingDataManager() 
	{
		traningTextDir = new File(defaultPath);
		if (!traningTextDir.isDirectory()) 
		{
			throw new IllegalArgumentException("ѵ�����Ͽ�����ʧ�ܣ� [" +defaultPath + "]");
		}
		this.traningFileClassifications = traningTextDir.list();
	}
	/**
	* ����ѵ���ı�������������Ŀ¼��
	* @return ѵ���ı����
	*/
	public String[] getTraningClassifications() 
	{
		return this.traningFileClassifications;
	}
	/**
	* ����ѵ���ı���𷵻��������µ�����ѵ���ı�·����full path��
	* @param classification �����ķ���
	* @return ���������������ļ���·����full path��
	*/
	public String[] getFilesPath(String classification) 
	{
		File classDir = new File(traningTextDir.getPath() +File.separator +classification);
		String[] ret = classDir.list();
		for (int i = 0; i < ret.length; i++) 
		{
			ret[i] = traningTextDir.getPath() +File.separator +classification +File.separator +ret[i];
		}
		return ret;
	}

	/**
	* ���ظ���·�����ı��ļ�����
	* @param filePath �������ı��ļ�·��
	* @return �ı�����
	* @throws java.io.FileNotFoundException
	* @throws java.io.IOException
	*/
	public static String getText(String filePath) throws FileNotFoundException,IOException 
	{
	
		InputStreamReader isReader =new InputStreamReader(new FileInputStream(filePath),"GBK");
		BufferedReader reader = new BufferedReader(isReader);
		String aline;
		StringBuilder sb = new StringBuilder();
	
		while ((aline = reader.readLine()) != null)
		{
			sb.append(aline + " ");
		}
		isReader.close();
		reader.close();
		return sb.toString();
	}

	/**
	* ����ѵ���ı��������е��ı���Ŀ
	* @return ѵ���ı��������е��ı���Ŀ
	*/
	public int getTrainingFileCount()
	{
		int ret = 0;
		for (int i = 0; i < traningFileClassifications.length; i++)
		{
			ret +=getTrainingFileCountOfClassification(traningFileClassifications[i]);
		}
		return ret;
	}

	/**
	* ����ѵ���ı������ڸ��������µ�ѵ���ı���Ŀ
	* @param classification �����ķ���
	* @return ѵ���ı������ڸ��������µ�ѵ���ı���Ŀ
	*/
	public int getTrainingFileCountOfClassification(String classification)
	{
		File classDir = new File(traningTextDir.getPath() +File.separator +classification);
		return classDir.list().length;
	}

	/**
	* ���ظ��������а����ؼ��֣��ʵ�ѵ���ı�����Ŀ
	* @param classification �����ķ���
	* @param key �����Ĺؼ��֣���
	* @return ���������а����ؼ��֣��ʵ�ѵ���ı�����Ŀ
	*/
	public int getCountContainKeyOfClassification(String classification,String key) 
	{
		int ret = 0;
		try 
		{
			String[] filePath = getFilesPath(classification);
			for (int j = 0; j < filePath.length; j++) 
			{
				String text = getText(filePath[j]);
				if (text.contains(key)) 
				{
					ret++;
				}
			}
		}
		catch (FileNotFoundException ex) 
		{
			Logger.getLogger(TrainingDataManager.class.getName()).log(Level.SEVERE, null,ex);
	
		} 
		catch (IOException ex)
		{
			Logger.getLogger(TrainingDataManager.class.getName()).log(Level.SEVERE, null,ex);
	
		}
		return ret;
	}
}