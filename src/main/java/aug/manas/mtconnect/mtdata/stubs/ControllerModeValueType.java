//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.28 at 10:32:49 PM EDT 
//


package aug.manas.mtconnect.mtdata.stubs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ControllerModeValueType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ControllerModeValueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AUTOMATIC"/>
 *     &lt;enumeration value="MANUAL"/>
 *     &lt;enumeration value="MANUAL_DATA_INPUT"/>
 *     &lt;enumeration value="SEMI_AUTOMATIC"/>
 *     &lt;enumeration value="EDIT"/>
 *     &lt;enumeration value="UNAVAILABLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ControllerModeValueType")
@XmlEnum
public enum ControllerModeValueType {

    AUTOMATIC,
    MANUAL,
    MANUAL_DATA_INPUT,
    SEMI_AUTOMATIC,
    EDIT,
    UNAVAILABLE;

    public String value() {
        return name();
    }

    public static ControllerModeValueType fromValue(String v) {
        return valueOf(v);
    }

}
