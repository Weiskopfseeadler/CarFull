package com.example.carfull.DBStuff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.carfull.Models.Car;
import com.example.carfull.Models.DriveData;
import com.example.carfull.Models.Expenses;
import com.example.carfull.Models.ExpensesCalculationMethods;
import com.example.carfull.Models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = SQLOpenHelper.class
            .getSimpleName();

    private static final String DATABASE_NAME = "CarFull";
    private static final int DATABASE_VERSION = 13;
    private static final String carTableName = "Car";
    private static final String idPrymaryKey = "id";
    private static final String carCollumnDesignation = "designation";
    private static final String carCollumnModel = "model";
    private static final String carCollumnFullMileagePerKm = "fullMileagePerKm";

    private static final String carCreateTable = "create table " + carTableName + " ( " + idPrymaryKey + " integer primary key autoincrement," +
            carCollumnDesignation + " text, " + carCollumnModel + " text, " + carCollumnFullMileagePerKm + " text)" + ";";
    private static final String carTableDrop = "drop table if exists " + carTableName + ";";

    //--------------------------------------

    private static final String PrymaryKey = "id";
    private static final String driveDataTableName = "driveData";
    public static final String driveDataCollumKmDrived = "kmDrived";
    public static final String driveDataCollumStartFuel = "startFuel";
    private static final String driveDataCollumendFuel = "endFuel";
    private static final String drivedatacollumstarttime = "starttime";
    private static final String driveDataCollumArrivingTime = "arrivingTime";
    private static final String driveDataCollumDuration = "duration";
    private static final String driveDataCollumCarId = "car_id";


    private static final String driveDadtaTablleCreate = "create table " + driveDataTableName + " (" + PrymaryKey + " integer primary key autoincrement, "
            + driveDataCollumKmDrived + " integer," + " " + driveDataCollumStartFuel + " integer, " + driveDataCollumendFuel + " integer ," +
            drivedatacollumstarttime + " text ," + driveDataCollumArrivingTime + " text," + driveDataCollumDuration + " text," +
            driveDataCollumCarId + " integer" + ");";
    // Tabelle "mood" löschen
    private static final String driveDataTableDrop = "drop table if exists " + driveDataTableName + ";";


    //--------------------------------------------------------------------------


    private static final String expensesTableName = "expenses";
    private static final String expensesPrymaryKey = "id";
    private static final String expensesCollumuserId = "userID";
    private static final String expensesCollumTotal = "total";
    private static final String expensesCollumExpensesPerUnit = "expensesPerUnit";
    private static final String expensesCollumExpensesCalculationMethod = "expensesCalculationMethod";
    private static final String expensesCollumIsSend = "isSend";
    private static final String expensesCollumIsPayd = "isPayd";
    private static final String expensesCollumDriveData_ID = "DriveData_Id";


    private static final String expensesTableCreate = "create table " + expensesTableName + " (" + expensesPrymaryKey + " integer primary key autoincrement, "
            + expensesCollumuserId + " integer," + " " + expensesCollumTotal + " integer, " + expensesCollumExpensesPerUnit + " integer ," +
            expensesCollumExpensesCalculationMethod + " text ," + expensesCollumIsSend + " text," + expensesCollumIsPayd + " text," + expensesCollumDriveData_ID
            + " integer" + ");";
    // Tabelle "mood" löschen
    private static final String expensesTableDrop = "drop table if exists " + expensesTableName + ";";


    //-----------------------------------------------------------------------


    private static final String userTabelName = "User";
    private static final String userPrymaryKey = "id";
    private static final String userCollumName = "name";
    private static final String userCollumPrename = "prename";
    private static final String userCollumIdentiication = "Identiication";


    private static final String userTableCreate = "create table " + userTabelName + " (" + userPrymaryKey + " integer primary key autoincrement," +
            " " + userCollumName + " text, " + userCollumPrename + " text , " + userCollumIdentiication + " integer );";
    // Tabelle "mood" löschen
    private static final String userTabledrop = "drop table if exists " + userTabelName + ";";


    //------------------------------------------------------------------------
    public SQLOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(carCreateTable);
        db.execSQL(driveDadtaTablleCreate);
        db.execSQL(expensesTableCreate);
        db.execSQL(userTableCreate);


    }


    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        Log.w(TAG, "Upgrade der Datenbank von Version "
                + oldVersion + " zu "
                + newVersion + "; alle Daten werden gelöscht");
        db.execSQL(carTableDrop + driveDataTableDrop + expensesTableCreate + userTabledrop);
        onCreate(db);

    }

    public List<Car> getCars() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(carTableName,
                null, null, null,
                null, null,
                null + " DESC");
        int idIndex = cursor.getColumnIndex(idPrymaryKey);
        int columnIndex = cursor.getColumnIndex(carCollumnDesignation);
        int columnIndex1 = cursor.getColumnIndex(carCollumnModel);
        int columnIndex2 = cursor.getColumnIndex(carCollumnFullMileagePerKm);


        List<Car> cars = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                cars.add(new Car(cursor.getLong(idIndex), cursor.getString(columnIndex), cursor.getString(columnIndex1), cursor.getFloat(columnIndex2)));
            }

        }

        return cars;


    }

    public long addCar(Car car) {
        long rowId = -1;
        try {
            SQLiteDatabase db = getWritableDatabase();
            Log.d(TAG, "Pfad: " + db.getPath());
            ContentValues values = new ContentValues();
            values.put(carCollumnModel, car.getName());
            values.put(carCollumnDesignation, car.getDesignation());
            values.put(carCollumnFullMileagePerKm, car.getFullMileagePerKm());


            rowId = db.insert(carTableName, null, values);

        } catch (SQLiteException e) {
            Log.e(TAG, "addCar()", e);
        } finally {
            Log.d(TAG, "addCar(): rowId=" + rowId);
        }
        return rowId;
    }


    public void update(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(carCollumnModel, car.getName());
        values.put(idPrymaryKey, car.getId());
        values.put(carCollumnFullMileagePerKm, car.getName());
        values.put(carCollumnDesignation, car.getDesignation());

        int numUpdated = db.update(carTableName,
                values, idPrymaryKey + " = ?", new String[]{Long.toString(car.getId())});
        Log.d(TAG, "update(): idPrymaryKey=" + car.getId()
                + " -> " + numUpdated);
    }

    public void deleteCar(long id) {
        SQLiteDatabase db = getWritableDatabase();
        int numDeleted = db.delete(carTableName, SQLOpenHelper.idPrymaryKey + " = ?",
                new String[]{Long.toString(id)});
        Log.d(TAG, "delete(): idPrymaryKey=" + id + " -> " + numDeleted);
    }

    public Car getCareByName(String serchName) {
        SQLiteDatabase db = getWritableDatabase();
        System.out.println("select * from carTableName WHERE  carCollumnModel = '" + serchName + "'LIMIT 1;");
        Cursor cursor = db.rawQuery("select * from " + carTableName + " WHERE  " + carCollumnDesignation + " = '" + serchName + "' LIMIT 1;", null);
        int idIndex = cursor.getColumnIndex(idPrymaryKey);
        int columnIndex = cursor.getColumnIndex(carCollumnDesignation);
        int columnIndex1 = cursor.getColumnIndex(carCollumnModel);
        int columnIndex2 = cursor.getColumnIndex(carCollumnFullMileagePerKm);


        Car car = new Car();
        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(columnIndex1));
            car = new Car(cursor.getLong(idIndex), cursor.getString(columnIndex), cursor.getString(columnIndex1), cursor.getFloat(columnIndex2))
            ;
        }
        return car;

    }

    public Car getCareByID(long id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + carTableName + " WHERE  " + idPrymaryKey + " = '" + id + "' LIMIT 1;", null);
        int idIndex = cursor.getColumnIndex(idPrymaryKey);
        int columnIndex = cursor.getColumnIndex(carCollumnDesignation);
        int columnIndex1 = cursor.getColumnIndex(carCollumnModel);
        int columnIndex2 = cursor.getColumnIndex(carCollumnFullMileagePerKm);


        Car car = new Car();
        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(columnIndex1));
            car = new Car(cursor.getLong(idIndex), cursor.getString(columnIndex), cursor.getString(columnIndex1), cursor.getFloat(columnIndex2))
            ;
        }
        return car;

    }

    public List<String> getCarsNames() {

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(carTableName,
                null, null, null,
                null, null,
                null + " DESC");

        int columnIndex = cursor.getColumnIndex(carCollumnDesignation);


        List<String> cars = new ArrayList<>();
        while (cursor.moveToNext()) {
            cars.add(cursor.getString(columnIndex));
        }
        return cars;

    }

    //-----------------------------------------------------------------------------------------------------------------------------


    public long addDriveData(DriveData driveData) {
        long rowId = -1;
        try {
            SQLiteDatabase db = getWritableDatabase();
            Log.d(TAG, "Pfad: " + db.getPath());
            ContentValues values = new ContentValues();
            values.put(driveDataCollumKmDrived, driveData.getKmDrived());
            values.put(driveDataCollumStartFuel, driveData.getStartFuel());
            values.put(driveDataCollumendFuel, driveData.getEndFuel());
            values.put(drivedatacollumstarttime, driveData.getStartTime().toString());
            values.put(driveDataCollumArrivingTime, driveData.getArrivingTime().toString());
            values.put(driveDataCollumDuration, driveData.getDuration().toString());
            values.put(driveDataCollumCarId, driveData.getCar().getId());


            rowId = db.insert(driveDataTableName, null, values);
            return rowId;
        } catch (SQLiteException e) {
            //TODO
            Log.e(TAG, "addCar()", e);
        } finally {
            //TODO
            Log.d(TAG, "addCar(): rowId=" + rowId);
        }
        return rowId;
    }

    public DriveData getDrivDatafromid(long id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + driveDataTableName + "   where   " + PrymaryKey + " = '" + id + "' limit 1;", null);
        int idIndex = cursor.getColumnIndex(PrymaryKey);
        int columnIndex = cursor.getColumnIndex(driveDataCollumKmDrived);
        int columnIndex1 = cursor.getColumnIndex(driveDataCollumStartFuel);
        int columnIndex2 = cursor.getColumnIndex(driveDataCollumendFuel);
        int columnIndex3 = cursor.getColumnIndex(drivedatacollumstarttime);
        int columnIndex4 = cursor.getColumnIndex(driveDataCollumArrivingTime);
        int columnIndex5 = cursor.getColumnIndex(driveDataCollumDuration);
        int columnIndex6 = cursor.getColumnIndex(driveDataCollumCarId);

        DriveData driveData = new DriveData();
        while (cursor.moveToNext()) {
            Car car = getCareByID(cursor.getLong(columnIndex6));
            Date date1 = null;
            Date date2 = null;
            System.out.println(cursor.getString(columnIndex3));
            System.out.println(cursor.getString(columnIndex4));
            System.out.println(cursor.getString(columnIndex5));
            try {
                date1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(cursor.getString(columnIndex3).replace(" IST ", " GMT+0530 "));
                date2 =new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(cursor.getString(columnIndex4).replace(" IST ", " GMT+0530 "));
            } catch (ParseException e) {

            }

                driveData = new DriveData(id, cursor.getFloat(columnIndex), cursor.getFloat(columnIndex1), cursor.getFloat(columnIndex2),date1,

                        date2,

                        Duration.parse((cursor.getString(columnIndex5))), car);

        }
        return driveData;
    }


    //-----------------------------------------------------------------------------------------------------------------


    public void addExpenses(Expenses expenses) {
        long rowId = -1;
        try {
            long driveDataId = addDriveData(expenses.getDriveData());

            SQLiteDatabase db = getWritableDatabase();
            Log.d(TAG, "Pfad: " + db.getPath());
            ContentValues values = new ContentValues();
            values.put(expensesCollumuserId, expenses.getUserId());
            values.put(expensesCollumTotal, expenses.getTotal());
            values.put(expensesCollumExpensesPerUnit, expenses.getExpensesPerUnit());
            values.put(expensesCollumExpensesCalculationMethod, expenses.getExpensesCalculationMethod());
            values.put(expensesCollumIsSend, expenses.isSend());
            values.put(expensesCollumIsPayd, expenses.isPayd());
            values.put(expensesCollumDriveData_ID, driveDataId);


            rowId = db.insert(expensesTableName, null, values);
        } catch (SQLiteException e) {
            //TODO
            Log.e(TAG, "addCar()", e);
        } finally {
            //TODO
            Log.d(TAG, "addCar(): rowId=" + rowId);
        }
    }

    public List<Expenses> getExpensesFromuser(long id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + expensesTableName + "  where   " + expensesCollumuserId + " = '" + id + "';", null);
        int idIndex = cursor.getColumnIndex(expensesPrymaryKey);
        int columnIndex = cursor.getColumnIndex(expensesCollumuserId);
        int columnIndex1 = cursor.getColumnIndex(expensesCollumTotal);
        int columnIndex2 = cursor.getColumnIndex(expensesCollumExpensesPerUnit);
        int columnIndex3 = cursor.getColumnIndex(expensesCollumExpensesCalculationMethod);
        int columnIndex4 = cursor.getColumnIndex(expensesCollumIsSend);
        int columnIndex5 = cursor.getColumnIndex(expensesCollumIsPayd);
        int columnIndex6 = cursor.getColumnIndex(expensesCollumDriveData_ID);
        List<Expenses> expensesList = new ArrayList<Expenses>();
        while (cursor.moveToNext()) {
            DriveData driveData = getDrivDatafromid(cursor.getLong(columnIndex6));
            Expenses expenses = new Expenses(cursor.getLong(idIndex), cursor.getLong(columnIndex), driveData, cursor.getDouble(columnIndex1), cursor.getDouble(columnIndex2),
                    ExpensesCalculationMethods.valueOf(cursor.getString(columnIndex3)), Boolean.parseBoolean(cursor.getString(columnIndex4)), Boolean.parseBoolean(cursor.getString(columnIndex5)));
        }
        return expensesList;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------------


    public User getUser(long id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + userTabelName + " where " + userPrymaryKey + " = " + id, null
        );
        int idIndex = cursor.getColumnIndex(userPrymaryKey);
        int columnIndex = cursor.getColumnIndex(userCollumName);
        int columnIndex1 = cursor.getColumnIndex(userCollumPrename);
        int columnIndex2 = cursor.getColumnIndex(userCollumIdentiication);

        User user = new User(cursor.getLong(idIndex), cursor.getString(columnIndex), cursor.getString(columnIndex1));
        return user;

    }

    public User getUserBylogin(String name, String prename, int identification) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            System.out.println("select * from " + userTabelName + " where  " + userCollumName + "= '" + name + "' and " + userCollumPrename + "= '" + prename + "' and " + userCollumIdentiication + "= '" + identification + "' limit 1 ");
            Cursor cursor = db.rawQuery("select * from " + userTabelName + " where  " + userCollumName + "= '" + name + "' and " + userCollumPrename + "= '" + prename + "' and " + userCollumIdentiication + "= '" + identification + "' limit 1 ", null
            );
            int idIndex = cursor.getColumnIndex(userPrymaryKey);
            int columnIndex = cursor.getColumnIndex(userCollumName);
            int columnIndex1 = cursor.getColumnIndex(userCollumPrename);

            if (cursor.getCount() == 1) {
                while (cursor.moveToNext()) {
                    User user = new User(cursor.getLong(idIndex), cursor.getString(columnIndex), cursor.getString(columnIndex1));
                    return user;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return null;


    }

    public void addUser(User user, int identification) {
        long rowId = -1;
        try {
            SQLiteDatabase db = getWritableDatabase();
            Log.d(TAG, "Pfad: " + db.getPath());
            ContentValues values = new ContentValues();
            values.put(userCollumName, user.getName());
            values.put(userCollumPrename, user.getPrename());
            values.put(userCollumIdentiication, identification);
            rowId = db.insert(userTabelName, null, values);
        } catch (SQLiteException e) {
            Log.e(TAG, "addCar()", e);
        } finally {
            Log.d(TAG, "addCar(): rowId=" + rowId);
        }
    }

    public void updateUser(User user, int identification) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userCollumName, user.getName());
        values.put(userCollumPrename, user.getPrename());
        values.put(userCollumIdentiication, identification);


        int numUpdated = db.update(userTabelName,
                values, userPrymaryKey + " = ?", new String[]{Long.toString(user.getId())});
        Log.d(TAG, "update(): id=" + user.getId() + " -> " + numUpdated);
    }

    //TODO Adapt
    public void deleteuser(long id) {
        SQLiteDatabase db = getWritableDatabase();
        int numDeleted = db.delete(userTabelName, userPrymaryKey + " = ?",
                new String[]{Long.toString(id)});
        Log.d(TAG, "delete(): id=" + id + " -> " + numDeleted);
    }
}

