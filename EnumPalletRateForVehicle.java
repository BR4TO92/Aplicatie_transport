
/**
 * @author Daniel Bratosin
 *
 */
public enum EnumPalletRateForVehicle
{
	PALLET_RATE_FOR_SHIP(1),
	PALLET_RATE_FOR_TRAIN(2),
	PALLET_RATE_FOR_TRUCK(3),
	PALLET_RATE_FOR_CAR(5),
	PALLET_RATE_FOR_PLANE(30),
	PALLET_RATE_FOR_HELICOPTER(50);
	
	private int palletRateForVehicle;
	
	EnumPalletRateForVehicle(int palletRateForVehicle)
	{
		this.palletRateForVehicle = palletRateForVehicle;
	}
	
	public int getPalletRateForVehicle()
	{
		return this.palletRateForVehicle;
	}
}
