package ma.emsi.artistapplication;
import ma.emsi.artistapplication.entities.Tableau;
import ma.emsi.artistapplication.service.TableauService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	TableauService  tableauService = new TableauService();
    	for(Tableau tableau :tableauService.findAll())
    		System.out.println(tableau);
    //	tableauService.save(new Tableau(null, null, null, null, null, null));
    }
}
