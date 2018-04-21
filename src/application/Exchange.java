package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class Exchange {

	public int currencyID;
	public String currencyName;
	public long currencyPrice;
	public String currencyCode;
	public final String URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,BTC,ETH";

	public CurrencyHistoryController currency;

	public void getCurrentPrices(String code) {
		String cryptoCode = code;
		BufferedReader rd;
		OutputStreamWriter wr;

		try
		{
			URL url = new URL(URL);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.flush();

			// Get the response
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}