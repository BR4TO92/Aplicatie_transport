
/**
 * @author Daniel Bratosin
 *
 */
public class UserRequest
{
	private byte priorityLevel;
	private int quantity;
	
	UserRequest(int quantity, byte priorityLevel)
	{
		this.quantity = quantity;
		this.priorityLevel = priorityLevel;
	}

	/**
	 * @return the priority level
	 */
	public byte getPriorityLevel()
	{
		return this.priorityLevel;
	}
	
	/**
	 * @param priorityLevel Sets a new priority level for an user object
	 */
	public void setPriorityLevel(byte priorityLevel)
	{
		this.priorityLevel = priorityLevel;
	}
	
	/**
	 * @return the quantity of the pallets
	 */
	public int getQuantity()
	{
		return this.quantity;
	}
	
	/**
	 * @param quantity Sets a new quantity of the pallets for an user object
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
}
