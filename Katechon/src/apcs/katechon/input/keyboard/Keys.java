package apcs.katechon.input.keyboard;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * With this enum I no longer have to deal with KeyEvent.VK_?. Enums are always nicer :)
 * </p>
 * 
 * <p>
 * Currently supports A-Z, 0-9, arrow keys, escape, space, shift, control, alt, 
 * 					  windows button and most other symbols supported by KeyEvent
 * </p>
 * @author Matt
 *
 */
public enum Keys
{
	A		(KeyEvent.VK_A),
	B		(KeyEvent.VK_B),
	C		(KeyEvent.VK_C),
	D		(KeyEvent.VK_D),
	E		(KeyEvent.VK_E),
	F		(KeyEvent.VK_F),
	G		(KeyEvent.VK_G),
	H		(KeyEvent.VK_H),
	I		(KeyEvent.VK_I),
	K		(KeyEvent.VK_K),
	J		(KeyEvent.VK_J),
	L		(KeyEvent.VK_L),
	M		(KeyEvent.VK_M),
	N		(KeyEvent.VK_N),
	O		(KeyEvent.VK_O),
	P		(KeyEvent.VK_P),
	Q		(KeyEvent.VK_Q),
	R		(KeyEvent.VK_R),
	S		(KeyEvent.VK_S),
	T		(KeyEvent.VK_T),
	U		(KeyEvent.VK_U),
	V		(KeyEvent.VK_V),
	W		(KeyEvent.VK_W),
	X		(KeyEvent.VK_X),
	Y		(KeyEvent.VK_Y),
	Z		(KeyEvent.VK_Z),
	
	ONE		(KeyEvent.VK_1),
	TWO		(KeyEvent.VK_2),
	THREE	(KeyEvent.VK_3),
	FOUR	(KeyEvent.VK_4),
	FIVE	(KeyEvent.VK_5),
	SIX		(KeyEvent.VK_6),
	SEVEN	(KeyEvent.VK_7),
	EIGHT	(KeyEvent.VK_8),
	NINE	(KeyEvent.VK_9),
	ZERO	(KeyEvent.VK_0),
	
	F1		(KeyEvent.VK_F1),
	F2		(KeyEvent.VK_F2),
	F3		(KeyEvent.VK_F3),
	F4		(KeyEvent.VK_F4),
	F5		(KeyEvent.VK_F5),
	F6		(KeyEvent.VK_F6),
	F7		(KeyEvent.VK_F7),
	F8		(KeyEvent.VK_F8),
	F9		(KeyEvent.VK_F9),
	F10		(KeyEvent.VK_F10),
	F11		(KeyEvent.VK_F11),
	F12		(KeyEvent.VK_F12),
	
	ESCAPE	(KeyEvent.VK_ESCAPE),
	SPACE	(KeyEvent.VK_SPACE),
	SHIFT	(KeyEvent.VK_SHIFT),
	CONTROL	(KeyEvent.VK_CONTROL),
	ALT		(KeyEvent.VK_ALT),
	TAB		(KeyEvent.VK_TAB),
	ENTER	(KeyEvent.VK_ENTER),
	
	HOME	(KeyEvent.VK_HOME),
	PG_UP	(KeyEvent.VK_PAGE_UP),
	PG_DOWN (KeyEvent.VK_PAGE_DOWN),
	END		(KeyEvent.VK_END),
	
	BACK_SLASH	(KeyEvent.VK_BACK_SLASH),
	SLASH	(KeyEvent.VK_SLASH),
	RIGHT_BRACKET	(KeyEvent.VK_BRACERIGHT),
	LEFT_BRACKET	(KeyEvent.VK_BRACELEFT),
	COLON	(KeyEvent.VK_COLON),
	SEMI_COLON	(KeyEvent.VK_SEMICOLON),
	PERIOD	(KeyEvent.VK_PERIOD),
	COMMA	(KeyEvent.VK_COMMA),
	
	EXCLAIMATION_MARK	(KeyEvent.VK_EXCLAMATION_MARK),
	AT			(KeyEvent.VK_AT),
	NUMBER_SIGN	(KeyEvent.VK_NUMBER_SIGN),
	DOLLAR		(KeyEvent.VK_DOLLAR),
	AMPERSAND	(KeyEvent.VK_AMPERSAND),
	ASTERISK	(KeyEvent.VK_ASTERISK),
	RIGHT_PARENTHESIS	(KeyEvent.VK_RIGHT_PARENTHESIS),
	LEFT_PARENTHESIS	(KeyEvent.VK_LEFT_PARENTHESIS),
	MINUS		(KeyEvent.VK_MINUS),
	UNDERSCORE	(KeyEvent.VK_UNDERSCORE),
	PLUS		(KeyEvent.VK_PLUS),
	EQUALS		(KeyEvent.VK_EQUALS),
	
	TILDE		(192),//Tilde is 192
	
	LEFT_ARROW	(KeyEvent.VK_LEFT),
	RIGHT_ARROW (KeyEvent.VK_RIGHT),
	UP_ARROW	(KeyEvent.VK_UP),
	DOWN_ARROW	(KeyEvent.VK_DOWN),
	
	WINDOWS		(KeyEvent.VK_WINDOWS),
	
	BACKSPACE	(KeyEvent.VK_BACK_SPACE),
	
	UNDEFINED (KeyEvent.VK_UNDEFINED);

	//we keep a map because maps are extremely fast
	private static Map<Integer, Keys> keyMap;
	
	static
	{
		keyMap = new HashMap<Integer, Keys>();
		Keys[] values = Keys.values();
		for(Keys key : values)
		{
			keyMap.put(key.getKey(), key);
		}
	}
	
	/**
	 * Returns a Key given a virtual key code, if it can
	 * @param vk The virtual key code. Most likely a constant from the {@link java.awt.event.KeyEvent KeyEvent} class
	 * @return A Key enum constant if it can
	 */
	public static Keys getKey(int vk)
	{
		if(keyMap.containsKey(vk))
		{
			return keyMap.get(vk);
		}
		else
		{
			return Keys.UNDEFINED;
		}
	}
	
	//constructor
	private Keys(int vk)
	{
		this.vk = vk;
	}
	
	private final int vk;
	
	/**
	 * Gets the key code
	 * @return The virtual key code constant
	 */
	public int getKey()
	{
		return vk;
	}
}
