
package JSON;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

// TODO: Auto-generated Javadoc
/**
 * The Class Juego.
 */
public class Juego implements JSONStreamAware{
	
	/** The name. */
	private String name;
	
	/** The fichas. */
	private int fichas;
	 
	
	/**
	 * Instantiates a new juego.
	 *
	 * @param name the name
	 * @param fichas the fichas
	 */
	public Juego(String name, int fichas) {
		this.name = name;
		this.fichas = fichas;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the fichas.
	 *
	 * @return the fichas
	 */
	public int getFichas() {
		return fichas;
	}

	/**
	 * Sets the fichas.
	 *
	 * @param fichas the new fichas
	 */
	public void setFichas(int fichas) {
		this.fichas = fichas;
	}

	/* (non-Javadoc)
	 * @see org.json.simple.JSONStreamAware#writeJSONString(java.io.Writer)
	 */
	@Override
	public void writeJSONString(Writer out) throws IOException {
		//darle formato al JSON
		LinkedHashMap<String, String> obj = new LinkedHashMap<String, String>();
		obj.put("Name", name);
		obj.put("Fichas", String.valueOf(fichas));
		JSONValue.writeJSONString(obj, out);
	}

	
}