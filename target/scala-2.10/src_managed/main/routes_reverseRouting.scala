// @SOURCE:/Users/zikesjan/Documents/workspace/911security-backend/conf/routes
// @HASH:dd33632170cfa68e058c71cd58ee0a10f26d443b
// @DATE:Sat May 11 13:00:15 CEST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:37
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:37
class ReverseAssets {
    

// @LINE:37
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
class ReverseDistrictCommunication {
    

// @LINE:30
def updateInformation(): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "district/information/")
}
                                                

// @LINE:33
def getInformation(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "district/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:29
def postInformation(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "district/information/")
}
                                                

// @LINE:32
def getAllInformations(idDistrict:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "district/" + implicitly[PathBindable[Long]].unbind("idDistrict", idDistrict))
}
                                                

// @LINE:34
def initWebSocket(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "district/websocket/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:31
def deleteInformation(id:Long): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "district/information/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                
    
}
                          

// @LINE:19
class ReverseUserInformationRequest {
    

// @LINE:19
def getInfoFeed(lat:Long, lon:Long, oldest:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "client/news" + queryString(List(Some(implicitly[QueryStringBindable[Long]].unbind("lat", lat)), Some(implicitly[QueryStringBindable[Long]].unbind("lon", lon)), Some(implicitly[QueryStringBindable[Long]].unbind("oldest", oldest)))))
}
                                                
    
}
                          

// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def authenticateDistrict(): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "authenticate/district/")
}
                                                

// @LINE:9
def authenticateUser(): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "authenticate/user/")
}
                                                

// @LINE:8
def authenticateAdmin(): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "authenticate/admin/")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          

// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
class ReverseAdminCommunication {
    

// @LINE:22
def createDistrict(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/district/")
}
                                                

// @LINE:26
def getDistrict(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/district/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:25
def listAllDistricts(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/district/")
}
                                                

// @LINE:24
def deleteDistrict(id:Long): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "admin/district/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:23
def modifyDistrict(): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "admin/district/")
}
                                                
    
}
                          

// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
class ReverseUsersReport {
    

// @LINE:13
def reportDanger(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "client/report/danger")
}
                                                

// @LINE:14
def reportDetails(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "client/report/details")
}
                                                

// @LINE:15
def reportPhoto(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "client/report/photo")
}
                                                

// @LINE:12
def reportHelp(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "client/report/help")
}
                                                

// @LINE:16
def getInfo(lat:Double, lon:Double): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "client/info/" + implicitly[PathBindable[Double]].unbind("lat", lat) + "/" + implicitly[PathBindable[Double]].unbind("lon", lon))
}
                                                
    
}
                          
}
                  


// @LINE:37
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:37
class ReverseAssets {
    

// @LINE:37
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
class ReverseDistrictCommunication {
    

// @LINE:30
def updateInformation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DistrictCommunication.updateInformation",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "district/information/"})
      }
   """
)
                        

// @LINE:33
def getInformation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DistrictCommunication.getInformation",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "district/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:29
def postInformation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DistrictCommunication.postInformation",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "district/information/"})
      }
   """
)
                        

// @LINE:32
def getAllInformations : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DistrictCommunication.getAllInformations",
   """
      function(idDistrict) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "district/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("idDistrict", idDistrict)})
      }
   """
)
                        

// @LINE:34
def initWebSocket : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DistrictCommunication.initWebSocket",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "district/websocket/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:31
def deleteInformation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.DistrictCommunication.deleteInformation",
   """
      function(id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "district/information/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        
    
}
              

// @LINE:19
class ReverseUserInformationRequest {
    

// @LINE:19
def getInfoFeed : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UserInformationRequest.getInfoFeed",
   """
      function(lat,lon,oldest) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "client/news" + _qS([(""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("lat", lat), (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("lon", lon), (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("oldest", oldest)])})
      }
   """
)
                        
    
}
              

// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def authenticateDistrict : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authenticateDistrict",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "authenticate/district/"})
      }
   """
)
                        

// @LINE:9
def authenticateUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authenticateUser",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "authenticate/user/"})
      }
   """
)
                        

// @LINE:8
def authenticateAdmin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authenticateAdmin",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "authenticate/admin/"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              

// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
class ReverseAdminCommunication {
    

// @LINE:22
def createDistrict : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AdminCommunication.createDistrict",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/district/"})
      }
   """
)
                        

// @LINE:26
def getDistrict : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AdminCommunication.getDistrict",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/district/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:25
def listAllDistricts : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AdminCommunication.listAllDistricts",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/district/"})
      }
   """
)
                        

// @LINE:24
def deleteDistrict : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AdminCommunication.deleteDistrict",
   """
      function(id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/district/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:23
def modifyDistrict : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AdminCommunication.modifyDistrict",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/district/"})
      }
   """
)
                        
    
}
              

// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
class ReverseUsersReport {
    

// @LINE:13
def reportDanger : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UsersReport.reportDanger",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "client/report/danger"})
      }
   """
)
                        

// @LINE:14
def reportDetails : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UsersReport.reportDetails",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "client/report/details"})
      }
   """
)
                        

// @LINE:15
def reportPhoto : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UsersReport.reportPhoto",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "client/report/photo"})
      }
   """
)
                        

