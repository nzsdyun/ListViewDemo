package com.common.adapter.com.common.entity;

/**
 * Simple Person class
 * @author sky
 */
public class Person {
    private int headId;
    private String name;

    public Person(int headId, String name) {
        this.headId = headId;
        this.name = name;
    }

    public int getHeadId() {
        return headId;
    }

    public String getName() {

        return name;
    }
}
