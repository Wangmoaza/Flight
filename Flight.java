// Bongki Moon (bkmoon@snu.ac.kr)
import java.lang.Integer;

public class Flight
{
	public static final int DAY = 60 * 24;
	private String src;
	private String dest;
	private int stime; // military hour
	private int dtime; // military hour
	private int onboardTime; // in minutes

  // constructor
	public Flight(String src, String dest, String stime, String dtime) 
	{
		this.src = src;
		this.dest = dest;
		this.stime = Integer.parseInt(stime);
		this.dtime = Integer.parseInt(dtime);
		int s_min = Planner.convert2min(this.stime);
		int d_min = Planner.convert2min(this.dtime);
		// validity check
		if (s_min >= DAY) s_min -= DAY;
		if (d_min >= DAY) d_min -= DAY; 
		this.onboardTime = Planner.duration(s_min, d_min);
	}

	public int stime()
	{
		int s_min = Planner.convert2min(stime);
		if (s_min >= DAY) s_min -= DAY;
		return s_min;
	}
	
	public int dtime()
	{
		int d_min = Planner.convert2min(dtime);
		if (d_min >= DAY) d_min -= DAY;
		return d_min;
	}
	
	public String source()
	{
		return src;
	}
	
	public String dest()
	{
		return dest;
	}
	
	public int onboardTime()
	{
		return onboardTime;
	}
	
	public void print() 
	{
		System.out.print(String.format("[%s->%s:%04d->%04d]", src, dest, stime, dtime));
	}

}
