package entity.language;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatinSyllableCalculator implements SyllableCalculator
{
    @Override
    public int calculate(String word)
    {
        word = word.toLowerCase();

        Pattern pattern = Pattern.compile("[aàâäáåeéèêëiîïíoóöõôuûüùúũyœ]+");
        Matcher matcher = pattern.matcher(word);

        int syllableCount = 0;

        while (matcher.find())
        {
            syllableCount++;
        }

        if (syllableCount > 1 && (word.endsWith("e") || word.endsWith("es") || word.endsWith("ent")))
        {
            syllableCount--;
        }

        return syllableCount;
    }
}
