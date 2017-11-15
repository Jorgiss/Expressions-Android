import org.json.JSONObject

typealias APIResponseHandler = (successs:Boolean, jsonObject:String?) -> Unit
typealias ResponseHandler = (success:Boolean) -> Unit
typealias JSONObjectResponseHandler = (success:Boolean, jsonObject:String) -> Unit