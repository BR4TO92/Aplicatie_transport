
public class User
{
	private byte prioritate;
	private int cantitatePaleti;
	
	User(int cantitatePaleti, byte prioritate)
	{
		this.cantitatePaleti = cantitatePaleti;
		this.prioritate = prioritate;
	}

	public byte getPrioritate()
	{
		return this.prioritate;
	}
	
	public int getCantitatePaleti()
	{
		return this.cantitatePaleti;
	}
}
