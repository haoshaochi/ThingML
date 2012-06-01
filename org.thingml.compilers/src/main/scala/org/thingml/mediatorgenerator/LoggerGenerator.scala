/**
 * Copyright (C) 2011 SINTEF <franck.fleurey@sintef.no>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * This code generator targets to log the process of message exchanges
 * @author: Runze HAO <haoshaochi@gmail.com>
 */

package org.thingml.mediatorgenerator
import org.thingml.scalagenerator.ScalaGenerator._
import org.thingml.javagenerator.gui.SwingGenerator._
import org.thingml.model.scalaimpl.ThingMLScalaImpl._
import scala.collection.JavaConversions._
import scala.actors._
import scala.actors.Actor._
import java.util._
import java.io._
import org.eclipse.emf.common.util.EList
import org.sintef.thingml._

import org.thingml.utils.log.Logger

object Util {
  val lBuilder = new StringBuilder()
  var thing : Thing = _
  var pack : String = _
  var logger_name : String = _
  var config_name :String =_
  var sourcefile:String=_
  val keywords = scala.List("implicit","match","requires","type","var","abstract","do","finally","import","object","throw","val","case","else","for","lazy","override","return","trait","catch","extends","forSome","match","package","sealed","try","while","class","false","if","new","private","super","true","with","def","final","implicit","null","protected","this","yield","_",":","=","=>","<-","<:","<%",">:","#","@")
  def protectScalaKeyword(value : String) : String = {
    if(keywords.exists(p => p.equals(value))){
      return "`"+value+"`"
    } 
    else {
      return value
    }
  }
  
  def firstToUpper(value : String) : String = {
    return value.capitalize
  }
  
  def init {
    lBuilder.clear
    thing = null
    pack = null
    logger_name = null
    config_name = null
    sourcefile= null
  }
}

object LoggerGenerator {
  def compileAndRun(cfg : Configuration, model: ThingMLModel, outputDir:String,sourcefile:String) {
    new File(outputDir+"/").deleteOnExit
    val outputDirFile = new File(outputDir)
    outputDirFile.mkdirs
    
    Util.init
    Util.sourcefile = sourcefile
    val code = compile(cfg, model)
    
    //var w = new PrintWriter(new FileWriter(new File(Util.logger_name+".txt")));
    var w = new PrintWriter(new FileWriter(new File(outputDir  + "/" + Util.logger_name+".thingml")));
    System.out.println("code generated at "+outputDir  + "\\" + Util.logger_name+".thingml");
    w.println(code);
    w.close();
    javax.swing.JOptionPane.showMessageDialog(null, "Mediator code generated");
    
  }
  
