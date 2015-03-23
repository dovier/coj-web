package cu.uci.coj.utils;

import cu.uci.coj.config.Config;
import cu.uci.coj.model.DatagenDataset;
import cu.uci.coj.model.Rejudge;
import cu.uci.coj.model.Repoint;
import cu.uci.coj.model.RepointUser;
import cu.uci.coj.model.SubmissionJudge;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class COJEvalImpl {

    public void startCalification(SubmissionJudge submission) {
    	
   /*     try {
            Socket cx = new Socket(InetAddress.getByName(Config.getProperty("server.listener")), new Integer(Config.getProperty("server.listener.port")));
            ObjectOutputStream output = new ObjectOutputStream(cx.getOutputStream());
            output.writeObject(submission);
            output.close();
            cx.close();
        } catch (IOException ex) {
            Logger.getLogger(COJEvalImpl.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    volatile Semaphore semaphore = new Semaphore(1, true);

    public String generateOutput(DatagenDataset dataset) {
        try {
            Socket cx = new Socket(InetAddress.getByName(Config.getProperty("server.listener")), new Integer(Config.getProperty("server.listener.port")));
            ObjectOutputStream output = new ObjectOutputStream(cx.getOutputStream());
            output.writeObject(dataset);
            ObjectInputStream input = new ObjectInputStream(cx.getInputStream());
            DatagenDataset datasetOut = (DatagenDataset) input.readObject();
            dataset.setOutput(datasetOut.getOutput());
            dataset.setSuccess(datasetOut.isSuccess());
            output.close();
            cx.close();
        } catch (IOException ex) {
            Logger.getLogger(COJEvalImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(COJEvalImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataset.getOutput();
    }
}
