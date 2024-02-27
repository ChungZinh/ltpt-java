/*
 * @ (#) $NAME.java         2/26/2024
 *
 * Copyright (c) 2024 IUH.
 *
 */

package app;

import entity.Person;
import handle.ObjectModelAPI;

import java.util.List;

/*
 * @description: ...
 * @author: Vinh Trung Pham
 * @studentID: 20119821
 * @date: 2/26/2024
 */
public class Main {
    public static void main(String[] args) {
        Person p = ObjectModelAPI.readFromFile("data/person.json");
        System.out.println(p);
        System.out.println("=====================================");
        List<Person> ps = ObjectModelAPI.readFromFile1("data/people.json");
        System.out.println(ps);
    }
}
