//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.28 at 10:32:49 PM EDT 
//


package aug.manas.mtconnect.mtdata.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         An abstract interface event
 *       
 * 
 * <p>Java class for InterfaceEventType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InterfaceEventType">
 *   &lt;simpleContent>
 *     &lt;restriction base="&lt;urn:mtconnect.org:MTConnectStreams:1.3>EventType">
 *     &lt;/restriction>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterfaceEventType")
@XmlSeeAlso({
    CloseDoorType.class,
    PartChangeType.class,
    MaterialFeedType.class,
    CloseChuckType.class,
    MaterialChangeType.class,
    MaterialLoadType.class,
    OpenDoorType.class,
    MaterialRetractType.class,
    MaterialUnloadType.class,
    OpenChuckType.class
})
public abstract class InterfaceEventType
    extends EventType
{


}
