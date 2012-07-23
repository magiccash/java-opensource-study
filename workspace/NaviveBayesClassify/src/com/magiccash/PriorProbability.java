package com.magiccash;

/**
* ������ʼ���
* <h3>������ʼ���</h3>
* P(c<sub>j</sub>)=N(C=c<sub>j</sub>)<b>/</b>N <br>
* ���У�N(C=c<sub>j</sub>)��ʾ���c<sub>j</sub>�е�ѵ���ı�������
* N��ʾѵ���ı�����������
*/

public class PriorProbability 
{
	private static TrainingDataManager tdm =new TrainingDataManager();

	/**
	* �������
	* @param c �����ķ���
	* @return ���������µ��������
	*/
	public static float calculatePc(String c)
	{
		float ret = 0F;
		float Nc = tdm.getTrainingFileCountOfClassification(c);
		float N = tdm.getTrainingFileCount();
		ret = Nc / N;
		return ret;
	}
}