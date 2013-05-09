package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import model.Crime;
import model.District;
import model.Info;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import utilities.geo.Convertor;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class UsersReport extends Controller{

	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result reportHelp(){
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("expecting json data");
		} else {
			Crime crime = new Crime();
			
			String wktPoint = Convertor.pointFromCoordinates(json.findPath("lat").asLong(), json.findPath("lon").asLong());
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
			
			for(District d : districts){
				if(d.getBounds().contains(geom)){
					crime.setDistrict(d);
					break;
				}
			}
			
			crime.setFlag("help");
			//TODO put here some sending by websocket to the police office

			JPA.em().persist(crime);

			return ok("result help");
		}
	}
	
	
	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result reportDanger(){
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("expecting json data");
		} else {
			Crime crime = new Crime();
			
			String wktPoint = Convertor.pointFromCoordinates(json.findPath("lat").asLong(), json.findPath("lon").asLong());
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
			
			crime.setDescription(json.findPath("description").getTextValue());
			
			Query query = JPA.em().createQuery("SELECT d FROM  District d");
			List<District> districts = query.getResultList();
			
			for(District d : districts){
				if(d.getBounds().contains(geom)){
					crime.setDistrict(d);
					break;
				}
			}
			
			crime.setFlag("danger");
			//TODO put here some sending by websocket to the police office

			JPA.em().persist(crime);

			return ok("result danger");
		}
	}
	
	
	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result reportDetails(){
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("expecting json data");
		} else {
			Crime crime = new Crime();
			
			String wktPoint = Convertor.pointFromCoordinates(json.findPath("lat").asLong(), json.findPath("lon").asLong());
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
			
			crime.setDescription(json.findPath("description").getTextValue());
			try {
				crime.setPhoto(json.findPath("photo").getBinaryValue());			//TODO deal with posibility being null here
			} catch (IOException e) {
				return badRequest("invalid photo");
			}	
			
			Query query = JPA.em().createQuery("SELECT d FROM  District d");
			List<District> districts = query.getResultList();
			
			for(District d : districts){
				if(d.getBounds().contains(geom)){
					crime.setDistrict(d);
					break;
				}
			}
			
			crime.setFlag("details");
			//TODO put here some sending by websocket to the police office

			JPA.em().persist(crime);

			return ok("result details");
		}
	}
	
	
	
	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result reportPhoto(){
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("expecting json data");
		} else {
			Crime crime = new Crime();
			
			String wktPoint = Convertor.pointFromCoordinates(json.findPath("lat").asLong(), json.findPath("lon").asLong());
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
			
			crime.setDescription(json.findPath("description").getTextValue());
			try {
				crime.setPhoto(json.findPath("photo").getBinaryValue());			//TODO solve photo uploading
			} catch (IOException e) {
				return badRequest("invalid photo");
			}					
			
			Query query = JPA.em().createQuery("SELECT d FROM  District d");
			List<District> districts = query.getResultList();
			
			for(District d : districts){
				if(d.getBounds().contains(geom)){
					crime.setDistrict(d);
					break;
				}
			}
			
			crime.setFlag("photo");
			//TODO put here some sending by websocket to the police office

			JPA.em().persist(crime);

			return ok("result photo");
		}
		
	}
	
	
	
	@Transactional(readOnly=true)
	public static Result getInfo(long lat, long lon){
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
		
		for(District d : districts){
			if(d.getBounds().contains(geom)){
				district = d;
				break;
			}
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