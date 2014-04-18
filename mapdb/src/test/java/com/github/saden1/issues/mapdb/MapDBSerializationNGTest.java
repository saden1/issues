package com.github.saden1.issues.mapdb;


import com.github.saden1.issues.mapdb.UserSerializer;
import com.github.saden1.issues.mapdb.model.User;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
public class MapDBSerializationNGTest {

    DB db;
    UserSerializer serializer;

    @BeforeMethod
    public void init() {
        db = DBMaker.newMemoryDirectDB().make();
        serializer = new UserSerializer();
    }

    @AfterMethod
    public void destroy() {
        db.commit();
        db.close();
    }

    @Test
    public void testMakeWithCustomSerialization() {
        HTreeMap<String, User> cache = db.createHashMap("makeCache")
                .keySerializer(Serializer.STRING)
                .valueSerializer(serializer)
                .make();

        User user = new User("test", 30);
        cache.put("test", user);
    }

    @Test
    public void testMakeOrGetWithCustumSerialization() {
        HTreeMap<String, User> cache = db.createHashMap("makeOrGetCache")
                .keySerializer(Serializer.STRING)
                .valueSerializer(serializer)
                .makeOrGet();

        User user = new User("test", 30);
        cache.put("test", user);
    }

}
