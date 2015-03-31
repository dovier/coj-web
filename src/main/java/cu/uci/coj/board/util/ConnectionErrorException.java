package cu.uci.coj.board.util;

/**
*
* @author Eddy Roberto Morales Perez
*/

public class ConnectionErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String host;
	
	public ConnectionErrorException(String host) {
		super();
		this.host = host;
	}

	String getMessage(String host) {
		return "Error in conection with host " + host;
	}
}
