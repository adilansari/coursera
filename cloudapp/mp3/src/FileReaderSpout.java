
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

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
  ArrayList<String> inputTokens = new ArrayList<String>();
  Random _rand;


  @Override
  public void open(Map conf, TopologyContext context,
   SpoutOutputCollector collector) {

    this._rand = new Random();
    String line;
    try {
      this.context = context;
      this.fileReader = new FileReader(conf.get("inputFile").toString());
      BufferedReader bufferedReader = new BufferedReader(this.fileReader);
      while ((line = bufferedReader.readLine()) != null) {
        this.inputTokens.add(line);
      }
      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this._collector = collector;
  }

  @Override
  public void nextTuple() {

     /*
    ----------------------TODO-----------------------
    Task:
    1. read the next line and emit a tuple for it
    2. don't forget to sleep when the file is entirely read to prevent a busy-loop

    ------------------------------------------------- */

    // try {
    //   BufferedReader bufferedReader = new BufferedReader(this.fileReader);
    //   while ((line = bufferedReader.readLine()) != null) {
    //     this._collector.emit(new Values(line));
    //   }
    //   fileReader.close();
    // } catch (Exception e){
    //   e.printStackTrace();
    // }
    Utils.sleep(100);
    String sentence = this.inputTokens.get(this._rand.nextInt(this.inputTokens.size()));
    this._collector.emit(new Values(sentence));
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
