// Bongki Moon (bkmoon@snu.ac.kr)
import java.lang.Short;

public class Flight
{

	private String src;
	private String dest;
	private short stime;
	private short dtime;
	private int duration; // in minutes
	
  // constructor
	public Flight(String src, String dest, String stime, String dtime) 
	{
		this.src = src;
		this.dest = dest;
		this.stime = Short.parseShort(stime);
		this.dtime = Short.parseShort(dtime);
		
		// validity check
		if (this.stime >= 2400) this.stime -= 2400;
		if (this.dtime >= 2400) this.dtime -= 2400;
		
		this.duration = cal_duration();
	}

	// calculate the duration of flight in minutes
	public int cal_duration()
	{
		int startMin = (stime / 100) * 60 + (stime % 100);
		int endMin = (dtime / 100) * 60 + (dtime % 100);
		int diff = endMin - startMin;
		
		if (diff >= 0)
			return diff;
		
		return diff + (60 * 24);
	}
	
	public int stime()
	{
		return stime;
	}
	
	public int dtime()
	{
		return dtime;
	}
	
	public void print() 
	{
		System.out.print(String.format("[%s->%s:%04d->%04d]", src, dest, stime, dtime));
	}

}
