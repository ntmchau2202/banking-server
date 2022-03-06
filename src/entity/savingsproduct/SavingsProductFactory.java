package entity.savingsproduct;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import database.DBConnector;

public class SavingsProductFactory {
	private static ArrayList<String> SavingsProductType = new ArrayList<String>();
	public static boolean isProductTypeExist(String type) {
		return SavingsProductType.contains(type);
	}
	
	public static void putProductType(String type) {
		if(isProductTypeExist(type)) {
			return;
		}
		
		SavingsProductType.add(type);
	}
	private static HashMap<String, SavingsProduct> savingsProductDetails = new HashMap<String, SavingsProduct>();
	
	public static void fetchAllSavingsProducts() throws Exception {
		for(String type : SavingsProductType) {
			JSONObject productRawInf = DBConnector.getDatabaseConnection().getSavingsProductDetails(type);
			SavingsProduct product = getSavingsProductFromRawInfo(productRawInf);
			savingsProductDetails.put(product.getSavingProductName(), product);
		}
	}
	
	private static SavingsProduct getSavingsProductFromRawInfo(JSONObject rawInf) throws Exception {
		Class<?> clz = Class.forName(rawInf.getString("product_alias"));
		Constructor<?> cstr = clz.getConstructor(String.class, String.class);
		SavingsProduct product = (SavingsProduct) cstr.newInstance(rawInf.getString("product_name"), rawInf.getString("product_id"));
		JSONArray listInterestRate = rawInf.getJSONArray("details");
		for(int i = 0; i < listInterestRate.length(); i++) {
			JSONObject tuple = listInterestRate.getJSONObject(i);
			product.addNewBaseInterestRate(tuple.getInt("period"), (float)tuple.getDouble("interest_rate"));
		}
		return product;
	}
	
	public static SavingsProduct getSavingsProductByName(String name) {
		return savingsProductDetails.get(name);
	}
}
