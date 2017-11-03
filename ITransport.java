
/**
 * @author Daniel Bratosin
 *
 */
public interface ITransport
{	
	enum PriorityLevel
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
	
	enum RateForEmergencyLevel
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
	
	enum PalletRateForVehicle
	{
		PALLET_RATE_FOR_SHIP(1),
		PALLET_RATE_FOR_TRAIN(2),
		PALLET_RATE_FOR_TRUCK(3),
		PALLET_RATE_FOR_CAR(5),
		PALLET_RATE_FOR_PLANE(15),
		PALLET_RATE_FOR_HELICOPTER(30);
		
		private int palletRateForVehicle;
		
		PalletRateForVehicle(int palletRateForVehicle)
		{
			this.palletRateForVehicle = palletRateForVehicle;
		}
		
		public int getPalletRateForVehicle()
		{
			return this.palletRateForVehicle;
		}
	}
	 
	 enum TransportVehicle
	 {
		 SHIP("a ship"),
		 TRAIN("a train"),
		 CAR("a car"),
		 PLANE("a plane"),
		 HELICOPTER("a helicopter"),
		 TRUCK("a truck");
		 
		 private String transportVehicle;
		 
		 TransportVehicle(String transportVehicle)
		 {
			 this.transportVehicle = transportVehicle;
		 }

		 public String getTransportVehicle()
		 {
		 	 return this.transportVehicle;
		 }
	 }
}
