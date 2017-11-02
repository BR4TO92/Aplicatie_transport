
public class BrokerDeTransport
{
	@SuppressWarnings("unused")
	private User user;
	private int pretMinim = 0;
	private String mijlocDeTransport = "";
	
	private static final byte FOARTE_URGENT = 1;
	private static final byte URGENT = 2;
	private static final byte NORMAL = 3;
	private static final byte IEFTIN = 4;
	
	private static final int TARIF_FOARTE_URGENT = 100;
	private static final int TARIF_URGENT = 80;
	private static final int TARIF_NORMAL = 30;
	private static final int TARIF_IEFTIN = 10;
	
	private static final String MASINA = "o masina";
	private static final String AVION = "un avion";
	private static final String ELICOPTER = "un elicopter";
	private static final String TIR = "un TIR";
	private static final String TREN = "un tren";
	private static final String VAPOR = "un vapor";
	
	//cu acesta metoda pasez un obiect de tip User creeat in functia main
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public int getPretMinim()
	{
		return this.pretMinim;
	}
	
	public String getMijlocDeTransport()
	{
		return this.mijlocDeTransport;
	}
	
	public void calculPretMinim(User user)
	{
		byte prioritate = user.getPrioritate();
		int cantitatePaleti = user.getCantitatePaleti();
		
		switch(prioritate)
		{
			case FOARTE_URGENT: calculPretDupaPrioritate(cantitatePaleti, FOARTE_URGENT);
			                    break;
			                    
			case URGENT: calculPretDupaPrioritate(cantitatePaleti, URGENT);
                         break;
                         
			case NORMAL: calculPretDupaPrioritate(cantitatePaleti, NORMAL);
                         break;
                         
			case IEFTIN: calculPretDupaPrioritate(cantitatePaleti, IEFTIN);
                         break;
		}
	}

	private void calculPretDupaPrioritate(int cantitatePaleti, int prioritate)
	{
		int tarifDupaPrioritate;
		
		if(prioritate == FOARTE_URGENT)
		{
			tarifDupaPrioritate = TARIF_FOARTE_URGENT;
		}
		
		else if(prioritate == URGENT)
		{
			tarifDupaPrioritate = TARIF_URGENT;
		}
		
		else if(prioritate == NORMAL)
		{
			tarifDupaPrioritate = TARIF_NORMAL;
		}
		
		else
		{
			tarifDupaPrioritate = TARIF_IEFTIN;
		}
		
		if(cantitatePaleti == 1)
		{
			calculPretPentruCantitateMica(tarifDupaPrioritate, pretMinim, mijlocDeTransport);
		}
					
		if((cantitatePaleti >= 2) && (cantitatePaleti <= 50))
		{
			calculPretPentruCantitateMedie(tarifDupaPrioritate, pretMinim, mijlocDeTransport);
		}
						
		if(cantitatePaleti > 50)
		{
			calculPretPentruCantitateMare(tarifDupaPrioritate, pretMinim, mijlocDeTransport);
		}
	}

	private void calculPretPentruCantitateMare(int prioritateTransport, int pretMinim, String mijlocDeTransport)
	{
		int pretVapor;
		
		pretVapor = 300 + prioritateTransport;
		this.pretMinim = pretVapor;
		this.mijlocDeTransport = VAPOR;
	}

	private void calculPretPentruCantitateMedie(int prioritateTransport, int pretMinim, String mijlocDeTransport)
	{
		int pretTren;
		int pretTir;
		
		pretTren = 50 + prioritateTransport;
		pretTir = 100 + prioritateTransport;
		this.pretMinim = Math.min(pretTren, pretTir);
		
		if(pretMinim == pretTren)
		{
			this.mijlocDeTransport = TREN;
		}
		
		else
		{
			this.mijlocDeTransport = TIR;
		}
	}

	private void calculPretPentruCantitateMica(int prioritateTransport, int pretMinim, String mijlocDeTransport)
	{
		int pretMasina;
		int pretAvion;
		int pretElicopter;
		
		pretMasina = 30 + prioritateTransport;
		pretAvion = 500 + prioritateTransport;
		pretElicopter = 400 + prioritateTransport;
		this.pretMinim = minimulATreiNumere(pretMasina, pretAvion, pretElicopter);
		
		if(pretMinim == pretMasina)
		{
			this.mijlocDeTransport = MASINA;
		}
		
		else if(pretMinim == pretAvion)
		{
			this.mijlocDeTransport = AVION;
		}
		
		else
		{
			this.mijlocDeTransport = ELICOPTER;
		}
	}
	
	private int minimulATreiNumere(int a, int b, int c)
	{
	    return Math.min(Math.min(a, b), c);
	}
}
