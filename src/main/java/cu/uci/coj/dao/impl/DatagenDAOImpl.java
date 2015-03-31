package cu.uci.coj.dao.impl;

import cu.uci.coj.dao.DatagenDAO;
import cu.uci.coj.model.DatagenDataset;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("datagenDAO")
@Transactional
public class DatagenDAOImpl extends BaseDAOImpl implements DatagenDAO {
    @Transactional(readOnly=true)
    public List<DatagenDataset> getDatasets(Integer problemId){
        return objects("load.datasets.pid",DatagenDataset.class,problemId);
    }
    
    public List<DatagenDataset> getDatasets(Integer problemId,Integer qty){
        return objects("load.some.datasets.pid",DatagenDataset.class,problemId,qty);
    }
    
    @Transactional(readOnly=true)
    public DatagenDataset getDataset(Integer datasetId){
        return object("load.datasets.did",DatagenDataset.class,datasetId);
    }
    
    public void saveDataset(DatagenDataset dataset){    
        dml("insert.dataset",dataset.getProblemId(),dataset.getInput(),dataset.getOutput());
        dml("limit.datasets.qty",Integer.valueOf(config.getProperty("max.dataset.qty")));
    }
    
    @Transactional(readOnly=true)
    public boolean datasetsExist(Integer problemId){    
        return bool("exist.dataset",problemId);
    }
}
