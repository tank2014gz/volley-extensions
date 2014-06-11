package com.navercorp.volleyextensions.volleyer.builder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.android.volley.RequestQueue;
import com.navercorp.volleyextensions.volleyer.DefaultVolleyerContextFactory;
import com.navercorp.volleyextensions.volleyer.VolleyerContext;
import com.navercorp.volleyextensions.volleyer.http.HttpContent;
import com.navercorp.volleyextensions.volleyer.http.HttpMethod;

public class RequestBuilderTest {
	RequestQueue requestQueue = mock(RequestQueue.class);

	@Test(expected=NullPointerException.class)
	public void requestBuilderConstructorShouldThrowNpeWhenVolleyerContextIsNull() {
		// Given
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		VolleyerContext nullVolleyerContext = null;
		
		// When & Then
		new RequestBuilder(nullVolleyerContext, url, method);
	}

	@Test(expected=NullPointerException.class)
	public void requestBuilderConstructorShouldThrowNpeWhenUrlIsNull() {
		// Given
		String nullUrl = null;
		HttpMethod method = HttpMethod.GET;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		
		// When & Then
		new RequestBuilder(volleyerContext, nullUrl, method);
	}

	@Test(expected=NullPointerException.class)
	public void requestBuilderConstructorShouldThrowNpeWhenHttpMethodIsNull() {
		// Given
		String url = "test";
		HttpMethod nullMethod = null;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		
		// When & Then
		new RequestBuilder(volleyerContext, url, nullMethod);
	}

	@Test(expected=NullPointerException.class)
	public void addHeaderMethodShouldThrowNpeWhenKeyIsNull() {
		// Given
		String nullKey = null;
		String value = "test";
		
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		RequestBuilder builder = new RequestBuilder(volleyerContext, url, method);
		
		// When & Then
		builder.addHeader(nullKey, value);
	}

	@Test(expected=NullPointerException.class)
	public void addHeaderMethodShouldThrowNpeWhenValueIsNull() {
		// Given
		String key = "test";
		String nullValue = null;
		
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		RequestBuilder builder = new RequestBuilder(volleyerContext, url, method);
		
		// When & Then
		builder.addHeader(key, nullValue);
	}

	@Test
	public void addHeaderMethodShouldReturnSameInstanceOfBuilder() {
		// Given
		String key = "testKey";
		String value = "testValue";
		
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		RequestBuilder builder = new RequestBuilder(volleyerContext, url, method);
		
		// When
		RequestBuilder newBuilder = builder.addHeader(key, value);
		// Then
		assertTrue(builder == newBuilder);
	}

	@Test
	public void afterRequestMethodShouldReturnAnActualInstance() {
		// Given
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		RequestBuilder builder = new RequestBuilder(volleyerContext, url, method);
		
		// When
		ResponseBuilder<String> responseBuilder = builder.setTargetClass(String.class);
		// Then
		assertNotNull(responseBuilder);
	}

	@Test(expected=IllegalStateException.class)
	public void addHeaderMethodShouldThrowIllegalStateExceptionWhenAfterRequestMethodIsAlreadyCalled() {
		// Given
		String key = "testKey";
		String value = "testValue";
		
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		RequestBuilder builder = new RequestBuilder(volleyerContext, url, method);
		
		// When & Then
		builder.setTargetClass(String.class);
		builder.addHeader(key, value);
	}
	@Test(expected = NullPointerException.class)
	public void setTargetClassMethodShouldThrowNpeWhenTargetClassIsNull() {
		// Given
		String key = "testKey";
		String value = "testValue";
		
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		RequestBuilder builder = new RequestBuilder(volleyerContext, url, method);
		Class<?> clazz = null;
		// When & Then
		builder.setTargetClass(clazz);
	}

	@Test
	public void setTargetClassMethodShouldReturnAnActualInstanceWhenTargetClassIsNotNull() {
		// Given
		String key = "testKey";
		String value = "testValue";
		
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		RequestBuilder builder = new RequestBuilder(volleyerContext, url, method);
		Class<String> clazz = String.class;
		// When
		ResponseBuilder<String> responseBuilder = builder.setTargetClass(clazz);
		// Then
		assertNotNull(responseBuilder);
	}

	@Test(expected = IllegalStateException.class)
	public void setTargetClassMethodShouldThrowIllegalStateExceptionWhenSetTargetClassMethodIsCalledAgain() {
		// Given
		String key = "testKey";
		String value = "testValue";
		
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		VolleyerContext volleyerContext = DefaultVolleyerContextFactory.create(requestQueue);
		RequestBuilder builder = new RequestBuilder(volleyerContext, url, method);
		Class<String> clazz = String.class;
		// When
		builder.setTargetClass(clazz);
		// Then
		builder.setTargetClass(clazz);
	}	
}
