package com.codeup.springblog;
import javax.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {
//    ================================= JPA TABLE CREATION PRACTICE
//
//Create a model class (Dog) with proper annotations to make a dogs table with the following properties:
//
//  1) id
    @Id
    @Column(columnDefinition = "int(11) unsigned NOT NULL AUTO_INCREMENT")
    private int id;
//  2) age
    @Column(columnDefinition = "TINYINT(3) UNSIGNED NOT NULL")
    private short age;
//  3) name
    @Column(length = 200, nullable = false)
    private String name;
//  4) resideState
    @Column(columnDefinition = "CHAR(2) DEFAULT 'XX'")
    private String resideState;
//
//Be sure to include getters and setters and constructors and needed annotations.
//

    public Dog(int id, short age, String name, String resideState) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.resideState = resideState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResideState() {
        return resideState;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }

    //When running the 'SHOW CREATE TABLE dogs', you should have the following schema:
//
//  CREATE TABLE `dogs` (
//    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
//    `age` tinyint(3) unsigned NOT NULL,
//    `name` varchar(200) NOT NULL,
//    `reside_state` char(2) DEFAULT 'XX',
//    PRIMARY KEY (`id`),
//    UNIQUE KEY `UK_?????????????????` (`age`)
//  ) ENGINE=<WHATEVER_VALUE_HERE> DEFAULT CHARSET=utf8
//
//Once you have the correct schema, use the following seed data to populate the table with default values:
//
//USE put_the_name_of_your_DB_here;
//
//INSERT INTO dogs (age, name, reside_state)
//VALUES
//  (2, 'Chuck', 'TX'),
//  (5, 'Fred', 'OH'),
//  (3, 'Bud', 'TN'),
//  (10, 'Bailey', 'AL'),
//  (11, 'Lexie', 'TX'),
//  (1, 'Snickers', 'TX'),
//  (6, 'Red', 'FL'),
//  (8, 'Barney', 'CA'),
//  (12, 'Bowser', 'TX');
//
//
//BONUS: add a named composite unique key of the combination of the name and reside_state columns...
//
//  CREATE TABLE `dogs` (
//    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
//    `age` tinyint(3) unsigned NOT NULL,
//    `name` varchar(200) NOT NULL,
//    `reside_state` char(2) DEFAULT 'XX',
//    PRIMARY KEY (`id`),
//    UNIQUE KEY `UK_?????????????????` (`age`),
//    UNIQUE KEY `UK_name_AND_reside_state` (`name`,`reside_state`)
//  ) ENGINE=MyISAM DEFAULT CHARSET=utf8
}
