package com.magiccash;

/**
* <b>��</b>�������ʼ���
*
* <h3>����������</h3>
* P(x<sub>j</sub>|c<sub>j</sub>)=( N(X=x<sub>i</sub>, C=c<sub>j
* </sub>)+1 ) <b>/</b> ( N(C=c<sub>j</sub>)+M+V ) <br>
* ���У�N(X=x<sub>i</sub>, C=c<sub>j</sub>����ʾ���c<sub>j</sub>�а�������x<sub>
* i</sub>��ѵ���ı�������N(C=c<sub>j</sub>)��ʾ���c<sub>j</sub>�е�ѵ���ı�������Mֵ���ڱ���
* N(X=x<sub>i</sub>, C=c<sub>j</sub>����С�����������⣻V��ʾ����������
*
* <h3>��������</h3>
* <b>����</b> ��A, B�������¼�����P(A)>0 ��<br>
* <tt>P(B�OA)=P(AB)/P(A)</tt><br>
* Ϊ������A�·����������¼�B�������������ʡ�

*/

public class ClassConditionalProbability 
{
	private static TrainingDataManager tdm = new TrainingDataManager();
	private static final float M = 0F;
	
	/**
	* ��������������
	* @param x �������ı�����
	* @param c �����ķ���
	* @return ���������µ�����������
	*/
	public static float calculatePxc(String x, String c) 
	{
		float ret = 0F;
		float Nxc = tdm.getCountContainKeyOfClassification(c, x);
		float Nc = tdm.getTrainingFileCountOfClassification(c);
		float V = tdm.getTraningClassifications().length;
		ret = (Nxc + 1) / (Nc + M + V);
		return ret;
	}
}
