import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Daniel Bratosin
 *
 */
public class MainClass
{
	private static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		boolean isNewUser = true;
		TransportBroker brokerDeTransport = new TransportBroker();
		String userName;
		Map<String, UserRequest> listOfExistingUsers = new LinkedHashMap<>();
		String programResumeCondition = "";
		
		do
		{
			if(listOfExistingUsers.isEmpty())
			{
				System.out.print("Please enter the user name: ");
				userName = userInput.next();
				
				showFinalResult(brokerDeTransport, userName, listOfExistingUsers, userInput, isNewUser);
				programResume(programResumeCondition);
			}
			
			else
			{
				System.out.print("Please enter the user name: ");
				userName = userInput.next();
				
				String yesOrNoAnswer;
				
				if(listOfExistingUsers.containsKey(userName))
				{
					System.out.print("\nThe user \'" + userName + "\' already exists in our database. Do you want to use it (Y) or create a new one (n)? Y/n: ");
					yesOrNoAnswer = userInput.next();
					System.out.print("\n");
					
					if(yesOrNoAnswer.equals("Y"))
					{
						isNewUser = false;
						showFinalResult(brokerDeTransport, userName, listOfExistingUsers, userInput, isNewUser);
						programResume(programResumeCondition);
					}
					
					if(yesOrNoAnswer.equals("n"))
					{
						isNewUser = true;
						
						String userNameForNewUser;
						System.out.print("Please enter the user name: ");
						userNameForNewUser = userInput.next();
						
						showFinalResult(brokerDeTransport, userNameForNewUser, listOfExistingUsers, userInput, isNewUser);
						programResume(programResumeCondition);
					}
				}
				
				else
				{
					isNewUser = true;
					showFinalResult(brokerDeTransport, userName, listOfExistingUsers, userInput, isNewUser);
					programResume(programResumeCondition);
				}
			}
		} while (!programResumeCondition.equals("n"));
	}

	/**
	 * This method prompts the user to make a new shipment order or close the program after each order is done.
	 * @param programResumeCondition The user input for closing the program or taking another order
	 */
	public static void programResume(String programResumeCondition)
	{
		System.out.print("\nProceed to a new shipment order? Y/n: ");
		programResumeCondition = userInput.next();
		System.out.print("\n");
		
		if(programResumeCondition.equals("n"))
		{
			System.exit(0);
		}
	}
	
	/**
	 * This method computes the lowest possible price or the price based on the fastest possible vehicle, based on the input from a single
	 * user. Also show the results. The input from a single user will be used for making the computation inside the transport broker.
	 * @param transportBroker The object where the costs computation and transport vehicle selection from a single user are actually made.
	 * @param userName The name of a single user used for identification
	 * @param listOfExistingUsers Map which contains a collection of all the existing users
	 * @param userInput Keyboard input from a single user
	 * @param isNewUser Condition based on what a new user will be created or an existing one will be used
	 */
	private static void showFinalResult(TransportBroker transportBroker, String userName, Map<String, UserRequest> listOfExistingUsers, Scanner userInput, boolean isNewUser)
	{
		UserRequest userRequest;
		int quantity;
		int lowestPrice;
		byte priorityLevel;
		String transportVehicle;
		String speedEfficiencyInput;
		boolean isSpeedEfficient;
		
		priorityLevel();
		
		System.out.print("Please enter the priority level: ");
		priorityLevel = userInput.nextByte();
		System.out.print("Please enter the number of pallets: ");
		quantity = userInput.nextInt();
		System.out.print("Do you want to use the fastest possible vehicle for transporting this quantity? Y/n: ");
		speedEfficiencyInput = userInput.next();
		
		if(speedEfficiencyInput.equals("Y"))
		{
			isSpeedEfficient = true;
		}
		
		else
		{
			isSpeedEfficient = false;
		}
		
		if(isNewUser)
		{
			userRequest = new UserRequest(quantity, priorityLevel);
			listOfExistingUsers.put(userName, userRequest);
		}
		
		else
		{
			userRequest = listOfExistingUsers.get(userName);
			userRequest.setPriorityLevel(priorityLevel);
			userRequest.setQuantity(quantity);
		}
		
		transportBroker.setUser(userRequest);
		transportBroker.computeLowestPrice(userRequest, isSpeedEfficient);
		
		lowestPrice = transportBroker.getPrice();
		transportVehicle = transportBroker.getTransportVehicle();
		
		result(lowestPrice, transportVehicle);
	}
	
	/**
	 * Information of the priority levels shown to the user each time a new order is being created.
	 */
	private static void priorityLevel()
	{
		System.out.print("\n===PRIORITY LEVEL===\n");
		System.out.print("1 - Very urgent\n");
		System.out.print("2 - Urgent\n");
		System.out.print("3 - Normal\n");
		System.out.print("4 - Low\n\n");
	}
	
	/**
	 * This method shows the lowest possible price and the vehicle used for the shipment.
	 * @param lowestPrice The computed lowest possible price for the shipment
	 * @param transportVehicle The selected transport vehicle used for the shipment
	 */
	private static void result(int lowestPrice, String transportVehicle)
	{
		System.out.print("\nThe shipment costs " + lowestPrice + " RON and will arrive with " + transportVehicle + ".\n");
	}
}
