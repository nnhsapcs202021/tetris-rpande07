import java.util.ArrayList;

/**
 * This class uses the factory design pattern to provide a list of
 *      objects that implement the Brain interface
 *
 * @author rpande
 * @version 30 May 2021
 */
public class BrainFactory
{
    /**
     * Create a list of references to objects whose classes implement the Brain
     *      interface
     *
     * @return a list of referenes to objects whose classes implement the Brain
     *      interface
     */
    public static ArrayList<Brain> createBrains()
    {
        /*
         * TODO: add instances of SimpleBrain and SmallBrain to the list.
         * 
         * If you do the BigBrain extension, also add an instance of BigBrain to
         *      the list.
         */
        
        ArrayList<Brain> list = new ArrayList<Brain>();
        
        list.add(new SimpleBrain());
        list.add(new SmallBrain());
        
        return list;
    }
}
