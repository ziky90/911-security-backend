// @SOURCE:/Users/zikesjan/Documents/workspace/911security-backend/conf/routes
// @HASH:8a69bd762f1e6640d0a64a868c3dccf2d68a179a
// @DATE:Sun May 12 15:36:41 CEST 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_authenticateDistrict1 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("authenticate/district/"))))
        

// @LINE:8
private[this] lazy val controllers_Application_authenticateAdmin2 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("authenticate/admin/"))))
        

// @LINE:11
private[this] lazy val controllers_UsersReport_reportHelp3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("client/report/help"))))
        

// @LINE:12
private[this] lazy val controllers_UsersReport_reportDanger4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("client/report/danger"))))
        

// @LINE:13
private[this] lazy val controllers_UsersReport_reportDetails5 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("client/report/details"))))
        

// @LINE:14
private[this] lazy val controllers_UsersReport_reportPhoto6 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("client/report/photo"))))
        

// @LINE:15
private[this] lazy val controllers_UsersReport_getInfo7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("client/info/"),DynamicPart("lat", """[^/]+"""),StaticPart("/"),DynamicPart("lon", """[^/]+"""))))
        

// @LINE:18
private[this] lazy val controllers_UserInformationRequest_getInfoFeed8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("client/news"))))
        

// @LINE:21
private[this] lazy val controllers_AdminCommunication_createDistrict9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/district/"))))
        

// @LINE:22
private[this] lazy val controllers_AdminCommunication_modifyDistrict10 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/district/"))))
        

// @LINE:23
private[this] lazy val controllers_AdminCommunication_deleteDistrict11 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/district/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:24
private[this] lazy val controllers_AdminCommunication_listAllDistricts12 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/district/"))))
        

// @LINE:25
private[this] lazy val controllers_AdminCommunication_getDistrict13 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/district/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:28
private[this] lazy val controllers_DistrictCommunication_postInformation14 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("district/information/"))))
        

// @LINE:29
private[this] lazy val controllers_DistrictCommunication_updateInformation15 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("district/information/"))))
        

// @LINE:30
private[this] lazy val controllers_DistrictCommunication_deleteInformation16 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("district/information/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:31
private[this] lazy val controllers_DistrictCommunication_getAllInformations17 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("district/"),DynamicPart("idDistrict", """[^/]+"""))))
        

// @LINE:32
private[this] lazy val controllers_DistrictCommunication_getInformation18 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("district/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:33
private[this] lazy val controllers_DistrictCommunication_initWebSocket19 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("district/websocket/"))))
        

// @LINE:34
private[this] lazy val controllers_DistrictCommunication_banUser20 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("district/ban/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:35
private[this] lazy val controllers_DistrictCommunication_archiveCrime21 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("district/archive/"),DynamicPart("id", """[^/]+"""))))
        

