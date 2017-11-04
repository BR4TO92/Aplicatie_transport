
/**
 * @author Daniel Bratosin
 *
 */
public enum EnumTransportVehicle
{
	SHIP("a ship"),
	TRAIN("a train"),
	CAR("a car"),
	PLANE("a plane"),
	HELICOPTER("a helicopter"),
	TRUCK("a truck");
	 
	private String transportVehicle;
	 
	EnumTransportVehicle(String transportVehicle)
	{
		this.transportVehicle = transportVehicle;
	}

	public String getTransportVehicle()
	{
		return this.transportVehicle;
	}
}
