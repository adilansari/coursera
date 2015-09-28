
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class FileReaderSpout implements IRichSpout {
  private SpoutOutputCollector _collector;
  private TopologyContext context;
  FileReader fileReader;
  BufferedReader bufferedReader;


  @Override
  public void open(Map conf, TopologyContext context,
   SpoutOutputCollector collector) {

    try {
      this.context = context;
      this.fileReader = new FileReader(conf.get("inputFile").toString());
      this.bufferedReader = new BufferedReader(this.fileReader);
    } catch (IOException e) {
      e.printStackTrace();
    }

    _collector = collector;
  }

  @Override
  public void nextTuple() {

     /*
    ----------------------TODO-----------------------
    Task:
    1. read the next line and emit a tuple for it
    2. don't forget to sleep when the file is entirely read to prevent a busy-loop

    ------------------------------------------------- */
    String line;
    try {
      if((line = this.bufferedReader.readLine()) != null) {
        _collector.emit(new Values(line));
      } else {
        Utils.sleep(100);
      }
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {

    declarer.declare(new Fields("word"));

  }

  @Override
  public void close() {
   /*
    ----------------------TODO-----------------------
    Task: close the file


    ------------------------------------------------- */
    this.bufferedReader.close();
    this.fileReader.close();
  }


  @Override
  public void activate() {
  }

  @Override
  public void deactivate() {
  }

  @Override
  public void ack(Object msgId) {
  }

  @Override
  public void fail(Object msgId) {
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
