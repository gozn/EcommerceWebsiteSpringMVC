package ElectronicComponentsShopMVC.Service.Algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class blockXSS {
	public static boolean isContainSpecialWord(String str) {
        Pattern VALID_INPUT_REGEX = Pattern.compile("[$&+,:;=\\\\\\\\?@#|/'<>.^*()%!-]",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_INPUT_REGEX.matcher(str);
        return matcher.find();
    }
}
