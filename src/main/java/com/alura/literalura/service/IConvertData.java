package com.alura.literalura.service;

public interface IConvertData {
    <T> T parseJsonToObject(String json, Class<T> targetClass);
}