// @LINE:12
def reportHelp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UsersReport.reportHelp",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "client/report/help"})
      }
   """
)
                        

// @LINE:16
def getInfo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.UsersReport.getInfo",
   """
      function(lat,lon) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "client/info/" + (""" + implicitly[PathBindable[Double]].javascriptUnbind + """)("lat", lat) + "/" + (""" + implicitly[PathBindable[Double]].javascriptUnbind + """)("lon", lon)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:37
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {

// @LINE:37
class ReverseAssets {
    

// @LINE:37
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
class ReverseDistrictCommunication {
    

// @LINE:30
def updateInformation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DistrictCommunication.updateInformation(), HandlerDef(this, "controllers.DistrictCommunication", "updateInformation", Seq(), "PUT", """""", _prefix + """district/information/""")
)
                      

// @LINE:33
def getInformation(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DistrictCommunication.getInformation(id), HandlerDef(this, "controllers.DistrictCommunication", "getInformation", Seq(classOf[Long]), "GET", """""", _prefix + """district/$id<[^/]+>""")
)
                      

// @LINE:29
def postInformation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DistrictCommunication.postInformation(), HandlerDef(this, "controllers.DistrictCommunication", "postInformation", Seq(), "POST", """ methods available to the particular police districts""", _prefix + """district/information/""")
)
                      

// @LINE:32
def getAllInformations(idDistrict:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DistrictCommunication.getAllInformations(idDistrict), HandlerDef(this, "controllers.DistrictCommunication", "getAllInformations", Seq(classOf[Long]), "GET", """""", _prefix + """district/$idDistrict<[^/]+>""")
)
                      

// @LINE:34
def initWebSocket(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DistrictCommunication.initWebSocket(id), HandlerDef(this, "controllers.DistrictCommunication", "initWebSocket", Seq(classOf[Long]), "GET", """""", _prefix + """district/websocket/$id<[^/]+>""")
)
                      

// @LINE:31
def deleteInformation(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.DistrictCommunication.deleteInformation(id), HandlerDef(this, "controllers.DistrictCommunication", "deleteInformation", Seq(classOf[Long]), "DELETE", """""", _prefix + """district/information/$id<[^/]+>""")
)
                      
    
}
                          

// @LINE:19
class ReverseUserInformationRequest {
    

// @LINE:19
def getInfoFeed(lat:Long, lon:Long, oldest:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UserInformationRequest.getInfoFeed(lat, lon, oldest), HandlerDef(this, "controllers.UserInformationRequest", "getInfoFeed", Seq(classOf[Long], classOf[Long], classOf[Long]), "GET", """ loading info requests from client""", _prefix + """client/news""")
)
                      
    
}
                          

// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:7
def authenticateDistrict(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authenticateDistrict(), HandlerDef(this, "controllers.Application", "authenticateDistrict", Seq(), "PUT", """""", _prefix + """authenticate/district/""")
)
                      

// @LINE:9
def authenticateUser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authenticateUser(), HandlerDef(this, "controllers.Application", "authenticateUser", Seq(), "PUT", """""", _prefix + """authenticate/user/""")
)
                      

// @LINE:8
def authenticateAdmin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authenticateAdmin(), HandlerDef(this, "controllers.Application", "authenticateAdmin", Seq(), "PUT", """""", _prefix + """authenticate/admin/""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          

// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
class ReverseAdminCommunication {
    

// @LINE:22
def createDistrict(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AdminCommunication.createDistrict(), HandlerDef(this, "controllers.AdminCommunication", "createDistrict", Seq(), "POST", """ methods available to the system admin """, _prefix + """admin/district/""")
)
                      

// @LINE:26
def getDistrict(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AdminCommunication.getDistrict(id), HandlerDef(this, "controllers.AdminCommunication", "getDistrict", Seq(classOf[Long]), "GET", """""", _prefix + """admin/district/$id<[^/]+>""")
)
                      

// @LINE:25
def listAllDistricts(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AdminCommunication.listAllDistricts(), HandlerDef(this, "controllers.AdminCommunication", "listAllDistricts", Seq(), "GET", """""", _prefix + """admin/district/""")
)
                      

// @LINE:24
def deleteDistrict(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AdminCommunication.deleteDistrict(id), HandlerDef(this, "controllers.AdminCommunication", "deleteDistrict", Seq(classOf[Long]), "DELETE", """""", _prefix + """admin/district/$id<[^/]+>""")
)
                      

// @LINE:23
def modifyDistrict(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AdminCommunication.modifyDistrict(), HandlerDef(this, "controllers.AdminCommunication", "modifyDistrict", Seq(), "PUT", """""", _prefix + """admin/district/""")
)
                      
    
}
                          

// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
class ReverseUsersReport {
    

// @LINE:13
def reportDanger(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UsersReport.reportDanger(), HandlerDef(this, "controllers.UsersReport", "reportDanger", Seq(), "POST", """""", _prefix + """client/report/danger""")
)
                      

// @LINE:14
def reportDetails(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UsersReport.reportDetails(), HandlerDef(this, "controllers.UsersReport", "reportDetails", Seq(), "POST", """""", _prefix + """client/report/details""")
)
                      

// @LINE:15
def reportPhoto(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UsersReport.reportPhoto(), HandlerDef(this, "controllers.UsersReport", "reportPhoto", Seq(), "POST", """""", _prefix + """client/report/photo""")
)
                      

// @LINE:12
def reportHelp(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UsersReport.reportHelp(), HandlerDef(this, "controllers.UsersReport", "reportHelp", Seq(), "POST", """ possible requests from the client side""", _prefix + """client/report/help""")
)
                      

// @LINE:16
def getInfo(lat:Double, lon:Double): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.UsersReport.getInfo(lat, lon), HandlerDef(this, "controllers.UsersReport", "getInfo", Seq(classOf[Double], classOf[Double]), "GET", """""", _prefix + """client/info/$lat<[^/]+>/$lon<[^/]+>""")
)
                      
    
}
                          
}
                  
      