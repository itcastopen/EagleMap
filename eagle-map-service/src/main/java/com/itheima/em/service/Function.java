package com.itheima.em.service;

public interface Function<T, E> {

    T callback(E e);

}