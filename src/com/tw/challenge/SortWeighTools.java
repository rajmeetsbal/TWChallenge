package com.tw.challenge;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class SortWeighTools {
	public static void main(String[] args) throws IOException {
		String input = Caller.getCall();
		JsonElement jelement = new JsonParser().parse(input);
		JsonObject jobject = jelement.getAsJsonObject();
		// jobject = jobject.getAsJsonObject("input");
		JsonArray tools = jobject.get("tools").getAsJsonArray();
		Long maxWeight = jobject.get("maximumWeight").getAsLong();
		SortedSet<String> outputList = toolsToTake(tools, maxWeight);
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
		String output = "{\"toolsToTakeSorted\":";
//		while(iter.hasNext()){
//			String tool = iter.next();
//			output = output + "\""+tool+"\",";
//		}
//		output = output.substring(0,output.length()-1);
		stringData = stringData.replace("[", "[\"");
		stringData = stringData.replace("]", "\"]");
		stringData = stringData.replace(",", "\",\"");
		
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
	
	public static SortedSet<String> toolsToTake(JsonArray tools, Long maxWeight){
		long maxWtCount = 0;
		SortedSet<String> toolsToTake = new TreeSet<String>();
//		SortedSet<String> sites = new TreeSet<>();
//		Type listType = new TypeToken<List<JsonObject>>() {}.getType();
//		List<JsonObject> listOfTools = new Gson().fromJson(tools, listType);
		List<JsonObject> listOfTools = new ArrayList<>();
		for(int i=0;i<tools.size();i++){
			listOfTools.add(tools.get(i).getAsJsonObject());
		}
		
		Collections.sort(listOfTools, new CompareByValue());
		
		Iterator<JsonObject> iter = listOfTools.iterator();
		while(iter.hasNext()){
			JsonObject jo = iter.next();
			Long weight = jo.get("weight").getAsLong();
			maxWtCount = maxWtCount + weight;
			if(maxWtCount <= maxWeight){
				toolsToTake.add(jo.get("name").getAsString());
			}
		}
		return toolsToTake;
	}
}

class CompareByValue implements Comparator<JsonObject> {

@Override
public int compare(JsonObject o1, JsonObject o2) {
    Long v1 = o1.get("value").getAsLong();
    Long v2 = o2.get("value").getAsLong();
    return v2.compareTo(v1);
}

}
