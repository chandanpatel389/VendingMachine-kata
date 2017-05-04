

public class VendingMachine {
	
	public static void main(String[] args) {
		VendingMachineProcessor process = new VendingMachineProcessor();
		
		process.displayProducts();
		process.insertCoin();
		process.performPurchase();
	}

}
