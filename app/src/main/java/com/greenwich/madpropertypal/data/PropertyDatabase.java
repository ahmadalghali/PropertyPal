package com.greenwich.madpropertypal.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.greenwich.madpropertypal.model.Property;
import com.greenwich.madpropertypal.model.Report;

@Database(entities = {Property.class, Report.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class PropertyDatabase extends RoomDatabase {



    public abstract PropertyDao propertyDao();
    public abstract ReportDao reportDao();

    private static PropertyDatabase propertyDatabaseInstance;

    public static synchronized PropertyDatabase getPropertyDatabaseInstance(Context context){
        if(propertyDatabaseInstance == null){
            propertyDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),PropertyDatabase.class,
                    "madPropertyPal")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return propertyDatabaseInstance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(propertyDatabaseInstance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private PropertyDao propertyDao;

        private PopulateDbAsyncTask(PropertyDatabase db){
            this.propertyDao = db.propertyDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            propertyDao.insertProperty(new Property("Judy", "97", "Bungalow", "Lease hold", 450, "Hooker", "SS8 1BZ", "Bradford", 1, 1, 2200000, "Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum."));
            propertyDao.insertProperty(new Property("Charing Cross", "33", "Flat", "Free hold", 400, "Weeping Birch", "SA18 1RW", "Bradford", 3, 2, 2650000, "Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem."));
            propertyDao.insertProperty(new Property("Fairview", "193", "Flat", "Long let", 200, "Schiller", "DE6 3LN", "Aberdeen", 2, 1, 1750000, "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi."));
            propertyDao.insertProperty(new Property("Atwood", "64", "Flat", "Free hold", 200, "Banding", "DA11 9EX", "Portsmouth", 3, 1, 850000, "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque."));
            propertyDao.insertProperty(new Property("Eliot", "61", "Apartment", "Short let", 450, "Bluestem", "KA17 0EB", "Gloucester", 1, 1, 1800000, "Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est."));
            propertyDao.insertProperty(new Property("Kensington", "99", "Flat", "Long let", 400, "Maple", "BS3 4PF", "Preston", 5, 2, 2500000, "Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris."));
            propertyDao.insertProperty(new Property("Milwaukee", "54", "Bungalow", "Long let", 100, "Thierer", "HD9 1LG", "Carlisle", 1, 1, 2400000, "Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis."));
            propertyDao.insertProperty(new Property("Golf", "27", "Bungalow", "Short let", 200, "New Castle", "PE11 9ZL", "Bristol", 3, 3, 2250000, "Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus."));
            propertyDao.insertProperty(new Property( "Lerdahl", "147", "House", "Lease hold", 100, "Pawling", "TW11 8SY", "Oxford", 1, 3, 700000, "Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus."));

            return null;
        }
    }
    
}
