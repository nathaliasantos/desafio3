package integrador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import soa32.resources.cliente.Cliente;


public class Interpretador {

	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	public Interpretador() {
		try {

			URL url = new URL("http://dls98:8181/captacao/api/clientes?wsdl");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			String total = "";
			while ((output = br.readLine()) != null) {
				total += output;
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	public void math(){
		System.out.println("Olá");
	}
	
	public void math2(){
		System.out.println("Olá");
	}

}
