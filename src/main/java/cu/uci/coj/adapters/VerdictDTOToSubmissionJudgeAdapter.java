/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.adapters;

import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.model.Verdicts;
import cu.uci.coj.model.dto.VerdictDTO;

/**
 *
 * @author lan
 */
public class VerdictDTOToSubmissionJudgeAdapter extends SubmissionJudge {

    private final VerdictDTO verdict;

    public VerdictDTOToSubmissionJudgeAdapter(VerdictDTO verdict) {
        this.verdict = verdict;

        setSid(verdict.getId());

        setPid(verdict.getProblemId());

        setVerdict(verdict.getVerdict());

        setTimeUsed(verdict.getTimeUsed() == null ? 0 : verdict.getTimeUsed().intValue());

        setCpuTimeUsed(verdict.getCpuTimeUsed() == null ? 0 : verdict.getCpuTimeUsed().intValue());

        setMemoryUsed(verdict.getMemoryUsed() == null ? 0 : verdict.getMemoryUsed());

        setErrMsg(verdict.getMessage());

        setAcTestCases(verdict.getAcceptedDatasets());
        setTotalTestCases(verdict.getProcessedDatasets());
        setFirstWaCase(verdict.getFirstFailedDataset() == null ? 0 : verdict.getFirstFailedDataset());
        setMinTimeUsed(verdict.getMinTimeUsed() == null ? 0 : verdict.getMinTimeUsed().intValue());
        setMaxTimeUsed(verdict.getMaxTimeUsed() == null ? 0 : verdict.getMaxTimeUsed().intValue());
        setAvgTimeUsed(verdict.getAverageTimeUsed() == null ? 0 : verdict.getAverageTimeUsed().intValue());
        setDate(verdict.getEvaluationDate());

        setDatasetVerdicts(verdict.getDatasetVerdictDTO());

        //Extras
        setCid((int) verdict.getMetadata().get("Cid"));
        setLang((String) (verdict.getMetadata().get("Lang")));
        setAccepted(verdict.getVerdict() == Verdicts.AC);
        setStatus(verdict.getVerdict().associatedMessage());
        setUid((int) verdict.getMetadata().get("Uid"));
    }

}
