package com.magiccash;

import com.magiccash.ChineseSpliter;
import com.magiccash.ClassConditionalProbability;
import com.magiccash.PriorProbability;
import com.magiccash.StopWordsHandler;
import com.magiccash.TrainingDataManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
* ���ر�Ҷ˹������
*/
public class BayesClassifier 
{
	private TrainingDataManager tdm;//ѵ����������
	private String trainnigDataPath;//ѵ����·��
	private static double zoomFactor = 10.0f;
	/**
	* Ĭ�ϵĹ���������ʼ��ѵ����
	*/
	public BayesClassifier() 
	{
		tdm =new TrainingDataManager();
	}

	/**
	* ����������ı���������X�ڸ����ķ���Cj�е�����������
	* <code>ClassConditionalProbability</code>����ֵ
	* @param X �������ı���������
	* @param Cj ���������
	* @return ����������������ֵ����<br>
	*/
	float calcProd(String[] X, String Cj) 
	{
		float ret = 1.0F;
		// ��������������
		for (int i = 0; i <X.length; i++)
		{
			String Xi = X[i];
			//��Ϊ�����С�����������֮ǰ�Ŵ�10����������ս������Ӱ�죬��Ϊ����ֻ�ǱȽϸ��ʴ�С����
			ret *=ClassConditionalProbability.calculatePxc(Xi, Cj)*zoomFactor;
		}
		// �ٳ����������
		ret *= PriorProbability.calculatePc(Cj);
		return ret;
	}
	/**
	* ȥ��ͣ�ô�
	* @param text �������ı�
	* @return ȥͣ�ôʺ���
	*/
	public String[] DropStopWords(String[] oldWords)
	{
		Vector<String> v1 = new Vector<String>();
		for(int i=0;i<oldWords.length;++i)
		{
			if(StopWordsHandler.IsStopWord(oldWords[i])==false)
			{//����ͣ�ô�
				v1.add(oldWords[i]);
			}
		}
		String[] newWords = new String[v1.size()];
		v1.toArray(newWords);
		return newWords;
	}
	/**
	* �Ը������ı����з���
	* @param text �������ı�
	* @return ������
	*/
	@SuppressWarnings("unchecked")
	public String classify(String text) 
	{
		String[] terms = null;
		terms= ChineseSpliter.split(text, " ").split(" ");//���ķִʴ���(�ִʺ������ܻ�������ͣ�ôʣ�
		terms = DropStopWords(terms);//ȥ��ͣ�ôʣ�����Ӱ�����
		
		String[] Classes = tdm.getTraningClassifications();//����
		float probility = 0.0F;
		List<ClassifyResult> crs = new ArrayList<ClassifyResult>();//������
		for (int i = 0; i <Classes.length; i++) 
		{
			String Ci = Classes[i];//��i������
			probility = calcProd(terms, Ci);   //����������ı���������terms�ڸ����ķ���Ci�еķ�����������
			//���������
			ClassifyResult cr = new ClassifyResult();
			cr.classification = Ci;//����
			cr.probility = probility;//�ؼ����ڷ������������
			System.out.println("In process....");
			System.out.println(Ci + "��" + probility);
			crs.add(cr);
		}
		//�������ʽ����������
		java.util.Collections.sort(crs,new Comparator() 
		{
			public int compare(final Object o1,final Object o2) 
			{
				final ClassifyResult m1 = (ClassifyResult) o1;
				final ClassifyResult m2 = (ClassifyResult) o2;
				final double ret = m1.probility - m2.probility;
				if (ret < 0) 
				{
					return 1;
				} 
				else 
				{
					return -1;
				}
			}
		});
		//���ظ������ķ���
		return crs.get(0).classification;
	}
	
	public static void main(String[] args)
	{
		String text = "CSDN���й�����������ˣ�Chinese software develop net������д�����й����Ŀ����߼������������Ǽ����š���̳��Ⱥ�顢Blog���ĵ������ء����顢Tag����ժ��������.NET��Java����Ϸ����Ƶ���˲š�������ڶ���ꡢ������Ա���ȶ�����Ŀ��һ��Ĵ����ۺ���IT�Ż���վ���к�ǿ��רҵ�ԣ����Ա�������й������ٷ�֮��ʮ���ϵ��������Ա����IT�������������ܱ߹������ǵ�һλ����վ��2011��12�£�CSDN��վ600�����û�������й¶�������򹫰����ر�����2012��3�£�CSDN����й¶�����ƣ���վ���������洦����";
		BayesClassifier classifier = new BayesClassifier();//����Bayes������
		String result = classifier.classify(text);//���з���
		System.out.println("��������["+result+"]");
	}
}