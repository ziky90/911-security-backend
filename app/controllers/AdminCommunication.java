package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import model.Crime;
import model.District;
import model.Info;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.mindrot.jbcrypt.BCrypt;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import sun.misc.BASE64Encoder;
import utilities.Point;
import utilities.geo.Convertor;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import controllers.security.Secured;

@Security.Authenticated(Secured.class)
public class AdminCommunication extends Controller {

	/**
	 * method dealing with the district creation
	 * 
	 * @return id of the district
	 */
	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result createDistrict() {
		if (Secured.isAdmin()) {
			JsonNode json = request().body().asJson();

			if (json == null) {
				return badRequest("expecting json data");
			} else {

				District district = new District();
				district.setName(json.findPath("name").getTextValue());
				district.setDescription(json.findPath("description")
						.getTextValue());

				List<Point> points = new ArrayList<Point>();
				WKTReader fromText = new WKTReader();
				Geometry geom = null;

				// Retrieving the set of points
				for (JsonNode jn : json.findPath("points")) {
					points.add(new Point(jn.findPath("lat").asDouble(), jn
							.findPath("lon").asDouble()));
				}
				String wktPolygon = Convertor.pointsToPolygon(points);
				try {
					geom = fromText.read(wktPolygon);
				} catch (ParseException e) {
					return badRequest("not a polygon 1: " + wktPolygon);
				}
				if (!geom.getGeometryType().equals("Polygon")) {
					return badRequest("not a polygon 2");
				}

				district.setBounds((Polygon) geom);
				district.setPassword(BCrypt.hashpw(json.findPath("password")
						.getTextValue(), BCrypt.gensalt()));

				// System.out.println(BCrypt.checkpw(json.findPath("password").getTextValue(),
				// district.getPassword()));

				JPA.em().persist(district);

				// TODO return new district id
				return ok("result is" + district.getId());
			}
		}
		return forbidden("you do not have right to do this");
	}

	/**
	 * method for modifying the district in the DB
	 * 
	 * @return
	 */
	@Transactional
	@BodyParser.Of(BodyParser.Json.class)
	public static Result modifyDistrict() {
		if (Secured.isAdmin()) {
			JsonNode json = request().body().asJson();
			if (json == null) {
				return badRequest("expecting json data");
			} else {
				District district = JPA.em().find(District.class,
						json.findPath("id").asLong());
				district.setName(json.findPath("name").getTextValue());
				district.setDescription(json.findPath("description")
						.getTextValue());

				List<Point> points = new ArrayList<Point>();
				WKTReader fromText = new WKTReader();
				Geometry geom = null;

				// Retrieving the set of points
				for (JsonNode jn : json.findPath("points")) {
					points.add(new Point(jn.findPath("lat").asDouble(), jn
							.findPath("lon").asDouble()));
				}
				String wktPolygon = Convertor.pointsToPolygon(points);
				try {
					geom = fromText.read(wktPolygon);
				} catch (ParseException e) {
					return badRequest();
				}
				if (!geom.getGeometryType().equals("Polygon")) {
					return badRequest();
				}

				district.setBounds((Polygon) geom);

				district.setPassword(BCrypt.hashpw(json.findPath("password")
						.getTextValue(), BCrypt.gensalt()));

				JPA.em().persist(district);
				// TODO return the id of the district
				return ok("modify district");
			}
		}
		return forbidden("you do not have right to do this");
	}

	/**
	 * method for deleting the district from the DB
	 * 
	 * @return
	 */
	// TODO security is very important here
	@Transactional
	public static Result deleteDistrict(long id) {
		if (Secured.isAdmin()) {
			District district = JPA.em().find(District.class, id);
			JPA.em().remove(district);

			return ok("district deleted");
		}
		return forbidden("you do not have right to do this");

	}

	/**
	 * method for listing all the districts
	 * 
	 * @return districts wrapped in the json
	 */
	@Transactional(readOnly = true)
	public static Result listAllDistricts() {
		if (Secured.isAdmin()) {
			Query query = JPA.em().createQuery("SELECT d FROM  District d");
			List<District> districtsDB = query.getResultList();

			ObjectNode result = Json.newObject();

			ArrayNode districts = result.putArray("districts");
			for (District r : districtsDB) {
				ObjectNode district = districts.addObject();
				district.put("id", r.getId());
				district.put("name", r.getName());
				district.put("description", r.getDescription());

				ArrayNode points = district.putArray("points");

				List<Point> pts = Convertor.polygonToPoints(r.getBounds());
				for (Point p : pts) {
					ObjectNode on = points.addObject();
					on.put("lat", p.getLatiude());
					on.put("lon", p.getLongtitude());
				}
				ArrayNode infos = result.putArray("informations");
				for (Info i : r.getInfos()) {
					ObjectNode information = infos.addObject();
					information.put("id", i.getId());
					information.put("text", i.getTextInformation());
				}
				ArrayNode crimes = result.putArray("crimes");
				for (Crime c : r.getCrimes()) {
					ObjectNode crime = crimes.addObject();
					crime.put("id", c.getId());
					crime.put("text", c.getFlag());
					crime.put("description", c.getDescription());
					if (c.getPhoto() != null) {
						BASE64Encoder encoder = new BASE64Encoder();
						String image = encoder.encode(c.getPhoto());
						crime.put("photo", image);
					}else{
						crime.put("photo", "");
					}
				}
			}
			// result.p

			return ok(result);
		}
		return forbidden("you do not have right to do this");
	}

	/**
	 * method for district from the DB retrieval
	 * 
	 * @param id
	 *            of the district
	 * 
	 * @return district wrapped in the json
	 */
	@Transactional(readOnly = true)
	public static Result getDistrict(Long id) {
		if (Secured.isAdmin()) {
			District district = JPA.em().find(District.class, id);

			ObjectNode result = Json.newObject();

			result.put("id", district.getId());
			result.put("name", district.getName());
			result.put("description", district.getDescription());

			ArrayNode points = result.putArray("points");

			List<Point> pts = Convertor.polygonToPoints(district.getBounds());
			for (Point p : pts) {
				ObjectNode on = points.addObject();
				on.put("lat", p.getLatiude());
				on.put("lon", p.getLongtitude());
			}

			ArrayNode infos = result.putArray("informations");
			for (Info i : district.getInfos()) {
				ObjectNode information = infos.addObject();
				information.put("id", i.getId());
				information.put("text", i.getTextInformation());
			}

			ArrayNode crimes = result.putArray("crimes");
			for (Crime c : district.getCrimes()) {
				ObjectNode crime = crimes.addObject();
				crime.put("id", c.getId());
				crime.put("text", c.getFlag());
				crime.put("description", c.getDescription());
				if (c.getPhoto() != null) {
					BASE64Encoder encoder = new BASE64Encoder();
					String image = encoder.encode(c.getPhoto());
					crime.put("photo", image);
				}else{
					crime.put("photo", "");
				}
			}

			return ok(result);
		}
		return forbidden("you do not have right to do this");
	}

}
