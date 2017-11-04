
/**
 * @author Daniel Bratosin
 *
 */
public enum EnumPriorityLevel
{
	VERY_URGENT(1),
	URGENT(2),
	NORMAL(3),
	LOW(4);
	
	private int priorityLevel;
	
	EnumPriorityLevel(int priorityLevel)
	{
		this.priorityLevel = priorityLevel;
	}
	
	public int getPriorityLevel()
	{
		return this.priorityLevel;
	}
}
