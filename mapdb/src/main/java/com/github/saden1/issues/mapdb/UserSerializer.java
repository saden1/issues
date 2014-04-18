/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.saden1.issues.mapdb;

import com.github.saden1.issues.mapdb.model.User;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.mapdb.Serializer;

/**
 *
 * @author Sharmarke Aden
 */
public class UserSerializer implements Serializer<User> {

    private final EncoderFactory ENCODER_FACTORY = EncoderFactory.get();
    private final DecoderFactory DECODER_FACTORY = DecoderFactory.get();

    @Override
    public void serialize(DataOutput out, User value) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BinaryEncoder encoder = ENCODER_FACTORY.binaryEncoder(stream, null);
        DatumWriter<User> writer = new SpecificDatumWriter<>(User.getClassSchema());
        writer.write(value, encoder);
        encoder.flush();
        out.write(stream.toByteArray());
    }

    @Override
    public User deserialize(DataInput in, int available) throws IOException {
        byte[] buffer = new byte[available];
        in.readFully(buffer);
        DatumReader<User> reader = new SpecificDatumReader<>(User.getClassSchema());
        BinaryDecoder decoder = DECODER_FACTORY.binaryDecoder(buffer, null);

        return reader.read(null, decoder);
    }

    @Override
    public int fixedSize() {
        return -1;
    }

}
