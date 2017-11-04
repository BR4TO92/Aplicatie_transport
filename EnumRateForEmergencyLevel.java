
/**
 * @author Daniel Bratosin
 *
 */
public enum EnumRateForEmergencyLevel
{
	VERY_URGENT_RATE(100),
	URGENT_RATE(80),
	NORMAL_RATE(30),
	LOW_RATE(10);
	
	private int rateForEmergencyLevel;
	
	EnumRateForEmergencyLevel(int rateForEmergencyLevel)
	{
		this.rateForEmergencyLevel = rateForEmergencyLevel;
	}
	
	public int getRateForEmergencyLevel()
	{
		return this.rateForEmergencyLevel;
	}
}
