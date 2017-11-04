
/**
 * @author Daniel Bratosin
 *
 */
public enum RateForEmergencyLevel
{
	VERY_URGENT_RATE(100),
	URGENT_RATE(80),
	NORMAL_RATE(30),
	LOW_RATE(10);
	
	private int rateForEmergencyLevel;
	
	RateForEmergencyLevel(int rateForEmergencyLevel)
	{
		this.rateForEmergencyLevel = rateForEmergencyLevel;
	}
	
	public int getRateForEmergencyLevel()
	{
		return this.rateForEmergencyLevel;
	}
}
