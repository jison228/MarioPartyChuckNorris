package com.chucknorris.server.command.serializer;

public interface Serializer<T> {
	String serialize(T object);

	T serialize(String json, Class classToCast);
}
