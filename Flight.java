// Bongki Moon (bkmoon@snu.ac.kr)
import java.lang.Integer;

public class Flight
{
	public static final int DAY = 60 * 24;
	private String src;
	private String dest;
	private int stime; // in minutes from 00:00
	private int dtime; // in minutes from 00:00
	private int onboardTime;

  // constructor
	public Flight(String src, String dest, String stime, String dtime) 
	{
		this.src = src;
		this.dest = dest;
		this.stime = Planner.convert2min(Integer.parseInt(stime));
		this.dtime = Planner.convert2min(Integer.parseInt(dtime));
		// validity check
		if (this.stime >= DAY) this.stime -= DAY;
		if (this.dtime >= DAY) this.dtime -= DAY;
		this.onboardTime = Planner.duration(this.stime, this.dtime);
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
	
	public int onboardTime()
	{
		return onboardTime;
	}
	
	public void print() 
	{
		System.out.print(String.format("[%s->%s:%04d->%04d]", src, dest, 
				Planner.convert2hr(stime), Planner.convert2hr(dtime)));
	}

}
