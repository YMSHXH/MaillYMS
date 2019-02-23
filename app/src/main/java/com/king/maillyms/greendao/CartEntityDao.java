package com.king.maillyms.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.king.maillyms.entity.CartEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CART_ENTITY".
*/
public class CartEntityDao extends AbstractDao<CartEntity, Long> {

    public static final String TABLENAME = "CART_ENTITY";

    /**
     * Properties of entity CartEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CommodityId = new Property(0, long.class, "commodityId", true, "_id");
        public final static Property Count = new Property(1, int.class, "count", false, "COUNT");
    }


    public CartEntityDao(DaoConfig config) {
        super(config);
    }
    
    public CartEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CART_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL UNIQUE ," + // 0: commodityId
                "\"COUNT\" INTEGER NOT NULL );"); // 1: count
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CART_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CartEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCommodityId());
        stmt.bindLong(2, entity.getCount());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CartEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCommodityId());
        stmt.bindLong(2, entity.getCount());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public CartEntity readEntity(Cursor cursor, int offset) {
        CartEntity entity = new CartEntity( //
            cursor.getLong(offset + 0), // commodityId
            cursor.getInt(offset + 1) // count
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CartEntity entity, int offset) {
        entity.setCommodityId(cursor.getLong(offset + 0));
        entity.setCount(cursor.getInt(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CartEntity entity, long rowId) {
        entity.setCommodityId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CartEntity entity) {
        if(entity != null) {
            return entity.getCommodityId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CartEntity entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
