
package JSON;

import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONArray;

import Msg.Message;

// TODO: Auto-generated Javadoc
/**
 * The Class Encode takes the instance of Message and encode it into a JSONarray.
 */
public class Encode {
	//acceso a los datos (atributos) de la clase 
	/**
	 * arrayData the Objetct message recived and creates a Json array with that data.
	 *
	 * @param Instances of Message
	 * @return JSONarray
	 */
	//Almacenar en un JSONArray
	public JSONArray arrayData(Message obj) {
		JSONArray array = new JSONArray();
			array.add(obj);
		return array;
	}



}