package com.agodademo.manager;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.core.Response;

import com.agodademo.model.KeyModel;

/**
 * @author Isuru Jayakantha
 *
 */
public class KeyManager {
	private int rateLimit = 0;
	private int suspendTime = 0;
	private static KeyManager instance;
	private static ConcurrentHashMap<String, KeyModel> keys;
	private Response successResp = Response.status(200).entity("key is invalid").build();

	private KeyManager() {
		keys = new ConcurrentHashMap<>();
	}

	public void addKey(String key, KeyModel value) {
		keys.put(key, value);
	}

	public static synchronized KeyManager getInstance(int rateLimit, int suspendTime) {
		if (instance == null) {
			instance = new KeyManager();
			instance.rateLimit = rateLimit;
			instance.suspendTime = suspendTime;
		}
		return instance;
	}

	public KeyModel getKey(String key) {
		return keys.get(key);
	}

	public boolean containsKey(String key) {
		return keys.containsKey(key);
	}

	public Response validateKey(String key) {
		if (key != null && !key.isEmpty() && containsKey(key)) {
			KeyModel model = getKey(key);
			Date date = model.getDate();
			// Get msec from each, and subtract.
			long diff = new Date().getTime() - date.getTime();
			long diffSeconds = diff / 1000;
			System.out.println("TIME : " + diffSeconds);
			int callCount = model.getCallCount();
			if (diffSeconds >= rateLimit) {
				if (!model.isSuspended()) {
					// hold for 5 min
					System.out.println("HOLD FOR 5 MIN");
					model.setSuspended(true);
					return Response.status(403).entity("key is suspended for 5 min").build();
				} else if (diffSeconds >= suspendTime + rateLimit) {
					System.out.println("KEY AVAILABLE");
					model.setSuspended(false);
					model.setDate(new Date());
					return successResp;
				}
			} else if (callCount > 0) {
				model.setCallCount(++callCount);
				model.setDate(new Date());
				return successResp;
			}

			return successResp;
		} else {
			return Response.status(403).entity("key is invalid").build();
		}
	}

}
