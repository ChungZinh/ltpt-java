/*
 * @ (#) $NAME.java         2/26/2024
 *
 * Copyright (c) 2024 IUH.
 *
 */

package handle;

import entity.Address;
import entity.Person;
import entity.PhoneNumber;
import jakarta.json.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
 * @description: ...
 * @author: Vinh Trung Pham
 * @studentID: 20119821
 * @date: 2/26/2024
 */


/*
 Use The Object Model API to read the data from the file and return the Person object
 data: {
    "firstName": "John",
    "lastName": "Smith",
    "age": 25,
    "address": {
        "streetAddress": "21 2nd Street",
        "city": "New York",
        "state": "NY",
        "postalCode": 10021
    },
    "phoneNumbers": [
        {
            "type": "home",
            "number": "212 555-1234"
        },
        {
            "type": "fax",
            "number": "646 555-4567"
        }
    ]
}
 */
public class ObjectModelAPI {
    public static Person readFromFile(String fileName) {
        Person p = null;
        Address add = null;
        try (
                JsonReader reader = Json.createReader(new FileReader(fileName));
        ) {
            JsonObject jo = reader.readObject();
            if (jo != null) {
                p = new Person();
                p.setFirstName(jo.getString("firstName"));
                p.setLastName(jo.getString("lastName"));
                p.setAge(jo.getInt("age"));


                JsonObject joAdd = jo.getJsonObject("address");
                System.out.println(joAdd.getString("streetAddress"));
                add = new Address();
                add.setStreetAddress(joAdd.getString("streetAddress"));
                add.setCity(joAdd.getString("city"));
                add.setState(joAdd.getString("state"));
                add.setPostalCode(joAdd.getInt("postalCode"));

                System.out.println(add);

                p.setAddress(add);

                List<PhoneNumber> ph = new ArrayList<>();

                PhoneNumber phone = new PhoneNumber();

                jo.getJsonArray("phoneNumbers").forEach(v -> {
                    JsonObject joPh = (JsonObject) v;
                    phone.setType(joPh.getString("type"));
                    phone.setNumber(joPh.getString("number"));
                    ph.add(phone);
                });

                p.setPhoneNumbers(ph);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public static List<Person> readFromFile1(String fileName) {
        List<Person> p = new ArrayList<>();

        try (
                JsonReader reader = Json.createReader(new FileReader(fileName));
        ) {
            JsonArray ja = reader.readArray();

            if(ja != null){
                ja.forEach(v -> {

                    JsonObject jo = (JsonObject) v;
                    Person person = new Person();
                    person.setFirstName(jo.getString("firstName"));
                    person.setLastName(jo.getString("lastName"));
                    person.setAge(jo.getInt("age"));

                    JsonObject joAdd = jo.getJsonObject("address");
                    Address add = new Address();
                    add.setStreetAddress(joAdd.getString("streetAddress"));
                    add.setCity(joAdd.getString("city"));
                    add.setState(joAdd.getString("state"));
                    add.setPostalCode(joAdd.getInt("postalCode"));

                    person.setAddress(add);

                    List<PhoneNumber> ph = new ArrayList<>();

                    jo.getJsonArray("phoneNumbers").forEach(vl -> {
                        JsonObject joPh = (JsonObject) vl;
                        PhoneNumber phone = new PhoneNumber();
                        phone.setType(joPh.getString("type"));
                        phone.setNumber(joPh.getString("number"));
                        ph.add(phone);
                    });

                    person.setPhoneNumbers(ph);
                    p.add(person);

                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
