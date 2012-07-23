public class GainAsk extends Gain{
    
    public double gain(int p, int n, int gp, int gn) {
	if (p == 0) {
	    return 0;
	}
	else {
		return (p*(Math.log((float)p/(float)(p+n))/Math.log(2)-Math.log((float)gp/(float)(gp+gn))/Math.log(2)));
	}
    }
}
