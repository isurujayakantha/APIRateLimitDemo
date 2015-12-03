package com.agodademo.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.agodademo.comparator.RecordComparator;
import com.agodademo.manager.KeyManager;
import com.agodademo.model.KeyModel;
import com.agodademo.model.Record;
import com.agodademo.util.CSVReader;
import com.agodademo.util.PropertyLoader;

/**
 * @author Isuru Jayakantha
 *
 */
@Path("/services")
public class ManagementService {
	// rateLimit : 10 seconds
	// suspendTime : 300 seconds - 5 min
	private static KeyManager keyManager;
	private static List<Record> records;

	static {
		Properties props = PropertyLoader.getProperty();
		// get the property value and print it out
		System.out.println(props.getProperty("dbPath"));
		System.out.println(props.getProperty("rateLimit"));
		System.out.println(props.getProperty("suspendTime"));

		keyManager = KeyManager.getInstance(Integer.parseInt(props.getProperty("rateLimit")),
				Integer.parseInt(props.getProperty("suspendTime")));

		records = new CSVReader(props.getProperty("dbPath")).loadCSV();
		System.out.println("RECORDS : " + records.size());
	}

	@GET
	@Path("/keys/generateKey")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateKey() {
		String uuid = UUID.randomUUID().toString();
		KeyModel model = new KeyModel();
		model.setUuid(uuid);
		model.setDate(new Date());
		keyManager.addKey(uuid, model);
		return Response.status(200).entity(uuid).build();
	}

	@GET
	@Path("/city/{id}/{sortBy}/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchUserById(@PathParam("id") String id, @PathParam("sortBy") String sortBy,
			@PathParam("key") String key) {
		Response response = keyManager.validateKey(key);
		if (response.getStatus() == 200) {
			System.out.println("SEARCHING");
			if (records == null) {
				return Response.status(200).entity("couldnot find the hotel").build();
			}
			if ("ASC".equalsIgnoreCase(sortBy)) {
				/* Sorting */
				Collections.sort(records, new RecordComparator());
			} else {
				Collections.reverse(records);
			}
			return Response.status(200).entity(records).build();
		}
		return response;
	}

}