  def compile(cfg: Configuration, model: ThingMLModel) : String = {
    generateHeader(cfg)
    generateLogger(cfg)
    generateThingLogger()
    generateThingControl()
    //generateControlMessage()
    generateConfig(cfg)
    
    (Util.lBuilder.toString)
  }
  def generateHeader(cfg:Configuration,builder:StringBuilder = Util.lBuilder){
    builder append "/*\n"
    builder append "*This is a logger for Configratrion("+cfg.getName+") generated automatically"
    builder append "\n*/\n"
    builder append "import \""+Util.sourcefile+"\"\n"
  }
  def generateLogger(cfg:Configuration,builder:StringBuilder = Util.lBuilder){
    var thing_logger_name :String = "Logger_"+cfg.getName    //thing mediator name
    Util.logger_name = "logger_"+cfg.getName      // file name for mediator model
    
    //to generate thing * includs *,*
    builder append "thing "+thing_logger_name+" includes "
    builder append getIncludes(cfg)+"\n"
    builder append "{\n"
    generatePorts(cfg)
    generateControlPort()
    generateStateMachine(cfg)
    builder append "}\n\n"
  }
  def generateStateMachine(cfg:Configuration,builder:StringBuilder = Util.lBuilder){
    builder append "statechart Logger init Ready {\n"
    generateReadyState(cfg)
    generateLoggingState(cfg)
    builder append "}\n"
  }
  def generateReadyState(cfg:Configuration,builder:StringBuilder = Util.lBuilder){
    builder append "state Ready{\n"
    builder append "on entry do\n"
    builder append "clearlog()\n"
    builder append "print \"Ready, Waiting for start trigger\"\n"
    builder append "end\n"
    builder append "transition->Logging\n"
    builder append "event e:PrvPort_Control?start\n"
    builder append "}\n"
  }
  def generateLoggingState(cfg:Configuration,builder:StringBuilder = Util.lBuilder){
    builder append "state Logging{\n"
    builder append "on entry do\n" 
    builder append "print \"Logger start!\"\n"
    builder append "log(\"@startuml\")\n"
    builder append "end\n"
    builder append "on exit do\n"
    builder append "log(\"@enduml\")\n"
    builder append "printlog()\n"
    builder append "end\n\n"
    generateTransitions(cfg)
    builder append "}\n"
  }
  def getParameters(m:Message):String ={
    m.getParameters.collect{case p=>
        "e."+p.getName
    }.mkString(",")
  }
  def generateTransitions(cfg:Configuration,builder:StringBuilder = Util.lBuilder){
    builder append "transition-> Ready\n"
    builder append "event e: PrvPort_Control?stop\n\n"
    //here since no mismatches, just got all messages and foward is ok.
    // but for the situation with mismatehes, how to do it
    cfg.allConnectors.foreach{case c=>
        var thing_logger_name :String = "Logger_"+cfg.getName    //thing mediator name
        var cli_name = c.getCli.getInstance.getName
        var srv_name = c.getSrv.getInstance.getName
        c.getRequired.getSends.foreach{case m=>
            if(c.getProvided.getReceives.contains(m)){
              var inPort = "PrvPort_"+cli_name+"_"+c.getRequired.getName
              var outPort = "ReqPort_"+srv_name+"_"+c.getProvided.getName
              builder append "internal\n"
              builder append "event e: "+inPort+"?"+m.getName+"\n"
              builder append "action do\n"
              builder append "log(\""+cli_name+" -> "+thing_logger_name+" : "+m.getName+"\")\n"
              builder append outPort+"!"+m.getName+"("+getParameters(m)+")\n"
              builder append "log(\""+thing_logger_name+" -> "+srv_name+" : "+m.getName+"\")\n"
              builder append "end\n"
            }
        }
        c.getProvided.getSends.foreach{case m=>
            if(c.getRequired.getReceives.contains(m)){
              var outPort = "PrvPort_"+cli_name+"_"+c.getRequired.getName
              var inPort = "ReqPort_"+srv_name+"_"+c.getProvided.getName
              builder append "internal\n"
              builder append "event e: "+inPort+"?"+m.getName+"\n"
              builder append "action do\n"
              builder append "log(\""+srv_name+" -> "+thing_logger_name+" : "+m.getName+"\")\n"
              builder append outPort+"!"+m.getName+"("+getParameters(m)+")\n"
              builder append "log(\""+thing_logger_name+" -> "+cli_name+" : "+m.getName+"\")\n"
              builder append "end\n"
            }
        }
//        var cmList = new ArrayList()
//        var smList = new ArrayList()
//        c.getCli.getInstance.getType.getBehaviour.foreach{case sm=>
//            if(sm.getInitial!=null)
//              ana_state(sm.getInitial)
//        }
//        c.getSrv.getInstance.getType.getBehaviour.foreach{case sm=>
//            if(sm.getInitial!=null)
//              ana_state(sm.getInitial)
//        }
//        def ana_state(s:State){
//          s.getEntry
//          s.getInternal
//          s.getOutgoing
//          s.getExit
//        }
    }
  }
  /*
   * to generate all the ports, for client and server respectively of each connector
   */
  def generatePorts(cfg:Configuration,builder:StringBuilder = Util.lBuilder){
    var reqPortList = new ArrayList[RequiredPort]()
    var prvPortList = new ArrayList[ProvidedPort]()
    cfg.allConnectors.foreach{case c=>
        var cli = c.getCli.getInstance
        var srv = c.getSrv.getInstance
        
        //first provided port for client connection
        if(!reqPortList.contains(c.getRequired)){
          
          builder append "provided port PrvPort_"+cli.getName+"_"+c.getRequired.getName+" {\n"
          if(c.getRequired.getSends.size>0){
            builder append "receives "+c.getRequired.getSends.collect{case s=>
                s.getName
            }.mkString(",")
          }
          builder append "\n"
          if(c.getRequired.getReceives.size>0){
            builder append "sends "+c.getRequired.getReceives.collect{case r=>
                r.getName
            }.mkString(",")
          }
          builder append "\n}\n"
          reqPortList.add(c.getRequired)
        }
        //then required port for server connection
        if(!prvPortList.contains(c.getProvided)){
          builder append "required port ReqPort_"+srv.getName+"_"+c.getProvided.getName+" {\n"
          if(c.getProvided.getReceives.size>0){
            builder append "sends "+c.getProvided.getReceives.collect{case r=>
                r.getName
            }.mkString(",")
          }
          builder append "\n"
          if(c.getProvided.getSends.size>0){
            builder append "receives "+c.getProvided.getSends.collect{case s=>
                s.getName
            }.mkString(",")
          } 
          builder append "\n}\n"
          prvPortList.add(c.getProvided)
        }
    }
  }
  def generateControlPort(builder:StringBuilder = Util.lBuilder){
    builder append "provided port PrvPort_Control{\n"
    builder append "receives start, stop\n}\n"
  }
  def getIncludes(cfg:Configuration):String ={
    var inclist = new ArrayList[String]()
    addinc("ControlMessage")
    addinc("Logger")
    cfg.allConnectors.foreach{case c=>
        if(c.getCli.getInstance.getType.getIncludes.size>0)
          c.getCli.getInstance.getType.getIncludes.collect{case i=>
              addinc(i.getName)
          }
        if(c.getSrv.getInstance.getType.getIncludes.size>0)
          c.getSrv.getInstance.getType.getIncludes.collect{case i=>
              addinc(i.getName)
          }
    }
    def addinc(name:String){
      if(!inclist.contains(name)) inclist.add(name)
    }
    inclist.mkString(",")
  }
  def generateThingLogger(builder:StringBuilder = Util.lBuilder){
    builder append "thing Logger{\n"
    builder append "property trace_buffer :String\n"
    builder append "function log(trace:String) do\n"
    builder append "print \"==LOG: \"+trace+\" !==\"\n"
    builder append "trace_buffer = trace_buffer + trace +\"\\n\"\n"
    builder append "end\n"
    builder append "function printlog() do\n"
    builder append "print \"====TRACE====\\n\"\n"
    builder append "print trace_buffer\n"
    builder append "end\n"
    builder append "function clearlog() do\n"
    builder append "trace_buffer =\"\"\n"
    builder append "end\n}\n\n"
    
  }
  def generateThingControl(builder:StringBuilder = Util.lBuilder){
    builder append "thing Control includes ControlMessage\n"
    builder append "@mock \"true\"{\n"
    builder append "required port ControlPort{\n"
    builder append "sends start, stop\n"
    builder append "}\n}\n\n"
  }
  def generateControlMessage(builder:StringBuilder = Util.lBuilder){
    builder append "thing fragment ControlMessage{\n"
    builder append "message start(msg:String);\n"
    builder append "message stop(msg:String);\n"
    builder append "}\n"
  }
  def generateConfig(cfg:Configuration,builder:StringBuilder = Util.lBuilder){
    var thing_logger_name :String = "Logger_"+cfg.getName    //thing mediator name
    builder append "configuration Config_"+thing_logger_name+"{\n"
    
    builder append "instance control: Control\n"
    builder append "instance logger: "+thing_logger_name+"\n"
    
    cfg.allInstances.foreach{case i=>
        builder append "instance ins_"+i.getName+": "+i.getType.getName+"\n"
    }
    builder append "connector control.ControlPort => logger.PrvPort_Control\n"
    cfg.allConnectors.foreach{case c=>
        var cli_name = c.getCli.getInstance.getName
        var srv_name = c.getSrv.getInstance.getName

        //builder append "connector control.ControlPort => ins_"+cli_name+".PrvPort_Control\n"
        builder append "connector ins_"+cli_name+"."+c.getRequired.getName+"=> logger.PrvPort_"+cli_name+"_"+c.getRequired.getName+"\n"
        builder append "connector logger.ReqPort_"+srv_name+"_"+c.getProvided.getName+"=> ins_"+srv_name+"."+c.getProvided.getName+"\n"
    }
    builder append "}\n"
      
  }
}