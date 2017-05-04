import java.util.HashMap;
import java.util.Map;

public enum ProductType {

	COLA("Cola", 1.0f, "1"),
	CANDY("Candy", 0.65f, "2"),
	CHIPS("Chips", 0.50f, "3");
	
	private String product;
	private float rate;
	private String symbol;	
	
	static {
		
		
		
	}
	
	ProductType(String product, float rate, String symbol) {
		this.product = product;
		this.rate = rate;
		this.symbol = symbol;
	}
	
	public static Map<String, Float> getAllProducts() {
		Map<String, Float> allProduct = new HashMap<String, Float>();
		for (ProductType value : ProductType.values()) {
			allProduct.put(value.product, value.rate);
		}
		
		return allProduct;
	}
	
	public static Map<String, String> getEligibleProducts(Float availableAmount) {
		Map<String, String> eligibleProducts = new HashMap<String, String>();
		for(ProductType productType : values()) {
			if (productType.rate <= availableAmount) {
				eligibleProducts.put(productType.symbol, productType.product);
			}
		}
		
		return eligibleProducts;
	}
	
	public static float getPrice(String productName) {
		float rate = 0.0f;
		for(ProductType productType : values()) {
			if (productType.product.equals(productName)) {
				rate = productType.rate;
			}
		}
		return rate;
	}

	public String getSymbol() {
		return symbol;
	}
}
