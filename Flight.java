// Bongki Moon (bkmoon@snu.ac.kr)
import java.lang.Integer;

public class Flight
{

	private String src;
	private String dest;
	private int stime;
	private int dtime;
	
  // constructor
	public Flight(String src, String dest, String stime, String dtime) 
	{
		this.src = src;
		this.dest = dest;
		this.stime = Integer.parseInt(stime);
		this.dtime = Integer.parseInt(dtime);
		
		// validity check
		if (this.stime >= 2400) this.stime -= 2400;
		if (this.dtime >= 2400) this.dtime -= 2400;
	}

	// calculate the duration of flight in minutes
	public int duration()
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
	
	public String source()
	{
		return src;
	}
	
	public String dest()
	{
		return dest;
	}
	
	
	public void print() 
	{
		System.out.print(String.format("[%s->%s:%04d->%04d]", src, dest, stime, dtime));
	}

}
