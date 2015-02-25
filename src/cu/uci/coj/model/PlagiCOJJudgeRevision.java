/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import cu.uci.coj.model.User;
import java.util.Date;
import cu.uci.coj.utils.enums.PlagiCOJEvaluation;

/**
 *
 * @author Leandro
 */
public class PlagiCOJJudgeRevision extends PlagiCOJRevision {

    private PlagiCOJEvaluation evaluation;

    public PlagiCOJJudgeRevision(String userId, PlagiCOJEvaluation evaluation, String comment) {
        super(userId, comment);
        this.evaluation = evaluation;
    }

    
    
    public PlagiCOJJudgeRevision(String userId, Date date,PlagiCOJEvaluation evaluation, String comment) {
        super(userId,date, comment);
        this.evaluation = evaluation;
    }
     public PlagiCOJJudgeRevision(int idRevision,String userId, Date date,PlagiCOJEvaluation evaluation, String comment) {
        super(idRevision,userId,date, comment);
        this.evaluation = evaluation;
    }

    /**
     * @return the evaluation
     */
    public PlagiCOJEvaluation getEvaluation() {
        return evaluation;
    }
    /**
     * @param evaluation the evaluation to set
     */
    public void setEvaluation(PlagiCOJEvaluation evaluation) {
        this.evaluation = evaluation;
    }
}
