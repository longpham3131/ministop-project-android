package hcmute.edu.vn.hlong18110314.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.hlong18110314.database.Model.CategoryModel;
import hcmute.edu.vn.hlong18110314.database.Model.NotificationModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderDetailModel;
import hcmute.edu.vn.hlong18110314.database.Model.OrderModel;
import hcmute.edu.vn.hlong18110314.database.Model.ProductModel;
import hcmute.edu.vn.hlong18110314.database.Model.ServiceModel;
import hcmute.edu.vn.hlong18110314.database.Model.StoreModel;
import hcmute.edu.vn.hlong18110314.database.Model.UserModel;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database name here
    public static final String DATABASE_NAME = "ministop.db";

    // All Columns Name
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_FULL_NAME = "fullName";
    private static final String COLUMN_USER_POINT = "point";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_USER_ROLE = "role";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRODUCT_DESCRIPTION ="description";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String COLUMN_PRODUCT_CATEGORY_ID = "categoryId";
    private static final String COLUMN_VOUCHER_VALUE = "value";
    private static final String COLUMN_STORE_LOCATION = "location";
    private static final String COLUMN_NOTIFICATION_STARTDATE = "startDate";
    private static final String COLUMN_NOTIFICATION_ENDDATE = "endDate";
    private static final String COLUMN_ORDER_DATE_CREATED = "dateCreated";
    private static final String COLUMN_ORDER_TOTAL = "total";
    private static final String COLUMN_ORDER_DETAIL_ID = "orderId";
    private static final String COLUMN_ORDER_DETAIL_QUANTITY = "quantity";
    private static final String COLUMN_ORDER_DETAIL_PRODUCT_ID = "productId";
    private static final String COLUMN_ORDER_USER_ID = "userId";
    private static final String COLUMN_ORDER_STATUS = "status";

    // User table name
    private static final String TABLE_USER = "user";
    // Category table name
    private static final String TABLE_CATEGORY = "category";
    // Product
    private static final String TABLE_PRODUCT ="product";
    // Voucher
    private static final String TABLE_VOUCHER ="voucher";
    // Service
    private static final String TABLE_SERVICE = "service";
    // Store
    private static final String TABLE_STORE = "store";
    // Notification
    private static final String TABLE_NOTIFICATION = "notification";
    // Order
    private static final String TABLE_ORDER = "order";
    // OrderDetail
    private static final String TABLE_ORDER_DETAIL = "orderdetail";

    // Constructor
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, DATABASE_VERSION);
    }

    // User Query
    public void userCreate(String email, String password, String fullName,String role, byte[] image){
        SQLiteDatabase dtb = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_EMAIL,email);
        cv.put(COLUMN_USER_PASSWORD,password);
        cv.put(COLUMN_USER_FULL_NAME, fullName);
        cv.put(COLUMN_IMAGE, image);
        cv.put(COLUMN_USER_ROLE,role);

        dtb.insert(TABLE_USER,null,cv);
        dtb.close();
    }
    public List<UserModel> getAllUser(){
        SQLiteDatabase dtb = getReadableDatabase();
        String[] columns = {
                COLUMN_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_FULL_NAME,
                COLUMN_USER_POINT,
                COLUMN_USER_ROLE,
                COLUMN_IMAGE
        };

        String sortOrder = COLUMN_USER_EMAIL + " ASC";

        List<UserModel> userList = new ArrayList<UserModel>();

        Cursor cursor = dtb.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                UserModel user = new UserModel();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FULL_NAME)));
                user.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
                user.setPoint(cursor.getString(cursor.getColumnIndex(COLUMN_USER_POINT)));
                user.setRole(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROLE)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return userList;
    }
    public UserModel userValidation(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_FULL_NAME,
                COLUMN_USER_POINT,
                COLUMN_USER_ROLE,
                COLUMN_IMAGE
        };

        SQLiteDatabase db = getReadableDatabase();
        // selection criteria
        String selection = "email = ?" + " AND password = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        UserModel user = new UserModel();
        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
            user.setFullName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FULL_NAME)));
            user.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
            user.setPoint(cursor.getString(cursor.getColumnIndex(COLUMN_USER_POINT)));
            user.setRole(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROLE)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
        }

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return user;
        }
        return null;
    }
    public boolean checkUserExist(String email){
        String[] columns = {"email"};

        SQLiteDatabase dtb = getReadableDatabase();

        String selection = "email=?";
        String[] selectionArgs = {email};

        Cursor cursor = dtb.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }
    public void updateUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_FULL_NAME, user.getFullName());
        values.put(COLUMN_IMAGE, user.getImage());

        // updating row
        db.update(TABLE_USER, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});

        db.close();
    }
    public void deleteUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete record by id
        db.delete(TABLE_CATEGORY, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    // Category Query
    public List<CategoryModel> getAllCategories(){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_IMAGE
        };

        String sortOrder = COLUMN_NAME + " ASC";

        List<CategoryModel> categoriesList = new ArrayList<CategoryModel>();

        Cursor cursor = dtb.query(TABLE_CATEGORY, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                CategoryModel category = new CategoryModel();
                category.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                category.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                category.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));

                categoriesList.add(category);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return categoriesList;
    }
    public CategoryModel getByIdCategory(Integer id){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_IMAGE
        };

        String selection = "id = ?";
        String[] selectionArgs = { id.toString() };

        CategoryModel category = new CategoryModel();

        Cursor cursor = dtb.query(TABLE_CATEGORY, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (cursor.moveToFirst()) {
            do {
                category.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                category.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                category.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return category;
    }
    public CategoryModel createCategory(CategoryModel newCategory){
        SQLiteDatabase dtb = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,newCategory.getName());
        cv.put(COLUMN_IMAGE, newCategory.getImage());

        Integer id = Math.toIntExact(dtb.insert(TABLE_CATEGORY, null, cv));
        dtb.close();
        return getByIdCategory(id);
    }
    public void updateCategory(CategoryModel category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, category.getName());
        values.put(COLUMN_IMAGE, category.getImage());

        // updating row
        db.update(TABLE_CATEGORY, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(category.getId())});

        db.close();
    }
    public void deleteCategory(CategoryModel category) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete record by id
        db.delete(TABLE_CATEGORY, COLUMN_ID + " = ?",
                new String[]{String.valueOf(category.getId())});
        db.close();
    }

    // Product Query
    public List<ProductModel> getAllProducts(){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_IMAGE,
                COLUMN_PRODUCT_CATEGORY_ID,
                COLUMN_PRODUCT_PRICE
        };

        String sortOrder = COLUMN_NAME + " ASC";

        List<ProductModel> productsList = new ArrayList<ProductModel>();

        Cursor cursor = dtb.query(TABLE_PRODUCT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                ProductModel product = new ProductModel();
                product.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
                product.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
                product.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY_ID)));
                product.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));

                productsList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return productsList;
    }
    public List<ProductModel> getProductByCategoryId(Integer id){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_IMAGE,
                COLUMN_PRODUCT_CATEGORY_ID,
                COLUMN_PRODUCT_PRICE
        };

        String sortOrder = COLUMN_NAME + " ASC";
        String selection = "categoryId = " + id;
        List<ProductModel> productsList = new ArrayList<ProductModel>();

        Cursor cursor = dtb.query(TABLE_PRODUCT, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                ProductModel product = new ProductModel();
                product.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
                product.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
                product.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY_ID)));
                product.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));

                productsList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return productsList;
    }
    public ProductModel getByIdProduct(Integer id){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_IMAGE,
                COLUMN_PRODUCT_CATEGORY_ID,
                COLUMN_PRODUCT_PRICE
        };

        String selection = "id = ?";
        String[] selectionArgs = { id.toString() };

        ProductModel product = new ProductModel();

        Cursor cursor = dtb.query(TABLE_PRODUCT, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (cursor.moveToFirst()) {
            do {
                product.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                product.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
                product.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
                product.setCategoryId(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY_ID)));
                product.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return product;
    }
    public ProductModel createProduct(ProductModel newProduct){
        SQLiteDatabase dtb = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,newProduct.getName());
        cv.put(COLUMN_PRODUCT_DESCRIPTION, newProduct.getDescription());
        cv.put(COLUMN_IMAGE, newProduct.getImage());
        cv.put(COLUMN_PRODUCT_CATEGORY_ID, newProduct.getCategoryId());
        cv.put(COLUMN_PRODUCT_PRICE, newProduct.getPrice());

        Integer id = Math.toIntExact(dtb.insert(TABLE_PRODUCT, null, cv));
        dtb.close();
        return getByIdProduct(id);
    }
    public void updateProduct(ProductModel product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(COLUMN_IMAGE, product.getImage());
        values.put(COLUMN_PRODUCT_CATEGORY_ID, product.getCategoryId());
        values.put(COLUMN_PRODUCT_PRICE, product.getPrice());

        // updating row
        db.update(TABLE_PRODUCT, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(product.getId())});

        db.close();
    }
    public void deleteProduct(ProductModel product) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete record by id
        db.delete(TABLE_PRODUCT, COLUMN_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
        db.close();
    }

    // Voucher Query

    // Service Query
    public List<ServiceModel> getAllServices(){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_IMAGE
        };

        String sortOrder = COLUMN_NAME + " ASC";

        List<ServiceModel> servicesList = new ArrayList<ServiceModel>();

        Cursor cursor = dtb.query(TABLE_SERVICE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                ServiceModel service = new ServiceModel();
                service.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                service.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                service.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
                service.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));

                servicesList.add(service);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return servicesList;
    }
    public ServiceModel getByIdService(Integer id){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_IMAGE
        };

        String selection = "id = ?";
        String[] selectionArgs = { id.toString() };

        ServiceModel service = new ServiceModel();

        Cursor cursor = dtb.query(TABLE_SERVICE, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (cursor.moveToFirst()) {
            do {
                service.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                service.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                service.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
                service.setImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return service;
    }
    public ServiceModel createService(ServiceModel newService){
        SQLiteDatabase dtb = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,newService.getName());
        cv.put(COLUMN_PRODUCT_DESCRIPTION, newService.getDescription());
        cv.put(COLUMN_IMAGE, newService.getImage());

        Integer id = Math.toIntExact(dtb.insert(TABLE_SERVICE, null, cv));
        dtb.close();
        return getByIdService(id);
    }
    public void updateService(ServiceModel service) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, service.getName());
        values.put(COLUMN_PRODUCT_DESCRIPTION, service.getDescription());
        values.put(COLUMN_IMAGE, service.getImage());

        // updating row
        db.update(TABLE_SERVICE, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(service.getId())});

        db.close();
    }
    public void deleteService(ServiceModel service) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete record by id
        db.delete(TABLE_SERVICE, COLUMN_ID + " = ?",
                new String[]{String.valueOf(service.getId())});
        db.close();
    }

    // Store Query
    public List<StoreModel> getAllStores(){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_STORE_LOCATION
        };

        String sortOrder = COLUMN_NAME + " ASC";

        List<StoreModel> storesList = new ArrayList<StoreModel>();

        Cursor cursor = dtb.query(TABLE_STORE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                StoreModel store = new StoreModel();
                store.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                store.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                store.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_STORE_LOCATION)));

                storesList.add(store);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return storesList;
    }
    public StoreModel getByIdStore(Integer id){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_STORE_LOCATION
        };

        String selection = "id = ?";
        String[] selectionArgs = { id.toString() };

        StoreModel store = new StoreModel();

        Cursor cursor = dtb.query(TABLE_STORE, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (cursor.moveToFirst()) {
            do {
                store.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                store.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                store.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_STORE_LOCATION)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return store;
    }
    public StoreModel createStore(StoreModel newStore){
        SQLiteDatabase dtb = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,newStore.getName());
        cv.put(COLUMN_STORE_LOCATION, newStore.getLocation());

        Integer id = Math.toIntExact(dtb.insert(TABLE_STORE, null, cv));
        dtb.close();
        return getByIdStore(id);
    }
    public void updateStore(StoreModel store) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, store.getName());
        values.put(COLUMN_STORE_LOCATION, store.getLocation());

        // updating row
        db.update(TABLE_STORE, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(store.getId())});

        db.close();
    }
    public void deleteStore(StoreModel store) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete record by id
        db.delete(TABLE_STORE, COLUMN_ID + " = ?",
                new String[]{String.valueOf(store.getId())});
        db.close();
    }

    // Notification Query
    public List<NotificationModel> getAllNotifies(){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_NOTIFICATION_STARTDATE,
                COLUMN_NOTIFICATION_ENDDATE
        };

        String sortOrder = COLUMN_NAME + " ASC";

        List<NotificationModel> notifiesList = new ArrayList<NotificationModel>();

        Cursor cursor = dtb.query(TABLE_NOTIFICATION, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                NotificationModel noti = new NotificationModel();
                noti.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                noti.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                noti.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
                noti.setStartDate(cursor.getString(cursor.getColumnIndex(COLUMN_NOTIFICATION_STARTDATE)));
                noti.setEndDate(cursor.getString(cursor.getColumnIndex(COLUMN_NOTIFICATION_ENDDATE)));

                notifiesList.add(noti);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return notifiesList;
    }
    public NotificationModel getByIdNoti(Integer id){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_NOTIFICATION_STARTDATE,
                COLUMN_NOTIFICATION_ENDDATE
        };

        String selection = "id = ?";
        String[] selectionArgs = { id.toString() };

        NotificationModel noti = new NotificationModel();

        Cursor cursor = dtb.query(TABLE_NOTIFICATION, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (cursor.moveToFirst()) {
            do {
                noti.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                noti.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                noti.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION)));
                noti.setStartDate(cursor.getString(cursor.getColumnIndex(COLUMN_NOTIFICATION_STARTDATE)));
                noti.setEndDate(cursor.getString(cursor.getColumnIndex(COLUMN_NOTIFICATION_ENDDATE)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return noti;
    }
    public NotificationModel createNoti(NotificationModel newNoti){
        SQLiteDatabase dtb = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,newNoti.getName());
        cv.put(COLUMN_PRODUCT_DESCRIPTION, newNoti.getDescription());
        cv.put(COLUMN_NOTIFICATION_STARTDATE, newNoti.getStartDate());
        cv.put(COLUMN_NOTIFICATION_ENDDATE, newNoti.getEndDate());


        Integer id = Math.toIntExact(dtb.insert(TABLE_NOTIFICATION, null, cv));
        dtb.close();
        return getByIdNoti(id);
    }
    public void updateNoti(NotificationModel noti) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,noti.getName());
        cv.put(COLUMN_PRODUCT_DESCRIPTION, noti.getDescription());
        cv.put(COLUMN_NOTIFICATION_STARTDATE, noti.getStartDate());
        cv.put(COLUMN_NOTIFICATION_ENDDATE, noti.getEndDate());

        // updating row
        db.update(TABLE_NOTIFICATION, cv, COLUMN_ID + " = ?",
                new String[]{String.valueOf(noti.getId())});

        db.close();
    }
    public void deleteNoti(NotificationModel noti) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete record by id
        db.delete(TABLE_NOTIFICATION, COLUMN_ID + " = ?",
                new String[]{String.valueOf(noti.getId())});
        db.close();
    }

    //  Order Query
    public List<OrderModel> getAllOrders(){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_ORDER_DATE_CREATED,
                COLUMN_ORDER_TOTAL,
                COLUMN_ORDER_USER_ID,
                COLUMN_ORDER_STATUS
        };

        String sortOrder = COLUMN_NAME + " ASC";

        List<OrderModel> storesList = new ArrayList<OrderModel>();

        Cursor cursor = dtb.query(TABLE_ORDER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                OrderModel order = new OrderModel();
                order.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                order.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                order.setDateCreated(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DATE_CREATED)));
                order.setTotal(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_TOTAL))));
                order.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_USER_ID))));
                order.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_STATUS)));
                storesList.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return storesList;
    }
    public OrderModel getByIdOrder(Integer id){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_ORDER_DATE_CREATED,
                COLUMN_ORDER_TOTAL,
                COLUMN_ORDER_USER_ID,
                COLUMN_ORDER_STATUS
        };

        String selection = "id = ?";
        String[] selectionArgs = { id.toString() };

        OrderModel order = new OrderModel();

        Cursor cursor = dtb.query(TABLE_ORDER, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (cursor.moveToFirst()) {
            do {
                order.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                order.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                order.setDateCreated(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DATE_CREATED)));
                order.setTotal(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_TOTAL))));
                order.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_USER_ID))));
                order.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_STATUS)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return order;
    }
    public OrderModel createOrder(OrderModel newOrder){
        SQLiteDatabase dtb = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,newOrder.getName());
        cv.put(COLUMN_ORDER_DATE_CREATED, newOrder.getDateCreated());
        cv.put(COLUMN_ORDER_TOTAL, newOrder.getTotal());
        cv.put(COLUMN_ORDER_USER_ID, newOrder.getUserID());
        cv.put(COLUMN_ORDER_STATUS, newOrder.getStatus());

        Integer id = Math.toIntExact(dtb.insert(TABLE_ORDER, null, cv));
        dtb.close();
        return getByIdOrder(id);
    }
    public void updateOrder(OrderModel order ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, order.getName());
        values.put(COLUMN_ORDER_DATE_CREATED, order.getDateCreated());
        values.put(COLUMN_ORDER_TOTAL, order.getTotal());
        values.put(COLUMN_ORDER_USER_ID, order.getUserID());
        values.put(COLUMN_ORDER_STATUS, order.getStatus());

        // updating row
        db.update(TABLE_ORDER, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(order.getId())});

        db.close();
    }
    public void deleteOrder(OrderModel store) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete record by id
        db.delete(TABLE_ORDER, COLUMN_ID + " = ?",
                new String[]{String.valueOf(store.getId())});
        db.close();
    }

    // OrderDetail
    public List<OrderDetailModel> getAllOrderDetails(){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_ORDER_DETAIL_ID,
                COLUMN_ORDER_DETAIL_PRODUCT_ID,
                COLUMN_ORDER_DETAIL_QUANTITY,
                COLUMN_ORDER_TOTAL
        };

        String sortOrder = COLUMN_NAME + " ASC";

        List<OrderDetailModel> storesList = new ArrayList<OrderDetailModel>();

        Cursor cursor = dtb.query(TABLE_ORDER_DETAIL, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                OrderDetailModel orderDetail = new OrderDetailModel();
                orderDetail.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                orderDetail.setOrderId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAIL_ID))));
                orderDetail.setProductId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAIL_PRODUCT_ID))));
                orderDetail.setQuantity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAIL_QUANTITY))));
                orderDetail.setTotal(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_TOTAL))));

                storesList.add(orderDetail);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return storesList;
    }
    public List<OrderDetailModel> getAllOrderDetailsByOrderId(OrderModel order){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_ORDER_DETAIL_ID,
                COLUMN_ORDER_DETAIL_PRODUCT_ID,
                COLUMN_ORDER_DETAIL_QUANTITY,
                COLUMN_ORDER_TOTAL
        };

        String sortOrder = COLUMN_NAME + " ASC";
        String selection = "orderId = ?";
        String[] selectionArgs = { order.getId().toString() };

        List<OrderDetailModel> storesList = new ArrayList<OrderDetailModel>();

        Cursor cursor = dtb.query(TABLE_ORDER_DETAIL, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                OrderDetailModel orderDetail = new OrderDetailModel();
                orderDetail.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                orderDetail.setOrderId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAIL_ID))));
                orderDetail.setProductId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAIL_PRODUCT_ID))));
                orderDetail.setQuantity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAIL_QUANTITY))));
                orderDetail.setTotal(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_TOTAL))));

                storesList.add(orderDetail);
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return storesList;
    }
    public OrderDetailModel getByIdOrderDetail(Integer id){
        SQLiteDatabase dtb = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_ORDER_DETAIL_ID,
                COLUMN_ORDER_DETAIL_PRODUCT_ID,
                COLUMN_ORDER_DETAIL_QUANTITY,
                COLUMN_ORDER_TOTAL
        };

        String selection = "id = ?";
        String[] selectionArgs = { id.toString() };

        OrderDetailModel orderDetail = new OrderDetailModel();

        Cursor cursor = dtb.query(TABLE_ORDER_DETAIL, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (cursor.moveToFirst()) {
            do {
                orderDetail.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                orderDetail.setOrderId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAIL_ID))));
                orderDetail.setProductId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAIL_PRODUCT_ID))));
                orderDetail.setQuantity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAIL_QUANTITY))));
                orderDetail.setTotal(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_TOTAL))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        dtb.close();
        return orderDetail;
    }
    public OrderDetailModel createOrderDetail(OrderDetailModel newOrder){
        SQLiteDatabase dtb = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ORDER_DETAIL_ID,newOrder.getOrderId());
        cv.put(COLUMN_ORDER_DETAIL_PRODUCT_ID, newOrder.getProductId());
        cv.put(COLUMN_ORDER_DETAIL_QUANTITY, newOrder.getQuantity());
        cv.put(COLUMN_ORDER_TOTAL, newOrder.getTotal());


        Integer id = Math.toIntExact(dtb.insert(TABLE_ORDER_DETAIL, null, cv));
        dtb.close();
        return getByIdOrderDetail(id);
    }
    public void updateOrder(OrderDetailModel orderDetail ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ORDER_DETAIL_ID,orderDetail.getOrderId());
        cv.put(COLUMN_ORDER_DETAIL_PRODUCT_ID, orderDetail.getProductId());
        cv.put(COLUMN_ORDER_DETAIL_QUANTITY, orderDetail.getQuantity());
        cv.put(COLUMN_ORDER_TOTAL, orderDetail.getTotal());

        // updating row
        db.update(TABLE_ORDER_DETAIL, cv, COLUMN_ID + " = ?",
                new String[]{String.valueOf(orderDetail.getId())});

        db.close();
    }
    public void deleteOrder(OrderDetailModel store) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete record by id
        db.delete(TABLE_ORDER_DETAIL, COLUMN_ID + " = ?",
                new String[]{String.valueOf(store.getId())});
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SET RELATIONSHIP ON
        db.execSQL("PRAGMA foreign_keys = ON");

        db.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT,email NVARCHAR(200) UNIQUE, password NVACHAR(200) NOT NULL," +
                " fullName NVARCHAR(100),point INTEGER default 0,role NVARCHAR(20), image BLOB)");
        db.execSQL("CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY AUTOINCREMENT, name NVACHAR(200), image BLOB)");
        db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT, name NVACHAR(200),description NVACHAR, image BLOB,categoryId INTEGER,price INTEGER," +
                " FOREIGN KEY (categoryId) REFERENCES category(id) ON DELETE CASCADE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS voucher(id INTEGER PRIMARY KEY AUTOINCREMENT,value INTEGER, name NVACHAR(200), image BLOB)");
        db.execSQL("CREATE TABLE IF NOT EXISTS service(id INTEGER PRIMARY KEY AUTOINCREMENT, name NVACHAR(200),description NVACHAR(200),image BLOB)");
        db.execSQL("CREATE TABLE IF NOT EXISTS store(id INTEGER PRIMARY KEY AUTOINCREMENT, name NVACHAR(200), location NVACHAR(200))");
        db.execSQL("CREATE TABLE IF NOT EXISTS notification(id INTEGER PRIMARY KEY AUTOINCREMENT, name NVACHAR(200), description NVACHAR(200), startDate NVACHAR(200), endDate NVACHAR(200))");
        db.execSQL("CREATE TABLE IF NOT EXISTS [order](id INTEGER PRIMARY KEY AUTOINCREMENT, name NVACHAR(200),dateCreated NVARCHAR, total INTEGER, userId INTEGER, status NVARCHAR(100))");
        db.execSQL("CREATE TABLE IF NOT EXISTS orderdetail(id INTEGER PRIMARY KEY AUTOINCREMENT,orderId INTEGER, productId INTEGER, quantity INTEGER, total INTEGER," +
                " FOREIGN KEY (orderId) REFERENCES [order](id) ON DELETE CASCADE," +
                "FOREIGN KEY (productId) REFERENCES product(id) ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db){
    }
}
