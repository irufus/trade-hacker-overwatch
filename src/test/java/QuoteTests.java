import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.irufus.tradingalertbot.data.raw.GoogleQuoteJSONObject;
import com.irufus.tradingalertbot.data.wrapper.GoogleQuote;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Created by irufus on 11/24/15.
 */
public class QuoteTests {
    @BeforeClass
    public static void oneTimeSetup(){System.out.println("=== Starting Symbology tests ===");}
    @Before
    public void setUp() {

    }
    @AfterClass
    public static void oneTimeTearDown(){System.out.println("=== Ending Symbology tests ===");}

    private String rawJson = "// [ { \"id\": \"22144\" ,\"t\" : \"AAPL\" ,\"e\" : \"NASDAQ\" ,\"l\" : \"118.93\" ,\"l_fix\" : \"118.93\" ,\"l_cur\" : \"118.93\" ,\"s\": \"0\" ,\"ltt\":\"1:35PM EST\" ,\"lt\" : \"Nov 24, 1:35PM EST\" ,\"lt_dts\" : \"2015-11-24T13:35:33Z\" ,\"c\" : \"+1.18\" ,\"c_fix\" : \"1.18\" ,\"cp\" : \"1.00\" ,\"cp_fix\" : \"1.00\" ,\"ccol\" : \"chg\" ,\"pcls_fix\" : \"117.75\" } ]";

    @Test
    public void testGoogleQuoteConversion(){
        String json = rawJson.replace("//", "");
        json = json.replace("[", "");
        json = json.replace("]", "");
        Gson gson = new Gson();
        JsonElement mJson = new JsonParser().parse(json);
        GoogleQuoteJSONObject quote = gson.fromJson(mJson, GoogleQuoteJSONObject.class);
        assertEquals("AAPL", quote.getT());
        System.out.println("Symbol: " + quote.getT() + " Price: " + quote.getL());
    }
    @Test
    public void testGoogleQuoteUseableObject() throws ParseException {
        GoogleQuote quote = new GoogleQuote(rawJson);
        assertEquals("AAPL", quote.getSymbol());
        System.out.println("GoogleQuote Symbol: " + quote.getSymbol() + " Price: " + quote.getCurrentPrice());
    }
}
