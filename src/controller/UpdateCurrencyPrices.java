package controller;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

public class UpdateCurrencyPrices implements Runnable, DAOInterface {
    private Thread t;

    //Default constructor
    public UpdateCurrencyPrices() {
    }

    @Override
    public void run() {
        boolean run = true;
        try {
            ////System.out.println("Currency Tread Started");
            while (run) {
                getBtc();
                getETH();
                Thread.sleep(5000);
            }
        } catch (IOException | JSONException | SQLException | InterruptedException e) {
            //e.printStackTrace();
            run = false;
        }

    }

    void start() {
        //////System.out.println("Starting thread");
        if (t == null) {
            t = new Thread(this);
            t.start();
            //System.out.println("Out of start Method");
        }
    }

    void stop() {
        t.interrupt();
        //System.out.println("Thread is stopped");
    }

    private void getBtc() throws IOException, JSONException, SQLException {
        //url to get USD and Eth for 1BTC
        String url = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,ETH";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //Do not need response code for now
        //int responseCode = con.getResponseCode();
        ////System.out.println("Resp Code" + responseCode);

        //Buffer Reader to surround input Stream Reader
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String jsonString = response.toString();

        //System.out.println(jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        //JSONObject myResponse = jsonObject.getJSONObject("MyResponse");
        //System.out.println("Eth: "+jsonObject.getString("ETH"));
        //System.out.println("USD: "+jsonObject.getString("USD"));
        //DaoUpdateCurrencyHist daoupd = new DaoUpdateCurrencyHist();
        daoUpdateCurrencyHist.updateCurrency("BTC", Double.valueOf(jsonObject.getString("ETH")), Double.parseDouble(jsonObject.getString("USD")));
        con.disconnect();
    }

    private void getETH() throws IOException, JSONException, SQLException {
        //url to get USD and Eth for 1BTC
        String url = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD,BTC";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //Do not need response code for now
        //int responseCode = con.getResponseCode();
        ////System.out.println("Resp Code" + responseCode);

        //Buffer Reader to surround input Stream Reader
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String jsonString = response.toString();

        //System.out.println(jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        //JSONObject myResponse = jsonObject.getJSONObject("MyResponse");
        //System.out.println("BTC: "+jsonObject.getString("BTC"));
        //System.out.println("USD: "+jsonObject.getString("USD"));
        //DaoUpdateCurrencyHist daoupd = new DaoUpdateCurrencyHist();
        daoUpdateCurrencyHist.updateCurrency("ETH", Double.valueOf(jsonObject.getString("BTC")), Double.parseDouble(jsonObject.getString("USD")));
        con.disconnect();
    }
}
