Simple test case that demonstrates MapDB serialization issue. 

* UserSerializer - A class that implements org.mapdb.Serializer. Under the hood this class delegates serialization
to avro encoder/decoders.
* UserSerilizerNGTest - Test class that validates avro encoding/decoding works as espected. 
* MapDBSerializationNGTest - A test class that tries to use mapdb custom serialization feature.

You need to first build the project to generate the avro User test bean:
> mvn install

Once the avro User test bean is generated the test will be executed and you should see test failure.
