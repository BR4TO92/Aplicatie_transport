
/**
 * @author Daniel Bratosin
 *
 */
public class TransportBroker implements ITransport
{
	@SuppressWarnings("unused")
	private User user;
	private int lowestPrice = 0;
	private String transportVehicle = "";
	
	/**
	 * This method will pass a newly created user in the transport broker, from the main method, for later computations.
	 * @param user A single user object
	 */
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public int getLowestPrice()
	{
		return this.lowestPrice;
	}
	
    public String getTransportVehicle()
	{
    	return this.transportVehicle;
	}
	
    /**
     * This method computes the lowest possible price, based on the priority level from a single user.
     * @param user An user object used for getting the priority level and quantity from it, for the computation of the lowest price possible
     */
    public void computeLowestPrice(User user)
	{
		byte priorityLevel = user.getPriorityLevel();
		int quantity = user.getQuantity();
		
		switch(priorityLevel)
		{
			case 1: computeCostForPriorityLevel(quantity, PriorityLevel.VERY_URGENT.getPriorityLevel());
			        break;
			                    
			case 2: computeCostForPriorityLevel(quantity, PriorityLevel.URGENT.getPriorityLevel());
                    break;
                         
			case 3: computeCostForPriorityLevel(quantity, PriorityLevel.NORMAL.getPriorityLevel());
                    break;
                         
			case 4: computeCostForPriorityLevel(quantity, PriorityLevel.LOW.getPriorityLevel());
                    break;
		}
	}

	/**
	 * This method computes the lowest possible price based on the priority level and the quantity from a single user.
	 * @param quantity The quantity from an user object used for computation of the quantity cost
	 * @param priorityLevel The priority level from an user object used for computation of the priority level cost
	 */
	private void computeCostForPriorityLevel(int quantity, int priorityLevel)
	{
		int costForPriorityLevel;
		
		if(priorityLevel == PriorityLevel.VERY_URGENT.getPriorityLevel())
		{
			costForPriorityLevel = RateForEmergencyLevel.VERY_URGENT_RATE.getRateForEmergencyLevel();
		}
		
		else if(priorityLevel == PriorityLevel.URGENT.getPriorityLevel())
		{
			costForPriorityLevel = RateForEmergencyLevel.URGENT_RATE.getRateForEmergencyLevel();
		}
		
		else if(priorityLevel == PriorityLevel.NORMAL.getPriorityLevel())
		{
			costForPriorityLevel = RateForEmergencyLevel.NORMAL_RATE.getRateForEmergencyLevel();
		}
		
		else
		{
			costForPriorityLevel = RateForEmergencyLevel.LOW_RATE.getRateForEmergencyLevel();
		}
		
		if(quantity == 1)
		{
			computeCostForLowQuantity(quantity, costForPriorityLevel);
		}
					
		if((quantity >= 2) && (quantity <= 50))
		{
			computeCostFormediumQuantity(quantity, costForPriorityLevel);
		}
						
		if(quantity > 50)
		{
			computeCostForHighQuantity(quantity, costForPriorityLevel);
		}
	}

	/**
	 * This method computes the price for each transport vehicle based on the priority level and the pallet rate for each vehicle. It only
	 * computes the price for a high quantity of pallets. After the price for each one is computed, only one will be selected as lowest
	 * possible, based on finding the minimal one. Also finds the right vehicle.
	 * @param quantity The quantity from an user object used for computation of the quantity cost
	 * @param costForPriorityLevel The priority level cost
	 */
	private void computeCostForHighQuantity(int quantity, int costForPriorityLevel)
	{
		int costForShip;
		
		costForShip = PalletRateForVehicle.PALLET_RATE_FOR_SHIP.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		this.lowestPrice = costForShip;
		this.transportVehicle = TransportVehicle.SHIP.getTransportVehicle();
	}

	/**
	 * This method computes the price for each transport vehicle based on the priority level and the pallet rate for each vehicle. It only
	 * computes the price for a medium quantity of pallets. After the price for each one is computed, only one will be selected as lowest
	 * possible, based on finding the minimal one. Also finds the right vehicle.
	 * @param quantity The quantity from an user object used for computation of the quantity cost
	 * @param costForPriorityLevel The priority level cost
	 */
	private void computeCostFormediumQuantity(int quantity, int costForPriorityLevel)
	{
		int costForTrain;
		int costForTruck;
		
		costForTrain = PalletRateForVehicle.PALLET_RATE_FOR_TRAIN.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		costForTruck = PalletRateForVehicle.PALLET_RATE_FOR_TRUCK.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		this.lowestPrice = Math.min(costForTrain, costForTruck);
		
		if(this.lowestPrice == costForTrain)
		{
			this.transportVehicle = TransportVehicle.TRAIN.getTransportVehicle();
		}
		
		else
		{
			this.transportVehicle = TransportVehicle.TRUCK.getTransportVehicle();
		}
	}

	/**
	 * This method computes the price for each transport vehicle based on the priority level and the pallet rate for each vehicle. It only
	 * computes the price for a low quantity of pallets. After the price for each one is computed, only one will be selected as lowest
	 * possible, based on finding the minimal one. Also finds the right vehicle.
	 * @param quantity The quantity from an user object used for computation of the quantity cost
	 * @param costForPriorityLevel The priority level cost
	 */
	private void computeCostForLowQuantity(int quantity, int costForPriorityLevel)
	{
		int costForCar;
		int costForPlane;
		int costForHelicopter;
		
		costForCar = PalletRateForVehicle.PALLET_RATE_FOR_CAR.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		costForPlane = PalletRateForVehicle.PALLET_RATE_FOR_PLANE.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		costForHelicopter = PalletRateForVehicle.PALLET_RATE_FOR_HELICOPTER.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		this.lowestPrice = minimumOfThreeNumbers(costForCar, costForPlane, costForHelicopter);
		
		if(this.lowestPrice == costForCar)
		{
			this.transportVehicle = TransportVehicle.CAR.getTransportVehicle();
		}
		
		else if(this.lowestPrice == costForPlane)
		{
			this.transportVehicle = TransportVehicle.PLANE.getTransportVehicle();
		}
		
		else
		{
			this.transportVehicle = TransportVehicle.HELICOPTER.getTransportVehicle();
		}
	}
	
	/**
	 * @return the minimum of three numbers given as parameters
	 */
	private int minimumOfThreeNumbers(int a, int b, int c)
	{
	    return Math.min(Math.min(a, b), c);
	}
}
