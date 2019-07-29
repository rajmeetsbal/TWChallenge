package com.tw.challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SearchHiddenObjects {
	public static void main(String[] args) throws IOException {
		String input = Caller.getCall();
		// String input = "{\"encryptedMessage\":\"GOVV GO MKX NY SD WI GKI, YB
		// GO MKX KVV MYWO LKMU SX DSWO PYB DRO XOHD KVSQXWOXD KXN IYE'BO
		// GOVMYWO DY DBI KXN USVV WO DROX, SX YR, CKI, KXYDROB 5,000
		// IOKBC?\",\"key\":10}";
		JsonElement jelement = new JsonParser().parse(input);
		JsonObject jobject = jelement.getAsJsonObject();
		// jobject = jobject.getAsJsonObject("input");
		String hiddenTools = jobject.get("hiddenTools").getAsString();
		JsonArray tools = jobject.get("tools").getAsJsonArray();
		List<String> outputList = findObjects(hiddenTools, tools);
		// String decodedString = decode("KBIA",53);
		System.out.println("found : " + outputList);
		JsonObject jsonObject = new JsonObject();
		String output = "{\"toolsFound\":[";
		Iterator<String> iter = outputList.iterator();
		while(iter.hasNext()){
			String tool = iter.next();
			output = output + "\""+tool+"\",";
		}
		output = output.substring(0,output.length()-1);
		output = output + "]}";
		jsonObject.addProperty("toolsFound", output);
//		jsonObject.addProperty("toolsFound", new Gson().toJson(outputList));
//		String output = jsonObject.toString();
//		output = output.replace("\"[", "[");
//		output = output.replace("]\"", "]");
//		
//		output = "{\"toolsFound\":[\"knife\",\"guns\",\"rope\",\"armour\"]}";
		System.out.println("POSTING.... " + output);
		// postDecodedData(postDecodedDataUrl,output);
		// post(postDecodedDataUrl,output);
		// getInputStream
		Caller.postCall(output);
	}
	
	public static List<String> findObjects(String hiddenTools, JsonArray tools){
//		String hidToolsSplit[] = hiddenTools.split("");
//		JsonArray ja = new JsonArray();
		ArrayList<String> foundTools = new ArrayList<String>();
		for(int i = 0; i<tools.size(); i++){
			String hiddenToolsCopy = hiddenTools;
			String tool = tools.get(i).getAsString();
			String toolSplit[] = tool.split("");
			boolean found = true;
			for(int j=0;j<toolSplit.length;j++){
				if(hiddenToolsCopy.indexOf(toolSplit[j]) > -1){
					hiddenToolsCopy = hiddenToolsCopy.substring(hiddenToolsCopy.indexOf(toolSplit[j]));
				}else{
					found = false;
					break;
				}
			}
			if(found)
				foundTools.add(tools.get(i).getAsString());
		}
		return foundTools;
	}
}