// @LINE:38
private[this] lazy val controllers_Assets_at22 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+"""))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """authenticate/district/""","""controllers.Application.authenticateDistrict()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """authenticate/admin/""","""controllers.Application.authenticateAdmin()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """client/report/help""","""controllers.UsersReport.reportHelp()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """client/report/danger""","""controllers.UsersReport.reportDanger()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """client/report/details""","""controllers.UsersReport.reportDetails()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """client/report/photo""","""controllers.UsersReport.reportPhoto()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """client/info/$lat<[^/]+>/$lon<[^/]+>""","""controllers.UsersReport.getInfo(lat:Double, lon:Double)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """client/news""","""controllers.UserInformationRequest.getInfoFeed(lat:Long, lon:Long, oldest:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/district/""","""controllers.AdminCommunication.createDistrict()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/district/""","""controllers.AdminCommunication.modifyDistrict()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/district/$id<[^/]+>""","""controllers.AdminCommunication.deleteDistrict(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/district/""","""controllers.AdminCommunication.listAllDistricts()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/district/$id<[^/]+>""","""controllers.AdminCommunication.getDistrict(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """district/information/""","""controllers.DistrictCommunication.postInformation()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """district/information/""","""controllers.DistrictCommunication.updateInformation()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """district/information/$id<[^/]+>""","""controllers.DistrictCommunication.deleteInformation(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """district/$idDistrict<[^/]+>""","""controllers.DistrictCommunication.getAllInformations(idDistrict:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """district/$id<[^/]+>""","""controllers.DistrictCommunication.getInformation(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """district/websocket/""","""controllers.DistrictCommunication.initWebSocket()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """district/ban/$id<[^/]+>""","""controllers.DistrictCommunication.banUser(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """district/archive/$id<[^/]+>""","""controllers.DistrictCommunication.archiveCrime(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_authenticateDistrict1(params) => {
   call { 
        invokeHandler(controllers.Application.authenticateDistrict(), HandlerDef(this, "controllers.Application", "authenticateDistrict", Nil,"PUT", """""", Routes.prefix + """authenticate/district/"""))
   }
}
        

// @LINE:8
case controllers_Application_authenticateAdmin2(params) => {
   call { 
        invokeHandler(controllers.Application.authenticateAdmin(), HandlerDef(this, "controllers.Application", "authenticateAdmin", Nil,"PUT", """""", Routes.prefix + """authenticate/admin/"""))
   }
}
        

// @LINE:11
case controllers_UsersReport_reportHelp3(params) => {
   call { 
        invokeHandler(controllers.UsersReport.reportHelp(), HandlerDef(this, "controllers.UsersReport", "reportHelp", Nil,"POST", """ possible requests from the client side""", Routes.prefix + """client/report/help"""))
   }
}
        

// @LINE:12
case controllers_UsersReport_reportDanger4(params) => {
   call { 
        invokeHandler(controllers.UsersReport.reportDanger(), HandlerDef(this, "controllers.UsersReport", "reportDanger", Nil,"POST", """""", Routes.prefix + """client/report/danger"""))
   }
}
        

// @LINE:13
case controllers_UsersReport_reportDetails5(params) => {
   call { 
        invokeHandler(controllers.UsersReport.reportDetails(), HandlerDef(this, "controllers.UsersReport", "reportDetails", Nil,"POST", """""", Routes.prefix + """client/report/details"""))
   }
}
        

// @LINE:14
case controllers_UsersReport_reportPhoto6(params) => {
   call { 
        invokeHandler(controllers.UsersReport.reportPhoto(), HandlerDef(this, "controllers.UsersReport", "reportPhoto", Nil,"POST", """""", Routes.prefix + """client/report/photo"""))
   }
}
        

// @LINE:15
case controllers_UsersReport_getInfo7(params) => {
   call(params.fromPath[Double]("lat", None), params.fromPath[Double]("lon", None)) { (lat, lon) =>
        invokeHandler(controllers.UsersReport.getInfo(lat, lon), HandlerDef(this, "controllers.UsersReport", "getInfo", Seq(classOf[Double], classOf[Double]),"GET", """""", Routes.prefix + """client/info/$lat<[^/]+>/$lon<[^/]+>"""))
   }
}
        

// @LINE:18
case controllers_UserInformationRequest_getInfoFeed8(params) => {
   call(params.fromQuery[Long]("lat", None), params.fromQuery[Long]("lon", None), params.fromQuery[Long]("oldest", None)) { (lat, lon, oldest) =>
        invokeHandler(controllers.UserInformationRequest.getInfoFeed(lat, lon, oldest), HandlerDef(this, "controllers.UserInformationRequest", "getInfoFeed", Seq(classOf[Long], classOf[Long], classOf[Long]),"GET", """ loading info requests from client""", Routes.prefix + """client/news"""))
   }
}
        

// @LINE:21
case controllers_AdminCommunication_createDistrict9(params) => {
   call { 
        invokeHandler(controllers.AdminCommunication.createDistrict(), HandlerDef(this, "controllers.AdminCommunication", "createDistrict", Nil,"POST", """ methods available to the system admin """, Routes.prefix + """admin/district/"""))
   }
}
        

// @LINE:22
case controllers_AdminCommunication_modifyDistrict10(params) => {
   call { 
        invokeHandler(controllers.AdminCommunication.modifyDistrict(), HandlerDef(this, "controllers.AdminCommunication", "modifyDistrict", Nil,"PUT", """""", Routes.prefix + """admin/district/"""))
   }
}
        

// @LINE:23
case controllers_AdminCommunication_deleteDistrict11(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.AdminCommunication.deleteDistrict(id), HandlerDef(this, "controllers.AdminCommunication", "deleteDistrict", Seq(classOf[Long]),"DELETE", """""", Routes.prefix + """admin/district/$id<[^/]+>"""))
   }
}
        

// @LINE:24
case controllers_AdminCommunication_listAllDistricts12(params) => {
   call { 
        invokeHandler(controllers.AdminCommunication.listAllDistricts(), HandlerDef(this, "controllers.AdminCommunication", "listAllDistricts", Nil,"GET", """""", Routes.prefix + """admin/district/"""))
   }
}
        

// @LINE:25
case controllers_AdminCommunication_getDistrict13(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.AdminCommunication.getDistrict(id), HandlerDef(this, "controllers.AdminCommunication", "getDistrict", Seq(classOf[Long]),"GET", """""", Routes.prefix + """admin/district/$id<[^/]+>"""))
   }
}
        

// @LINE:28
case controllers_DistrictCommunication_postInformation14(params) => {
   call { 
        invokeHandler(controllers.DistrictCommunication.postInformation(), HandlerDef(this, "controllers.DistrictCommunication", "postInformation", Nil,"POST", """ methods available to the particular police districts""", Routes.prefix + """district/information/"""))
   }
}
        

// @LINE:29
case controllers_DistrictCommunication_updateInformation15(params) => {
   call { 
        invokeHandler(controllers.DistrictCommunication.updateInformation(), HandlerDef(this, "controllers.DistrictCommunication", "updateInformation", Nil,"PUT", """""", Routes.prefix + """district/information/"""))
   }
}
        

// @LINE:30
case controllers_DistrictCommunication_deleteInformation16(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.DistrictCommunication.deleteInformation(id), HandlerDef(this, "controllers.DistrictCommunication", "deleteInformation", Seq(classOf[Long]),"DELETE", """""", Routes.prefix + """district/information/$id<[^/]+>"""))
   }
}
        

// @LINE:31
case controllers_DistrictCommunication_getAllInformations17(params) => {
   call(params.fromPath[Long]("idDistrict", None)) { (idDistrict) =>
        invokeHandler(controllers.DistrictCommunication.getAllInformations(idDistrict), HandlerDef(this, "controllers.DistrictCommunication", "getAllInformations", Seq(classOf[Long]),"GET", """""", Routes.prefix + """district/$idDistrict<[^/]+>"""))
   }
}
        

// @LINE:32
case controllers_DistrictCommunication_getInformation18(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.DistrictCommunication.getInformation(id), HandlerDef(this, "controllers.DistrictCommunication", "getInformation", Seq(classOf[Long]),"GET", """""", Routes.prefix + """district/$id<[^/]+>"""))
   }
}
        

// @LINE:33
case controllers_DistrictCommunication_initWebSocket19(params) => {
   call { 
        invokeHandler(controllers.DistrictCommunication.initWebSocket(), HandlerDef(this, "controllers.DistrictCommunication", "initWebSocket", Nil,"GET", """""", Routes.prefix + """district/websocket/"""))
   }
}
        

// @LINE:34
case controllers_DistrictCommunication_banUser20(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.DistrictCommunication.banUser(id), HandlerDef(this, "controllers.DistrictCommunication", "banUser", Seq(classOf[Long]),"GET", """""", Routes.prefix + """district/ban/$id<[^/]+>"""))
   }
}
        

// @LINE:35
case controllers_DistrictCommunication_archiveCrime21(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.DistrictCommunication.archiveCrime(id), HandlerDef(this, "controllers.DistrictCommunication", "archiveCrime", Seq(classOf[Long]),"GET", """""", Routes.prefix + """district/archive/$id<[^/]+>"""))
   }
}
        

// @LINE:38
case controllers_Assets_at22(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}
    
}
        