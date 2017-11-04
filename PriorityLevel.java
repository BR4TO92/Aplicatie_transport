
/**
 * @author Daniel Bratosin
 *
 */
public enum PriorityLevel
{
	VERY_URGENT(1),
	URGENT(2),
	NORMAL(3),
	LOW(4);
	
	private int priorityLevel;
	
	PriorityLevel(int priorityLevel)
	{
		this.priorityLevel = priorityLevel;
	}
	
	public int getPriorityLevel()
	{
		return this.priorityLevel;
	}
}
