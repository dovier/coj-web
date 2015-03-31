package cu.uci.coj.board.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import cu.uci.coj.model.WbContest;

/**
*
* @author Eddy Roberto Morales Perez
*/

public class WbContestTableDecorator extends TableDecorator {

	private String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	private SimpleDateFormat format;  
	
    /**
     * Constructor que asigna el formato, según documentación hace más eficiente la clase
     */
    public WbContestTableDecorator() {
            super();
            format = new SimpleDateFormat(DATE_FORMAT);
    }

    /**
     * Método para regresar nulos
     *
     * @return <code>null</code>
     */
    public String getNullValue() {
            return null;
    }    

    public String getStartDate() {
    	return format.format(((WbContest)  this.getCurrentRowObject()).getStartDate());
    }
    
    public String getEndDate() {
    	return format.format(((WbContest)  this.getCurrentRowObject()).getEndDate());
    }
}
