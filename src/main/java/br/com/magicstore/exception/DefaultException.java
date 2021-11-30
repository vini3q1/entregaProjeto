package br.com.magicstore.exception;

public class DefaultException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1403836782595493937L;

	public DefaultException(String keyMessage, Throwable cause) {
		super(keyMessage, cause);
	}
	
	public DefaultException(String keyMessage) {
		super(keyMessage);
	}
	
}
