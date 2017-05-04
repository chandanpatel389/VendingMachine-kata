import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class VendingMachineProcessor {
	private final String RETURNSYMBOL = "9";
	private float accountBalance = 0.0f;
	private String insertedCoin;
	private int count = 0;
	private CoinType coinType;
	private Map<String, String> eligibleProducts;
	
	public void displayProducts() {
		System.out.println(":::::::::::::::::::PRODUCTS:::::::::::::::::::::");
		
		for (Entry<String, Float> product : ProductType.getAllProducts().entrySet()) {
			System.out.print(product.getKey() + " - $" + product.getValue() + "\t");
		}
		System.out.println("\n9 - Return Coin");
	}
	
	public void insertCoin() {
		System.out.println(":::::::::::::::::::Insert Coin::::::::::::::::::");
		
		System.out.println(CoinType.DIME.getDisplay() + " -" + CoinType.DIME.getType());
		System.out.println(CoinType.QUARTER.getDisplay() + " -" + CoinType.QUARTER.getType());
		System.out.println(CoinType.NICKLE.getDisplay() + " -" + CoinType.NICKLE.getType());
		
		System.out.println(CoinType.PENNY.getDisplay() + " -" + CoinType.PENNY.getType() + " - Not Valid Coin");
		
		
		System.out.println("Choose Your Option");
		Scanner scanner = new Scanner(System.in);
		insertedCoin = scanner.nextLine();
		if (!insertedCoin.isEmpty() && insertedCoin.equals(RETURNSYMBOL)) {
			returnCoin();
		}
		else {
			System.out.println("How Many Coins");
			count = scanner.nextInt();
		}
	}
	
	public void performPurchase() {	
		if((coinType = isValidCoin(insertedCoin)) != null) {
			showOptions(coinType, count);
		}
		else if(insertedCoin != null && insertedCoin.equals(RETURNSYMBOL)) {
			returnCoin();			
		}
		else {
			System.out.println("Not Valid Coin");
		}
		
	}
	
	private CoinType isValidCoin(String insertedCoin) {
		CoinType coinType = null;
		for (CoinType coin : CoinType.values()) {
			if (!CoinType.PENNY.getType().equalsIgnoreCase(insertedCoin) && coin.getType().equalsIgnoreCase(insertedCoin)) {
				coinType = coin;
				break;
			}
		}
		
		return coinType;
	}
	
	private void showOptions(CoinType coinType, int count) {
		accountBalance  = (Float.valueOf(coinType.getCent()) * count) / 100.0f ;
		System.out.println("Inserted Amount - $" + accountBalance);
		
		eligibleProducts = ProductType.getEligibleProducts(accountBalance);
		
		if(eligibleProducts.isEmpty()) {
			System.out.println("Not Enough Money!!!");
			takeChange(accountBalance);
		}
		else {
			System.out.println("...........Select Product...........");
			
			for (Entry<String, String> product : eligibleProducts.entrySet()) {
				System.out.print(product.getKey() + " - " + product.getValue() + "\t");
			}
			System.out.println("\n9 - Return Coin");
			System.out.println("Choose Your Option");
			Scanner scanner = new Scanner(System.in);
			String optionSelected = scanner.nextLine();
			if (!optionSelected.isEmpty()) {
				processRequest(optionSelected, eligibleProducts);
			}
		}
		
	}
	
	private void takeChange(float amountToReturn) {
		System.out.print("Take coin - $");
		System.out.printf("%.2f", amountToReturn);
	}
	
	private void returnCoin() {
		System.out.println("TAKE COIN BACK");
	}
	
	private void processRequest(String optionSelected, Map<String, String> eligibleProducts) {
		if (optionSelected.equals(RETURNSYMBOL)) {
			returnCoin();
		}
		else if (eligibleProducts.containsKey(optionSelected)) {
			boolean itemSoldOut = false;
			if (optionSelected.equals(ProductType.CANDY.getSymbol())) {
				soldOut();
			}
			else {
				String purchasedItem = eligibleProducts.get(optionSelected);
				System.out.println("Take your " + purchasedItem);
				float moneySpend = ProductType.getPrice(purchasedItem);
				accountBalance = accountBalance - moneySpend;
				if (accountBalance > 0.0) {
					takeChange(accountBalance);
				}
			}
		}
		else {
			System.out.println("Option Not Available");
			returnCoin();
		}
			
		
	}
	
	private void soldOut() {
		System.out.println("Item is Sold Out, Please select other option");
		Scanner scanner = new Scanner(System.in);
		String optionSelected = scanner.nextLine();
		if (!optionSelected.isEmpty()) {
			processRequest(optionSelected, eligibleProducts);
		}		
	}
	
	public boolean exactChangeRequired() {		
		boolean coinOnly = true;
			//logic for checking the machine if Only Coin needs to be inserted
		
		return coinOnly;
	}

}
