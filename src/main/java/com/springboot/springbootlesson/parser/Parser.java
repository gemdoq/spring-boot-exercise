package com.springboot.springbootlesson.parser;

public interface Parser <T> {
    T parse(String str);
}
