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
/*
 * @author Brice MORIN, SINTEF IKT
 */
package org.thingml.utils.comm

import gnu.io.{CommPort, CommPortIdentifier, PortInUseException, SerialPort, SerialPortEvent, SerialPortEventListener}

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

import org.thingml.utils.log.Logger

trait SerialThingML {
  
  protected var out : OutputStream = _
  
  def setOutputStream(out : OutputStream) {this.out = out}
    
  def sendData(bytes : Array[Byte]) {
    //Logger.debug("sendData(" + bytes.mkString("[", ", ", "]") + ")")
    try {
      bytes.foreach{b => 
        //Logger.debug("out.write(" + b.toInt + ")")
        out.write(b.toInt)
      }
    } catch {
      case e : IOException =>
        e.printStackTrace()
    }
  }
  
  def receive(byte : Array[Byte]) {
    //This will be refined in the Serial Thing defined in ThingML
  }
}