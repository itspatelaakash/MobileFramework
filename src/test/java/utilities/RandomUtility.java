package utilities;

public class RandomUtility {
	public static enum Mode 
	{

        NUMERIC("1234567890"),
        SYMBOLIC("'-=[];,./~!@#$%^&*()_+{}:\" <>?"),
        SELECTEDSYMBOLIC("@#$%&-+';!,_{}[]^:"),
        SPECIAL(SYMBOLIC.getStringValue() + "\\"),

        /* LATIN */
        ALPHA("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        ALPHANUMERIC(ALPHA.getStringValue() + NUMERIC.getStringValue()),
        ALPHANUMERICSPECIALCHAR(ALPHANUMERIC.getStringValue() + SPECIAL.getStringValue()),
        ALPHANUMERICSELECTEDSPECIALCHAR(ALPHANUMERIC.getStringValue() + SELECTEDSYMBOLIC.getStringValue()),
        ALPHACAPITAL("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        ALPHANUMERICSYMBOLIC(ALPHANUMERIC.getStringValue() + SYMBOLIC.getStringValue());


        private final String stringValue;

        Mode(String s) {
            stringValue = s;
        }

        public String getStringValue() {
            return stringValue;
        }
    }

    /**
     * This method generates random character depending on modes(Alphabetic,Alphanumeric,numeric) and length
     *
     * @param length the length of string
     * @param mode   different types of mode
     * @return String of characters
     * @throws Exception
     */
    public static String generateRandomString(int length, Mode mode) throws Exception {

        StringBuffer buffer = new StringBuffer();
        String characters = mode.getStringValue();
        int charactersLength = characters.length();

        for (int i = 0; i < length; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
        }
        return buffer.toString();
    }

}
