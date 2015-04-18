/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.adapters;

import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.dto.SubmissionDTO;
import java.util.HashMap;

/**
 *
 * @author lan
 */
public class SubmissionJudgeToSubmissionDTOAdapter extends SubmissionDTO {

    private final SubmissionJudge submissionJudge;

    public SubmissionJudgeToSubmissionDTOAdapter(SubmissionJudge submissionJudge) {
        this.submissionJudge = submissionJudge;

        this.id = submissionJudge.getSid();

        this.problemId = submissionJudge.getPid();

        this.memoryLimit = submissionJudge.getMemoryLimit();

        this.timeLimit = (long) submissionJudge.getTimeLimit();
        
        this.caseTimeLimit = (long) submissionJudge.getCaseTimeLimit();
        
        this.evaluationType = submissionJudge.isSpecialJudge() ? "SpecialJudge" : "PrototypeOutput";

        this.language = submissionJudge.getLang();

        this.sourceCode = submissionJudge.getSource();

        this.allResults = false;//Para modo IOI cuando procesar y devolver todas los testcase results por separado

        this.trusted = false;//This is for security reasons, to espesify that 

        this.metadata = new HashMap<>();//Metadata can be used to sent extra values to UEngine that it should return as equal for some reason.
        
        this.metadata.put("Cid", submissionJudge.getCid());
        this.metadata.put("Lang", submissionJudge.getLang());
        this.metadata.put("Uid", submissionJudge.getUid());
    }
}
