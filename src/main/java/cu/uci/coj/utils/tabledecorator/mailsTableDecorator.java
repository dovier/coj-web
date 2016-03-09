package cu.uci.coj.utils.tabledecorator;

import org.displaytag.decorator.TableDecorator;
import cu.uci.coj.model.Mail;

/**
 * Created by michael on 15/02/16.
 */
public class mailsTableDecorator extends TableDecorator {

    public mailsTableDecorator() {
        super();
    }

    public String addRowClass() {
        return (String) ((Mail) getCurrentRowObject()).getCclass();
    }
}
