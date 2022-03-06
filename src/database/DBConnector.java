package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.customer.CustomerFactory;
import entity.savingsproduct.SavingsProductFactory;


public class DBConnector {
	private static DBConnector connector;
	private Connection connection;
	
	private DBConnector() throws Exception {
		Class.forName("org.sqlite.JBDC");
		String url = "jdbc:sqlite:src/resource/bankDB.db";
		this.connection = DriverManager.getConnection(url);
		initConfigs();
	}
	
	public static DBConnector getDatabaseConnection() throws Exception {
		if (connector == null) {
			connector = new DBConnector();
		}
		return connector;
	}
	
	private void initConfigs() throws Exception {
		String sql = "SELECT * FROM configs";
		PreparedStatement sqlStm = connection.prepareStatement(sql);
		ResultSet result = sqlStm.executeQuery();
		while(result.next()) {
			String field = result.getString("field");
			String value = result.getString("value");
			if (field.equalsIgnoreCase("customer_type")) {
				CustomerFactory.putCustomerType(value);
			}
			
			if (field.equalsIgnoreCase("savings_product_type")) {
				SavingsProductFactory.putProductType(value);
			}
		}
	}
	
	public JSONObject getCustomerByPhone(String phone) throws Exception {
		String sql = "SELECT * FROM customer WHERE customer_phone=?";
		PreparedStatement sqlStm = connection.prepareStatement(sql);
		sqlStm.setString(1, phone);
		ResultSet result = sqlStm.executeQuery();
		JSONObject json = new JSONObject();
		json.put("customer_type", result.getString("customer_type"));
		json.put("customer_phone", result.getString("customer_phone"));
		json.put("customer_id", result.getString("customer_id"));
		json.put("customer_name", result.getString("customer_name"));
		return json;
	}
	
	public JSONObject getBankAccountOfCustomer(int customerID) throws Exception {
		String sql = "SELECT * FROM customer_bankaccount WHERE customer_id=?";
		PreparedStatement sqlStm = connection.prepareStatement(sql);
		sqlStm.setInt(1, customerID);
		ResultSet result = sqlStm.executeQuery();
		JSONObject json = new JSONObject();
		JSONArray bankAccountList = new JSONArray();
		json.put("customer_id", result.getInt("customer_id"));
		do {
			JSONObject acc = new JSONObject();
			acc.put("bankaccount_id", result.getString("bankaccount_id"));
			acc.put("bankaccount_balance", (float)result.getDouble("bankaccount_balance"));
			bankAccountList.put(acc);		
		} while(result.next());
		json.put("details", bankAccountList);
		return json;
	}
	
	public JSONObject getSavingsAccountOfCustomer(int customerID) throws Exception {
		String sql = "SELECT * FROM customer_savingsaccount WHERE customer_id=?";
		PreparedStatement sqlStm = connection.prepareStatement(sql);
		sqlStm.setInt(1, customerID);
		ResultSet result = sqlStm.executeQuery();
		JSONObject json = new JSONObject();
		json.put("customer_id", result.getInt("customer_id"));
		JSONArray savingsAccountList = new JSONArray();
		do {
			savingsAccountList.put(result.getBoolean("savingsaccount_id"));
		} while (result.next());
		json.put("savingsaccount_id", savingsAccountList);
		return json;
	}
	
	public JSONObject getSavingsAccountByID(String savingsAccountID) throws Exception {
		String sql = "SELECT * FROM savingsaccout WHERE savingsaccount_id=?";
		PreparedStatement sqlStm = connection.prepareStatement(sql);
		sqlStm.setString(1, savingsAccountID);
		ResultSet result = sqlStm.executeQuery();
		JSONObject json = new JSONObject();
		json.put("savingsaccount_id", result.getString("savingsaccount_id"));
		json.put("amount", result.getDouble("amount"));
		json.put("period", result.getInt("period"));
		json.put("interest_rate", result.getDouble("interest_rate"));
		json.put("interest_amount", result.getDouble("interest_amount"));
		json.put("actual_interest_amount", result.getDouble("actual_interest_amount"));
		json.put("settle_time", result.getString("settle_time"));
		json.put("open_time", result.getString("open_time"));
		json.put("type", result.getString("type"));
		json.put("blockchain_confirmed", result.getBoolean("blockchain_confirmed"));
		json.put("currency", result.getString("currency"));
		json.put("settle_instruction", result.getString("settle_instruction"));
		return json;
	}
	
	public JSONObject getSavingsProductDetails(String productName) throws Exception {
		String sql = "SELECT * FROM savingsproduct WHERE product_name=?";
		PreparedStatement sqlStm = connection.prepareStatement(sql);
		sqlStm.setString(1, productName);
		ResultSet result = sqlStm.executeQuery();
		JSONObject json = new JSONObject();
		json.put("product_name", result.getString("product_name"));
		json.put("product_alias", result.getString("product_alias"));
		String productID = result.getString("product_id");
		sql = "SELECT * FROM interestrate WHERE product_id=?";
		sqlStm = connection.prepareStatement(sql);
		sqlStm.setString(1, productID);
		result = sqlStm.executeQuery();
		JSONArray listInterestRate = new JSONArray();
		while(result.next()) {
			JSONObject interestRate = new JSONObject();
			interestRate.put("period", result.getDouble("interest_rate"));
			listInterestRate.put(interestRate);
		}
		json.put("details", listInterestRate);
		return json;		
	}
	
	public JSONObject getCustomerLoginDetails(String customerPhone) throws Exception {
		String sql = "SELECT * FROM logindetails WHERE customer_phone=?";
		PreparedStatement sqlStm = connection.prepareStatement(sql);
		sqlStm.setString(1, customerPhone);
		ResultSet result = sqlStm.executeQuery();
		JSONObject json = new JSONObject();
		json.put("customer_phone", result.getString("customer_phone"));
		json.put("password", result.getString("password"));
		return json;
	}
}
