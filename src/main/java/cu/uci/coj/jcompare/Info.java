/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.jcompare;

/**
 *
 * @author amado
 */
public class Info<T> {
private T info;
private Resultado result;

    public Info(T info, Resultado result) {
        this.info = info;
        this.result = result;
    }

    public T getInfo() {
        return info;
    }

    public Resultado getResult() {
        return result;
    }

}
