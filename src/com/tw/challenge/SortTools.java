package com.tw.challenge;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SortTools {
	public static void main(String[] args) throws IOException {
		String input = Caller.getCall();
		JsonElement jelement = new JsonParser().parse(input);
		JsonObject jobject = jelement.getAsJsonObject();
		// jobject = jobject.getAsJsonObject("input");
		JsonArray toolUsage = jobject.get("toolUsage").getAsJsonArray();
		List<JsonObject> outputList = getSortedUsage(toolUsage);
		// String decodedString = decode("KBIA",53);
		System.out.println("found : " + outputList);
//		JsonObject jsonObject = new JsonObject();
//		String output = "{\"toolsFound\":[";
//		Iterator<String> iter = outputList.iterator();
//		while(iter.hasNext()){
//			String tool = iter.next();
//			output = output + "\""+tool+"\",";
//		}
//		output = output.substring(0,output.length()-1);
//		output = output + "]}";
//		jsonObject.addProperty("toolsSortedOnUsage", new Gson().toJson(outputList));
//		jsonObject.add("toolsSortedOnUsage", (outputList);
//		String stringData = new Gson().toJson(outputList);
		String stringData = outputList.toString();
//		String output = jsonObject.toString();
		String output = "{\"toolsSortedOnUsage\":";
//		while(iter.hasNext()){
//			String tool = iter.next();
//			output = output + "\""+tool+"\",";
//		}
//		output = output.substring(0,output.length()-1);
		output = output + stringData;
		output = output + "}";
//		jsonObject.addProperty("toolsSortedOnUsage", output);
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
	
	public static List<JsonObject> getSortedUsage(JsonArray toolUsage){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<JsonObject> sortedUsage = new ArrayList<JsonObject>();
		Map<String,Long> sortedUsageMap = new HashMap();
		for(int i=0;i<toolUsage.size();i++){
			JsonObject jo = toolUsage.get(i).getAsJsonObject();
			String toolName = jo.get("name").getAsString();
			String useStartTime = jo.get("useStartTime").getAsString();
			String useEndTime = jo.get("useEndTime").getAsString();
			Date d1 = null;
			Date d2 = null;
			try {
			    d1 = format.parse(useStartTime);
			    d2 = format.parse(useEndTime);
			    long diff = d2.getTime() - d1.getTime();
			    long diffMinutes = diff / (60 * 1000);
			    if(sortedUsageMap.containsKey(toolName)){
			    	long mins = sortedUsageMap.get(toolName);
			    	sortedUsageMap.put(toolName, diffMinutes+mins);
			    }else{
			    	sortedUsageMap.put(toolName, diffMinutes);
			    }
			} catch (ParseException e) {
			    e.printStackTrace();
			}    
		}
		
		Iterator<String> iter = sortedUsageMap.keySet().iterator();
		while(iter.hasNext()){
			String toolname = iter.next();
			long toolUsageInMins = sortedUsageMap.get(toolname);
			JsonObject jo = new JsonObject();
			jo.addProperty("name", toolname);
			jo.addProperty("timeUsedInMinutes", toolUsageInMins);
			sortedUsage.add(jo);
		}
		
		Collections.sort(sortedUsage, new MyJSONComparator());
		
		return sortedUsage;
	}
	
	
	
	
}

