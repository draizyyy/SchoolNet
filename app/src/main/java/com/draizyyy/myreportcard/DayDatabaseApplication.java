//
//import android.app.Application;
//
//import androidx.room.Room;
//
//public class DayDatabaseApplication extends Application {
//
//    private static DayDatabaseApplication instance;
//
//    public static DayDatabaseApplication getInstance() {
//        return instance;
//    }
//
//    private static DayDatabase dayDatabase;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        instance = this;
//        dayDatabase = Room.databaseBuilder(this, DayDatabase.class, DayDatabase.NAME).fallbackToDestructiveMigration().build();
//    }
//
//    public static DayDatabase getUserDatabase() {
//        return dayDatabase;
//    }
//}