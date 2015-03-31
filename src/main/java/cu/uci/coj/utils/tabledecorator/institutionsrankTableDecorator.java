/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.utils.tabledecorator;

import cu.uci.coj.model.Institution;
import cu.uci.coj.model.User;
import java.text.DecimalFormat;
import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author Juanky
 */
public class institutionsrankTableDecorator extends TableDecorator{

    /**
         * DecimalFormat usado para dar formato a getRecurso().
         */
        private DecimalFormat decimal;

        /**
         * Constructor que asigna el formato, según documentación hace más eficiente la clase
         */
        public institutionsrankTableDecorator() {
                super();
                this.decimal = new DecimalFormat("#.##");
                this.decimal.setMaximumFractionDigits(2);
                this.decimal.setMinimumFractionDigits(2);
        }

        /**
         * Método para regresar nulos
         *
         * @return <code>null</code>
         */
        public String getNullValue() {
                return null;
        }

        /**
         * Damos formato al dinero como un String $ #,###,###.format.
         * Estamos tomando directamente del objeto tipo Partida, la propiedad recurso
         * @return String
         */
        public String getPoints() {
                return this.decimal.format(((Institution) this.getCurrentRowObject())
                                .getPoints());
        }       

}
