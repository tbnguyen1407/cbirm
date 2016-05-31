package storage;

import helpers.FileSystemOperator;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class ImageIndexHolder
{
    private static ImageIndexHolder instance;

    // Hashtables of image features (RAM)
    private static HashMap<String, String> tableColorHistogram;
    private static HashMap<String, String> tableColorCorrelogram;
    private static HashMap<String, String> tableColorCoherenceVector;
    private static HashMap<String, String> tableDateTime;
    private static HashMap<String, String> tableEdgeDirection;
    private static HashMap<String, String> tableLocation;
    private static HashMap<String, String> tableSemanticTag;

    private static HashMap<String, ImageIcon> tableImageIcon;
    private static HashMap<String, String> tableSimilarTags;

    // Storage files of image features (File)
    private static final File fileCorlorHistogram = new File("D:\\index-colorhistogram.dat");
    private static final File fileColorCorrelogram = new File("D:\\index-colorcorrelogram.dat");
    private static final File fileColorCoherenceVector = new File("D:\\index-colorcoherencevector.dat");
    private static final File fileDateTime = new File("D:\\index-datetime.dat");
    private static final File fileEdgeDirection = new File("D:\\index-edgedirection.dat");
    private static final File fileLocation = new File("D:\\index-location.dat");
    private static final File fileSemanticTag = new File("D:\\index-semantictag.dat");
    private static final File fileSimilarTags = new File("D:\\index-similartags.dat");

    public static enum FeatureType
    {
        ColorHistogram,
        ColorCoherenceVector,
        ColorCorrelogram,
        DateTime,
        EdgeDirection,
        Location,
        SemanticTag,
        SimilarTags,
        ImageIcon
    }

    private ImageIndexHolder()
    {
        tableColorHistogram = new HashMap<String, String>();
        tableColorCoherenceVector = new HashMap<String, String>();
        tableColorCorrelogram = new HashMap<String, String>();
        tableDateTime = new HashMap<String, String>();
        tableEdgeDirection = new HashMap<String, String>();
        tableLocation = new HashMap<String, String>();
        tableSemanticTag = new HashMap<String, String>();
    //    tableImageIcon = new HashMap<String, ImageIcon>();
        tableSimilarTags = new HashMap<String, String>();
    }

    public static ImageIndexHolder getInstance()
    {
        if (instance == null)
        {
            instance = new ImageIndexHolder();
        }
        return instance;
    }

    /// <summary>
    /// Initialize index storage by loading cached indice from index-storage files
    /// </summary>
    public void initializeStorage()
    {
        try
        {
            flushTables();

            loadFile2Table(fileCorlorHistogram, tableColorHistogram);
            loadFile2Table(fileColorCorrelogram, tableColorCorrelogram);
            loadFile2Table(fileColorCoherenceVector, tableColorCoherenceVector);
            loadFile2Table(fileDateTime, tableDateTime);
            loadFile2Table(fileEdgeDirection, tableEdgeDirection);
            loadFile2Table(fileLocation, tableLocation);
            loadFile2Table(fileSemanticTag, tableSemanticTag);
            if (tableSimilarTags.size() == 0)
                initilizeTableSimilarTags();

//            for (String imagePath : tableColorHistogram.keySet())
  //              tableImageIcon.put(imagePath, ImageOperator.createImageIcon(imagePath));

        }
        catch (IOException ioe)
        {
        }
    }

    /// <summary>
    /// Finalize index storage by writing indice to index-storage files
    /// </summary>
    public void finalizeStorage()
    {
        try
        {
            saveTable2File(fileCorlorHistogram, tableColorHistogram);
            saveTable2File(fileColorCorrelogram, tableColorCorrelogram);
            saveTable2File(fileColorCoherenceVector, tableColorCoherenceVector);
            saveTable2File(fileDateTime, tableDateTime);
            saveTable2File(fileEdgeDirection, tableEdgeDirection);
            saveTable2File(fileLocation, tableLocation);
            saveTable2File(fileSemanticTag, tableSemanticTag);
            saveTable2File(fileSimilarTags, tableSimilarTags);
        }
        catch (IOException ioe)
        {
        }
    }

    private void flushTables()
    {
        tableColorHistogram.clear();
        tableColorCorrelogram.clear();
        tableColorCoherenceVector.clear();
        tableDateTime.clear();
        tableEdgeDirection.clear();
        tableLocation.clear();
        tableSemanticTag.clear();
        tableSimilarTags.clear();
    }

    public void saveImageIndex(FeatureType featureType, String image, String index)
    {
        saveToTable(featureType, image, index);
    }

    public String loadImageIndex(FeatureType featureType, String image)
    {
        return loadFromTable(featureType, image);
    }

    private void loadFile2Table(File file, HashMap<String, String> table) throws IOException
    {
        FileSystemOperator.checkAndCreateFile(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
        String line;

        while ((line = br.readLine()) != null)
            table.put(line.split(",")[0], line.split(",")[1]);

        br.close();
    }

    private void saveTable2File(File file, HashMap<String, String> table) throws IOException
    {
        file.delete();
        FileSystemOperator.checkAndCreateFile(file);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        Set<String> keyList = table.keySet();

        for (String key : keyList)
        {
            bw.write(key + "," + table.get(key) + "\n");
        }
        bw.close();
    }

    private void saveToTable(FeatureType featureType, String image, String index)
    {
        String normalizedKey = image.trim().toLowerCase();
        switch (featureType)
        {
            case ColorHistogram:
                tableColorHistogram.put(normalizedKey, index);
                break;
            case ColorCoherenceVector:
                tableColorCoherenceVector.put(normalizedKey, index);
                break;
            case ColorCorrelogram:
                tableColorCorrelogram.put(normalizedKey, index);
                break;
            case DateTime:
                tableDateTime.put(normalizedKey, index);
                break;
            case EdgeDirection:
                tableEdgeDirection.put(normalizedKey, index);
                break;
            case Location:
                tableLocation.put(normalizedKey, index);
                break;
            case SemanticTag:
                tableSemanticTag.put(normalizedKey, index);
                break;
      //      case ImageIcon:
        //        tableImageIcon.put(normalizedKey, ImageOperator.createImageIcon(normalizedKey));
            default:
                break;
        }
    }

    private String loadFromTable(FeatureType featureType, String image)
    {
        String index;
        String normalizedKey = image.trim().toLowerCase();
        switch (featureType)
        {
            case ColorHistogram:
                index = tableColorHistogram.get(normalizedKey);
                break;
            case ColorCoherenceVector:
                index = tableColorCoherenceVector.get(normalizedKey);
                break;
            case ColorCorrelogram:
                index = tableColorCorrelogram.get(normalizedKey);
                break;
            case DateTime:
                index = tableDateTime.get(normalizedKey);
                break;
            case EdgeDirection:
                index = tableEdgeDirection.get(normalizedKey);
                break;
            case Location:
                index = tableLocation.get(normalizedKey);
                break;
            case SemanticTag:
                index = tableSemanticTag.get(normalizedKey);
                break;
            case SimilarTags:
                index = tableSimilarTags.get(normalizedKey);
                break;
            default:
                index = null;
                break;
        }
        return index;
    }

    private void initilizeTableSimilarTags()
    {
        tableSimilarTags.put("people", "people actress andy lau army avril lavigne baby blink-182 blond hair boy britney spears bruce lee celebrity cheerleaders cheerleading stunt teams chef child children class classroom cyndi dance eat eating emperor faith hill fisherman girl guards han chae young japanese italian kids korean loewy maozedong marley military model musicians officer patrick pcd police prince smile student teacher tourist undead viking visitor witch woman wong tai sin woods");
        tableSimilarTags.put("animal", "animal animals pet atomic kitten bear bird birds carp cat chicken coral cormorant dog dragon duck fish goldfish lamb lick liger lion monkey parrot pet pigeon puppy rhino rhinoceros sharks shepherd_dog spitz squirrel starfish stray swan tiger wolf zoo");
        tableSimilarTags.put("food", "food bacon bak kut teh beef beer bread burger cake cheese cheesy crab wings chocolate coconut cookie cooking corn crab cupcake cupcakes  curry dessert dish drink eat eating egg eggs escargot fish fishball fries fruit grape mooncake mushroom otah papaya pasta pastry pork potatoes prawn rice roasted salad sashimi sauce scallop snacks soup spaghetti steamboat strawberry sushi tomyam vitasoy wings");
        tableSimilarTags.put("vehicle", "vehicle vehicles airplane bikes boat boats bicycle car cars crane cart machine technology truck train");
        tableSimilarTags.put("landscape", "landscape airport alps amsterdam amusement park avenue of stars batam bay beach beijing berlin bratislava bridge building buildings bukit bintang capital castle catalonia causeway bay cbd changi chateau china chinatown city cityscape cliff convention center disneyland eiffel tower england esplanade farm forbidden city fountain france fuji garden gardens germany great wall greece hdb harbour henderson hills highway holland hongkong hualian island iluma italy japan jeju jetty kelong kl klcc korea kyoto kuala lumpur lake landmark lantau island lighthouse london lotte world macau malaysia marina memorial hall mount fuji mountain museum newton netherlands oslo palace paris myanmar nanshan norway old trafford pisa plaza pond pool port portugal pulau ubin reservoir resort river road rome sanqing scenery school sea seletar sentosa seoul shanghai shopping centre siloso singapore skyline slovakia spain stadium station stockholm summer palace suzhou sweden taipei taiwan tazmania temple tiananmen timbre time square tokyo tower town travel town united kingdom university vatican city venice versailles victoria harbour victoria peak vivocity waterfall wharf windmill");
        tableSimilarTags.put("party", "party band friends birthday new year christmas festive fun holiday naked parade");
        tableSimilarTags.put("sport", "sport sports archery target badminton barcelona basketball converse cycling football ball boots players kendo manchester united olympic porto ski clothing skiboards snowboarding soccer ball boots players complex swimming pool team teammate water skiing");
        tableSimilarTags.put("recreation", "recreation amusement park anime arts ball balloons band boardgame bowling camping cartoon casino album charizard cryptlord dance drawing driving electric guitar fishing frisbee gambling game games gaming go cart handcraft happy hobbies hobby jogging karaoke kayaking kite lesuire boating lord of the rings mahjong table tiles movie music photography poker prawning praying roller coaster shopping singing sleep song stargazing stars theme park warcraft");
        tableSimilarTags.put("plant", "plant plants flower rose orchid lotus leaf trees trunk waterlily");

    }
}
