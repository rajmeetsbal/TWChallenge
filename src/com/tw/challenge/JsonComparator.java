package com.tw.challenge;

import java.util.Comparator;

import com.google.gson.JsonObject;

class MyJSONComparator implements Comparator<JsonObject> {

@Override
public int compare(JsonObject o1, JsonObject o2) {
    Long v1 = o1.get("timeUsedInMinutes").getAsLong();
    Long v2 = o2.get("timeUsedInMinutes").getAsLong();
    return v2.compareTo(v1);
}

}
