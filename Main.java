import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		boolean creezUserNou = true;
		BrokerDeTransport brokerDeTransport = new BrokerDeTransport();
		String nume;
		Map<String, User> listaDeUseriExistenti = new LinkedHashMap<>();
		Scanner inputTastatura = new Scanner(System.in);
		String conditieDeContinuare = "";
		
		do
		{
			if(listaDeUseriExistenti.isEmpty())
			{
				System.out.print("Introduceti numele: ");
				nume = inputTastatura.next();
				
				afisareRezultat(brokerDeTransport, nume, listaDeUseriExistenti, inputTastatura, creezUserNou);
				continuareProgram(conditieDeContinuare);
			}
			
			else
			{
				System.out.print("Introduceti numele: ");
				nume = inputTastatura.next();
				
				String raspuns;
				
				if(listaDeUseriExistenti.containsKey(nume))
				{
					System.out.print("Userul " + nume + " exista deja in baza de date. Il folositi sau creati altul nou? Y/n: ");
					raspuns = inputTastatura.next();
					
					if(raspuns.equals("Y"))
					{
						creezUserNou = false;
						afisareRezultat(brokerDeTransport, nume, listaDeUseriExistenti, inputTastatura, creezUserNou);
						continuareProgram(conditieDeContinuare);
					}
					
					if(raspuns.equals("n"))
					{
						creezUserNou = true;
						afisareRezultat(brokerDeTransport, nume, listaDeUseriExistenti, inputTastatura, creezUserNou);
						continuareProgram(conditieDeContinuare);
					}
				}
				
				else
				{
					System.out.print("Introduceti numele: ");
					nume = inputTastatura.next();
					
					afisareRezultat(brokerDeTransport, nume, listaDeUseriExistenti, inputTastatura, creezUserNou);
					continuareProgram(conditieDeContinuare);
				}
			}
		} while (!conditieDeContinuare.equals("n"));
	}

	public static void continuareProgram(String conditieDeContinuare)
	{
		Scanner inputTastatura = new Scanner(System.in);
		
		System.out.print("\nDoriti un transport nou? Y/n: ");
		conditieDeContinuare = inputTastatura.next();
	}
	
	private static void afisareRezultat(BrokerDeTransport brokerDeTransport, String nume, Map<String, User> listaDeUseriExistenti, Scanner inputTastatura, boolean creezUserNou)
	{
		User user;
		int cantitate;
		int pretMinim;
		byte prioritateTransport;
		String mijlocDeTransport;
		
		prioritati();
		
		System.out.print("Introduceti prioritatea: ");
		prioritateTransport = inputTastatura.nextByte();
		System.out.print("Introduceti cantitatea dorita: ");
		cantitate = inputTastatura.nextInt();
		
		if((creezUserNou) && (!listaDeUseriExistenti.isEmpty()))
		{
			System.out.print("Introduceti numele: ");
			nume = inputTastatura.next();
			
			user = new User(cantitate, prioritateTransport);
			listaDeUseriExistenti.put(nume, user);
		}
		
		if((creezUserNou) && (listaDeUseriExistenti.isEmpty()))
		{
			user = new User(cantitate, prioritateTransport);
			listaDeUseriExistenti.put(nume, user);
		}
		
		else
		{
			user = listaDeUseriExistenti.get(nume);
		}
		
		brokerDeTransport.setUser(user);
		brokerDeTransport.calculPretMinim(user);
		
		pretMinim = brokerDeTransport.getPretMinim();
		mijlocDeTransport = brokerDeTransport.getMijlocDeTransport();
		
		rezultat(pretMinim, mijlocDeTransport);
	}
	
	private static void prioritati()
	{
		System.out.print("\n=====PRIORITATE====\n");
		System.out.print("1 - Foarte urgent\n");
		System.out.print("2 - Urgent\n");
		System.out.print("3 - Normal\n");
		System.out.print("4 - Ieftin\n\n");
	}
	
	private static void rezultat(int pretMinim, String mijlocDeTransport)
	{
		System.out.print("\nTransportul costa " + pretMinim + " lei si va sosi cu " + mijlocDeTransport + ".\n");
	}
}
