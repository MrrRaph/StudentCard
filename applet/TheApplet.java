package applet;

import javacard.framework.*;

public class TheApplet extends Applet {
	private static final byte UPDATECARDKEY						= (byte)0x14;
	private static final byte UNCIPHERFILEBYCARD				= (byte)0x13;
	private static final byte CIPHERFILEBYCARD					= (byte)0x12;
	private static final byte CIPHERANDUNCIPHERNAMEBYCARD		= (byte)0x11;
	private static final byte READFILEFROMCARD					= (byte)0x10;
	private static final byte WRITEFILETOCARD					= (byte)0x09;
	private static final byte UPDATEWRITEPIN					= (byte)0x08;
	private static final byte UPDATEREADPIN						= (byte)0x07;
	private static final byte DISPLAYPINSECURITY				= (byte)0x06;
	private static final byte DESACTIVATEACTIVATEPINSECURITY	= (byte)0x05;
	private static final byte ENTERREADPIN						= (byte)0x04;
	private static final byte ENTERWRITEPIN						= (byte)0x03;
	private static final byte READNAMEFROMCARD					= (byte)0x02;
	private static final byte WRITENAMETOCARD					= (byte)0x01;

	private final static short NVRSIZE      = (short)1024;
	private static byte[] NVR               = new byte[NVRSIZE];

	//private OwnerPIN pin;



	protected TheApplet() {
		/*byte[] pincode = {(byte)0x30,(byte)0x30,(byte)0x30,(byte)0x30}; // PIN code "0000"
		pin = new OwnerPIN(3, 8);
		pin.update*/
		this.register();
	}


	public static void install(byte[] bArray, short bOffset, byte bLength) throws ISOException {
		new TheApplet();
	} 


	public boolean select() {
		return true;
		//return !(pin.getTriesRemaining() == 0);
	} 


	public void deselect() {
		//pin.reset();
	}


	public void process(APDU apdu) throws ISOException {
		if(selectingApplet() == true)
			return;

		byte[] buffer = apdu.getBuffer();

		switch(buffer[1]) {
			case UPDATECARDKEY: 
				updateCardKey(apdu); 
				break;
			case UNCIPHERFILEBYCARD: 
				uncipherFileByCard(apdu); 
				break;
			case CIPHERFILEBYCARD: 
				cipherFileByCard(apdu); 
				break;
			case CIPHERANDUNCIPHERNAMEBYCARD: 
				cipherAndUncipherNameByCard(apdu); 
				break;
			case READFILEFROMCARD: 
				readFileFromCard(apdu); 
				break;
			case WRITEFILETOCARD: 
				writeFileToCard(apdu); 
				break;
			case UPDATEWRITEPIN: 
				updateWritePIN(apdu); 
				break;
			case UPDATEREADPIN: 
				updateReadPIN(apdu); 
				break;
			case DISPLAYPINSECURITY: 
				displayPINSecurity(apdu); 
				break;
			case DESACTIVATEACTIVATEPINSECURITY: 
				desactivateActivatePINSecurity(apdu); 
				break;
			case ENTERREADPIN: 
				enterReadPIN(apdu); 
				break;
			case ENTERWRITEPIN: 
				enterWritePIN(apdu); 
				break;
			case READNAMEFROMCARD: 
				readNameFromCard(apdu); 
				break;
			case WRITENAMETOCARD: 
				writeNameToCard(apdu); 
				break;
			default: ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
		}
	}


	void updateCardKey(APDU apdu) {
	}


	void uncipherFileByCard(APDU apdu) {
	}


	void cipherFileByCard(APDU apdu) {
	}


	void cipherAndUncipherNameByCard(APDU apdu) {
	}


	void readFileFromCard(APDU apdu) {
	}


	void writeFileToCard(APDU apdu) {
	}


	void updateWritePIN(APDU apdu) {
	}


	void updateReadPIN(APDU apdu) {
	}


	void displayPINSecurity(APDU apdu) {
	}


	void desactivateActivatePINSecurity(APDU apdu) {
	}


	void enterReadPIN(APDU apdu) {
	}


	void enterWritePIN(APDU apdu) {
	}


	void readNameFromCard(APDU apdu) {
		byte[] buffer = apdu.getBuffer();

		Util.arrayCopy(NVR, (short) 1, buffer, (short) 0, NVR[0]);
		apdu.setOutgoingAndSend((short) 0, NVR[0]);
	}


	void writeNameToCard(APDU apdu) {
		apdu.setIncomingAndReceive();
		byte[] buffer = apdu.getBuffer();
		Util.arrayCopy(buffer, (short) 4, NVR, (short) 0, (short) (buffer[4] + 1));
	}
}