package com.app.e_commerceapp.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User users);

    @Query("SELECT EXISTS (SELECT * FROM User WHERE productId=:productId)")
    Boolean is_exist(int productId);

    @Query("SELECT * FROM User")
    List<User> getallusers();

    @Query("DELETE FROM User")
    void deleteallusers();

    @Query("SELECT * FROM User WHERE productId=:productId")
    User getUserFromId(int productId);

    @Query("DELETE FROM User WHERE productId = :productId")
    void deleteById(int productId);



    @Query("UPDATE User SET productQuantity=:productQuantity  WHERE productId= :productId")
    void  updateById(int productId, int productQuantity);



}
