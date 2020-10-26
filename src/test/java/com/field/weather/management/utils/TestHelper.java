package com.field.weather.management.utils;

import java.util.Random;
import java.util.UUID;

import com.field.weather.management.entity.Boundaries;
import com.field.weather.management.entity.Field;
import com.field.weather.management.entity.GeoJson;
import com.field.weather.management.entity.Geometry;
import com.field.weather.management.entity.Properties;

/*import random.images.entity.User;*/

public class TestHelper {
	
	private static String coordinates = "[[[-5.553604888914691, 33.88229680420605],[-5.5516736984239685, 33.88229680420605],[-5.5516736984239685, 33.88372189858022],[-5.555965232847882, 33.88390003370375],[-5.555965232847882, 33.88229680420605],[-5.553604888914691, 33.88229680420605]]";
	
	public static Field buildField() {
		String uuid = UUID.randomUUID().toString();

		return Field
					.builder()
						.name("name-" + uuid)
						.id(uuid)
						.countryCode("IA")
						.boundaries(Boundaries
									.builder()
									.id(UUID.randomUUID().toString())
									.geoJson(GeoJson
												.builder()
													.type("Feature")
													.properties(Properties.builder().build())
													.geometry(Geometry
																.builder()
																	.type("Polygon")
																	.coordinates(coordinates)
																.build())
												.build())
									.build())
					.build();
	}

	public static Field buildUserWithId() {
		Random random = new Random();
		String uuid = UUID.randomUUID().toString();
		return Field
				.builder()
					.fieldid(random.nextLong())
					.name("name-" + uuid)
					.id(uuid)
					.countryCode("IA")
					.boundaries(Boundaries
							.builder()
								.boundaryid(random.nextLong())
								.id(UUID.randomUUID().toString())
								.geoJson(GeoJson
										.builder()
											.id(random.nextLong())
											.type("Feature")
											.properties(Properties.builder().build())
											.geometry(Geometry
														.builder()
															.id(random.nextLong())
															.type("Polygon")
															.coordinates(coordinates)
														.build())
										.build())
							.build())
			.build();
	}

}