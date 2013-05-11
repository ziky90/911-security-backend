package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import model.Crime;
import model.District;
import model.Info;
import model.operations.UserOperations;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import sun.misc.BASE64Decoder;
import utilities.ObjectNodeCreator;
import utilities.geo.Convertor;
import websocket.WebSocketPool;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class UsersReport extends Controller {

	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result reportHelp() {
		JsonNode json = request().body().asJson();

		if (json == null) {
			return badRequest("expecting json data");
		} else {
			if (UserOperations.isAllowed(json.findPath("uid").asText())) {
				Crime crime = new Crime();

				String wktPoint = Convertor.pointFromCoordinates(
						json.findPath("lat").asDouble(), json.findPath("lon")
								.asDouble());
				WKTReader fromText = new WKTReader();
				Geometry geom = null;

				try {
					geom = fromText.read(wktPoint);
				} catch (ParseException e) {
					return badRequest("parser exception");
				}
				if (!geom.getGeometryType().equals("Point")) {
					return badRequest("not a point");
				}

				crime.setPoint((Point) geom);

				Query query = JPA.em().createQuery("SELECT d FROM  District d");
				List<District> districts = query.getResultList();

				for (District d : districts) {
					if (d.getBounds().contains(geom)) {
						crime.setDistrict(d);
						break;
					}
				}

				if (crime.getDistrict() == null) {
					return notFound("district not found");
				}

				crime.setFlag("help");
				crime.setActual(true);
				
				WebSocketPool.writeToWebSocket(crime.getDistrict().getId(), ObjectNodeCreator.createHelpObjectNode(json.findPath("lat").asDouble(), json.findPath("lon").asDouble()));

				JPA.em().persist(crime);

				return ok("result help");
			} else {
				return forbidden();
			}
		}
	}

	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result reportDanger() {
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("expecting json data");
		} else {
			if (UserOperations.isAllowed(json.findPath("uid").asText())) {
				Crime crime = new Crime();

				String wktPoint = Convertor.pointFromCoordinates(
						json.findPath("lat").asDouble(), json.findPath("lon")
								.asDouble());
				WKTReader fromText = new WKTReader();
				Geometry geom = null;

				try {
					geom = fromText.read(wktPoint);
				} catch (ParseException e) {
					return badRequest("parser exception");
				}
				if (!geom.getGeometryType().equals("Point")) {
					return badRequest("not a point");
				}

				crime.setPoint((Point) geom);

				crime.setDescription(json.findPath("description")
						.getTextValue());

				Query query = JPA.em().createQuery("SELECT d FROM  District d");
				List<District> districts = query.getResultList();

				for (District d : districts) {
					if (d.getBounds().contains(geom)) {
						crime.setDistrict(d);
						break;
					}
				}

				crime.setFlag("danger");
				crime.setActual(true);
				
				WebSocketPool.writeToWebSocket(crime.getDistrict().getId(), ObjectNodeCreator.createDangerObjectNode(json.findPath("lat").asDouble(), json.findPath("lon").asDouble(), json.findPath("description")
						.getTextValue()));

				JPA.em().persist(crime);

				return ok("result danger");
			} else {
				return forbidden();
			}
		}
	}

	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result reportDetails() {
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("expecting json data");
		} else {
			if (UserOperations.isAllowed(json.findPath("uid").asText())) {
				Crime crime = new Crime();

				String wktPoint = Convertor.pointFromCoordinates(
						json.findPath("lat").asDouble(), json.findPath("lon")
								.asDouble());
				WKTReader fromText = new WKTReader();
				Geometry geom = null;

				try {
					geom = fromText.read(wktPoint);
				} catch (ParseException e) {
					return badRequest("parser exception");
				}
				if (!geom.getGeometryType().equals("Point")) {
					return badRequest("not a point");
				}

				crime.setPoint((Point) geom);

				crime.setDescription(json.findPath("description")
						.getTextValue());

				Query query = JPA.em().createQuery("SELECT d FROM  District d");
				List<District> districts = query.getResultList();

				for (District d : districts) {
					if (d.getBounds().contains(geom)) {
						crime.setDistrict(d);
						break;
					}
				}

				crime.setFlag("details");
				crime.setActual(true);
				
				WebSocketPool.writeToWebSocket(crime.getDistrict().getId(), ObjectNodeCreator.createDetailsObjectNode(json.findPath("lat").asDouble(), json.findPath("lon").asDouble(), json.findPath("description")
						.getTextValue()));
				
				JPA.em().persist(crime);

				return ok("result details");
			} else {
				return forbidden();
			}
		}
	}

	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result reportPhoto() {
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("expecting json data");
		} else {
			if (UserOperations.isAllowed(json.findPath("uid").asText())) {
				Crime crime = new Crime();

				String wktPoint = Convertor.pointFromCoordinates(
						json.findPath("lat").asDouble(), json.findPath("lon")
								.asDouble());
				WKTReader fromText = new WKTReader();
				Geometry geom = null;

				try {
					geom = fromText.read(wktPoint);
				} catch (ParseException e) {
					return badRequest("parser exception");
				}
				if (!geom.getGeometryType().equals("Point")) {
					return badRequest("not a point");
				}

				crime.setPoint((Point) geom);

				crime.setDescription(json.findPath("description")
						.getTextValue());
				try {
					BASE64Decoder decoder = new BASE64Decoder();
					byte[] decodedBytes = decoder.decodeBuffer(json.findPath(
							"photo").asText());
					crime.setPhoto(decodedBytes); // TODO solve photo uploading
				} catch (IOException e) {
					return badRequest("invalid photo");
				}

				Query query = JPA.em().createQuery("SELECT d FROM  District d");
				List<District> districts = query.getResultList();

				for (District d : districts) {
					if (d.getBounds().contains(geom)) {
						crime.setDistrict(d);
						break;
					}
				}

				crime.setFlag("photo");
				crime.setActual(true);
				
				WebSocketPool.writeToWebSocket(crime.getDistrict().getId(), ObjectNodeCreator.createPhotoObjectNode(json.findPath("lat").asDouble(), json.findPath("lon").asDouble(), json.findPath("photo").asText()));

				JPA.em().persist(crime);

				return ok("result photo");
			} else {
				return forbidden();
			}
		}

	}

	@Transactional(readOnly = true)
	public static Result getInfo(double lat, double lon) {
		District district = null;

		String wktPoint = Convertor.pointFromCoordinates(lat, lon);
		WKTReader fromText = new WKTReader();
		Geometry geom = null;

		try {
			geom = fromText.read(wktPoint);
		} catch (ParseException e) {
			return badRequest("parser exception");
		}
		if (!geom.getGeometryType().equals("Point")) {
			return badRequest("not a point");
		}

		Query query = JPA.em().createQuery("SELECT d FROM  District d");
		List<District> districts = query.getResultList();

		for (District d : districts) {
			if (d.getBounds().contains(geom)) {
				district = d;
				break;
			}
		}
		if (district == null) {
			return badRequest("no district found for your area");
		}
		ObjectNode result = Json.newObject();
		result.put("name", district.getName());
		result.put("description", district.getDescription());

		ArrayNode infos = result.putArray("informations");
		for (Info i : district.getInfos()) {
			ObjectNode information = infos.addObject();
			information.put("id", i.getId());
			information.put("text", i.getTextInformation());
		}

		return ok(result);
	}
}
