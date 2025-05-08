/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.crud.p_uts_23090106_d_2025;

/**
 *
 * @author HP
 */

//import semua library yang dibutuhkan kode java
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;

//deklarasi kelas utama
public class CRUD_23090106_D_2025 {
    
    
    private static void viewData(FindIterable<Document> doc, String text){
        System.out.println(text);
        for (Document d : doc){
            System.out.println(d);}
    }
    
    //fungsi utama main, titik masuk eksekusi program
    public static void main(String[] args) {
        String URL = "mongodb://localhost:27017/" ; //URL koneksi ke server MongoDB lokal
        MongoClient client= MongoClients.create(URL);
        MongoDatabase db = client.getDatabase("uts_23090106_D_2025"); //menghubungkan ke database yang sudah ada
        MongoCollection<Document> tabel =db.getCollection("coll_23090106_D_2025"); //mengakses koleksi yang ada di database
        
        
        //Insert data(create)
        Document data = new Document ("name","Bayu Rahmat Nurhidayanto")
                .append ("nim", Arrays.asList("23090106"))
                .append ("kelas", Arrays.asList("4D"));
        tabel.insertOne(data);

        //view
        FindIterable<Document> result = tabel.find();
        viewData(result, "Before Update");
        

        
        //Update
        Bson filter = Filters.eq("name", "Bayu Rahmat Nurhidayanto");
        Bson update = Updates.set("pid", "P1");
        tabel.updateOne(filter, update);
        
        
        //view
        result = tabel.find();
        viewData(result, "After Update");
        
        
        
        //DELETE
        System.out.println("=====DELETE DATA=====");
        Bson dataTarget = Filters.eq("pid", "P1");
        tabel.deleteOne(dataTarget);
        
        
        
        System.out.println("==SEARCH DATA");
        Bson find = Filters.eq("pid" , "P1");
        Document d = tabel.find(find).first();
        if(d != null){
            System.out.println(d.toJson());
        }else {
            System.out.println("Data tidak ditemukan!!!");
        }
    }
}
