//package com.draizyyy.myreportcard;
//
//import androidx.room.TypeConverter;
//
//import java.lang.reflect.Type;
//import java.util.List;
//
//public class Converters {
//    @TypeConverter
//    public static List<String> fromString(String value) {
//        Type listType = new TypeToken<List<String>>() {}.getType();
//        return new Gson().fromJson(value, listType);
//    }
//
//    @TypeConverter
//    public static String fromArrayList(List<String> list) {
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//        return json;
//    }
//}
