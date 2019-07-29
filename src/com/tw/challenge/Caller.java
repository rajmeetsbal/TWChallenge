package com.tw.challenge;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Caller {

	static String getEncodedDataUrl = "https://http-hunt.thoughtworks-labs.net/challenge/input";
	static String postDecodedDataUrl = "https://http-hunt.thoughtworks-labs.net/challenge/output";
	
	public static String getCall() throws IOException {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				// No need to implement.
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				// No need to implement.
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			System.out.println(e);
		}
		HttpURLConnection conn = null;
		URL url = new URL(getEncodedDataUrl);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("userId", "RtsfQ5jCe");
		conn.setRequestProperty("Accept", "application/json");
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}
		String output = "";
		String toreturn = "";
		while ((output = br.readLine()) != null) {
			System.out.println("resp : " + output);
			toreturn = output;
		}
		return toreturn;
	}

	public static void postCall(String json) throws IOException {

		URL url = new URL(postDecodedDataUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestProperty("content-type" , "application/json");
		conn.setRequestProperty("userId", "RtsfQ5jCe");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");

		OutputStream os = conn.getOutputStream();
		os.write(json.getBytes("UTF-8"));
		os.close();

		// read the response
		InputStream in = new BufferedInputStream(conn.getInputStream());
		System.out.println(conn.getResponseCode());

		in.close();
		conn.disconnect();

		// return jsonObject;
	}
}
