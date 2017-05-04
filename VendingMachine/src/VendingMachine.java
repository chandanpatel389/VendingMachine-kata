

public class VendingMachine {
	
	public static void main(String[] args) {
		VendingMachineProcessor process = new VendingMachineProcessor();
		
		process.displayProducts();
		if (process.exactChangeRequired()) {
			process.insertCoin();
		}
		else {
			System.out.println("Choose Card/Coin option");
			//business logic
		}
			
		
		process.performPurchase();
	}

}
