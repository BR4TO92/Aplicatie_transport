
/**
 * @author Daniel Bratosin
 *
 */
public class TransportBroker
{
	@SuppressWarnings("unused")
	private UserRequest userRequest;
	private int price = 0;
	private String transportVehicle = "";
	
	/**
	 * This method will pass a newly created user in the transport broker, from the main method, for later computations.
	 * @param userRequest A single user object
	 */
	public void setUser(UserRequest userRequest)
	{
		this.userRequest = userRequest;
	}
	
	public int getPrice()
	{
		return this.price;
	}
	
    public String getTransportVehicle()
	{
    	return this.transportVehicle;
	}

	/**
	 * This method computes the lowest possible price or the price based on the fastest vehicle possible, based on the priority
	 * level and the quantity from a single user.
	 * @param quantity The quantity from an user object used for computation of the quantity cost
	 * @param priorityLevel The priority level from an user object used for computation of the priority level cost
	 * @param isSpeedEfficient true/false if the user chose the fastest vehicle
	 */
	public void computeLowestPrice(UserRequest userRequest, boolean isSpeedEfficient)
	{		
		byte priorityLevel = userRequest.getPriorityLevel();
		int quantity = userRequest.getQuantity();
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
			computeCostForLowQuantity(quantity, costForPriorityLevel, isSpeedEfficient);
		}
					
		if((quantity >= 2) && (quantity <= 50))
		{
			computeCostForMediumQuantity(quantity, costForPriorityLevel, isSpeedEfficient);
		}
						
		if(quantity > 50)
		{
			computeCostForHighQuantity(quantity, costForPriorityLevel);
		}
	}

	/**
	 * This method computes the price and the vehicle used for the transport based on high priority level and the pallet rate for the vehicle
	 * used. It only computes the price for a high quantity of pallets. In this case, for a single vehicle, a single price will be computed.
	 * @param quantity The quantity from an user object used for computation of the quantity cost
	 * @param costForPriorityLevel The priority level cost
	 */
	private void computeCostForHighQuantity(int quantity, int costForPriorityLevel)
	{
		int costForShip;
		
		costForShip = PalletRateForVehicle.PALLET_RATE_FOR_SHIP.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		
		this.price = costForShip;
		this.transportVehicle = TransportVehicle.SHIP.getTransportVehicle();
	}

	/**
	 * This method computes the price and the vehicle used for each transport vehicle based on the priority level and the pallet rate for
	 * each vehicle. It only computes the price for a medium quantity of pallets. After the price for each vehicle is computed, one will be
	 * selected as the lowest price possible (the minimal one), or the one for the fastest vehicle.
	 * @param quantity The quantity from an user object used for computation of the quantity cost
	 * @param costForPriorityLevel The priority level cost
	 * @param isSpeedEfficient true/false if the user chose the fastest vehicle
	 */
	private void computeCostForMediumQuantity(int quantity, int costForPriorityLevel, boolean isSpeedEfficient)
	{
		int costForTrain;
		int costForTruck;
		
		costForTrain = PalletRateForVehicle.PALLET_RATE_FOR_TRAIN.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		costForTruck = PalletRateForVehicle.PALLET_RATE_FOR_TRUCK.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		
		if(isSpeedEfficient)
		{
			this.price = costForTruck;
			this.transportVehicle = TransportVehicle.TRUCK.getTransportVehicle();
		}
		
		else
		{
			this.price = costForTrain;
			this.transportVehicle = TransportVehicle.TRAIN.getTransportVehicle();
		}
	}

	/**
	 * This method computes the price and the vehicle used for each transport vehicle based on the priority level and the pallet rate for
	 * each vehicle. It only computes the price for a low quantity of pallets. After the price for each vehicle is computed, one will be
	 * selected as the lowest price possible (the minimal one), or the one for the fastest vehicle.
	 * @param quantity The quantity from an user object used for computation of the quantity cost
	 * @param costForPriorityLevel The priority level cost
	 * @param isSpeedEfficient true/false if the user chose the fastest vehicle
	 */
	private void computeCostForLowQuantity(int quantity, int costForPriorityLevel, boolean isSpeedEfficient)
	{
		int costForCar;
		int costForPlane;
		int costForHelicopter;
		
		costForCar = PalletRateForVehicle.PALLET_RATE_FOR_CAR.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		costForPlane = PalletRateForVehicle.PALLET_RATE_FOR_PLANE.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		costForHelicopter = PalletRateForVehicle.PALLET_RATE_FOR_HELICOPTER.getPalletRateForVehicle() * quantity + costForPriorityLevel;
		
		if(isSpeedEfficient)
		{
			this.price = costForHelicopter;
			this.transportVehicle = TransportVehicle.HELICOPTER.getTransportVehicle();
		}
		
		else
		{
			this.price = costForCar;
			this.transportVehicle = TransportVehicle.CAR.getTransportVehicle();
		}
	}
}
