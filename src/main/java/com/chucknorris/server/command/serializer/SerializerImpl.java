package com.chucknorris.server.command.serializer;

import com.google.gson.Gson;

public class SerializerImpl<T> implements Serializer<T> {
	private static final Gson gson = new Gson();

	@Override
	public String serialize(T object) {
		return gson.toJson(object);
	}

	@Override
	public T serialize(String json, Class classToCast) {
		return (T) gson.fromJson(json, classToCast);
	}

}
