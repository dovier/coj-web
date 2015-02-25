package cu.uci.coj.dao;

import cu.uci.coj.model.DatagenDataset;
import java.util.List;

public interface DatagenDAO extends BaseDAO {
     public List<DatagenDataset> getDatasets(Integer problemId);
    public List<DatagenDataset> getDatasets(Integer problemId,Integer qty);
    public DatagenDataset getDataset(Integer datasetId);
    
    public void saveDataset(DatagenDataset dataset);
    
    public boolean datasetsExist(Integer problemId);
}
