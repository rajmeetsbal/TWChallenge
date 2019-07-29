package com.tw.challenge;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Decode {
	static String myCircularQueue = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) throws IOException {
		Caller caller = new Caller();
		String input = Caller.getCall();
		// String input = "{\"encryptedMessage\":\"GOVV GO MKX NY SD WI GKI, YB
		// GO MKX KVV MYWO LKMU SX DSWO PYB DRO XOHD KVSQXWOXD KXN IYE'BO
		// GOVMYWO DY DBI KXN USVV WO DROX, SX YR, CKI, KXYDROB 5,000
		// IOKBC?\",\"key\":10}";
		JsonElement jelement = new JsonParser().parse(input);
		JsonObject jobject = jelement.getAsJsonObject();
		// jobject = jobject.getAsJsonObject("input");
		String msg = jobject.get("encryptedMessage").getAsString();
		int key = jobject.get("key").getAsInt();
		String decodedString = decode(msg, key);
		// String decodedString = decode("KBIA",53);
		System.out.println("decodedString : " + decodedString);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", decodedString);
		String output = jsonObject.toString();
		System.out.println("POSTING.... " + output);
		// postDecodedData(postDecodedDataUrl,output);
		// post(postDecodedDataUrl,output);
		// getInputStream
		Caller.postCall(output);
	}

	public static String decode(String msg, int key) {
		String[] splitMsg = msg.split("");
		String decoded = "";
		for (int i = 0; i < splitMsg.length; i++) {
			int newKey = key % 26;
			String chr = splitMsg[i];
			String decodedChar = "";
			if (myCircularQueue.indexOf(chr) > -1) {
				int indexofChr = myCircularQueue.indexOf(chr);
				// int goBackBy = newKey;
				while (newKey > -1) {
					if (indexofChr == -1)
						indexofChr = 25;
					decodedChar = new Character(myCircularQueue.charAt(indexofChr)).toString();
					// goBackBy--;
					indexofChr--;
					newKey--;
				}
			} else {
				decodedChar = chr;
			}
			decoded = decoded + decodedChar;
		}
		return decoded;
	}

	

}
