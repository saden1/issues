/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.saden1.issues.mapdb;

import com.github.saden1.issues.mapdb.model.User;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
public class UserSerializerNGTest {

    UserSerializer sut;

    @BeforeMethod
    public void init() {
        sut = new UserSerializer();
    }

    @Test
    public void testSerialization() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOuput = new DataOutputStream(outputStream);
        User user = new User("test", 30);

        assertThat(outputStream.size()).isZero();
        sut.serialize(dataOuput, user);
        assertThat(outputStream.size()).isEqualTo(6);
    }

    @Test
    public void testDeserialization() throws IOException {
        byte[] buffer = {8, 116, 101, 115, 116, 60};
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        DataInputStream dataInput = new DataInputStream(inputStream);
        
        User result = sut.deserialize(dataInput, buffer.length);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("test");
        assertThat(result.getAge()).isEqualTo(30);

    }

}
